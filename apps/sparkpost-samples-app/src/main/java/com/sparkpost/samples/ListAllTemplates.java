package com.sparkpost.samples;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.sparkpost.Client;
import com.sparkpost.exception.SparkPostException;
import com.sparkpost.model.TemplateItem;
import com.sparkpost.model.TemplateListResponse;
import com.sparkpost.resources.ResourceTemplates;
import com.sparkpost.sdk.samples.helpers.SparkPostBaseApp;
import com.sparkpost.transport.RestConnection;

/**
 * List all templates stored in SparkPost
 *
 */
public class ListAllTemplates  extends SparkPostBaseApp {

	static final Logger logger = Logger.getLogger(CreateAndSendTemplate.class);

	private Client client;
	
	public static void main(String[] args) throws SparkPostException, IOException {
		Logger.getRootLogger().setLevel(Level.DEBUG);

		ListAllTemplates app = new ListAllTemplates();
		app.runApp();
		
	}
	
	private void runApp() throws SparkPostException, IOException {
		client = this.newConfiguredClient();
		RestConnection connection = new RestConnection(client);
		TemplateListResponse listResponse = ResourceTemplates.listAll(connection);
		
		List<TemplateItem> results = listResponse.getResults();
		System.out.println("Found " + results.size() + " tempaltes");
		for (TemplateItem item : results) {
			System.out.println("\tName \"" + item.getName() + "\" updated " + item.getLastUpdateTime());
		}
	}
}
