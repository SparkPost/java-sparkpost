
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

public class SendEmailWithSubstitutionSample extends SparkPostBaseApp {

    static final Logger logger = LogManager.getLogger(SendEmailWithSubstitutionSample.class);

    private Client client;

    public static void main(String[] args) throws SparkPostException, IOException {
        Configurator.setRootLevel(Level.DEBUG);

        SendEmailWithSubstitutionSample sample = new SendEmailWithSubstitutionSample();
        sample.runApp();
    }

    private void runApp() throws SparkPostException, IOException {
        this.client = this.newConfiguredClient();

        // Loads an email to send from the file system
        String template = getTemplate("sample_sp_substitution_email.eml");
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

            Map<String, Object> substitution = new HashMap<String, Object>();
            recipientAttribs.setSubstitutionData(substitution);
            substitution.put("my_string", "This is a string value");

            // Demonstrate dynamic subject per recipient. The subject contains "{{subject}}"
            substitution.put("subject", "A dynamic subject for " + "\"" + recipient + "\"");

            /*
             * Demonstrate array of substitution data that is used in plain and HTML parts
             * Email body will contain:
             * {{each row_array}}
             * {{loop_var.row}},\t {{loop_var.value}}
             * {{end}}
             */
            List<Map<String, String>> myArray = new ArrayList<Map<String, String>>();
            for (int i = 0; i < 10; i++) {
                Map<String, String> myMap = new HashMap<String, String>();
                myMap.put("row", "row " + (i + 1));
                myMap.put("value", "Value " + (i + 1));
                myArray.add(myMap);
            }
            substitution.put("row_array", myArray);
        }

        transmission.setRecipientArray(recipientArray);

        transmission.setReturnPath(from);

        // Populate Substitution Data
        Map<String, Object> substitutionData = new HashMap<String, Object>();
        substitutionData.put("from", from);
        transmission.setSubstitutionData(substitutionData);

        // Populate Email Body
        TemplateContentAttributes contentAttributes = new TemplateContentAttributes();
        contentAttributes.setEmailRFC822(email);
        transmission.setContentAttributes(contentAttributes);

        // Send the Email
        IRestConnection connection = new RestConnection(this.client, getEndPoint());
        Response response = ResourceTransmissions.create(connection, 0, transmission);

        logger.debug("Transmission Response: " + response);
    }

}
