
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
import com.sparkpost.model.AttachmentAttributes;
import com.sparkpost.model.InlineImageAttributes;
import com.sparkpost.model.RecipientAttributes;
import com.sparkpost.model.TemplateContentAttributes;
import com.sparkpost.model.TransmissionWithRecipientArray;
import com.sparkpost.model.responses.Response;
import com.sparkpost.resources.ResourceTransmissions;
import com.sparkpost.sdk.samples.helpers.SparkPostBaseApp;
import com.sparkpost.transport.IRestConnection;
import com.sparkpost.transport.RestConnection;

public class SendEmailWithFilesSample extends SparkPostBaseApp {

    static final Logger logger = LogManager.getLogger(CreateTemplateSimple.class);

    private Client client;

    public static void main(String[] args) throws SparkPostException, IOException {
        Configurator.setRootLevel(Level.DEBUG);

        SendEmailWithFilesSample sample = new SendEmailWithFilesSample();
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

        // Populate Email Body
        TemplateContentAttributes contentAttributes = new TemplateContentAttributes();
        contentAttributes.setFrom(new AddressAttributes(from));
        contentAttributes.setSubject("Hello World");
        contentAttributes.setText("Simple text content");

        // Add a text attachment
        AttachmentAttributes attachment = new AttachmentAttributes();
        attachment.setName("aFile.txt");
        attachment.setType("text/plain; charset=UTF-8;");
        // This is Base64 of the file contents
        attachment.setData("SGVsbG8gV29ybGQhCuydvA==");
        List<AttachmentAttributes> attachments = new ArrayList<>();
        attachments.add(attachment);
        contentAttributes.setAttachments(attachments);

        // Add inline image
        InlineImageAttributes image = new InlineImageAttributes();
        /*
         * The name of the inline image, which will be inserted into the Content-ID header.
         * The image should be referenced in your HTML content using <img src="cid:THIS_NAME">.
         * The name must be unique within the content.inline_images array.
         */
        contentAttributes.setHtml("<p>My fantastic HTML content.<br><br><b>SparkPost</b> <img src=\"cid:AnImage.png\"></p>");
        image.setName("AnImage.png");
        image.setType("image/png");
        // This is Base64 of the file contents
        image.setData(
                "iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAAXNSR0IArs4c6QAAAAlwSFlzAAAWJQAAFiUBSVIk8AAAAXxJREFUOBFjvJVg84P5718WBjLAX2bmPyxMf/+xMDH8YyZDPwPDXwYGJkIaOXTNGdiUtHAqI2jA/18/GUQzGsg3gMfKg4FVQo6BiYcPqyF4XcChaczA4+DP8P//f4b/P3+SZgAzvxCDSGYjAyMjI8PvZw+AoYXdLuyiQLtE0uoZWAREwLb+fnKXQTipkngXcJu7MnACQx8G2FX1GHgs3bDGBlYX8HlFM/z9+JbhzewWhmf1CQyfti9j+PfzBwO/ZxTMTDiNmQKBfmZX1GB42V/K8P38YbDCX/dvMDAwMzPwuYbBNcIYmC4AhfjvXwx/376AqQHTf96+ZPj34xuKGIiDaQBQ8PPBTQwCoZkMjJzcYA3MgqIMAr7xDJ/3rAHzkQnGO7FWf5gZ/qLmBSZmBoHgNAZee1+Gf18/MzCyczJ83LyQ4fPetch6Gf4xMP3FbgBMGdAgJqAr/n37zABMTTBROA0ygAWUJUG5Civ4B8xwX78CpbD6FJiHmf4AAFicbTMTr5jAAAAAAElFTkSuQmCC");
        List<InlineImageAttributes> inlineImages = new ArrayList<InlineImageAttributes>();
        inlineImages.add(image);
        contentAttributes.setInlineImages(inlineImages);

        transmission.setContentAttributes(contentAttributes);

        // Send the Email
        IRestConnection connection = new RestConnection(this.client, getEndPoint());
        Response response = ResourceTransmissions.create(connection, 0, transmission);

        logger.debug("Transmission Response: " + response);
    }

}
