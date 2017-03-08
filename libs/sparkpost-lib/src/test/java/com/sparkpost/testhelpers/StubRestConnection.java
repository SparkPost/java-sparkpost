
package com.sparkpost.testhelpers;

import com.sparkpost.exception.SparkPostException;
import com.sparkpost.model.responses.Response;
import com.sparkpost.resources.Endpoint;
import com.sparkpost.transport.IRestConnection;

public class StubRestConnection implements IRestConnection {

    private String path;

    private Endpoint endpoint;

    private String json;

    private Response response;

    private boolean wasGet;

    private boolean wasPost;

    private boolean wasPut;

    private boolean wasDelete;

    public StubRestConnection(Response response) {
        this.response = response;
    }

    @Override
    public Response get(String path) throws SparkPostException {
        this.wasGet = true;
        this.path = path;
        return this.response;
    }

    @Override
    public Response post(String path, String json) throws SparkPostException {
        this.wasPost = true;
        this.path = path;
        this.json = json;

        return this.response;
    }

    @Override
    public Response put(String path, String json) throws SparkPostException {
        this.wasPut = true;
        this.path = path;
        this.json = json;
        return this.response;
    }

    @Override
    public Response delete(String path) throws SparkPostException {
        this.wasDelete = true;
        this.path = path;
        return this.response;
    }

    @Override
    public Response get(Endpoint endpoint) throws SparkPostException {
        this.endpoint = endpoint;
        this.wasGet = true;
        return this.response;
    }

    @Override
    public Response post(Endpoint endpoint, String json) throws SparkPostException {
        this.endpoint = endpoint;
        this.wasPost = true;
        this.json = json;
        return this.response;
    }

    @Override
    public Response put(Endpoint endpoint, String json) throws SparkPostException {
        this.endpoint = endpoint;
        this.wasPut = true;
        this.json = json;
        return this.response;
    }

    @Override
    public Response delete(Endpoint endpoint) throws SparkPostException {
        this.endpoint = endpoint;
        this.wasDelete = true;
        return this.response;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Endpoint getEndpoint() {
        return this.endpoint;
    }

    public void setEndpoint(Endpoint endpoint) {
        this.endpoint = endpoint;
    }

    public String getRequestUri() {
        if (this.endpoint != null && this.endpoint.toString() != null) {
            return this.endpoint.toString();
        }
        return this.path;
    }

    public String getJson() {
        return this.json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public Response getResponse() {
        return this.response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public boolean wasGet() {
        return this.wasGet;
    }

    public boolean wasPost() {
        return this.wasPost;
    }

    public boolean wasPut() {
        return this.wasPut;
    }

    public boolean wasDelete() {
        return this.wasDelete;
    }

}
