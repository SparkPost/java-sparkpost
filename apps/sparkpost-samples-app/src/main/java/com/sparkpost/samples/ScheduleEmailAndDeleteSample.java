
package com.sparkpost.samples;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.sparkpost.Client;
import com.sparkpost.exception.SparkPostException;
import com.sparkpost.model.AddressAttributes;
import com.sparkpost.model.OptionsAttributes;
import com.sparkpost.model.RecipientAttributes;
import com.sparkpost.model.TemplateContentAttributes;
import com.sparkpost.model.TransmissionWithRecipientArray;
import com.sparkpost.model.responses.Response;
import com.sparkpost.model.responses.TransmissionCreateResponse;
import com.sparkpost.model.responses.TransmissionResponseInfo;
import com.sparkpost.model.responses.TransmissionRetrieveResponseContainer;
import com.sparkpost.model.responses.TransmissionRetrieveResults;
import com.sparkpost.resources.ResourceTransmissions;
import com.sparkpost.sdk.samples.helpers.SparkPostBaseApp;
import com.sparkpost.transport.RestConnection;

public class ScheduleEmailAndDeleteSample extends SparkPostBaseApp {

    static final Logger logger = Logger.getLogger(CreateTemplateSimple.class);

    private Client client;

    private static final int FUTURE_OFFSET = (60 * 20) * 1000;// 20 minutes from now

    public static void main(String[] args) throws SparkPostException, IOException {
        Logger.getRootLogger().setLevel(Level.DEBUG);

        ScheduleEmailAndDeleteSample sample = new ScheduleEmailAndDeleteSample();
        sample.runApp();
    }

    private void runApp() throws SparkPostException, IOException {
        this.client = this.newConfiguredClient();

        // Loads an email to send from the file system
        String fromAddress = getFromAddress();
        String[] recipients = getTestRecipients();

        TransmissionCreateResponse response = sendEmail(fromAddress, recipients);

        String transmissionId = response.getResults().getId();
        logger.debug("Transmission ID: " + transmissionId);
        boolean found = queryTransmissionById(transmissionId);
        if (!found) {
            throw new IllegalStateException("Transmission should exist!!!");
        }

        deleteTransmission(transmissionId);

        found = queryTransmissionById(transmissionId);
        if (found) {
            throw new IllegalStateException("Transmission should not exist!!!");
        }
    }

    private void deleteTransmission(String transmissionId) throws SparkPostException {
        RestConnection connection = new RestConnection(this.client, getEndPoint());
        Response deleteResponse = ResourceTransmissions.delete(connection, transmissionId);
        logger.debug("Delete Response: " + deleteResponse);
    }

    private boolean queryTransmissionById(String transmissionId) throws SparkPostException {
        RestConnection connection = new RestConnection(this.client, getEndPoint());
        TransmissionRetrieveResults retrieveResults = ResourceTransmissions.retrieve(connection, transmissionId);

        TransmissionRetrieveResponseContainer results = retrieveResults.getResults();
        if (results == null) {
            logger.debug("Transmission [" + transmissionId + "] not found.");
            return false;
        }

        TransmissionResponseInfo transmissionInfo = results.getTransmission();
        if (transmissionInfo == null) {
            logger.debug("Transmission [" + transmissionId + "] not found..");
            return false;
        }

        logger.debug("Transmission Info: " + transmissionInfo);

        String state = transmissionInfo.getState();
        if (state.equalsIgnoreCase("canceled")) {
            return false;
        }

        logger.debug("Transmission State: " + state);
        return true;

    }

    private TransmissionCreateResponse sendEmail(String from, String[] recipients) throws SparkPostException {
        TransmissionWithRecipientArray transmission = new TransmissionWithRecipientArray();

        transmission.setCampaignId("delete_scheduled_test");

        // Populate Recipients
        List<RecipientAttributes> recipientArray = new ArrayList<RecipientAttributes>();
        for (String recipient : recipients) {
            RecipientAttributes recipientAttribs = new RecipientAttributes();
            recipientAttribs.setAddress(new AddressAttributes(recipient));
            recipientArray.add(recipientAttribs);
        }
        transmission.setRecipientArray(recipientArray);

        OptionsAttributes options = new OptionsAttributes();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        String result = formatter.format(new Date((System.currentTimeMillis() + FUTURE_OFFSET)));
        logger.debug("Schedule: " + result);
        options.setStartTime(result);
        transmission.setOptions(options);

        // Populate Substitution Data
        Map<String, Object> substitutionData = new HashMap<String, Object>();
        substitutionData.put("yourContent", "You can add substitution data too.");
        transmission.setSubstitutionData(substitutionData);

        // Populate Email Body
        TemplateContentAttributes contentAttributes = new TemplateContentAttributes();
        contentAttributes.setFrom(new AddressAttributes(from));
        contentAttributes.setSubject("☰☰ Test Delete scheduled transmission [" + System.currentTimeMillis() + "]");
        Date date = new Date();
        contentAttributes.setText("Test Delete scheduled transmission. " + date);
        contentAttributes.setHtml(
                "<p>Test Delete scheduled transmission<br><b>Now: "
                        + date
                        + "</b><br>Scheduled for: "
                        + result
                        + "<br>time: "
                        + System.currentTimeMillis()
                        + "<br></p>");
        transmission.setContentAttributes(contentAttributes);

        // Send the Email
        RestConnection connection = new RestConnection(this.client, getEndPoint());
        TransmissionCreateResponse response = ResourceTransmissions.create(connection, 0, transmission);

        logger.debug("Transmission Response: " + response);

        return response;
    }

}
