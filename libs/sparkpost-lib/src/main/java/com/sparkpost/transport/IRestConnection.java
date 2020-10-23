
package com.sparkpost.transport;

import com.sparkpost.exception.SparkPostException;
import com.sparkpost.model.responses.Response;
import com.sparkpost.resources.Endpoint;

public interface IRestConnection {

    String SPC_EU_ENDPOINT = "https://api.eu.sparkpost.com/api/v1";

    String SPC_US_ENDPOINT = "https://api.sparkpost.com/api/v1";

    String SUBACCOUNT_HEADER = "X-MSYS-SUBACCOUNT";

    /**
     * Default endpoint to use for connections :
     * https://api.sparkpost.com/api/v1
     */
    String defaultApiEndpoint = SPC_US_ENDPOINT;

    /**
     * @param key
     *            The HTTP header key
     * @param value
     *            The HTTP header value
     */
    void addHeader(String key, String value);

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
     * @param endpoint
     *            API endpoint to send the request to.
     * @return Server response to the request.
     * @throws SparkPostException
     *             if something goes wrong
     */
    Response delete(Endpoint endpoint) throws SparkPostException;

}
