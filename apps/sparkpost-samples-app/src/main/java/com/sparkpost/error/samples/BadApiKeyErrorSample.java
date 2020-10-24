
package com.sparkpost.error.samples;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sparkpost.Client;
import com.sparkpost.exception.SparkPostAccessForbiddenException;
import com.sparkpost.exception.SparkPostException;
import com.sparkpost.resources.ResourceSendingDomains;
import com.sparkpost.sdk.samples.helpers.SparkPostBaseApp;
import com.sparkpost.transport.IRestConnection;
import com.sparkpost.transport.RestConnection;

/**
 * Lists all Sending Domains
 */
public class BadApiKeyErrorSample extends SparkPostBaseApp {

    static final Logger logger = LoggerFactory.getLogger(BadApiKeyErrorSample.class);

    private Client client;

    public static void main(String[] args) throws SparkPostException, IOException {
        BadApiKeyErrorSample sample = new BadApiKeyErrorSample();
        sample.runApp();
    }

    private void runApp() throws SparkPostException, IOException {
        this.client = this.newConfiguredClient();
        this.client.setAuthKey("This_is_a_bad_api_key");
        IRestConnection connection = new RestConnection(this.client, getEndPoint());
        try {
            ResourceSendingDomains.list(connection);

            throw new IllegalStateException("Error: Expected SparkPostAccessForbiddenException");
        } catch (SparkPostAccessForbiddenException e) {
            System.out.println("GOOD: Sucecssfuly got a SparkPostAccessForbiddenException");
        }

    }

}
