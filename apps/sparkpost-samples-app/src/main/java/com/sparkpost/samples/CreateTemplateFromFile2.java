
package com.sparkpost.samples;

import java.io.IOException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

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

/**
 * This class demonstrates how to store an HTML template in SparkPost
 */
public class CreateTemplateFromFile2 extends SparkPostBaseApp {

    private static final Logger logger = LogManager.getLogger(CreateTemplateSimple.class);

    private Client client;

    public static void main(String[] args) throws SparkPostException, IOException {
        Configurator.setRootLevel(Level.DEBUG);

        CreateTemplateFromFile2 sample = new CreateTemplateFromFile2();
        sample.runApp();

    }

    private void runApp() throws SparkPostException, IOException {
        client = this.newConfiguredClient();
        String template = this.getTemplate("richContent.html");
        createTemplate(template, SAMPLE_TEMPLATE_NAME);
    }

    /**
     * Demonstrates how to store an email template in SparkPost
     * 
     * @throws SparkPostException
     */
    public void createTemplate(String html, String name) throws SparkPostException {
        if (logger.isDebugEnabled()) {
            logger.debug("createTemplate()");
        }
        TemplateAttributes template = new TemplateAttributes();
        template.setName(name);

        TemplateContentAttributes content = new TemplateContentAttributes();

        content.setSubject("Template Test");
        content.setFrom(new AddressAttributes(client.getFromEmail(), "me", null));
        content.setHtml(html);
        template.setContent(content);

        IRestConnection connection = new RestConnection(client, getEndPoint());
        try {
            Response response = ResourceTemplates.create(connection, template);
            if (logger.isDebugEnabled()) {
                logger.debug("Create Template Response: " + response);
            }
        } catch (SparkPostException e) {
            logger.debug("Create Template Failed: ");
            throw e;
        }

    }
}
