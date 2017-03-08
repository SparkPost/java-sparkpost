
package com.sparkpost.sample.helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.sparkpost.Client;
import com.sparkpost.exception.SparkPostException;
import com.sparkpost.transport.IRestConnection;

/**
 * A simple class to setup up the SparkPost client with the API KEY from a property file. 
 */

public class SparkPostBaseApp {

    public static final String SAMPLE_TEMPLATE_NAME = "_TMP_TEMPLATE_TEST";

    public static final String SAMPLE_RECIPIENT_LIST_NAME = "_TMP_RECIPLIST_TEST";

    private static final String CONFIG_FILE = "config.properties";

    private final Properties properties = new Properties();

    public SparkPostBaseApp() {
        super();
        this.loadProperties();
    }

    protected Client newConfiguredClient() throws SparkPostException, IOException {

        Client client = new Client(properties.getProperty("SPARKPOST_API_KEY"));
        if (StringUtils.isEmpty(client.getAuthKey())) {
            throw new SparkPostException("SPARKPOST_API_KEY must be defined in " + CONFIG_FILE + ".");
        }
        client.setFromEmail(properties.getProperty("SPARKPOST_SENDER_EMAIL"));
        if (StringUtils.isEmpty(client.getFromEmail())) {
            throw new SparkPostException("SPARKPOST_SENDER_EMAIL must be defined in " + CONFIG_FILE + ".");
        }
        
        System.out.println(client);

        return client;
    }

    public String getEndPoint() {
        String endpoint = this.properties.getProperty("SPARKPOST_BASE_URL", IRestConnection.defaultApiEndpoint);

        return endpoint;
    }

    /**
     * Loads properties from config.properties.
     */
    private void loadProperties() {
        try (InputStream inputStream = new FileInputStream(CONFIG_FILE);) {

            properties.load(inputStream);

        } catch (IOException e) {
            System.err.println("Unable to locate configuration file \"" + CONFIG_FILE + "\". Make sure it is in your classpath.");
        }
    }

    public String getFromAddress() {
        String fromAddress = properties.getProperty("SPARKPOST_FROM");

        if (StringUtils.isEmpty(fromAddress)) {
            throw new IllegalStateException("This sample requires you to fill in `SPARKPOST_FROM` in config.properties.");
        }

        return fromAddress;
    }

    public String getTemplate(String name) {

        try {

            String template = FileUtils.readFileToString(new File("samples/" + name), "UTF-8");
            return template;

        } catch (IOException e) {
            System.err.println("Failed to load template file. " + e.getMessage());
            System.exit(-1);
        }

        return null;
    }

    public String[] getTestRecipients() {
        String recipListString = properties.getProperty("SPARKPOST_RECIPIENTS", null);
        if (StringUtils.isAnyEmpty(recipListString)) {
            throw new IllegalStateException("This sample requires you to fill in `SPARKPOST_RECIPIENTS` in config.properties.");
        }

        String[] results = recipListString.split(",");
        return results;
    }

    public List<String> getTestRecipientsAsList() {
        return Arrays.asList(getTestRecipients());
    }

}
