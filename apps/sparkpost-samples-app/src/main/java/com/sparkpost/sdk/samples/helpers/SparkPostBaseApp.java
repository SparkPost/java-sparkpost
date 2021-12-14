
package com.sparkpost.sdk.samples.helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sparkpost.Client;
import com.sparkpost.exception.SparkPostException;
import com.sparkpost.samples.CreateTemplateSimple;
import com.sparkpost.transport.IRestConnection;

public class SparkPostBaseApp {

    public static final String SAMPLE_TEMPLATE_NAME = "_TMP_TEMPLATE_TEST";

    public static final String SAMPLE_RECIPIENT_LIST_NAME = "_TMP_RECIPLIST_TEST";

    private static final String CONFIG_FILE = "config.properties";

    private final Properties properties = new Properties();

    static final Logger logger = LogManager.getLogger(CreateTemplateSimple.class);

    public SparkPostBaseApp() {
        super();
        //Configurator.reconfigure();
        //BasicConfigurator.configure();
        this.loadProperties();
    }

    protected Client newConfiguredClient() throws SparkPostException, IOException {

        Client client = new Client(this.properties.getProperty("SPARKPOST_API_KEY"));
        if (StringUtils.isEmpty(client.getAuthKey())) {
            throw new SparkPostException("SPARKPOST_API_KEY must be defined in " + CONFIG_FILE + ".");
        }
        client.setFromEmail(this.properties.getProperty("SPARKPOST_SENDER_EMAIL"));
        if (StringUtils.isEmpty(client.getFromEmail())) {
            throw new SparkPostException("SPARKPOST_SENDER_EMAIL must be defined in " + CONFIG_FILE + ".");
        }

        if (logger.isDebugEnabled()) {
            logger.debug(client);
        }

        return client;
    }

    protected String getProperty(String name, String defaultValue) {
        return this.properties.getProperty(name, defaultValue);
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

            this.properties.load(inputStream);

        } catch (IOException e) {
            logger.error("Unable to locate configuration file \"" + CONFIG_FILE + "\". Make sure it is in your classpath.");
        }
    }

    public String getFromAddress() {
        String fromAddress = this.properties.getProperty("SPARKPOST_FROM");

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

    public final static String loadJsonFile(String name) {

        try {

            String jsonContent = FileUtils.readFileToString(new File("samples/" + name), "UTF-8");
            return jsonContent;

        } catch (IOException e) {
            System.err.println("Failed to load json file. " + e.getMessage());
            System.exit(-1);
        }

        return null;
    }

    public String[] getTestRecipients() {
        return getRecipientListProperty("SPARKPOST_RECIPIENTS");
    }

    public List<String> getTestRecipientsAsList() {
        return Arrays.asList(getTestRecipients());
    }

    public String[] getCCRecipients() {
        return getRecipientListProperty("SPARKPOST_CC_RECIPIENTS");
    }

    public String[] getBCCRecipients() {
        return getRecipientListProperty("SPARKPOST_BCC_RECIPIENTS");
    }

    public String stringArrayToCSV(String[] lst) {
        StringBuilder result = new StringBuilder();
        for (int idx = 0; idx < lst.length; ++idx) {
            result.append(lst[idx]);
            if (idx < lst.length - 1) {
                result.append(",");
            }
        }
        return result.toString();
    }

    private String[] getRecipientListProperty(String propName) {
        String recipListString = getProperty(propName, null);
        if (StringUtils.isAnyEmpty(recipListString)) {
            throw new IllegalStateException("This sample requires you to fill in `" + propName + "` in config.properties.");
        }

        String[] results = recipListString.split(",");
        return results;
    }
}
