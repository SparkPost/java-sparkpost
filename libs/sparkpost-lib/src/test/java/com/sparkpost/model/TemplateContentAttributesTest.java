
package com.sparkpost.model;

import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.Gson;

public class TemplateContentAttributesTest {

    private String TEMPLATE_ATTRIBUTES_JSON = "{\n"
            + "         \"email_rfc822\": \"RFC 822 Content\",\n"
            + "         \"reply_to\": \"reply to\",\n"
            + "         \"subject\": \"the subject\",\n"
            + "         \"text\": \"some text\",\n"
            + "         \"html\": \"html content\",\n"
            + "         \"headers\": {\n"
            + "            \"Content-Type\": \"text/plain\"\n"
            + "          },"
            + "          \"from\": {\n"
            + "              \"email\" : \"someone@example.com\","
            + "              \"name\" : \"First Last\""
            + "          }"
            + "}";

    @BeforeClass
    public static void setUpClass() {
        Configurator.setRootLevel(Level.DEBUG);
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
    public void testDecodeTemplateAttributes() {
        Gson gson = new Gson();
        TemplateContentAttributes templateAttributes = gson.fromJson(this.TEMPLATE_ATTRIBUTES_JSON, TemplateContentAttributes.class);
        Assert.assertNotNull(templateAttributes);

        Assert.assertEquals(templateAttributes.getEmailRFC822(), "RFC 822 Content");
        Assert.assertEquals(templateAttributes.getReplyTo(), "reply to");
        Assert.assertEquals(templateAttributes.getSubject(), "the subject");
        Assert.assertEquals(templateAttributes.getText(), "some text");
        Assert.assertEquals(templateAttributes.getHtml(), "html content");

        Map<String, String> headers = templateAttributes.getHeaders();
        Assert.assertNotNull(headers);

        AddressAttributes from = templateAttributes.getFrom();
        Assert.assertNotNull(from);
    }

    /**
     *
     */
    @Test
    public void testTemplateAttributesRoundtrip() {
        Gson gson = new Gson();
        TemplateContentAttributes templateAttributes = gson.fromJson(this.TEMPLATE_ATTRIBUTES_JSON, TemplateContentAttributes.class);
        Assert.assertNotNull(templateAttributes);

        String templateAttributes_json = templateAttributes.toJson();
        TemplateContentAttributes templateAttributes2 = gson.fromJson(templateAttributes_json, TemplateContentAttributes.class);
        Assert.assertNotNull(templateAttributes2);

        Assert.assertEquals(templateAttributes.getEmailRFC822(), templateAttributes2.getEmailRFC822());
        Assert.assertEquals(templateAttributes.getReplyTo(), templateAttributes2.getReplyTo());
        Assert.assertEquals(templateAttributes.getSubject(), templateAttributes2.getSubject());
        Assert.assertEquals(templateAttributes.getText(), templateAttributes2.getText());
        Assert.assertEquals(templateAttributes.getHtml(), templateAttributes2.getHtml());

        Map<String, String> headers = templateAttributes2.getHeaders();
        Assert.assertNotNull(headers);

        AddressAttributes from = templateAttributes2.getFrom();
        Assert.assertNotNull(from);
    }
}
