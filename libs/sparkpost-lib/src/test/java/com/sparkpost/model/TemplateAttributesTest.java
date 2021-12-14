
package com.sparkpost.model;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.Gson;

public class TemplateAttributesTest {

    private String TEMPLATE_ATTRIBUTES_JSON = "{\n"
            + "          \"id\" : \"summer_sale\",\n"
            + "          \"name\" : \"Summer Sale!\",\n"
            + "          \"description\" : \"some description\",\n"
            + "          \"published\" : true,\n"
            + "\n"
            + "          \"options\": {\n"
            + "            \"open_tracking\" : false,\n"
            + "            \"click_tracking\" : true\n"
            + "          },\n"
            + "\n"
            + "          \"content\": {\n"
            + "            \"from\": {\n"
            + "              \"email\": \"marketing@bounces.company.example\",\n"
            + "              \"name\": \"Example Company Marketing\"\n"
            + "            },\n"
            + "\n"
            + "            \"subject\": \"Summer deals for {{name}}\",\n"
            + "            \"reply_to\": \"Summer deals <summer_deals@company.example>\",\n"
            + "\n"
            + "            \"text\": \"Check out these deals {{name}}!\",\n"
            + "            \"html\": \"<b>Check out these deals {{name}}!</b>\",\n"
            + "\n"
            + "            \"headers\": {\n"
            + "              \"X-Customer-Campaign-ID\": \"Summer2014\"\n"
            + "            }\n"
            + "          }\n"
            + "        } ";

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
        TemplateAttributes templateAttributes = gson.fromJson(this.TEMPLATE_ATTRIBUTES_JSON, TemplateAttributes.class);
        Assert.assertNotNull(templateAttributes);

        Assert.assertEquals(templateAttributes.getId(), "summer_sale");
        Assert.assertEquals(templateAttributes.getName(), "Summer Sale!");
        Assert.assertEquals(templateAttributes.getDescription(), "some description");
        Assert.assertTrue(templateAttributes.getPublished());

        OptionsAttributes options = templateAttributes.getOptions();
        Assert.assertNotNull(options);

        TemplateContentAttributes content = templateAttributes.getContent();
        Assert.assertNotNull(content);

    }

    /**
     *
     */
    @Test
    public void testTemplateAttributesRoundtrip() {
        Gson gson = new Gson();
        TemplateAttributes templateAttributes = gson.fromJson(this.TEMPLATE_ATTRIBUTES_JSON, TemplateAttributes.class);
        Assert.assertNotNull(templateAttributes);

        String templateAttributes_json = templateAttributes.toJson();
        TemplateAttributes templateAttributes2 = gson.fromJson(templateAttributes_json, TemplateAttributes.class);
        Assert.assertNotNull(templateAttributes2);

        Assert.assertEquals(templateAttributes.getId(), templateAttributes2.getId());
        Assert.assertEquals(templateAttributes.getName(), templateAttributes2.getName());
        Assert.assertEquals(templateAttributes.getDescription(), templateAttributes2.getDescription());
        Assert.assertTrue(templateAttributes2.getPublished());

        OptionsAttributes options = templateAttributes2.getOptions();
        Assert.assertNotNull(options);

        TemplateContentAttributes content = templateAttributes2.getContent();
        Assert.assertNotNull(content);

    }
}
