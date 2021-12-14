
package com.sparkpost.error.samples;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import com.sparkpost.Client;
import com.sparkpost.exception.SparkPostErrorServerResponseException;
import com.sparkpost.exception.SparkPostException;
import com.sparkpost.model.AddressAttributes;
import com.sparkpost.model.RecipientAttributes;
import com.sparkpost.model.TemplateContentAttributes;
import com.sparkpost.model.TransmissionWithRecipientArray;
import com.sparkpost.model.responses.ServerErrorResponse;
import com.sparkpost.resources.ResourceTransmissions;
import com.sparkpost.sdk.samples.helpers.SparkPostBaseApp;
import com.sparkpost.transport.IRestConnection;
import com.sparkpost.transport.RestConnection;

public class SendEmailErrorSample extends SparkPostBaseApp {

    static final Logger logger = LogManager.getLogger(SendEmailErrorSample.class);

    private Client client;

    public static void main(String[] args) throws SparkPostException, IOException {
        Configurator.setRootLevel(Level.DEBUG);

        SendEmailErrorSample sample = new SendEmailErrorSample();
        sample.runApp();
    }

    private void runApp() throws SparkPostException, IOException {
        this.client = this.newConfiguredClient();

        // Loads an email to send from the file system
        String fromAddress = getFromAddress();
        String[] recipients = getTestRecipients();

        sendEmail(fromAddress, recipients);

    }

    private void sendEmail(String from, String[] recipients) throws SparkPostException {
        TransmissionWithRecipientArray transmission = new TransmissionWithRecipientArray();

        // Populate Recipients
        List<RecipientAttributes> recipientArray = new ArrayList<RecipientAttributes>();
        for (String recipient : recipients) {
            RecipientAttributes recipientAttribs = new RecipientAttributes();
            recipientAttribs.setAddress(new AddressAttributes(recipient));
            recipientArray.add(recipientAttribs);
        }
        transmission.setRecipientArray(recipientArray);

        // Populate Substitution Data
        Map<String, Object> substitutionData = new HashMap<String, Object>();
        substitutionData.put("yourContent", "You can add substitution data too.");
        transmission.setSubstitutionData(substitutionData);

        // Populate Email Body
        TemplateContentAttributes contentAttributes = new TemplateContentAttributes();
        contentAttributes.setFrom(new AddressAttributes(from));
        contentAttributes.setSubject("Your subject content here. {{yourContent}}");
        contentAttributes.setText("Your Text content here.  {{yourContent}}");
        contentAttributes.setHtml("<p>Your <b>HTML</b> content here.  {{yourContent}}</p>");

        // It is illegal to set a templateID with content
        contentAttributes.setTemplateId("This_Is_Bad_When_There_Is_Inline_Content");

        transmission.setContentAttributes(contentAttributes);

        // Send the Email
        IRestConnection connection = new RestConnection(this.client, getEndPoint());

        try {

            ResourceTransmissions.create(connection, 0, transmission);

            throw new IllegalStateException("Error: Expected SparkPostErrorServerResponseException");
        } catch (SparkPostErrorServerResponseException e) {
            System.out.println("GOOD: we got the expected SparkPostErrorServerResponseException");
            System.out.println(e.getMessage());

            if (e.getServerErrorResponses() != null) {
                for (ServerErrorResponse error : e.getServerErrorResponses().getErrors()) {
                    System.out.println("ERROR: " + error);
                }
            }
        }

    }

}
