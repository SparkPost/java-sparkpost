
package com.sparkpost.model;

import java.util.List;
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

public class TransmissionWithRecipientArrayTest {

    private String TRANSMISSION_JSON = "{\n"
            + "          \"options\": {\n"
            + "            \"open_tracking\": true,\n"
            + "            \"click_tracking\": true\n"
            + "          },\n"
            + "\n"
            + "          \"campaign_id\": \"christmas_campaign\",\n"
            + "          \"return_path\": \"bounces-christmas-campaign@flintstone.com\",\n"
            + "\n"
            + "          \"metadata\": {\n"
            + "            \"user_type\": \"students\"\n"
            + "          },\n"
            + "\n"
            + "          \"substitution_data\": {\n"
            + "            \"sender\": \"Big Store Team\"\n"
            + "          },\n"
            + "\n"
            + "          \"recipients\": [\n"
            + "            {\n"
            + "              \"return_path\": \"123@bounces.flintstone.com\",\n"
            + "              \"address\": {\n"
            + "                \"email\": \"wilma@flintstone.com\",\n"
            + "                \"name\": \"Wilma Flintstone\"\n"
            + "              },\n"
            + "              \"tags\": [\n"
            + "                \"greeting\",\n"
            + "                \"prehistoric\",\n"
            + "                \"fred\",\n"
            + "                \"flintstone\"\n"
            + "              ],\n"
            + "              \"metadata\": {\n"
            + "                \"place\": \"Bedrock\"\n"
            + "              },\n"
            + "              \"substitution_data\": {\n"
            + "                \"customer_type\": \"Platinum\"\n"
            + "              }\n"
            + "            }\n"
            + "          ],\n"
            + "          \"content\": {\n"
            + "            \"from\": {\n"
            + "              \"name\": \"Fred Flintstone\",\n"
            + "              \"email\": \"fred@flintstone.com\"\n"
            + "            },\n"
            + "            \"subject\": \"Big Christmas savings!\",\n"
            + "            \"reply_to\": \"Christmas Sales <sales@flintstone.com>\",\n"
            + "            \"headers\": {\n"
            + "              \"X-Customer-Campaign-ID\": \"christmas_campaign\"\n"
            + "            },\n"
            + "            \"text\": \"Hi {{address.name}} \\nSave big this Christmas in your area {{place}}! \\nClick http://www.mysite.com and get huge discount\\n Hurry, this offer is only to {{user_type}}\\n {{sender}}\",\n"
            + "            \"html\": \"<p>Hi {{address.name}} \\nSave big this Christmas in your area {{place}}! \\nClick http://www.mysite.com and get huge discount\\n</p><p>Hurry, this offer is only to {{user_type}}\\n</p><p>{{sender}}</p>\"\n"
            + "          }\n"
            + "        }";

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
        TransmissionWithRecipientArray transmissionWithRecipientArray = gson.fromJson(this.TRANSMISSION_JSON, TransmissionWithRecipientArray.class);
        Assert.assertNotNull(transmissionWithRecipientArray);

        OptionsAttributes options = transmissionWithRecipientArray.getOptions();
        Assert.assertNotNull(options);

        Assert.assertEquals(transmissionWithRecipientArray.getCampaignId(), "christmas_campaign");
        Assert.assertEquals(transmissionWithRecipientArray.getReturnPath(), "bounces-christmas-campaign@flintstone.com");

        Map<String, Object> metadata = transmissionWithRecipientArray.getMetadata();
        Assert.assertNotNull(metadata);

        Map<String, Object> substitutionData = transmissionWithRecipientArray.getSubstitutionData();
        Assert.assertNotNull(substitutionData);

        List<RecipientAttributes> recipientArray = transmissionWithRecipientArray.getRecipientArray();
        Assert.assertNotNull(recipientArray);
        Assert.assertEquals(1, recipientArray.size());

        TemplateContentAttributes contentAttributes = transmissionWithRecipientArray.getContentAttributes();
        Assert.assertNotNull(contentAttributes);

    }
}
