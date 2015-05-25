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

    static SPClient client = null;

    public SPRestConnTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        Logger.getRootLogger().setLevel(Level.DEBUG);
        client = new SPClient(System.getenv("SPARKPOST_API_KEY"));
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
     * Test of get method, of class SPRestConn.
     */
    @Test
    public void testGet() {
        System.out.println("---- get");
        try {
            SPRestConn conn = new SPRestConn(client);
            conn.get("metrics");
            conn.get("transmissions");
            conn.get("webhooks");

        } catch (SparkpostSdkException ex) {
            System.out.println(ex.toString());
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
                + "    \"recipients\": [ { \"address\": { \"email\": \"grava@messagesystems.com\", }, } ],\n"
                + "    \"content\": {\n"
                + "        \"from\": { \"email\": \"" + client.getFromEmail() + "\" },\n"
                + "        \"subject\": \"SPRestConnTest.java Unit Test\" ,\n"
                + "        \"text\": \"TEST\",\n"
                + "    }\n"
                + "}";
        try {
            SPRestConn conn = new SPRestConn(client);
            conn.post("transmissions?num_rcpt_errors=3", sendJson);
        } catch (SparkpostSdkException ex) {
            System.out.println(ex.toString());
            fail(ex.toString());
        }

    }

    /**
     * Test of put method, of class SPRestConn.
     */
    @Test
    public void testPut() {
        System.out.println("---- put");
        String sendJson = "{\n  \"options\":{\n  \"open_tracking\": true\n  }\n}\n";
        try {
            SPRestConn conn = new SPRestConn(client);
            conn.put("templates/thankyou", sendJson);
        } catch (SparkpostSdkException ex) {
            System.out.println(ex.toString());
            fail(ex.toString());
        }
    }

    /**
     * Test of delete method, of class SPRestConn.
     */
    @Test
    public void testDelete() {
        System.out.println("---- delete");
        SPRestConn conn = null;

        try {
            conn = new SPRestConn(client);
            conn.delete("templates/templateTHATdoesntEXIST");

        } catch (SparkpostSdkException ex) {
            if (conn == null || conn.getResponseCode() != 404) {
                System.out.println(ex.toString());
                fail(ex.toString());
            } else {
                System.out.println("As expected: template not found.");
            }
        }
        if (conn == null || conn.getResponseCode() != 404) {
            fail("prog error");
        }
    }

}