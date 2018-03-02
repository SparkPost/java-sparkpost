
package com.sparkpost.transport;

import java.util.Map;

import com.sparkpost.exception.SparkPostException;
import com.sparkpost.model.responses.Response;
import com.sparkpost.resources.Endpoint;

public interface IRestConnection {

    /**
     * Default endpoint to use for connections :
     * https://api.sparkpost.com/api/v1
     */
    String defaultApiEndpoint = "https://api.sparkpost.com/api/v1";

    /**
     * Perform an HTTP GET request. This method throws an exception if the
     * server returns anything else than a 200.
     *
     * @deprecated use method that uses Endpoint as an argument instead
     * @param path
     *            API endpoint to send the request to.
     * @return Server response to the request.
     * @throws SparkPostException
     *             if something goes wrong
     */
    @Deprecated
    Response get(String path) throws SparkPostException;

    /**
     * Perform an HTTP GET request. This method throws an exception if the
     * server returns anything else than a 200.
     *
     * @param endpoint
     *            API endpoint to send the request to.
     * @return Server response to the request.
     * @throws SparkPostException
     *             if something goes wrong
     */
    Response get(Endpoint endpoint) throws SparkPostException;

    /**
     * Perform an HTTP POST request. This method throws an exception if the
     * server returns anything else than a 200.
     *
     * @param path
     *            API endpoint to send the request to.
     * @param json
     *            POST data block to send with the request. May be null.
     * @return Server response to the request.
     * @throws SparkPostException
     *             if something goes wrong
     */
    @Deprecated
    Response post(String path, String json) throws SparkPostException;

    /**
     * Perform an HTTP POST request. This method throws an exception if the
     * server returns anything else than a 200.
     *
     * @param endpoint
     *            API endpoint to send the request to.
     * @param json
     *            POST data block to send with the request. May be null.
     * @return Server response to the request
     * @throws SparkPostException
     *             if something goes wrong
     */
    Response post(Endpoint endpoint, String json) throws SparkPostException;

    /**
     * Perform an HTTP PUT request. This method throws an exception if the
     * server returns anything else than a 200.
     *
     * @deprecated use method that uses Endpoint as an argument instead
     * @param path
     *            API endpoint to send the request to.
     * @param json
     *            PUT data block to send with the request. May be null.
     * @return Server response to the request.
     * @throws SparkPostException
     *             if something goes wrong
     */
    @Deprecated
    Response put(String path, String json) throws SparkPostException;

    /**
     * Perform an HTTP PUT request. This method throws an exception if the
     * server returns anything else than a 200.
     *
     * @param endpoint
     *            API endpoint to send the request to.
     * @param json
     *            PUT data block to send with the request. May be null.
     * @return Server response to the request.
     * @throws SparkPostException
     *             if something goes wrong
     */
    Response put(Endpoint endpoint, String json) throws SparkPostException;

    /**
     * Perform an HTTP DELETE request. This method throws an exception if the
     * server returns anything else than a 200.
     *
     * @deprecated use method that uses Endpoint as an argument instead
     * @param path
     *            API endpoint to send the request to.
     * @return Server response to the request.
     * @throws SparkPostException
     *             if something goes wrong
     */
    @Deprecated
    Response delete(String path) throws SparkPostException;

    /**
     * Perform an HTTP DELETE request. This method throws an exception if the
     * server returns anything else than a 200.
     *
     * @param endpoint
     *            API endpoint to send the request to.
     * @return Server response to the request.
     * @throws SparkPostException
     *             if something goes wrong
     */
    Response delete(Endpoint endpoint) throws SparkPostException;

    Map<String, String> getAdditionalRequestHeaders();

    void setSubAccountId(String subAccountId);

}
