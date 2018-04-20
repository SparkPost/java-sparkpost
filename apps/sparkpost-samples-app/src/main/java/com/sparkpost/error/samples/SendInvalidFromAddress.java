
package com.sparkpost.error.samples;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

public class SendInvalidFromAddress extends SparkPostBaseApp {

    static final Logger logger = LoggerFactory.getLogger(SendInvalidFromAddress.class);

    private Client client;

    public static void main(String[] args) throws SparkPostException, IOException {
        SendInvalidFromAddress sample = new SendInvalidFromAddress();
        sample.runApp();
    }

    private void runApp() throws SparkPostException, IOException {
        this.client = this.newConfiguredClient();

        // Loads an email to send from the file system
        String fromAddress = "invalid@sparkpost.com";
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
        contentAttributes.setSubject("☰ Your subject content here. {{yourContent}}");
        contentAttributes.setText("Your Text content here.  {{yourContent}}");
        contentAttributes.setHtml("<p>Your <b>HTML</b> content here.  {{yourContent}}</p>");
        transmission.setContentAttributes(contentAttributes);

        transmission.setContentAttributes(contentAttributes);

        // Send the Email
        try {
            IRestConnection connection = new RestConnection(this.client, getEndPoint());
            ResourceTransmissions.create(connection, 0, transmission);

            throw new IllegalStateException("Error: Expected Exception for invalid to address");
        } catch (SparkPostErrorServerResponseException e) {
            System.out.println("GOOD: we got the expected exception");
            System.out.println(e.getMessage());

            if (e.getServerErrorResponses() != null) {
                for (ServerErrorResponse error : e.getServerErrorResponses().getErrors()) {
                    System.out.println("ERROR: " + error);
                }
            }
        }
    }

}
