/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.messagesystems.sparkpostsdk;

import static org.junit.Assert.fail;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sparkpost.sdk.Client;
import com.sparkpost.sdk.ResourceTemplates;
import com.sparkpost.sdk.RestConn;
import com.sparkpost.sdk.dto.Address;
import com.sparkpost.sdk.dto.SparkpostSdkException;
import com.sparkpost.sdk.dto.Template;
import com.sparkpost.sdk.dto.TemplateContent;

/**
 *
 * @author grava
 */
public class SPResourceTemplateTest {

	static Client client = null;

	public SPResourceTemplateTest() {
	}

	@BeforeClass
	public static void setUpClass() {
		Logger.getRootLogger().setLevel(Level.DEBUG);
		client = new Client(System.getenv("SPARKPOST_API_KEY"));
		if (client.GetAuthKey() == null || client.GetAuthKey().isEmpty()) {
			fail("SPARKPOST_API_KEY must be defined as an environment variable.");
		}
		client.setFromEmail(System.getenv("SPARKPOST_SENDER_EMAIL"));
		if (client.getFromEmail() == null || client.getFromEmail().isEmpty()) {
			fail("SPARKPOST_SENDER_EMAIL must be defined as an environment variable.");
		}
		System.out.println(client);
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
	 * Test of postCreateTemplate method, of class SPTemplateResource.
	 */
	@Test
	public void testPostCreateTemplate() {
		System.out.println("---- SPResourceTemplateTest.testPostCreateTemplate");
		RestConn conn = null;
		Template tpl = new Template();

		tpl.setName("_TMP_TEMPLATE_TEST");
		tpl.setContent(new TemplateContent());
		tpl.getContent().setFrom(new Address(client.getFromEmail(), "me", null));
		tpl.getContent().setHtml("Hello!");
		tpl.getContent().setSubject("Template Test");
		try {
			conn = new RestConn(client);
			ResourceTemplates.create(conn, tpl);

		} catch (SparkpostSdkException ex) {
			System.out.println(ex.toString());
			if (conn != null) {
				System.out.println("Response:" + conn.getLastResponse().toString());
			}
			fail(ex.toString());
		}

	}

}
