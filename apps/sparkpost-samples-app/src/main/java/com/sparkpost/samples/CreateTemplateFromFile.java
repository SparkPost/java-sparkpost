
package com.sparkpost.samples;

import java.io.IOException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import com.sparkpost.Client;
import com.sparkpost.exception.SparkPostException;
import com.sparkpost.model.TemplateAttributes;
import com.sparkpost.model.TemplateContentAttributes;
import com.sparkpost.model.responses.Response;
import com.sparkpost.resources.ResourceTemplates;
import com.sparkpost.sdk.samples.helpers.SparkPostBaseApp;
import com.sparkpost.transport.IRestConnection;
import com.sparkpost.transport.RestConnection;

/**
 * This class demonstrates how to store and RFC822 template in SparkPost
 * Warning: RFC822 templates are only manageable via API and not from website directly.
 */
public class CreateTemplateFromFile extends SparkPostBaseApp {

    private static final Logger logger = LogManager.getLogger(CreateTemplateSimple.class);

    private Client client;

    public static void main(String[] args) throws SparkPostException, IOException {
        Configurator.setRootLevel(Level.DEBUG);

        CreateTemplateFromFile sample = new CreateTemplateFromFile();
        sample.runApp();

    }

    private void runApp() throws SparkPostException, IOException {
        this.client = this.newConfiguredClient();
        String template = this.getTemplate("sample_email.txt");
        createTemplate(template, SAMPLE_TEMPLATE_NAME);
    }

    /**
     * Demonstrates how to store an email template in SparkPost
     *
     * @throws SparkPostException
     */
    public void createTemplate(String rfc822Content, String name) throws SparkPostException {
        if (logger.isDebugEnabled()) {
            logger.debug("createTemplate()");
        }
        TemplateAttributes template = new TemplateAttributes();
        template.setName(name);

        TemplateContentAttributes content = new TemplateContentAttributes();

        //content.setFrom(new AddressAttributes(client.getFromEmail(), "me", null));
        content.setEmailRFC822(rfc822Content);
        template.setContent(content);

        IRestConnection connection = new RestConnection(this.client, getEndPoint());
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
