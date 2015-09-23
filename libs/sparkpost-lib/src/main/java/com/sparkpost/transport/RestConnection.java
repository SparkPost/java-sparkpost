package com.sparkpost.transport;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.sparkpost.Client;
import com.sparkpost.exception.SparkPostException;
import com.sparkpost.exception.SparkPostIllegalServerResponseException;
import com.sparkpost.model.Response;

/**
 * The REST connection class wraps HTTP requests to the SparkPost API.
 *
 * @author grava
 */
public class RestConnection {

	private static final Logger logger = Logger.getLogger(RestConnection.class);

	/**
	 * Default endpoint to use for connections :
	 * https://api.sparkpost.com/api/v1/
	 *
	 */
	public final static String defaultApiEndpoint = "https://api.sparkpost.com/api/v1/";

	private Client client;

	private String endpoint;

	private Response lastResponse;

	/**
	 * Retrieve the response from the last HTTP request
	 *
	 * @return the response
	 */
	public Response getLastResponse() {
		return lastResponse;
	}

	/**
	 * Supported HTTP methods
	 *
	 */
	public enum Method {

		GET, POST, PUT, DELETE
	}

	/**
	 * Create a REST connection object. The default endpoint defaultApiEndpoint
	 * will be used for connections.
	 *
	 * @param client
	 *            Client object to use (in particular for authentication info)
	 * @throws SparkPostException
	 */
	public RestConnection(Client client) throws SparkPostException {
		this(client, null /* means:set to default endpoint */);
	}

	/**
	 * Create a REST connection object. The given {@link endpoint} will be used
	 * for connections
	 *
	 * @param client
	 *            Client object to use (in particular for authentication info)
	 * @param endpoint
	 *            Endpoint to use instead of the default defaultApiEndpoint
	 * @throws SparkPostException
	 */
	public RestConnection(Client client, String endpoint) throws SparkPostException {
		this.client = client;
		if (StringUtils.isAnyEmpty(endpoint)) {
			this.endpoint = defaultApiEndpoint;
		} else {
			if (endpoint.endsWith("/")) {
				this.endpoint = endpoint;
			} else {
				this.endpoint = endpoint + '/';
			}
		}
		lastResponse = new Response();
	}

	/**
	 * Create a connection object but doesn't actually connect to the server.
	 *
	 * @param path
	 *            URL suffix to be appended to the object's endpoint
	 * @param method
	 *            HTTP method for the connection
	 * @return the connection object
	 * @throws SparkPostException
	 */
	private HttpURLConnection createConnectionObject(String path, Method method) throws SparkPostException {
		HttpURLConnection conn;
		try {
			URL url;
			url = new URL(this.endpoint + path);

			// Retrieve the URLConnection object (but doesn't actually connect):
			// (HttpUrlConnection doesn't connect to the server until we've
			// got one of its streams)
			conn = (HttpURLConnection) url.openConnection();

			if (StringUtils.isNotEmpty(client.getAuthKey())) {
				conn.setRequestProperty("Authorization", this.client.getAuthKey());
			} else if (StringUtils.isNotEmpty(client.getUsername()) && StringUtils.isNotEmpty(client.getPassword())) {
				Base64 b = new Base64();
				String encoding = b
						.encodeAsString((client.getUsername() + ":" + client.getPassword()).getBytes("UTF-8"));
				conn.setRequestProperty("Authorization", "Basic " + encoding);
			}

			conn.setRequestProperty("Content-Type", "application/json");
			switch (method) {
			case GET:
				conn.setRequestMethod("GET");
				if (logger.isDebugEnabled()) {
					logger.debug("GET " + url);
				}
				break;
			case POST:
				conn.setRequestMethod("POST");
				// we write the POST data to the "output" stream:
				conn.setDoOutput(true);
				if (logger.isDebugEnabled()) {
					logger.debug("POST " + path);
				}
				break;
			case PUT:
				conn.setRequestMethod("PUT");
				// we write the POST data to the "output" stream:
				conn.setDoOutput(true);
				if (logger.isDebugEnabled()) {
					logger.debug("PUT " + path);
				}
				break;
			case DELETE:
				conn.setRequestMethod("DELETE");
				if (logger.isDebugEnabled()) {
					logger.debug("DELETE " + path);
				}
				break;
			default:
				throw new SparkPostException("Invalid Method");
			}
		} catch (MalformedURLException ex) {
			throw new SparkPostException("Invalid path: " + path + ex.toString());
		} catch (ProtocolException ex) {
			throw new SparkPostException("Invalid method:" + ex.toString());
		} catch (IOException ex) {
			throw new SparkPostException("Error with connection to " + path + ex.toString());
		}
		return conn;
	}

	// Send HTTP data (payload) to server
	private void sendData(HttpURLConnection conn, String data) throws SparkPostException {
		String lenStr;
		try {
			lenStr = Integer.toString(data.getBytes("UTF-8").length);
		} catch (UnsupportedEncodingException e) {
			// This should never happen. UTF-8 should always be available but we
			// have
			// to catch it so pass it on if it fails.
			throw new SparkPostException(e);
		}
		conn.setRequestProperty("Content-Length", lenStr);
		conn.setRequestProperty("Content-Type", "application/json");

		if (logger.isDebugEnabled()) {
			logger.debug("Sending data (" + lenStr + " bytes): " + data);
		}
		// Send data. At this point connection to server may not be established,
		// but writing data to it will trigger the connection.
		try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream());) {

			wr.writeBytes(data);
			wr.flush();
		} catch (IOException ex) {
			throw new SparkPostException("Error sending request data:" + ex.toString());
		}
	}

	// Send HTTP request to server
	private void sendRequest(HttpURLConnection conn, String data) throws SparkPostException {

		if (data != null) {
			sendData(conn, data);
		}

		try {
			// If no data was sent (right above), then connection to server
			// is not made yet ; however asking for the response code (below)
			// makes conn connect to the server,
			// send the request, and start reading the response.

			// getResponseCode() blocks until the response code is read from the
			// stream from the server
			int code = conn.getResponseCode();
			lastResponse.setResponseCode(code);
			String msg = conn.getResponseMessage();
			lastResponse.setResponseMessage(msg);

		} catch (IOException ex) {
			throw new SparkPostException("Connection error:" + ex.toString());
		}
	}

	// Read response body from server
	private Response receiveResponse(HttpURLConnection conn) throws SparkPostException {

		if (!conn.getContentType().equalsIgnoreCase("application/json")) {
			throw new SparkPostIllegalServerResponseException(
					"Unexpected content type (" + conn.getContentType() + ") from " + conn.getURL());
		}

		StringBuilder sb = new StringBuilder();
		try (BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));) {
			// Buffer the result into a string:
			String line;
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}

			lastResponse.setResponseBody(sb.toString());
			lastResponse.setRequestId(conn.getHeaderField("X-SparkPost-Request-Id"));
		} catch (FileNotFoundException ex) {
			// We get here if the connection was closed:
			// There are cases in REST where the server won't return a response
			// body but only a response status. So if we get here , it is not
			// an error.
			lastResponse.setResponseBody("");
		} catch (IOException ex) {
			throw new SparkPostException("Error reading server response: " + ex.toString() + ": " + sb.toString() + "("
					+ lastResponse.getResponseMessage() + ")");
		}

		return lastResponse;
	}

	// This method actually performs an HTTP request.
	// It is called by get(), put(), post() and delete() below
	private Response doHttpMethod(String path, Method method, String data) throws SparkPostException {
		HttpURLConnection conn = null;
		try {
			lastResponse.reset();
			lastResponse.setRequest(path);
			conn = createConnectionObject(path, method);
			sendRequest(conn, data);
			receiveResponse(conn);

			if (logger.isDebugEnabled()) {
				logger.debug("Server Response:" + lastResponse);
			}

			return lastResponse;
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
	}

	/**
	 * Perform an HTTP GET request. This method throws an exception if the
	 * server returns anything else than a 200. In that case, you need to
	 * retrieve the response with {@link getLastResponse()}.
	 *
	 * @param path
	 *            API endpoint to send the request to.
	 * @return Server response to the request.
	 * @throws SparkPostException
	 */
	public Response get(String path) throws SparkPostException {
		return doHttpMethod(path, Method.GET, null);
	}

	/**
	 * Perform an HTTP POST request. This method throws an exception if the
	 * server returns anything else than a 200. In that case, you need to
	 * retrieve the response with {@link getLastResponse()}.
	 *
	 * @param path
	 *            API endpoint to send the request to.
	 * @param json
	 *            POST data block to send with the request. May be null.
	 * @return Server response to the request.
	 * @throws SparkPostException
	 */
	public Response post(String path, String json) throws SparkPostException {
		return doHttpMethod(path, Method.POST, json);
	}

	/**
	 * Perform an HTTP PUT request. This method throws an exception if the
	 * server returns anything else than a 200. In that case, you need to
	 * retrieve the response with {@link getLastResponse()}.
	 *
	 * @param path
	 *            API endpoint to send the request to.
	 * @param json
	 *            PUT data block to send with the request. May be null.
	 * @return Server response to the request.
	 * @throws SparkPostException
	 */
	public Response put(String path, String json) throws SparkPostException {
		return doHttpMethod(path, Method.PUT, json);
	}

	/**
	 * Perform an HTTP DELETE request. This method throws an exception if the
	 * server returns anything else than a 200. In that case, you need to
	 * retrieve the response with {@link getLastResponse()}.
	 *
	 * @param path
	 *            API endpoint to send the request to.
	 * @return Server response to the request.
	 * @throws SparkPostException
	 */
	public Response delete(String path) throws SparkPostException {
		return doHttpMethod(path, Method.DELETE, null);
	}
}
