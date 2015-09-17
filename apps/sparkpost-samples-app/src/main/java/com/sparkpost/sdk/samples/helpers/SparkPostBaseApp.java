package com.sparkpost.sdk.samples.helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.sparkpost.Client;
import com.sparkpost.exception.SparkPostException;
import com.sparkpost.samples.CreateAndSendTemplate;
import com.sparkpost.transport.RestConnection;

public class SparkPostBaseApp {

	public static final String SAMPLE_TEMPLATE_NAME = "_TMP_TEMPLATE_TEST";
	
	private static final String CONFIG_FILE = "config.properties";
	
	private final Properties properties = new Properties();
	
	static final Logger logger = Logger.getLogger(CreateAndSendTemplate.class);
	
	public SparkPostBaseApp() {
		super();
		BasicConfigurator.configure();
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
		
		if (logger.isDebugEnabled()) {
			logger.debug(client);
		}
		
		return client;
	}
	
	public String getEndPoint() {
		String endpoint = this.properties.getProperty("SPARKPOST_BASE_URL", RestConnection.defaultApiEndpoint);
		
		return endpoint;
	}
	
	/**
	 * Loads properties from config.properties.
	 * 
	 */
	private void loadProperties() {
		try (InputStream inputStream = new FileInputStream(CONFIG_FILE);) {

			properties.load(inputStream);

		} catch (IOException e) {
			logger.error("Unable to locate configuration file \"" + CONFIG_FILE + "\". Make sure it is in your classpath.");
		}
	}
	
}