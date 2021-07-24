
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

public class DNSAttributesTest {

    private static final String DNS_ATTRIBUTE_JSON = "{\n"
            + "      \"dkim_record\": \"some dkim record\",\n"
            + "      \"spf_record\": \"some spf record\",\n"
            + "      \"dkim_error\": \"some dkim error\",\n"
            + "      \"spf_error\": \"some spf error\"\n"
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
    public void testDecodeDKIM() {
        Gson gson = new Gson();
        DNSAttributes dnsAttributes = gson.fromJson(DNS_ATTRIBUTE_JSON, DNSAttributes.class);

        Assert.assertNotNull(dnsAttributes);

        Assert.assertEquals(dnsAttributes.getDkimRecord(), "some dkim record");
        Assert.assertEquals(dnsAttributes.getSpfRecord(), "some spf record");
        Assert.assertEquals(dnsAttributes.getDkimError(), "some dkim error");
        Assert.assertEquals(dnsAttributes.getSpfError(), "some spf error");
    }

    /**
     *
     */
    @Test
    public void testDKIMRoundTrip() {
        Gson gson = new Gson();
        DNSAttributes dnsAttributes = gson.fromJson(DNS_ATTRIBUTE_JSON, DNSAttributes.class);
        Assert.assertNotNull(dnsAttributes);

        String dnsAttributes_json = dnsAttributes.toJson();
        DNSAttributes dnsAttributes2 = gson.fromJson(dnsAttributes_json, DNSAttributes.class);
        Assert.assertNotNull(dnsAttributes2);

        Assert.assertEquals(dnsAttributes.getDkimRecord(), dnsAttributes2.getDkimRecord());
        Assert.assertEquals(dnsAttributes.getSpfRecord(), dnsAttributes2.getSpfRecord());
        Assert.assertEquals(dnsAttributes.getDkimError(), dnsAttributes2.getDkimError());
        Assert.assertEquals(dnsAttributes.getSpfError(), dnsAttributes2.getSpfError());
    }

}
