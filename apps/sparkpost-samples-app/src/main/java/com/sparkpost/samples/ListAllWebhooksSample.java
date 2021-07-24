
package com.sparkpost.samples;

import java.io.IOException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import com.sparkpost.Client;
import com.sparkpost.exception.SparkPostException;
import com.sparkpost.model.responses.WebhookListAllResponse;
import com.sparkpost.resources.ResourceWebhooks;
import com.sparkpost.sdk.samples.helpers.SparkPostBaseApp;
import com.sparkpost.transport.IRestConnection;
import com.sparkpost.transport.RestConnection;

/**
 * List all Webhooks stored in a SparkPost account
 */
public class ListAllWebhooksSample extends SparkPostBaseApp {

    static final Logger logger = LogManager.getLogger(CreateTemplateSimple.class);

    private Client client;

    public static void main(String[] args) throws SparkPostException, IOException {
        Configurator.setRootLevel(Level.DEBUG);

        ListAllWebhooksSample sample = new ListAllWebhooksSample();
        sample.runApp();
    }

    private void runApp() throws SparkPostException, IOException {
        client = this.newConfiguredClient();
        IRestConnection connection = new RestConnection(client, getEndPoint());
        WebhookListAllResponse response = ResourceWebhooks.listAll(connection, "America/Chicago");
        System.out.println("Webhooks: " + response);

    }
}
