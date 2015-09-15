/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sparkpost.sdk;

import static org.junit.Assert.fail;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sparkpost.sdk.exception.SparkpostSdkException;
import com.sparkpost.sdk.transport.RestConnection;

/**
 *
 * @author grava
 */
public class SPRestConnTest {

	private static final Logger logger = Logger.getLogger(SPRestConnTest.class);

	static Client client = null;

	public SPRestConnTest() {
	}

	@BeforeClass
	public static void setUpClass() {
		Logger.getRootLogger().setLevel(Level.DEBUG);
		client = new Client(System.getenv("SPARKPOST_API_KEY"));
		if (StringUtils.isEmpty(client.getAuthKey())) {
			fail("SPARKPOST_API_KEY must be defined as an environment variable.");
		}
		client.setFromEmail(System.getenv("SPARKPOST_SENDER_EMAIL"));
		if (StringUtils.isEmpty(client.getFromEmail())) {
			fail("SPARKPOST_SENDER_EMAIL must be defined as an environment variable.");
		}
		logger.info(client);
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
	 * Test of get method, of class SPRestConn.
	 */
	@Test
	public void testGet() {
		logger.info("---- SPRestConnTest.testGet");
		try {
			RestConnection conn = new RestConnection(client);
			conn.get("metrics");
			conn.get("transmissions");
			conn.get("webhooks");

		} catch (SparkpostSdkException ex) {
			logger.info(ex.toString());
			fail(ex.toString());
		}
	}

	/**
	 * Test of post method, of class SPRestConn.
	 */
	@Test
	public void testPost() {
		logger.info("---- SPRestConnTest.testPost");
		String sendJson = "{\n"
				+ "    \"recipients\": [ { \"address\": { \"email\": \"grava@messagesystems.com\", }, } ],\n"
				+ "    \"content\": {\n" + "        \"from\": { \"email\": \"" + client.getFromEmail() + "\" },\n"
				+ "        \"subject\": \"SPRestConnTest.java Unit Test\" ,\n" + "        \"text\": \"TEST\",\n"
				+ "    }\n" + "}";
		try {
			RestConnection conn = new RestConnection(client);
			conn.post("transmissions?num_rcpt_errors=3", sendJson);
		} catch (SparkpostSdkException ex) {
			logger.info(ex.toString());
			fail(ex.toString());
		}

	}

	/**
	 * Test of put method, of class SPRestConn.
	 */
	@Test
	public void testPut() {
		logger.info("---- SPRestConnTest.testPut");
		String sendJson = "{\n  \"options\":{\n  \"open_tracking\": true\n  }\n}\n";
		try {
			RestConnection conn = new RestConnection(client);
			conn.put("templates/thankyou", sendJson);
		} catch (SparkpostSdkException ex) {
			logger.info(ex.toString());
			fail(ex.toString());
		}
	}

	/**
	 * Test of delete method, of class SPRestConn.
	 */
	@Test
	public void testDelete() {
		logger.info("---- SPRestConnTest.testDelete");
		RestConnection conn = null;

		try {
			conn = new RestConnection(client);
			conn.delete("templates/templateTHATdoesntEXIST");
			if (conn.getLastResponse().getResponseCode() == 404) {
				logger.info("As expected: template not found.");
			} else {
				throw new SparkpostSdkException("prog error");
			}

		} catch (SparkpostSdkException ex) {
			logger.info(ex.toString());
			fail(ex.toString());
		}
	}

}
