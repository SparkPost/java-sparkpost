/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.messagesystems.sparkpostsdk;

import static com.messagesystems.sparkpostsdk.SPRestConnTest.client;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
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
public class SPResourceTemplateTest {

    static SPClient client = null;

    public SPResourceTemplateTest() {
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
     * Test of postCreateTemplate method, of class SPTemplateResource.
     */
    @Test
    public void testPostCreateTemplate() {
        System.out.println("---- SPResourceTemplateTest.testPostCreateTemplate");
        SPRestConn conn = null;
        SPDTOTemplate tpl = new SPDTOTemplate() ;
       
        tpl.name = "_TMP_TEMPLATE_TEST" ;
        tpl.content = new SPDTOTemplateContent() ;
        tpl.content.from = client.getFromEmail() ;
        tpl.content.html = "Hello!" ;
        tpl.content.subject =  "Template Test" ;
        try {
            conn = new SPRestConn(client);
            SPResourceTemplates.create(conn, tpl);

        } catch (SparkpostSdkException ex) {
            System.out.println(ex.toString());
            if ( conn != null ) {
                System.out.println( "Response:" + conn.getLastResponse().toString() ) ;
            }
            fail(ex.toString());
        }

    }

}
