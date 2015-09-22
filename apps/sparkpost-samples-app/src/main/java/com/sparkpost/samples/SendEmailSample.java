package com.sparkpost.samples;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.sparkpost.Client;
import com.sparkpost.exception.SparkPostException;
import com.sparkpost.model.AddressAttributes;
import com.sparkpost.model.RecipientAttributes;
import com.sparkpost.model.Response;
import com.sparkpost.model.TemplateContentAttributes;
import com.sparkpost.model.TransmissionWithRecipientArray;
import com.sparkpost.resources.ResourceTransmissions;
import com.sparkpost.sdk.samples.helpers.SparkPostBaseApp;
import com.sparkpost.transport.RestConnection;

public class SendEmailSample extends SparkPostBaseApp {

	static final Logger logger = Logger.getLogger(CreateAndSendTemplate.class);

	private Client client;
	
	public static void main(String[] args) throws SparkPostException, IOException {
		Logger.getRootLogger().setLevel(Level.DEBUG);

		SendEmailSample app = new SendEmailSample();
		app.runApp();
	}
	
	private void runApp() throws SparkPostException, IOException {
		client = this.newConfiguredClient();
		
		// Loads an email to send from the file system
		String template = getTemplate("sample_sp_email.eml");
		String fromAddress = getFromAddress();
		String[] recipients = getTestRecipients();
		
		sendEmail(fromAddress, recipients, template);
		
	}
	
	private void sendEmail(String from, String[] recipients, String email) throws SparkPostException {
		TransmissionWithRecipientArray transmission = new TransmissionWithRecipientArray();
		
		// Populate Recipients
		List<RecipientAttributes> recipientArray = new ArrayList<RecipientAttributes>();
		for (String recipient : recipients) {
			RecipientAttributes recipientAttribs = new RecipientAttributes();
			recipientAttribs.setAddress(new AddressAttributes(recipient));
			recipientArray.add(recipientAttribs);
		}
		transmission.setRecipientArray(recipientArray);
		
		transmission.setReturnPath(from);
		
		// Populate Substitution Data
		Map<String, String> substitutionData = new HashMap<String, String>();
		substitutionData.put("from", from);
		transmission.setSubstitutionData(substitutionData);
		
		// Populate Email Body
		TemplateContentAttributes contentAttributes = new TemplateContentAttributes();
		contentAttributes.setEmailRFC822(email);
		transmission.setContentAttributes(contentAttributes);
		
		// Send the Email
		RestConnection connection = new RestConnection(client, getEndPoint());
		Response response = ResourceTransmissions.create(connection, 0, transmission);
		
		logger.debug("Transmission Response: " + response);
	}

}
