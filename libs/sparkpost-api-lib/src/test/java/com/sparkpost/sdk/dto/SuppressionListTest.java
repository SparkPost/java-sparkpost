package com.sparkpost.sdk.dto;

import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.Gson;

public class SuppressionListTest {

	private String SUPPRESSION_LIST_JSON = "{\n" + 
			"        \"recipients\": [\n" + 
			"          {\n" + 
			"            \"email\": \"rcpt_1@example.com\",\n" + 
			"            \"transactional\": true,\n" + 
			"            \"description\": \"User requested to not receive any transactional emails.\"\n" + 
			"          },\n" + 
			"          {\n" + 
			"            \"email\": \"rcpt_2@example.com\",\n" + 
			"            \"non_transactional\": true\n" + 
			"          }\n" + 
			"      ]\n"
			+ "}";

	@BeforeClass
	public static void setUpClass() {
		Logger.getRootLogger().setLevel(Level.DEBUG);
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	/**
	 * 
	 */
	@Test
	public void testDecodeStoredTemplate() {
		Gson gson = new Gson();
		SuppressionList suppressionList = gson.fromJson(SUPPRESSION_LIST_JSON, SuppressionList.class);
		Assert.assertNotNull(suppressionList);		
		
		List<SuppressionListEntry> recipients = suppressionList.getRecipients();
		// Internal state will be tested in SuppressionListEntry unit tests
		Assert.assertNotNull(suppressionList);
		Assert.assertEquals(recipients.size(), 2);
	}
}
