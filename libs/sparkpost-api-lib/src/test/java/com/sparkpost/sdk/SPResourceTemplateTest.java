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

import com.sparkpost.sdk.Client;
import com.sparkpost.sdk.ResourceTemplates;
import com.sparkpost.sdk.RestConn;
import com.sparkpost.sdk.dto.AddressAttributes;
import com.sparkpost.sdk.dto.SparkpostSdkException;
import com.sparkpost.sdk.dto.TemplateAttributes;
import com.sparkpost.sdk.dto.TemplateContentAttributes;

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
		if (StringUtils.isEmpty(client.getAuthKey())) {
			fail("SPARKPOST_API_KEY must be defined as an environment variable.");
		}
		client.setFromEmail(System.getenv("SPARKPOST_SENDER_EMAIL"));
		if (StringUtils.isEmpty(client.getFromEmail())) {
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
		TemplateAttributes tpl = new TemplateAttributes();

		tpl.setName("_TMP_TEMPLATE_TEST");
		tpl.setContent(new TemplateContentAttributes());
		tpl.getContent().setFrom(new AddressAttributes(client.getFromEmail(), "me", null));
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
