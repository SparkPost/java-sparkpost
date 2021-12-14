package com.sparkpost.sample;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;

import com.sparkpost.Client;
import com.sparkpost.exception.SparkPostException;
import com.sparkpost.model.AddressAttributes;
import com.sparkpost.model.RecipientAttributes;
import com.sparkpost.model.TemplateContentAttributes;
import com.sparkpost.model.TransmissionWithRecipientArray;
import com.sparkpost.model.responses.Response;
import com.sparkpost.resources.ResourceTransmissions;
import com.sparkpost.sample.helpers.SparkPostBaseApp;
import com.sparkpost.transport.IRestConnection;
import com.sparkpost.transport.RestConnection;

/**
 * This demonstration of using JavaMail mime message with the SparkPosts REST API
 */
public class App extends SparkPostBaseApp {

	public static void main(String[] args) throws Exception {
		Configurator.setRootLevel(Level.DEBUG);
		
		App app = new App();
		app.runApp();
	}

	private void runApp() throws Exception {

		Message message = createMultipartMessage();

		// Convert JavaMail message into a string for transmission
		String rfc822Content = getMessageAsString(message);
		
		// Add in a TO and a From field that will be populated from SparkPost substitution data
		rfc822Content = "To: {{address.email}}\r\nFrom: {{from}}\r\n" + rfc822Content;

		// Loads an email to send from the file system
		String fromAddress = getFromAddress();
		String[] recipients = getTestRecipients();

		sendEmail(fromAddress, recipients, rfc822Content);

	}

	private void sendEmail(String from, String[] recipients, String email) throws SparkPostException, IOException {
		Client sparkpostClient = newConfiguredClient();

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
		Map<String, Object> substitutionData = new HashMap<String, Object>();
		substitutionData.put("from", from);
		
		// SparkPost will set fields in HTML and/or Plain parts with the value here
		// See: https://developers.sparkpost.com/api/#/introduction/substitutions-reference
		substitutionData.put("name", "Your Name Here");
		
		transmission.setSubstitutionData(substitutionData);

		// Populate Email Body
		TemplateContentAttributes contentAttributes = new TemplateContentAttributes();
		contentAttributes.setEmailRFC822(email);
		transmission.setContentAttributes(contentAttributes);

		// Send the Email
		IRestConnection connection = new RestConnection(sparkpostClient, getEndPoint());

		Response response = ResourceTransmissions.create(connection, 0, transmission);
		if (response.getResponseCode() == 200) {
			// Message successfully sent
			System.out.println("Transmission Response: " + response);
		} else {
			// An error occurred
			System.err.println("TRANSMISSION ERROR: " + response);
		}
	}

	/**
	 * Builds an email with a text, HTML, and attachment part
	 * 
	 * @return a JavaMail message
	 * @throws MessagingException
	 */
	private Message createMultipartMessage() throws MessagingException {
		Properties props = new Properties();
		// This is not used but we have to set it for JavaMail to create the
		// message
		props.put("mail.smtp.host", "none");

		Session session = Session.getDefaultInstance(props, null);
		Message message = new MimeMessage(session);

		message.setSubject("A multipart mime message demo");
		
		Multipart multiPart = new MimeMultipart("alternative");

		// Create at text part
		MimeBodyPart textPart = new MimeBodyPart();
		textPart.setText("{{name}},\r\nplain text content", "utf-8");

		// Build HTML part of email
		MimeBodyPart htmlPart = new MimeBodyPart();
		htmlPart.setContent("<b>{{name}},<br><br>Our HTML content</b>", "text/html; charset=utf-8");

		// Put all the parts together
		multiPart.addBodyPart(textPart);
		multiPart.addBodyPart(htmlPart);
		message.setContent(multiPart);
		
		// Add an attachment to email
		MimeBodyPart attachmentPart = new MimeBodyPart();

		String filename = "java_SparkPost_background.pdf";
		DataSource source = new FileDataSource(filename);
		attachmentPart.setDataHandler(new DataHandler(source));
		attachmentPart.setFileName(filename);
		multiPart.addBodyPart(attachmentPart);
		
		return message;
	}

	/**
	 * Turn a JavaMail message into RFC822 content
	 * 
	 * @param msg
	 *            the message that will be converted
	 * @return RFC822 content
	 * @throws MessagingException
	 * @throws IOException
	 */
	private String getMessageAsString(Message msg) throws IOException, MessagingException {
		String content = "";
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			msg.writeTo(out);
			content = new String(out.toByteArray(), "UTF-8");
			return content;

		} catch (UnsupportedEncodingException e) {
			// This should never happen
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// Fail
		return null;
	}
}
