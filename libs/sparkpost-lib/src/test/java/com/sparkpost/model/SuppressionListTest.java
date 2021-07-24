
package com.sparkpost.model;

import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.Gson;

public class SuppressionListTest {

    private String SUPPRESSION_LIST_JSON = "{\n"
            + "        \"recipients\": [\n"
            + "          {\n"
            + "            \"email\": \"rcpt_1@example.com\",\n"
            + "            \"transactional\": true,\n"
            + "            \"description\": \"User requested to not receive any transactional emails.\"\n"
            + "          },\n"
            + "          {\n"
            + "            \"email\": \"rcpt_2@example.com\",\n"
            + "            \"non_transactional\": true\n"
            + "          }\n"
            + "      ]\n"
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
    public void testDecodeSuppressionList() {
        Gson gson = new Gson();
        SuppressionList suppressionList = gson.fromJson(this.SUPPRESSION_LIST_JSON, SuppressionList.class);
        Assert.assertNotNull(suppressionList);

        List<SuppressionListEntry> recipients = suppressionList.getRecipients();
        // Internal state will be tested in SuppressionListEntry unit tests
        Assert.assertNotNull(suppressionList);
        Assert.assertEquals(recipients.size(), 2);
    }

    /**
     *
     */
    @Test
    public void testSuppressionListRoundTrip() {
        Gson gson = new Gson();
        SuppressionList suppressionList = gson.fromJson(this.SUPPRESSION_LIST_JSON, SuppressionList.class);
        Assert.assertNotNull(suppressionList);

        String suppressionList_json = suppressionList.toJson();
        SuppressionList suppressionList2 = gson.fromJson(suppressionList_json, SuppressionList.class);
        Assert.assertNotNull(suppressionList2);

        List<SuppressionListEntry> recipients = suppressionList2.getRecipients();
        // Internal state will be tested in SuppressionListEntry unit tests
        Assert.assertNotNull(suppressionList2);
        Assert.assertEquals(recipients.size(), 2);
    }
}
