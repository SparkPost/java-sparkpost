
package com.sparkpost.error.samples;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sparkpost.Client;
import com.sparkpost.exception.SparkPostErrorServerResponseException;
import com.sparkpost.exception.SparkPostException;
import com.sparkpost.model.Webhook;
import com.sparkpost.resources.ResourceWebhooks;
import com.sparkpost.sdk.samples.helpers.SparkPostBaseApp;
import com.sparkpost.transport.IRestConnection;
import com.sparkpost.transport.RestConnection;

public class ForceTransportError extends SparkPostBaseApp {

    static final Logger logger = LoggerFactory.getLogger(ForceTransportError.class);

    private Client client;

    public static void main(String[] args) throws SparkPostException, IOException {
        ForceTransportError app = new ForceTransportError();
        app.runApp();
    }

    private void runApp() throws SparkPostException, IOException {
        try {
            foceFourHundredError();
        } catch (SparkPostException e) {
            e.printStackTrace();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    // Test https://github.com/SparkPost/java-sparkpost/issues/44#issuecomment-215549742
    private void foceFourHundredError() throws SparkPostException, IOException {
        this.client = this.newConfiguredClient();

        IRestConnection restConnection = new RestConnection(this.client);
        Webhook webhook = new Webhook();
        webhook.setName("name with spaces");
        try {
            ResourceWebhooks.update(restConnection, webhook.getName(), webhook);
            throw new IllegalStateException("Error: Expected SparkPostErrorServerResponseException");
        } catch (SparkPostErrorServerResponseException e) {
            System.out.println("GOOD: Sucecssfuly got a SparkPostErrorServerResponseException");

        }
    }

}
