
package com.sparkpost.samples;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import com.sparkpost.Client;
import com.sparkpost.exception.SparkPostException;
import com.sparkpost.model.TemplateItem;
import com.sparkpost.model.responses.TemplateListResponse;
import com.sparkpost.model.responses.TemplateRetrieveResponse;
import com.sparkpost.resources.ResourceTemplates;
import com.sparkpost.sdk.samples.helpers.SparkPostBaseApp;
import com.sparkpost.transport.IRestConnection;
import com.sparkpost.transport.RestConnection;

/**
 * List all templates stored in a SparkPost account
 */
public class RetrieveAllTemplatesSample extends SparkPostBaseApp {

    static final Logger logger = LogManager.getLogger(CreateTemplateSimple.class);

    private Client client;

    public static void main(String[] args) throws SparkPostException, IOException {
        Configurator.setRootLevel(Level.DEBUG);

        RetrieveAllTemplatesSample sample = new RetrieveAllTemplatesSample();
        sample.runApp();
    }

    private void runApp() throws SparkPostException, IOException {
        this.client = this.newConfiguredClient();
        IRestConnection connection = new RestConnection(this.client, getEndPoint());
        TemplateListResponse listResponse = ResourceTemplates.listAll(connection);

        List<TemplateItem> results = listResponse.getResults();

        // Print out the templates
        System.out.println("Found " + results.size() + " tempaltes");
        for (TemplateItem item : results) {
            System.out.println("\tName \"" + item.getName() + "\" updated " + item.getLastUpdateTime());
            TemplateRetrieveResponse templateResponse = ResourceTemplates.retrieve(connection, item.getId(), !item.getPublished());
            System.out.println("Content: " + templateResponse.getResponseBody());

        }
    }
}
