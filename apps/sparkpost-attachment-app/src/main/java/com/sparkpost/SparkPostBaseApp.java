package com.sparkpost;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.sparkpost.sdk.Client;
import com.sparkpost.sdk.exception.SparkpostSdkException;

public class SparkPostBaseApp {

	private static final String CONFIG_FILE = "config.properties";
	
	private final Properties properties = new Properties();
	
	static final Logger logger = Logger.getLogger(CreateAndSendTemplate.class);
	
	public SparkPostBaseApp() {
		super();
		BasicConfigurator.configure();
		this.loadProperties();
	}

	protected Client newConfiguredClient() throws SparkpostSdkException, IOException {
		
		Client client = new Client(properties.getProperty("SPARKPOST_API_KEY"));
		if (StringUtils.isEmpty(client.getAuthKey())) {
			throw new SparkpostSdkException("SPARKPOST_API_KEY must be defined in " + CONFIG_FILE + ".");
		}
		client.setFromEmail(properties.getProperty("SPARKPOST_SENDER_EMAIL"));
		if (StringUtils.isEmpty(client.getFromEmail())) {
			throw new SparkpostSdkException("SPARKPOST_SENDER_EMAIL must be defined in " + CONFIG_FILE + ".");
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug(client);
		}
		
		return client;
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