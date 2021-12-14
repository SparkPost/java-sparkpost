
package com.sparkpost.samples;

import java.io.IOException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;

import com.sparkpost.Client;
import com.sparkpost.exception.SparkPostException;
import com.sparkpost.model.TransmissionBase;
import com.sparkpost.model.responses.TransmissionListResponse;
import com.sparkpost.model.responses.TransmissionRetrieveResults;
import com.sparkpost.resources.ResourceTransmissions;
import com.sparkpost.sdk.samples.helpers.SparkPostBaseApp;
import com.sparkpost.transport.IRestConnection;
import com.sparkpost.transport.RestConnection;

/**
 * List an array of transmission summary objects. A transmission summary object
 * contains the "template_id", "id", "campaign_id", "description", and "state".
 * The following lists are supported:
 * <ul>
 * <li>All multi-recipient transmissions</li>
 * <li>Multi-recipient transmissions using a specific template</li>
 * <li>Multi-recipient transmissions for a campaign</li>
 * <li>Multi-recipient transmissions for a campaign that use a specific template
 * </li>
 * </ul>
 * Note that single recipient transmissions are not returned.
 * By default, the list includes all transmissions for all campaigns. Use the
 * template_id parameter to specify a template and the campaign_id parameter to
 * specify a campaign. The response for transmissions using an inline template
 * will include "template_id":"inline". Inline templates cannot be specifically
 * queried.The example response shows a query on campaign_id=thanksgiving, with
 * template_id not specified as part of the query.
 */
public class ListAllTransmissionsSample extends SparkPostBaseApp {

    private Client client;

    public static void main(String[] args) throws SparkPostException, IOException {
        Configurator.setRootLevel(Level.DEBUG);

        ListAllTransmissionsSample sample = new ListAllTransmissionsSample();
        sample.runApp();

    }

    private void runApp() throws SparkPostException, IOException {
        client = this.newConfiguredClient();
        listAllTransmissions();
    }

    /**
     * Demonstration how to retrieve the full list or transmission from server
     */
    private TransmissionListResponse listAllTransmissions() throws SparkPostException {
        IRestConnection connection = new RestConnection(client, getEndPoint());
        TransmissionListResponse response = ResourceTransmissions.list(connection, null, null);

        if (response.getResponseCode() == 200) {
            if (response.getResults() == null || response.getResults().size() == 0) {
                System.out.println("There are no transmissions available.");
                return response;
            }

            for (TransmissionBase transmission : response.getResults()) {
                System.out.println("TransmissionId: " + transmission.getId() + " state: " + transmission.getState());

                // Fetch transmission detail
                fetchTransmissionDetail(transmission, connection);
            }
        }

        return response;
    }

    /**
     * This demonstrates how to retrieve the contents of a specific transmission using the transmissionId
     */
    private void fetchTransmissionDetail(TransmissionBase transmission, IRestConnection connection) throws SparkPostException {
        TransmissionRetrieveResults results = ResourceTransmissions.retrieve(connection, transmission.getId());
        System.out.println("Result: " + results.getResults().getTransmission());
    }
}
