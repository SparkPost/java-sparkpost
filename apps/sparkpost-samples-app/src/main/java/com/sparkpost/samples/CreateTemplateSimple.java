
package com.sparkpost.samples;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sparkpost.Client;
import com.sparkpost.exception.SparkPostException;
import com.sparkpost.model.AddressAttributes;
import com.sparkpost.model.TemplateAttributes;
import com.sparkpost.model.TemplateContentAttributes;
import com.sparkpost.model.responses.Response;
import com.sparkpost.resources.ResourceTemplates;
import com.sparkpost.sdk.samples.helpers.SparkPostBaseApp;
import com.sparkpost.transport.IRestConnection;
import com.sparkpost.transport.RestConnection;

public class CreateTemplateSimple extends SparkPostBaseApp {

    private static final Logger logger = LoggerFactory.getLogger(CreateTemplateSimple.class);

    private Client client;

    public static void main(String[] args) throws SparkPostException, IOException {
        CreateTemplateSimple sample = new CreateTemplateSimple();
        sample.runApp();

    }

    private void runApp() throws SparkPostException, IOException {
        this.client = this.newConfiguredClient();
        createTemplate();
    }

    /**
     * Demonstrates how to store an email template in SparkPost
     * 
     * @throws SparkPostException
     */
    public void createTemplate() throws SparkPostException {
        if (logger.isDebugEnabled()) {
            logger.debug("createTemplate()");
        }
        TemplateAttributes tpl = new TemplateAttributes();

        tpl.setName(SAMPLE_TEMPLATE_NAME);
        tpl.setContent(new TemplateContentAttributes());
        tpl.getContent().setFrom(new AddressAttributes(this.client.getFromEmail(), "me", null));
        tpl.getContent().setHtml("Hello!");
        tpl.getContent().setSubject("Template Test");
        IRestConnection connection = new RestConnection(this.client, getEndPoint());
        Response response = ResourceTemplates.create(connection, tpl);

        if (logger.isDebugEnabled()) {
            logger.debug("Create Template Response: " + response);
        }
    }

    public void sendEmail(String templateName, List<String> recipients) {
        if (logger.isDebugEnabled()) {
            logger.debug("sendEmail(...)");
        }

    }

}
