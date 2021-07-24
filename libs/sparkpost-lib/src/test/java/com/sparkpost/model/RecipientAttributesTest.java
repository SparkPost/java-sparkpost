
package com.sparkpost.model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.Gson;

public class RecipientAttributesTest {

    private String RECIPIENT_ATTRIBUTES_JSON = " {\n"
            + "      \"return_path\": \"123@bounces.flintstone.com\",\n"
            + "      \"address\": {\n"
            + "        \"email\": \"wilma@flintstone.com\",\n"
            + "        \"name\": \"Wilma Flintstone\"\n"
            + "      },\n"
            + "      \"tags\": [\n"
            + "        \"greeting\",\n"
            + "        \"prehistoric\",\n"
            + "        \"fred\",\n"
            + "        \"flintstone\"\n"
            + "      ],\n"
            + "      \"metadata\": {\n"
            + "        \"place\": \"Bedrock\"\n"
            + "      },\n"
            + "      \"substitution_data\": {\n"
            + "        \"customer_type\": \"Platinum\",\n"
            + "        \"array_data\": [ \"object1\", \"object2\", \"object3\" ]\n"
            + "      }\n"
            + "    }";

    private List<String> expected = Arrays.asList("greeting", "prehistoric", "fred", "flintstone");

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
    public void testDecodeRecipientAttributes() {
        Gson gson = new Gson();
        RecipientAttributes recipientAttributes = gson.fromJson(this.RECIPIENT_ATTRIBUTES_JSON, RecipientAttributes.class);
        Assert.assertNotNull(recipientAttributes);

        Assert.assertEquals(recipientAttributes.getReturnPath(), "123@bounces.flintstone.com");

        AddressAttributes addressAttributes = recipientAttributes.getAddress();
        Assert.assertNotNull(addressAttributes);
        Assert.assertEquals(addressAttributes.getEmail(), "wilma@flintstone.com");
        Assert.assertEquals(addressAttributes.getName(), "Wilma Flintstone");

        List<String> tags = recipientAttributes.getTags();
        Assert.assertTrue(tags.containsAll(this.expected));

        Map<String, String> metadata = recipientAttributes.getMetadata();
        MatcherAssert.assertThat(metadata, Matchers.hasEntry("place", "Bedrock"));

        String result = (String) recipientAttributes.getSubstitutionData().get("customer_type");
        Assert.assertNotNull(result);
        Assert.assertEquals(result, "Platinum");

        @SuppressWarnings("unchecked")
        List<String> arrayData = (List<String>) recipientAttributes.getSubstitutionData().get("array_data");
        Assert.assertNotNull(arrayData);
        Assert.assertEquals(arrayData.size(), 3);

    }

    /**
     *
     */
    @Test
    public void testRecipientAttributesRoundtrip() {
        Gson gson = new Gson();
        RecipientAttributes recipientAttributes = gson.fromJson(this.RECIPIENT_ATTRIBUTES_JSON, RecipientAttributes.class);
        Assert.assertNotNull(recipientAttributes);

        String recipientAttributes_json = recipientAttributes.toJson();
        RecipientAttributes recipientAttributes2 = gson.fromJson(recipientAttributes_json, RecipientAttributes.class);
        Assert.assertNotNull(recipientAttributes2);

        Assert.assertEquals(recipientAttributes.getReturnPath(), recipientAttributes2.getReturnPath());

        AddressAttributes addressAttributes = recipientAttributes.getAddress();
        Assert.assertNotNull(addressAttributes);
        AddressAttributes addressAttributes2 = recipientAttributes.getAddress();
        Assert.assertNotNull(addressAttributes2);

        Assert.assertEquals(addressAttributes.getEmail(), addressAttributes2.getEmail());
        Assert.assertEquals(addressAttributes.getName(), addressAttributes2.getName());

        List<String> tags = recipientAttributes2.getTags();
        Assert.assertTrue(tags.containsAll(this.expected));

        Map<String, String> metadata = recipientAttributes2.getMetadata();
        MatcherAssert.assertThat(metadata, Matchers.hasEntry("place", "Bedrock"));

        Map<String, Object> substitutionData = recipientAttributes.getSubstitutionData();
        String result = (String) substitutionData.get("customer_type");
        Assert.assertNotNull(result);
        Assert.assertEquals(result, "Platinum");

    }

}
