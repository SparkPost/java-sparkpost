
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

public class StatusAttributesTest {

    private String STATUS_ATTRIBUTES_JSON = "{\n"
            + "     \"ownership_verified\": true,\n"
            + "     \"spf_status\": \"pending\",\n"
            + "     \"abuse_at_status\": \"pending\",\n"
            + "     \"dkim_status\": \"pending\",\n"
            + "     \"compliance_status\": \"pending\",\n"
            + "     \"postmaster_at_status\": \"pending\"\n"
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
    public void testDecodeStatusAttributes() {
        Gson gson = new Gson();
        StatusAttributes statusAttrbutes = gson.fromJson(this.STATUS_ATTRIBUTES_JSON, StatusAttributes.class);
        Assert.assertNotNull(statusAttrbutes);

        Assert.assertTrue(statusAttrbutes.getOwnershipVerified());
        Assert.assertEquals(statusAttrbutes.getSpfStatus(), "pending");
        Assert.assertEquals(statusAttrbutes.getAbuseAtStatus(), "pending");
        Assert.assertEquals(statusAttrbutes.getDkimStatus(), "pending");
        Assert.assertEquals(statusAttrbutes.getComplianceStatus(), "pending");
        Assert.assertEquals(statusAttrbutes.getPostmasterAtStatus(), "pending");
    }

    /**
     *
     */
    @Test
    public void testStatusAttributesRoundtrip() {
        Gson gson = new Gson();
        StatusAttributes statusAttributes = gson.fromJson(this.STATUS_ATTRIBUTES_JSON, StatusAttributes.class);
        Assert.assertNotNull(statusAttributes);

        String StatusAttributes_json = statusAttributes.toJson();
        StatusAttributes statusAttributes2 = gson.fromJson(StatusAttributes_json, StatusAttributes.class);

        Assert.assertEquals(statusAttributes.getOwnershipVerified(), statusAttributes2.getOwnershipVerified());
        Assert.assertEquals(statusAttributes.getSpfStatus(), statusAttributes2.getSpfStatus());
        Assert.assertEquals(statusAttributes.getAbuseAtStatus(), statusAttributes2.getAbuseAtStatus());
        Assert.assertEquals(statusAttributes.getDkimStatus(), statusAttributes2.getDkimStatus());
        Assert.assertEquals(statusAttributes.getComplianceStatus(), statusAttributes2.getComplianceStatus());
        Assert.assertEquals(statusAttributes.getPostmasterAtStatus(), statusAttributes2.getPostmasterAtStatus());
    }
}
