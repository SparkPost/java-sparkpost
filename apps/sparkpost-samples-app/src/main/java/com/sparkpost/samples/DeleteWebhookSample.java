
package com.sparkpost.samples;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sparkpost.Client;
import com.sparkpost.exception.SparkPostException;
import com.sparkpost.model.Webhook;
import com.sparkpost.model.responses.WebhookListAllResponse;
import com.sparkpost.resources.ResourceWebhooks;
import com.sparkpost.sdk.samples.helpers.SparkPostBaseApp;
import com.sparkpost.transport.IRestConnection;
import com.sparkpost.transport.RestConnection;

/**
 * Delete all webhooks with "deleteme" in their name
 */
public class DeleteWebhookSample extends SparkPostBaseApp {

    static final Logger logger = LoggerFactory.getLogger(CreateTemplateSimple.class);

    private Client client;

    public static void main(String[] args) throws SparkPostException, IOException {
        DeleteWebhookSample sample = new DeleteWebhookSample();
        sample.runApp();
    }

    private void runApp() throws SparkPostException, IOException {
        this.client = this.newConfiguredClient();
        IRestConnection connection = new RestConnection(this.client, getEndPoint());
        WebhookListAllResponse response = ResourceWebhooks.listAll(connection, "America/Chicago");

        int deletedCount = 0;
        for (Webhook webhook : response.getResults()) {
            if (webhook.getName().toLowerCase().contains("deleteme")) {
                System.out.println("Will delete: " + webhook.getName() + " (" + webhook.getId() + ")");
                ResourceWebhooks.delete(connection, webhook.getId());
                deletedCount++;
            }
        }

        System.out.println("Deleted " + deletedCount + " webhooks.");
    }
}
