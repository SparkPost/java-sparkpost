
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

public class SuppressionListEntryTest {

    private String SUPPRESSION_LIST_JSON = "{\n"
            + "            \"email\": \"rcpt_1@example.com\",\n"
            + "            \"transactional\": true,\n"
            + "            \"source\": \"some source\",\n"
            + "            \"description\": \"User requested to not receive any transactional emails.\"\n"
            + "          }";

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
    public void testDecodeSuppressionListEntry() {
        Gson gson = new Gson();
        SuppressionListEntry suppressionListEntry = gson.fromJson(this.SUPPRESSION_LIST_JSON, SuppressionListEntry.class);
        Assert.assertNotNull(suppressionListEntry);

        Assert.assertEquals(suppressionListEntry.getEmail(), "rcpt_1@example.com");
        Assert.assertTrue(suppressionListEntry.isTransactional());
        Assert.assertFalse(suppressionListEntry.isNonTransactional());
        Assert.assertEquals(suppressionListEntry.getDescription(), "User requested to not receive any transactional emails.");
        Assert.assertEquals(suppressionListEntry.getSource(), "some source");

    }

    /**
     *
     */
    @Test
    public void testSuppressionListEntryRoundtrip() {
        Gson gson = new Gson();
        SuppressionListEntry suppressionListEntry = gson.fromJson(this.SUPPRESSION_LIST_JSON, SuppressionListEntry.class);
        Assert.assertNotNull(suppressionListEntry);

        String suppressionListEntry_json = suppressionListEntry.toJson();
        SuppressionListEntry suppressionListEntry2 = gson.fromJson(suppressionListEntry_json, SuppressionListEntry.class);
        Assert.assertNotNull(suppressionListEntry2);

        Assert.assertEquals(suppressionListEntry.getEmail(), suppressionListEntry2.getEmail());
        Assert.assertEquals(suppressionListEntry.isTransactional(), suppressionListEntry2.isTransactional());
        Assert.assertEquals(suppressionListEntry.isNonTransactional(), suppressionListEntry2.isNonTransactional());
        Assert.assertEquals(suppressionListEntry.getDescription(), suppressionListEntry2.getDescription());
        Assert.assertEquals(suppressionListEntry.getSource(), suppressionListEntry2.getSource());

    }
}
