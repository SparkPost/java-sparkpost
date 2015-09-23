package com.sparkpost.samples;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.sparkpost.Client;
import com.sparkpost.exception.SparkPostException;
import com.sparkpost.model.AddressAttributes;
import com.sparkpost.model.Response;
import com.sparkpost.model.TemplateAttributes;
import com.sparkpost.model.TemplateContentAttributes;
import com.sparkpost.resources.ResourceTemplates;
import com.sparkpost.sdk.samples.helpers.SparkPostBaseApp;
import com.sparkpost.transport.RestConnection;

public class CreateAndSendTemplate extends SparkPostBaseApp {

	private static final Logger logger = Logger.getLogger(CreateAndSendTemplate.class);

	private Client client;
	
	public static void main(String[] args) throws SparkPostException, IOException {
		Logger.getRootLogger().setLevel(Level.DEBUG);

		CreateAndSendTemplate templateTest = new CreateAndSendTemplate();
		templateTest.runApp();
		
	}
	
	private void runApp() throws SparkPostException, IOException {
		client = this.newConfiguredClient();
		createTemplate();
	}
	
	/**
	 * Demonstrates how to store an email template in SparkPost
	 * 
	 * @throws SparkPostException
	 */
	public void createTemplate() throws SparkPostException {
		if (logger.isDebugEnabled()) {
			logger.debug("createTemplate()");
		}
		TemplateAttributes tpl = new TemplateAttributes();

		tpl.setName(SAMPLE_TEMPLATE_NAME);
		tpl.setContent(new TemplateContentAttributes());
		tpl.getContent().setFrom(new AddressAttributes(client.getFromEmail(), "me", null));
		tpl.getContent().setHtml("Hello!");
		tpl.getContent().setSubject("Template Test");
		RestConnection connection = new RestConnection(client, getEndPoint());
		Response response = ResourceTemplates.create(connection, tpl);
		
		if (logger.isDebugEnabled()) {
			logger.debug("Create Template Response: " + response);
		}
	}
	
	public void sendEmail(String templateName, List<String> recipients) {
		if (logger.isDebugEnabled()) {
			logger.debug("sendEmail(...)");
		}
		
	}
	
	
	
}
