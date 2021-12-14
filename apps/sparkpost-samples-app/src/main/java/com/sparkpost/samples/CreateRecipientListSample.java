
package com.sparkpost.samples;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import com.sparkpost.Client;
import com.sparkpost.exception.SparkPostException;
import com.sparkpost.model.AddressAttributes;
import com.sparkpost.model.RecipientAttributes;
import com.sparkpost.model.RecipientList;
import com.sparkpost.model.responses.Response;
import com.sparkpost.resources.ResourceRecipientLists;
import com.sparkpost.sdk.samples.helpers.SparkPostBaseApp;
import com.sparkpost.transport.IRestConnection;
import com.sparkpost.transport.RestConnection;

/**
 * A recipient list is a collection of recipients that can be used in a
 * transmission. The Recipient List API provides the means to manage recipient
 * lists. When creating a new transmission using the Transmissions API, the
 * recipients may be submitted "inline" as part of the transmission data, or a
 * stored recipient list id attribute can be specified.The Recipient List API
 * operates on lists as a whole and does not currently support management of
 * individual recipients.
 * See:
 * https://www.sparkpost.com/api#/reference/recipient-lists/create-a-recipient-
 * list
 */
public class CreateRecipientListSample extends SparkPostBaseApp {

    static final Logger logger = LogManager.getLogger(CreateTemplateSimple.class);

    private Client client;

    public static void main(String[] args) throws SparkPostException, IOException {
        Configurator.setRootLevel(Level.DEBUG);

        CreateRecipientListSample app = new CreateRecipientListSample();
        app.runApp();
    }

    private void runApp() throws SparkPostException, IOException {

        RecipientList recipientList = createRecipientList();

        this.client = this.newConfiguredClient();
        IRestConnection connection = new RestConnection(this.client, getEndPoint());

        Response response = ResourceRecipientLists.create(connection, 0, recipientList);

        if (logger.isDebugEnabled()) {
            logger.debug("Create Recipient List Response: " + response);
        }

    }

    private RecipientList createRecipientList() {
        RecipientList recipientList = new RecipientList();

        recipientList.setName("100 List");
        recipientList.setDescription("Demonstration of storing a recipient list on the server");

        List<RecipientAttributes> recipients = new ArrayList<RecipientAttributes>();
        // Populate our recipient list attributes
        for (String recipient : getTestRecipients()) {
            RecipientAttributes attributes = new RecipientAttributes();
            attributes.setAddress(new AddressAttributes(recipient));
            recipients.add(attributes);
        }

        recipientList.setRecipients(recipients);

        return recipientList;
    }
}
