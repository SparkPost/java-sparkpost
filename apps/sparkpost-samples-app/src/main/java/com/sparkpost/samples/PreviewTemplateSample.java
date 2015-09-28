package com.sparkpost.samples;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.sparkpost.Client;
import com.sparkpost.exception.SparkPostException;
import com.sparkpost.model.AddressAttributes;
import com.sparkpost.model.TemplateAttributes;
import com.sparkpost.model.TemplateContentAttributes;
import com.sparkpost.model.TemplateSubstitutionData;
import com.sparkpost.model.responses.TemplateCreateResponse;
import com.sparkpost.model.responses.TemplatePreviewResponse;
import com.sparkpost.resources.ResourceTemplates;
import com.sparkpost.sdk.samples.helpers.SparkPostBaseApp;
import com.sparkpost.transport.RestConnection;


/**
 * This sample uploads a template to SparkPost.com and then requests a preview of the template
 *
 */
public class PreviewTemplateSample  extends SparkPostBaseApp {
	private static final Logger logger = Logger.getLogger(CreateTemplateSimple.class);

	private Client client;
	
	public static void main(String[] args) throws SparkPostException, IOException {
		Logger.getRootLogger().setLevel(Level.DEBUG);

		PreviewTemplateSample sample = new PreviewTemplateSample();
		sample.runApp();
		
	}
	
	private void runApp() throws SparkPostException, IOException {
		client = this.newConfiguredClient();
		String template = this.getTemplate("richContent.html");
		createTemplate(template, SAMPLE_TEMPLATE_NAME);
	}
	
	/**
	 * Demonstrates how to store an email template in SparkPost
	 * 
	 * @throws SparkPostException
	 */
	public void createTemplate(String html, String name) throws SparkPostException {
		if (logger.isDebugEnabled()) {
			logger.debug("createTemplate()");
		}
		TemplateAttributes template = new TemplateAttributes();
		template.setName(name);

		TemplateContentAttributes content = new TemplateContentAttributes();
		
		content.setSubject("Template Test");
		content.setFrom(new AddressAttributes(client.getFromEmail(), "me", null));
		content.setHtml(html);
		template.setContent(content);
		
		RestConnection connection = new RestConnection(client, getEndPoint());
		try {
			TemplateCreateResponse response = ResourceTemplates.create(connection, template);
			String templateId = response.getResults().getId();
			
			logger.debug("Create Template Response: " + response);
			
			showTemplatePreview(templateId, connection);
		} catch (SparkPostException e) {
			logger.debug("Create Template Failed: ");
			throw e;
		}
	}
	
	private void showTemplatePreview(String templateId, RestConnection connection) throws SparkPostException {
		TemplateSubstitutionData substitutionData = new TemplateSubstitutionData();
		
		TemplatePreviewResponse response = ResourceTemplates.preview(connection, templateId, true, substitutionData);
		String path = "/tmp/preview.html";
		saveToFile(response.getResults().getHtml(), path);
		
		try {
			openWebpage(new URI("file://" + path));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	private void saveToFile(String html, String path) {
		PrintWriter fileWriter = null;;
		try {
			File file = new File(path);
			fileWriter = new PrintWriter(file, "UTF-8");
			fileWriter.write(html);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fileWriter != null) {
				fileWriter.flush();
				fileWriter.close();
			}
		}
	}
	
	public static void openWebpage(URI uri) {
	    Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
	    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
	        try {
	            desktop.browse(uri);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}
}
