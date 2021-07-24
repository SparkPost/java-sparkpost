
package com.sparkpost.samples;

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
import com.sparkpost.exception.SparkPostException;
import com.sparkpost.model.AddressAttributes;
import com.sparkpost.model.RecipientAttributes;
import com.sparkpost.model.TemplateContentAttributes;
import com.sparkpost.model.TransmissionWithRecipientArray;
import com.sparkpost.model.responses.Response;
import com.sparkpost.resources.ResourceTransmissions;
import com.sparkpost.sdk.samples.helpers.SparkPostBaseApp;
import com.sparkpost.transport.IRestConnection;
import com.sparkpost.transport.RestConnection;

public class SendEmailCCSample extends SparkPostBaseApp {

    static final Logger logger = LogManager.getLogger(CreateTemplateSimple.class);

    private Client client;

    public static void main(String[] args) throws SparkPostException, IOException {
        Configurator.setRootLevel(Level.DEBUG);

        SendEmailCCSample sample = new SendEmailCCSample();
        sample.runApp();
    }

    private void runApp() throws SparkPostException, IOException {
        this.client = this.newConfiguredClient();

        // Loads an email to send from the file system
        String fromAddress = getFromAddress();
        String[] toRecipients = getTestRecipients();
        String[] ccRecipients = getCCRecipients();

        sendEmail(fromAddress, toRecipients, ccRecipients);
    }

    private void sendEmail(String from, String[] toRecipients, String[] ccRecipients) throws SparkPostException {
        TransmissionWithRecipientArray transmission = new TransmissionWithRecipientArray();

        // Populate Recipients
        List<RecipientAttributes> recipientArray = new ArrayList<RecipientAttributes>();

        // Primary 'To' recipients
        for (String to : toRecipients) {
            RecipientAttributes recipientAttribs = new RecipientAttributes();
            AddressAttributes addressAttribs = new AddressAttributes(to);
            recipientAttribs.setAddress(addressAttribs);
            recipientArray.add(recipientAttribs);
        }

        // Secondary 'CC' recipients with the primary recipients listed in 'To:' header
        String toHeader = stringArrayToCSV(toRecipients);
        for (String cc : ccRecipients) {
            RecipientAttributes recipientAttribs = new RecipientAttributes();
            AddressAttributes addressAttribs = new AddressAttributes(cc);
            addressAttribs.setHeaderTo(toHeader);
            recipientAttribs.setAddress(addressAttribs);
            recipientArray.add(recipientAttribs);
        }

        transmission.setRecipientArray(recipientArray);

        // Populate Email Body
        TemplateContentAttributes contentAttributes = new TemplateContentAttributes();
        contentAttributes.setFrom(new AddressAttributes(from));
        contentAttributes.setSubject("CC Example");
        contentAttributes.setText("This message was sent To 1 recipient and some other recipients were CC'd");
        contentAttributes.setHtml("<p>This message was sent To 1 recipient and some other recipients were CC'd</p>");

        // List the CC recipients in the CC header
        Map<String, String> headers = new HashMap<String, String>();
        String ccHeader = stringArrayToCSV(ccRecipients);
        headers.put("CC", ccHeader);

        contentAttributes.setHeaders(headers);
        transmission.setContentAttributes(contentAttributes);

        // Send the Email
        IRestConnection connection = new RestConnection(this.client, getEndPoint());
        Response response = ResourceTransmissions.create(connection, 0, transmission);

        logger.debug("Transmission Response: " + response);
    }
}

