
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

public class StoredTemplateTest {

    private String STORED_TEMPLATE_JSON = "{\n"
            + "                \"template_id\": \"christmas_offer\",\n"
            + "                \"use_draft_template\": true\n"
            + "              }";

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
    public void testDecodeStoredTemplate() {
        Gson gson = new Gson();
        StoredTemplate storedTemplate = gson.fromJson(this.STORED_TEMPLATE_JSON, StoredTemplate.class);
        Assert.assertNotNull(storedTemplate);

        Assert.assertEquals(storedTemplate.getTemplateId(), "christmas_offer");
        Assert.assertTrue(storedTemplate.getUseDraftTemplate());

    }

    /**
     *
     */
    @Test
    public void testStoredTemplateRoundtrip() {
        Gson gson = new Gson();
        StoredTemplate storedTemplate = gson.fromJson(this.STORED_TEMPLATE_JSON, StoredTemplate.class);
        Assert.assertNotNull(storedTemplate);

        String storedTemplate_json = storedTemplate.toJson();
        StoredTemplate storedTemplate2 = gson.fromJson(storedTemplate_json, StoredTemplate.class);
        Assert.assertNotNull(storedTemplate2);

        Assert.assertEquals(storedTemplate.getTemplateId(), storedTemplate2.getTemplateId());
        Assert.assertEquals(storedTemplate.getUseDraftTemplate(), storedTemplate2.getUseDraftTemplate());

    }

}
