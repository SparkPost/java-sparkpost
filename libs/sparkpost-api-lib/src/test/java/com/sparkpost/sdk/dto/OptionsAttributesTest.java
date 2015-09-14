package com.sparkpost.sdk.dto;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.Gson;

public class OptionsAttributesTest {
	private static final String OPTIONS_ATTRIBUTE_JSON = "{\n" 
			+ "      \"start_time\": \"some start time\",\n"
			+ "      \"open_tracking\": true,\n"
			+ "      \"click_tracking\": true,\n"
			+ "      \"transactional\": true,\n" 
			+ "      \"skip_suppression\": true\n" 
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
	public void testDecodeOptionsAttributes() {
		Gson gson = new Gson();
		OptionsAttributes optionsAttributes = gson.fromJson(OPTIONS_ATTRIBUTE_JSON, OptionsAttributes.class);
		Assert.assertNotNull(optionsAttributes);

		Assert.assertEquals(optionsAttributes.getStartTime(), "some start time");
		Assert.assertTrue(optionsAttributes.getClickTracking());
		Assert.assertTrue(optionsAttributes.getOpenTracking());
		Assert.assertTrue(optionsAttributes.getSkipSuppression());
		Assert.assertTrue(optionsAttributes.getTransactional());
		
	}
	
	/**
	 * 
	 */
	@Test
	public void testOptionsAttributesRoundtrip() {
		Gson gson = new Gson();
		OptionsAttributes optionsAttributes = gson.fromJson(OPTIONS_ATTRIBUTE_JSON, OptionsAttributes.class);
		Assert.assertNotNull(optionsAttributes);
		
		String optionsAttributes_json = optionsAttributes.toJson();
		OptionsAttributes optionsAttributes2 = gson.fromJson(optionsAttributes_json, OptionsAttributes.class);
		
		Assert.assertEquals(optionsAttributes.getStartTime(), optionsAttributes2.getStartTime());
		Assert.assertEquals(optionsAttributes.getClickTracking(), optionsAttributes2.getClickTracking());
		Assert.assertEquals(optionsAttributes.getOpenTracking(), optionsAttributes2.getOpenTracking());
		Assert.assertEquals(optionsAttributes.getSkipSuppression(), optionsAttributes2.getSkipSuppression());
		Assert.assertEquals(optionsAttributes.getTransactional(), optionsAttributes2.getTransactional());
		
	}
}
