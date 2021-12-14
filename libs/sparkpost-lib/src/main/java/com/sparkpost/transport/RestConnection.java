
package com.sparkpost.transport;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sparkpost.Build;
import com.sparkpost.Client;
import com.sparkpost.exception.SparkPostAccessForbiddenException;
import com.sparkpost.exception.SparkPostAuthorizationFailedException;
import com.sparkpost.exception.SparkPostErrorServerResponseException;
import com.sparkpost.exception.SparkPostException;
import com.sparkpost.exception.SparkPostIllegalServerResponseException;
import com.sparkpost.model.responses.Response;
import com.sparkpost.model.responses.ServerErrorResponses;
import com.sparkpost.resources.Endpoint;

import lombok.Getter;

/**
 * The REST connection class wraps HTTP requests to the SparkPost API.
 */
public class RestConnection implements IRestConnection {

    private static final Logger logger = LogManager.getLogger(RestConnection.class);

    private static final String VERSION = Build.VERSION + " (" + Build.GIT_SHORT_HASH + ")";

    private static final Base64 BASE64 = new Base64();
    private static final String DEFAULT_CHARSET = "UTF-8";

    private static final int UNAUTHORIZED_RESPONSE_STATUS_CODE = 401;
    private static final int ACCESS_FORBIDDEN_RESPONSE_STATUS_CODE = 403;

    private final Client client;

    @Getter
    private final String baseUrl;

    private final Map<String, String> extraHeaders = new HashMap<String, String>();

    /**
     * Supported HTTP methods
     */
    public enum Method {

        GET,
        POST,
        PUT,
        DELETE
    }

    /**
     * Create a REST connection object. The default endpoint defaultApiEndpoint
     * will be used for connections.
     *
     * @param client
     *            Client object to use (in particular for authentication info)
     * @throws SparkPostException
     *             if something goes wrong
     */
    public RestConnection(Client client) throws SparkPostException {
        this(client, "" /* means:set to default endpoint */);
    }

    /**
     * Create a REST connection object. The given baseUrl will be used
     * for connections
     *
     * @param client
     *            Client object to use (in particular for authentication info)
     * @param baseUrl
     *            Endpoint to use instead of the default defaultApiEndpoint
     * @throws SparkPostException
     *             if something goes wrong
     */
    public RestConnection(Client client, String baseUrl) throws SparkPostException {

        this.client = client;
        if (StringUtils.isAnyEmpty(baseUrl)) {
            this.baseUrl = defaultApiEndpoint;
        } else {

            this.baseUrl = baseUrl;
        }

        if (this.baseUrl.endsWith("/")) {
            throw new IllegalStateException("SPARKPOST_BASE_URL should not end with a '/',  SPARKPOST_BASE_URL=" + this.baseUrl + "");
        }
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
     *             if something goes wrong
     */
    private HttpURLConnection createConnectionObject(String path, Method method) throws SparkPostException {
        HttpURLConnection conn;
        try {
            URL url;
            url = new URL(this.baseUrl + path);

            /*
             * Retrieve the URLConnection object (but doesn't actually connect):
             * (HttpUrlConnection doesn't connect to the server until we've
             * got one of its streams)
             * Enable Client to set up proxy if need be
             */

            if (this.client.getProxy() != null) {
                conn = (HttpURLConnection) url.openConnection(this.client.getProxy());
            } else {
                conn = (HttpURLConnection) url.openConnection();
            }

            conn.setConnectTimeout(this.client.getHttpConnectTimeout());
            conn.setReadTimeout(this.client.getHttpReadTimeout());

            if (StringUtils.isNotEmpty(this.client.getAuthKey())) {
                conn.setRequestProperty("Authorization", this.client.getAuthKey());
            } else if (StringUtils.isNotEmpty(this.client.getUsername()) && StringUtils.isNotEmpty(this.client.getPassword())) {
                String encoding = BASE64.encodeAsString((this.client.getUsername() + ":" + this.client.getPassword()).getBytes(DEFAULT_CHARSET));
                conn.setRequestProperty("Authorization", "Basic " + encoding);
            }

            conn.setRequestProperty("User-Agent", "java-sparkpost/" + VERSION);

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
                    // we write the PUT data to the "output" stream:
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
        byte[] bytes = null;
        try {
            bytes = data.getBytes(DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException e) {
            // This should never happen. UTF-8 should always be available but we
            // have to catch it so pass it on if it fails.
            throw new SparkPostException(e);
        }

        String lenStr = Integer.toString(bytes.length);
        conn.setRequestProperty("Content-Length", lenStr);
        conn.setRequestProperty("Content-Type", "application/json");

        if (logger.isDebugEnabled()) {
            logger.debug("Sending data (" + lenStr + " bytes): " + data);
        }
        // Send data. At this point connection to server may not be established,
        // but writing data to it will trigger the connection.
        try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
            wr.write(bytes);
            wr.flush();
        } catch (IOException ex) {
            throw new SparkPostException("Error sending request data:" + ex.toString());
        }
    }

    // Send HTTP request to server
    private void sendRequest(HttpURLConnection conn, String data, Response response) throws SparkPostException {

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
            response.setResponseCode(code);
            response.setContentType(conn.getHeaderField("Content-Type"));
            String msg = conn.getResponseMessage();
            response.setResponseMessage(msg);

        } catch (IOException ex) {
            throw new SparkPostException("Connection error:" + ex.toString());
        }
    }

    // Read response body from server
    private Response receiveResponse(HttpURLConnection conn, Response response) throws SparkPostException {
        StringBuilder sb = new StringBuilder();
        try {
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 299) {
                // All 2xx responses are success
                return receiveSuccessResponse(conn, response);

            } else if (conn.getResponseCode() >= 400 && conn.getResponseCode() <= 499) {
                // 4xx errors means there is something wrong with the request
                return receiveErrorResponse(conn, response);

            } else if (conn.getResponseCode() >= 500 && conn.getResponseCode() <= 599) {
                // 5xx errors means something went wrong on server and should be retried
                return receiveErrorResponse(conn, response);

            } else {
                // We got some other response from the server.
                throw new SparkPostIllegalServerResponseException(
                        "Unexpected server response ContentType("
                                + conn.getContentType()
                                + ") from "
                                + conn.getURL()
                                + " responseCode("
                                + conn.getResponseCode()
                                + ")"
                                + " contentLength("
                                + conn.getContentLength()
                                + ")");
            }

        } catch (IOException ex) {
            throw new SparkPostErrorServerResponseException(
                    "Error reading server response: " + ex.toString() + ": " + sb.toString() + "(" + response.getResponseMessage() + ")",
                    response.getResponseCode());
        }
    }

    // This is used to handle 2xx HTTP responses
    private Response receiveSuccessResponse(HttpURLConnection conn, Response response) throws SparkPostException {
        response.setRequestId(conn.getHeaderField("X-SparkPost-Request-Id"));

        if (conn.getContentLength() == 0) {
            return response;
        }

        StringBuilder sb = new StringBuilder();
        try (BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), DEFAULT_CHARSET))) {
            // Buffer the result into a string:
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }

            response.setResponseBody(sb.toString());
        } catch (IOException ex) {
            String line = "";

            try {
                // We are in the success case handling but check the error stream anyway just in case
                try (BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(), DEFAULT_CHARSET))) {

                    while ((line = rd.readLine()) != null) {
                        sb.append(line);
                    }

                    response.setResponseBody(sb.toString());

                    logger.error("Server Response:\n" + sb.toString() + "\n");

                } catch (IOException ex2) {
                    // Ignore we are going to throw an exception anyway
                }
            } catch (Exception e) {
                // Log but ignore we are going to throw an exception anyway
                logger.error("Error while handlign an HTTP response error. Ignoring and will use orginal exception", e);
            }

            if (logger.isDebugEnabled()) {
                logger.error("Server Response:" + response);
            }

            throw new SparkPostErrorServerResponseException(
                    "Error reading server response: " + ex.toString() + ": " + sb.toString() + "(" + response.getResponseMessage() + ")",
                    response.getResponseCode());
        }
        return response;
    }

    // This is used to handle non 2xx HTTP responses
    private Response receiveErrorResponse(HttpURLConnection conn, Response response) throws SparkPostException, IOException {
        response.setRequestId(conn.getHeaderField("X-SparkPost-Request-Id"));

        if (conn.getContentLength() == 0) {
            throw new SparkPostErrorServerResponseException(
                    "Unexpected server response ContentType("
                            + conn.getContentType()
                            + ") from "
                            + conn.getURL()
                            + " contentLength("
                            + conn.getContentLength()
                            + ")",
                    conn.getResponseCode());
        }
        StringBuilder sb = new StringBuilder();
        ServerErrorResponses errorResponses = null;

        try {
            try (BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(), DEFAULT_CHARSET))) {

                String line = "";
                while ((line = rd.readLine()) != null) {
                    sb.append(line);
                }

                response.setResponseBody(sb.toString());

                logger.error("Server Response:\n" + sb.toString() + "\n");

                try {
                    errorResponses = (ServerErrorResponses) ServerErrorResponses.decode(response, ServerErrorResponses.class);
                } catch (Exception e) {
                    // Maybe there is something wrong where HTML is returned or some other very bad error. Protect
                    // against exception here so a more specific exception can be thrown later later.
                    logger.error("Failed to parse server response:\n", e);
                }

            } catch (IOException ex2) {
                // Ignore since an exception is getting thrown anyway
            }
        } catch (Exception e) {
            // Log but ignore since an exception is getting thrown anyway
            logger.error("Error while handling an HTTP response error. Ignoring and will use orginal exception", e);
        }

        if (logger.isDebugEnabled()) {
            logger.error("Server Response:" + response);
        }

        if (response.getResponseCode() == UNAUTHORIZED_RESPONSE_STATUS_CODE) {
            throw new SparkPostAuthorizationFailedException(
                    "Error reading server response: "
                            + sb.toString()
                            + "("
                            + response.getResponseMessage()
                            + ") responseCode("
                            + response.getResponseCode()
                            + ")",
                    errorResponses);

        } else if (response.getResponseCode() == ACCESS_FORBIDDEN_RESPONSE_STATUS_CODE) {
            throw new SparkPostAccessForbiddenException(
                    "Error reading server response: "
                            + sb.toString()
                            + "("
                            + response.getResponseMessage()
                            + ") responseCode("
                            + response.getResponseCode()
                            + ")",
                    errorResponses);
        } else {

            throw new SparkPostErrorServerResponseException(
                    "Error reading server response: " + sb.toString() + "(" + response.getResponseMessage() + ")",
                    response.getResponseCode(),
                    errorResponses);
        }
    }

    // This method actually performs the HTTP request
    // It is called by get(), put(), post() and delete() below
    private Response doHttpMethod(Endpoint endpoint, Method method, String data, Response response) throws SparkPostException {
        HttpURLConnection conn = null;
        try {
            String path = endpoint.toString();
            response.setRequest(path);
            conn = createConnectionObject(path, method);

            // Add additional request headers
            Set<Entry<String, String>> entrySet = this.extraHeaders.entrySet();
            for (Entry<String, String> entry : entrySet) {
                conn.setRequestProperty(entry.getKey(), entry.getValue());
            }

            sendRequest(conn, data, response);
            receiveResponse(conn, response);

            if (logger.isDebugEnabled()) {
                logger.debug("Server Response:" + response);
            }

            return response;
        } finally {
            if (this.client.isDisconnectAfterRequest() && conn != null) {
                // Disconnect will more aggressively close idle connections but it is preferred to cache as much as possible
                conn.disconnect();
            }
        }
    }

    /*
     * (non-Javadoc)
     * @see com.sparkpost.transport.IRestConnection#get(com.sparkpost.resources.Endpoint)
     */
    @Override
    public Response get(Endpoint endpoint) throws SparkPostException {
        Response response = new Response();
        return doHttpMethod(endpoint, Method.GET, null, response);
    }

    /*
     * (non-Javadoc)
     * @see com.sparkpost.transport.IRestConnection#post(com.sparkpost.resources.Endpoint, java.lang.String)
     */
    @Override
    public Response post(Endpoint endpoint, String json) throws SparkPostException {
        Response response = new Response();
        return doHttpMethod(endpoint, Method.POST, json, response);
    }

    /*
     * (non-Javadoc)
     * @see com.sparkpost.transport.IRestConnection#put(com.sparkpost.resources.Endpoint, java.lang.String)
     */
    @Override
    public Response put(Endpoint endpoint, String json) throws SparkPostException {
        Response response = new Response();
        return doHttpMethod(endpoint, Method.PUT, json, response);
    }

    /*
     * (non-Javadoc)
     * @see com.sparkpost.transport.IRestConnection#delete(com.sparkpost.resources.Endpoint)
     */
    @Override
    public Response delete(Endpoint endpoint) throws SparkPostException {
        Response response = new Response();
        return doHttpMethod(endpoint, Method.DELETE, null, response);
    }

    @Override
    public void addHeader(String key, String value) {
        this.extraHeaders.put(key, value);

    }
}
