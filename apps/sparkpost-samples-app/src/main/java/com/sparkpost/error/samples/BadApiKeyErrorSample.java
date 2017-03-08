
package com.sparkpost.error.samples;

import java.io.IOException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

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

    static final Logger logger = Logger.getLogger(BadApiKeyErrorSample.class);

    private Client client;

    public static void main(String[] args) throws SparkPostException, IOException {
        Logger.getRootLogger().setLevel(Level.DEBUG);

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
