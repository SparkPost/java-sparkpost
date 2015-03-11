// This file is part of the SparkPost Java SDK

package com.messagesystems.sparkpostsdk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import org.apache.log4j.Logger;

public class SPRestConn {
  private static Logger logger = Logger.getLogger(SPRestConn.class);

  private SPClient client = null;

  private String endpoint = null;

  private Gson gson = null;

  private boolean isAuthValid = false;

  public enum Method {
    GET, POST, PUT, DELETE
  }

  private final static int URL_MAX_LENGTH = 2048;

  public SPRestConn(SPClient client, String endpoint)
    throws SparkpostSdkException
    {
      this(client, endpoint, false);
    }

  public SPRestConn(SPClient client, String endpoint, boolean isAuthValid)
    throws SparkpostSdkException
    {
      this.client = client;
      this.endpoint = endpoint;
      GsonBuilder gsonBuilder = new GsonBuilder()
        .excludeFieldsWithoutExposeAnnotation()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss");
      if (logger.isDebugEnabled()) {
        gson = gsonBuilder.setPrettyPrinting().create();
      } else {
        gson = gsonBuilder.create();
      }
    }

    public Response get(String path)
      throws SparkpostSdkException
      {
        Response response = new Response();
        HttpURLConnection connection = sendRequest(path, Method.GET);
        String json = receiveResponse(connection);
        response.setRequestId(connection.getHeaderField("X-SparkPost-Request-Id"));
        try {
          response.setResponseCode(connection.getResponseCode());
          response.setResponseMessage(connection.getResponseMessage());
        } catch (IOException ex) {
          throw new SparkpostSdkException(ex);
        }
        response.setResponseBody(json);
        connection.disconnect();
        return response;
      }
}
