/* Copyright 2014 Message Systems, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this software except in compliance with the License.
 *
 * A copy of the License is located at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0.html
 *
 * or in the "license" file accompanying this software. This file is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF
 * ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */
package com.messagesystems.sparkpostsdk;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import org.apache.log4j.Logger;

/**
 * The REST connection class wraps HTTP requests to the SparkPost API.
 *
 * @author grava
 */
public class SPRestConn {

    private static final Logger logger = Logger.getLogger(SPRestConn.class);

    /**
     * Default endpoint to use for connections :
     * https://api.sparkpost.com/api/v1/
     *
     */
    public final static String defaultApiEndpoint = "https://api.sparkpost.com/api/v1/";

    private SPClient client = null;
    private String endpoint = null;
    private SPResponse lastResponse = null;

    /**
     * Retrieve the response from the last HTTP request
     *
     * @return the response
     */
    public SPResponse getLastResponse() {
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
     * @param client Client object to use (in particular for authentication
     * info)
     * @throws SparkpostSdkException
     */
    public SPRestConn(SPClient client)
            throws SparkpostSdkException {
        this(client, null /*means:set to default endpoint */);
    }

    /**
     * Create a REST connection object. The given {@link endpoint} will be used
     * for connections
     *
     * @param client Client object to use (in particular for authentication
     * info)
     * @param endpoint Endpoint to use instead of the default defaultApiEndpoint
     * @throws SparkpostSdkException
     */
    public SPRestConn(SPClient client, String endpoint)
            throws SparkpostSdkException {
        this.client = client;
        if (endpoint == null) {
            this.endpoint = defaultApiEndpoint;
        } else {
            if (endpoint.endsWith("/")) {
                this.endpoint = endpoint;
            } else {
                this.endpoint = endpoint + '/';
            }
        }
        lastResponse = new SPResponse();
    }

    /**
     * Create a connection object but doesn't actually connect to the server
     *
     * @param path URL suffix to be appended to the object's endpoint
     * @param method HTTP method for the connection
     * @return the connection object
     * @throws SparkpostSdkException
     */
    private HttpURLConnection createConnectionObject(String path, Method method)
            throws SparkpostSdkException {
        HttpURLConnection conn;
        try {
            URL url;
            url = new URL(this.endpoint + path);

            // Retrieve the URLConnection object (but doesn't actually connect):
            // (HttpUrlConnection doesn't connect to the server until we've
            //  got one of its streams)
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Authorization", this.client.GetAuthKey());
            conn.setRequestProperty("Content-Type", "application/json");
            switch (method) {
                case GET:
                    conn.setRequestMethod("GET");
                    logger.debug("GET " + url);
                    break;
                case POST:
                    conn.setRequestMethod("POST");
                    // we write the POST data to the "output" stream:
                    conn.setDoOutput(true);
                    logger.debug("POST " + path);
                    break;
                case PUT:
                    conn.setRequestMethod("PUT");
                    // we write the POST data to the "output" stream:
                    conn.setDoOutput(true);
                    logger.debug("PUT " + path);
                    break;
                case DELETE:
                    conn.setRequestMethod("DELETE");
                    logger.debug("DELETE " + path);
                    break;
                default:
                    throw new SparkpostSdkException("Invalid Method");
            }
        } catch (MalformedURLException ex) {
            throw new SparkpostSdkException("Invalid path: " + path + ex.toString());
        } catch (ProtocolException ex) {
            throw new SparkpostSdkException("Invalid method:" + ex.toString());
        } catch (IOException ex) {
            throw new SparkpostSdkException("Error with connection to " + path + ex.toString());
        }
        return conn;
    }

    // Send HTTP data (payload) to server
    private void sendData(HttpURLConnection conn, String data)
            throws SparkpostSdkException {
        String lenStr = Integer.toString(data.getBytes().length);
        conn.setRequestProperty("Content-Length", lenStr);
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        logger.debug("Sending data (" + lenStr + " bytes): " + data);
        // Send data. At this point connection to server may not be established,
        // but writing data to it will trigger the connection.
        DataOutputStream wr;
        try {
            wr = new DataOutputStream(conn.getOutputStream());
            wr.writeBytes(data);
            wr.flush();
            wr.close();
        } catch (IOException ex) {
            throw new SparkpostSdkException("Error sending request data:" + ex.toString());
        }
    }

    // Send HTTP request to server
    private void sendRequest(HttpURLConnection conn, String data)
            throws SparkpostSdkException {

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
            throw new SparkpostSdkException("Connection error:" + ex.toString());
        }
    }

    // Read response body from server
    private SPResponse receiveResponse(HttpURLConnection conn)
            throws SparkpostSdkException {
        BufferedReader rd = null;
        try {
            // Buffer the result into a string:
            rd = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
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
            throw new SparkpostSdkException("Error reading server response: " + ex.toString());
        } finally {
            try {
                if (rd != null) {
                    rd.close();
                }
            } catch (IOException ex) {
            }
        }
        return lastResponse;
    }

    // This method actually performs an HTTP request. 
    // It is called by get(), put(), post() and delete() below
    private SPResponse doHttpMethod(String path, Method method, String data)
            throws SparkpostSdkException {
        HttpURLConnection conn = null;
        try {
            lastResponse.reset();
            lastResponse.setRequest(path);
            conn = createConnectionObject(path, method);// throws SparkpostSdkException
            sendRequest(conn, data); // throws SparkpostSdkException
            receiveResponse(conn);
            logger.debug("Server Response:" + lastResponse);
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
     * @param path API endpoint to send the request to.
     * @return Server response to the request.
     * @throws SparkpostSdkException
     */
    public SPResponse get(String path)
            throws SparkpostSdkException {
        return doHttpMethod(path, Method.GET, null);
    }

    /**
     * Perform an HTTP POST request. This method throws an exception if the
     * server returns anything else than a 200. In that case, you need to
     * retrieve the response with {@link getLastResponse()}.
     *
     * @param path API endpoint to send the request to.
     * @param json POST data block to send with the request. May be null.
     * @return Server response to the request.
     * @throws SparkpostSdkException
     */
    public SPResponse post(String path, String json) throws SparkpostSdkException {
        return doHttpMethod(path, Method.POST, json);
    }

    /**
     * Perform an HTTP PUT request. This method throws an exception if the
     * server returns anything else than a 200. In that case, you need to
     * retrieve the response with {@link getLastResponse()}.
     *
     * @param path API endpoint to send the request to.
     * @param json PUT data block to send with the request. May be null.
     * @return Server response to the request.
     * @throws SparkpostSdkException
     */
    public SPResponse put(String path, String json) throws SparkpostSdkException {
        return doHttpMethod(path, Method.PUT, json);
    }

    /**
     * Perform an HTTP DELETE request. This method throws an exception if the
     * server returns anything else than a 200. In that case, you need to
     * retrieve the response with {@link getLastResponse()}.
     *
     * @param path API endpoint to send the request to.
     * @return Server response to the request.
     * @throws SparkpostSdkException
     */
    public SPResponse delete(String path)
            throws SparkpostSdkException {
        return doHttpMethod(path, Method.DELETE, null);
    }
}
