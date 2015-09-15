package com.sparkpost;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.sparkpost.sdk.Client;
import com.sparkpost.sdk.dto.AddressAttributes;
import com.sparkpost.sdk.dto.Response;
import com.sparkpost.sdk.dto.TemplateAttributes;
import com.sparkpost.sdk.dto.TemplateContentAttributes;
import com.sparkpost.sdk.exception.SparkpostSdkException;
import com.sparkpost.sdk.resources.ResourceTemplates;
import com.sparkpost.sdk.transport.RestConnection;

public class CreateAndSendTemplate extends SparkPostBaseApp {

	static final Logger logger = Logger.getLogger(CreateAndSendTemplate.class);

	private Client client;
	
	public static void main(String[] args) throws SparkpostSdkException, IOException {
		Logger.getRootLogger().setLevel(Level.DEBUG);

		CreateAndSendTemplate templateTest = new CreateAndSendTemplate();
		templateTest.runApp();
		
	}
	
	private void runApp() throws SparkpostSdkException, IOException {
		client = this.newConfiguredClient();
		createTemplate();
	}
	
	public void createTemplate() throws SparkpostSdkException {
		if (logger.isDebugEnabled()) {
			logger.debug("createTemplate()");
		}
		RestConnection conn = null;
		TemplateAttributes tpl = new TemplateAttributes();

		tpl.setName("_TMP_TEMPLATE_TEST");
		tpl.setContent(new TemplateContentAttributes());
		tpl.getContent().setFrom(new AddressAttributes(client.getFromEmail(), "me", null));
		tpl.getContent().setHtml("Hello!");
		tpl.getContent().setSubject("Template Test");
		conn = new RestConnection(client);
		Response response = ResourceTemplates.create(conn, tpl);
		
		if (logger.isDebugEnabled()) {
			logger.debug("Create Template Response: " + response);
		}
		
	}
	
	public void sendEmail(String templateName, List<String> recipients) {
		if (logger.isDebugEnabled()) {
			logger.debug("sendEmail(...)");
		}
		
		
		
	}
	
	public void deleteTemplate(String tempalteId) {
		
	}
	
	
}
