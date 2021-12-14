
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

public class SendEmailTemplateSample extends SparkPostBaseApp {

    static final Logger logger = LogManager.getLogger(CreateTemplateSimple.class);

    private Client client;

    public static void main(String[] args) throws SparkPostException, IOException {
        Configurator.setRootLevel(Level.DEBUG);

        SendEmailTemplateSample sample = new SendEmailTemplateSample();
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

        // You can use Jackson, GSON or whatever your standard JSON decoding library
        // is to build this structure.
        List<Map<String, String>> offers = new ArrayList<Map<String, String>>();
        for (int i = 0; i < 2; i++) {

            Map<String, String> offer = new HashMap<String, String>();
            offer.put("description", "description value " + i);
            offer.put("discount", "discount " + i);
            offer.put("image", "image " + i);
            offer.put("image_announcer", "image_announcer " + i);
            offer.put("alt_title", "alt_title " + i);
            offer.put("tracking", "tracking " + i);
            offer.put("name", "name " + i);
            offer.put("id", "id " + i);
            offer.put("announcer_paid", "announcer_paid " + i);
            offer.put("announcer_image", "announcer_image " + i);
            offer.put("announcer_alt_title", "announcer_alt_title " + i);

            offers.add(offer);
        }

        substitutionData.put("offers", offers);

        // Populate Email Body
        TemplateContentAttributes contentAttributes = new TemplateContentAttributes();
        contentAttributes.setFrom(new AddressAttributes(from));
        contentAttributes.setSubject("☰ Your subject content here. {{yourContent}}");
        contentAttributes.setText("You could do it for text too. See https://www.sparkpost.com/blog/advanced-email-templates/ for an example");
        contentAttributes.setHtml(
                "<b>Your Data:</b><br>\n"
                        + "<table border='1'>\n"
                        + "    <tr>\n"
                        + "        <th>description</th>\n"
                        + "        <th>discount</th>\n"
                        + "        <th>image</th>\n"
                        + "        <th>image_announcer</th>\n"
                        + "        <th>alt_title</th>\n"
                        + "        <th>tracking</th>\n"
                        + "        <th>name</th>\n"
                        + "        <th>id</th>\n"
                        + "        <th>announcer_paid</th>\n"
                        + "        <th>announcer_image</th>\n"
                        + "        <th>announcer_alt_title</th>\n"
                        + "    </tr>\n"
                        + "    {{each offers}}    \n"
                        + "        <tr>\n"
                        + "            <td> {{{offers.description}}} </td>\n"
                        + "            <td> {{{offers.discount}}} </td>\n"
                        + "            <td> {{{offers.image}}} </td>\n"
                        + "            <td> {{{offers.image_announcer}}} </td>\n"
                        + "            <td> {{{offers.alt_title}}} </td>\n"
                        + "            <td> {{{offers.tracking}}} </td>\n"
                        + "            <td> {{{offers.name}}} </td>\n"
                        + "            <td> {{{offers.id}}} </td>\n"
                        + "            <td> {{{offers.announcer_paid}}} </td>\n"
                        + "            <td> {{{offers.announcer_image}}} </td>\n"
                        + "            <td> {{{offers.announcer_alt_title}}} </td>\n"
                        + "        </tr>\n"
                        + "    {{ end }} \n"
                        + "</table>\n\n");
        transmission.setContentAttributes(contentAttributes);

        transmission.setContentAttributes(contentAttributes);

        // Send the Email
        IRestConnection connection = new RestConnection(this.client, getEndPoint());
        Response response = ResourceTransmissions.create(connection, 0, transmission);

        logger.debug("Transmission Response: " + response);
    }

}
