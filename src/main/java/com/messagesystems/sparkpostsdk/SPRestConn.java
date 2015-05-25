// This file is part of the SparkPost Java SDK
package com.messagesystems.sparkpostsdk;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.logging.Level;

import org.apache.log4j.Logger;

public class SPRestConn {

    private static final Logger logger = Logger.getLogger(SPRestConn.class);
    public final static String defaultApiEndpoint = "https://api.sparkpost.com/api/v1/";

    private SPClient client = null;
    private String endpoint = null;
    private int responseCode = 0 ;

    /**
     * @return the responseCode
     */
    public int getResponseCode() {
        return responseCode;
    }

    public enum Method {

        GET, POST, PUT, DELETE
    }

    public SPRestConn(SPClient client)
            throws SparkpostSdkException {
        this(client, null /*means:set to default endpoint */);
    }

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

    private void sendData(HttpURLConnection conn, String data)
            throws SparkpostSdkException {
        String lenStr = Integer.toString(data.getBytes().length);
        conn.setRequestProperty("Content-Length", lenStr);
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        logger.debug("Sending data (" + lenStr + " bytes): " + data);
        // Send data
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

    private void sendRequest(HttpURLConnection conn, String data)
            throws SparkpostSdkException {
        if (data != null) {
            sendData(conn, data);
        }

        try {
            // Asking for the response code makes conn connect to the server,
            // send the request, and start reading the response.
            // getResponseCode() blocks until the response code is read from the
            // stream from the server
            this.responseCode = conn.getResponseCode();

            if (this.responseCode != 200) {
                String respMessage = conn.getResponseMessage();
                throw new SparkpostSdkException("Server says: " + this.responseCode + " " + respMessage);
            }
        } catch (IOException ex) {
            throw new SparkpostSdkException("Connection error:" + ex.toString());
        }
    }

    private SPResponse receiveResponse(HttpURLConnection conn)
            throws SparkpostSdkException {
        BufferedReader rd = null;
        SPResponse response = new SPResponse();
        try {
            // Buffer the result into a string:
            rd = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }

            response.setResponseBody(sb.toString());

            response.setRequestId(conn.getHeaderField("X-SparkPost-Request-Id"));
            response.setResponseCode(conn.getResponseCode());
            return response;
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
    }

    private SPResponse doHttpMethod(String path, Method method, String data)
            throws SparkpostSdkException {
        HttpURLConnection conn = null;
        try {
            conn = createConnectionObject(path, method);// throws SparkpostSdkException
            sendRequest(conn, data); // throws SparkpostSdkException
            SPResponse response = receiveResponse(conn);
            response.setRequest(path);
            logger.debug("Server Response:" + response);
            return response;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    public SPResponse get(String path)
            throws SparkpostSdkException {
        return doHttpMethod(path, Method.GET, null);
    }

    public SPResponse post(String path, String json) throws SparkpostSdkException {
        return doHttpMethod(path, Method.POST, json);
    }

    /**
     *
     * @param path
     * @param json
     * @return
     * @throws SparkpostSdkException
     */
    public SPResponse put(String path, String json) throws SparkpostSdkException {
        return doHttpMethod(path, Method.PUT, json);
    }

    public SPResponse delete(String path)
            throws SparkpostSdkException {
        return doHttpMethod(path, Method.DELETE, null);
    }
}
