
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

public class AddressAttributesTests {

    private String SIMPLE_ADDRESS_JSON = "{" + "\"email\" : \"someone@example.com\"," + "\"name\" : \"First Last\"" + "}";

    private String SIMPLE_ADDRESS_JSON_2 = "{"
            + "\"email\" : \"someone@example.com\","
            + "\"header_to\" : \"some header to\","
            + "\"name\" : \"First Last\""
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
     * Check headerto is null if not sent
     */
    @Test
    public void testSimpleDecodeAddress() {
        Gson gson = new Gson();
        AddressAttributes addressAttributes = gson.fromJson(this.SIMPLE_ADDRESS_JSON, AddressAttributes.class);

        Assert.assertNull(addressAttributes.getHeaderTo());
        Assert.assertEquals(addressAttributes.getEmail(), "someone@example.com");
        Assert.assertEquals(addressAttributes.getName(), "First Last");
    }

    /**
     * Make sure all fields are decoded
     */
    @Test
    public void testSimpleDecodeAddress_2() {
        Gson gson = new Gson();
        AddressAttributes addressAttributes = gson.fromJson(this.SIMPLE_ADDRESS_JSON_2, AddressAttributes.class);

        Assert.assertEquals(addressAttributes.getHeaderTo(), "some header to");
        Assert.assertEquals(addressAttributes.getEmail(), "someone@example.com");
        Assert.assertEquals(addressAttributes.getName(), "First Last");
    }

    /**
     * Make sure a decoded and encoded AdressAttribute are equal is represent same data
     */
    @Test
    public void testAddressRoundTrip() {
        Gson gson = new Gson();
        AddressAttributes addressAttributes = gson.fromJson(this.SIMPLE_ADDRESS_JSON_2, AddressAttributes.class);

        Assert.assertEquals(addressAttributes.getHeaderTo(), "some header to");
        Assert.assertEquals(addressAttributes.getEmail(), "someone@example.com");
        Assert.assertEquals(addressAttributes.getName(), "First Last");

        String json = addressAttributes.toJson();
        AddressAttributes anotherAddressAttributes = gson.fromJson(json, AddressAttributes.class);

        Assert.assertEquals(addressAttributes.getEmail(), anotherAddressAttributes.getEmail());
        Assert.assertEquals(addressAttributes.getName(), anotherAddressAttributes.getName());
        Assert.assertEquals(addressAttributes.getHeaderTo(), anotherAddressAttributes.getHeaderTo());
    }
}
