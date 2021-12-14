
package com.sparkpost.samples;

import java.io.IOException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import com.sparkpost.Client;
import com.sparkpost.exception.SparkPostException;
import com.sparkpost.model.responses.Response;
import com.sparkpost.resources.ResourceSendingDomains;
import com.sparkpost.sdk.samples.helpers.SparkPostBaseApp;
import com.sparkpost.transport.IRestConnection;
import com.sparkpost.transport.RestConnection;

/**
 * Lists all Sending Domains
 */
public class ListAllSendingDomains extends SparkPostBaseApp {

    static final Logger logger = LogManager.getLogger(CreateTemplateSimple.class);

    private Client client;

    public static void main(String[] args) throws SparkPostException, IOException {
        Configurator.setRootLevel(Level.DEBUG);

        ListAllSendingDomains sample = new ListAllSendingDomains();
        sample.runApp();
    }

    private void runApp() throws SparkPostException, IOException {
        client = this.newConfiguredClient();
        IRestConnection connection = new RestConnection(client, getEndPoint());
        Response list = ResourceSendingDomains.list(connection);
        System.out.println("Sending Domains: " + list);

        //		List<TemplateItem> results = listResponse.getResults();
        //		
        //		// Print out the templates
        //		System.out.println("Found " + results.size() + " tempaltes");
        //		for (TemplateItem item : results) {
        //			System.out.println("\tName \"" + item.getName() + "\" updated " + item.getLastUpdateTime());
        //		}
    }

}
