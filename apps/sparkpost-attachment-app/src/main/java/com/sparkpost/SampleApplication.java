package com.sparkpost;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sparkpost.sdk.Client;
import com.sparkpost.sdk.ResourceTemplates;
import com.sparkpost.sdk.ResourceTransmissions;
import com.sparkpost.sdk.RestConnection;
import com.sparkpost.sdk.SparkpostSdkException;
import com.sparkpost.sdk.dto.AddressAttributes;
import com.sparkpost.sdk.dto.RecipientAttributes;
import com.sparkpost.sdk.dto.Response;
import com.sparkpost.sdk.dto.StoredTemplate;
import com.sparkpost.sdk.dto.TemplateAttributes;
import com.sparkpost.sdk.dto.TemplateContentAttributes;
import com.sparkpost.sdk.dto.TransmissionWithRecipientArray;

import lombok.Data;

/**
 * This sample application creates a template, stores it at the server, and then
 * creates a transmission using the stored template.
 *
 */
public class SampleApplication {

	private static Gson gson;

	public static void main(String[] args) throws SparkpostSdkException {
		// Set to DEBUG so we can see what the SparkPost SDK is doing:
		Logger.getRootLogger().setLevel(Level.DEBUG);

		// Initialize our JSON parser. We'll use it to parse responses from
		// the SparkPost server.
		GsonBuilder gsonBuilder = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		gson = gsonBuilder.setPrettyPrinting().create();

		System.out.println("*** SparkPost API Sample application ***");

		// ---------------------------------------------------
		// Create a client object:
		// ---------------------------------------------------
		Client client = new Client(System.getenv("SPARKPOST_API_KEY"));
		if (StringUtils.isEmpty(client.getAuthKey())) {
			System.err.println("SPARKPOST_API_KEY must be defined as an environment variable.");
			System.err.println("Visit https://app.sparkpost.com/account/credentials to create your API Key.");
			return;
		}

		client.setFromEmail(System.getenv("SPARKPOST_SENDER_EMAIL"));
		if (StringUtils.isEmpty(client.getFromEmail())) {
			System.err.println("SPARKPOST_SENDER_EMAIL must be defined as an environment variable.");
			return;
		}

		// ---------------------------------------------------
		// Create a connection object:
		// ---------------------------------------------------
		RestConnection conn = new RestConnection(client);

		// ---------------------------------------------------
		// Create a template and store it at the server:
		// ---------------------------------------------------
		String templateId = createTemplate(client, conn);
		if (templateId == null) {
			return;
		}

		// ---------------------------------------------------
		// Create a transmission using the stored template:
		// ---------------------------------------------------
		createTransmission(client, conn, templateId);

	}

	// Data Transfer Object for parsing a
	// 200 Response to a Template Create Request.
	// For use by gson.fromJson() in createTemplate()
	private class Response200_TemplateCreate {

		@Data
		class _Results {

			String id;
		}

		_Results results;
	}

	// Create a template and store it at the server:
	private static String createTemplate(Client client, RestConnection conn) throws SparkpostSdkException {
		
		TemplateAttributes tpl = new TemplateAttributes();
		tpl.setName("_TMP_TEMPLATE_TEST");
		
		TemplateContentAttributes templateContent = new TemplateContentAttributes();
		AddressAttributes fromAddress = new AddressAttributes(client.getFromEmail(), "Testing", null);
		templateContent.setFrom(fromAddress);
		templateContent.setHtml("Hello!");
		templateContent.setSubject("Template Test");
		tpl.setContent(templateContent);
		
		Response response = ResourceTemplates.create(conn, tpl);
		if (response.getResponseCode() != 200) {
			System.err.println("Could not create template.");
			return null;
		}

		String json = response.getResponseBody();
		Response200_TemplateCreate dto;
		dto = gson.fromJson(json, Response200_TemplateCreate.class);
		System.out.println("Server says template was created with ID: " + dto.results.getId());

		return dto.results.id;
	}

	// Data Transfer Object for parsing a
	// 200 Response to a Transmission Create Request.
	// For use by gson.fromJson() in createTransmission()
	private class Response200_TransmissionCreate {

		@Data
		class _Results {

			private int total_rejected_recipients;
			private int total_accepted_recipients;
			private String id;
		}

		_Results results;
	}

	// Create a transmission using the stored template:
	private static String createTransmission(Client client, RestConnection conn, String templateId)
			throws SparkpostSdkException {

		TransmissionWithRecipientArray trans = new TransmissionWithRecipientArray();
		trans.setCampaignId("sample_app_trans_test");
		trans.setReturnPath(client.getFromEmail());

		List<RecipientAttributes> recipArray = new ArrayList<RecipientAttributes>();
		trans.setRecipientArray(recipArray);

		RecipientAttributes recipient = new RecipientAttributes();
		recipient.setReturnPath(client.getFromEmail());
		recipient.setAddress(new AddressAttributes(client.getFromEmail()));
		recipArray.add(recipient);
		
		TemplateContentAttributes template = new TemplateContentAttributes();
		template.setTemplateId(templateId);
		template.setUseDraftTemplate(true);
		trans.setContentAttributes(template);

		Response response = ResourceTransmissions.create(conn, null, trans);
		if (response.getResponseCode() != 200) {
			System.err.println("Could not create transmission.");
			return null;
		}
		
		String json = response.getResponseBody();
		Response200_TransmissionCreate dto;
		dto = gson.fromJson(json, Response200_TransmissionCreate.class);
		System.out.println("Server says transmission was created with ID: " + dto.results.id
				+ "\nTotal rejected recipients: " + Integer.toString(dto.results.total_rejected_recipients)
				+ "\nTotal accepted recipients: " + Integer.toString(dto.results.total_accepted_recipients));
		return dto.results.id;

	}
}
