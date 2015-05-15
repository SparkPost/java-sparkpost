/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.messagesystems.sparkpostsdk;

import org.apache.log4j.Logger;
import org.apache.log4j.Level;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author grava
 */
public class SPRestConnTest {

    static private String authKey = null;
    static private String senderEmail = null;
    SPClient client = null;

    public SPRestConnTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        Logger.getRootLogger().setLevel(Level.DEBUG);

        SPRestConnTest.authKey = System.getenv("SPARKPOST_API_KEY");
        SPRestConnTest.senderEmail = System.getenv("SPARKPOST_SENDER_EMAIL");
        if (SPRestConnTest.authKey == null || SPRestConnTest.authKey.isEmpty()) {
            fail("SPARKPOST_API_KEY must be defined as an environment variable.");
        }
        System.out.println("SPRestConnTest: SPARKPOST_API_KEY=" + SPRestConnTest.authKey);

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.client = new SPClient();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of get method, of class SPRestConn.
     */
    @Test
    public void testGet() {
        System.out.println("---- get");
        try {
            SPRestConn conn = new SPRestConn(client, authKey);
            conn.get("metrics");
            conn.get("transmissions");
            conn.get("webhooks");

        } catch (SparkpostSdkException ex) {
            fail(ex.toString());
        }
    }

    /**
     * Test of SetAuthKey method, of class SPRestConn.
     */
    @Test
    public void testSetAuthKey() {
        System.out.println("---- SetAuthKey");
        try {
            SPRestConn conn = new SPRestConn(client, authKey);
            conn.SetAuthKey(authKey);
        } catch (SparkpostSdkException ex) {
            fail(ex.toString());
        }
    }

    /**
     * Test of post method, of class SPRestConn.
     */
    @Test
    public void testPost() {
        System.out.println("---- post");
        String sendJson = "{\n"
                + "    \"recipients\": [ { \"address\": { \"email\": \"guillaume.rava@messagesystems.com\", }, } ],\n"
                + "    \"content\": {\n"
                + "        \"from\": { \"email\": \""+senderEmail+"\" },\n"
                + "        \"subject\": \"SPRestConnTest.java Unit Test\" ,\n"
                + "        \"text\": \"TEST\",\n"
                + "    }\n"
                + "}";
        try {
            SPRestConn conn = new SPRestConn(client, authKey);
            conn.post("transmissions?num_rcpt_errors=3", sendJson);
        } catch (SparkpostSdkException ex) {
            fail(ex.toString());
        }

    }

}
