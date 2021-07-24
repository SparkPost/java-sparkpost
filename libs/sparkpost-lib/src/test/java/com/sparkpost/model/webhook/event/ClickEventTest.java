
package com.sparkpost.model.webhook.event;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.Gson;

public class ClickEventTest {

    private static final String EVENT_DATA = "{\n"
            + "    \"type\": \"click\",\n"
            + "    \"delv_method\": \"esmtp\",\n"
            + "    \"timestamp\": \"1416500167.241\",\n"
            + "    \"message_id\": \"54863dba-3c9f-42f2-880a-07fc85a5158d\",\n"
            + "    \"campaign_id\": \"Test Campaign Name\",\n"
            + "    \"template_id\": \"Test Template Name\",\n"
            + "    \"target_link_name\": \"Test Link Name\",\n"
            + "    \"target_link_url\": \"http://example.com\",\n"
            + "    \"transmission_id\": \"65832150921904138\",\n"
            + "    \"user_agent\": \"test\",\n"
            + "    \"rcpt_tags\": [\n"
            + "        \"male\",\n"
            + "        \"MD\",\n"
            + "        \"50+\"\n"
            + "    ],\n"
            + "    \"node_name\": \"Test-Node\",\n"
            + "    \"customer_id\": \"1\",\n"
            + "    \"template_version\": \"1\"\n"
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
    public void testDecodeRecipientAttributes() {
        Gson gson = new Gson();
        //System.out.println("JSON: " + EVENT_DATA);
        ClickEvent event = gson.fromJson(ClickEventTest.EVENT_DATA, ClickEvent.class);

        Assert.assertNotNull(event);

        Assert.assertEquals(event.getTemplateVersion(), "1");
        Assert.assertNull(event.getFriendlyFrom());
        Assert.assertNull(event.getSubject());
        Assert.assertNull(event.getIpPool());
        Assert.assertNotNull(event.getRcptTags());
        Assert.assertEquals(event.getType(), "click");
        Assert.assertNull(event.getNumRetries());
        Assert.assertNull(event.getRawRcptTo());
        Assert.assertNull(event.getMsgFrom());
        Assert.assertNull(event.getRcptTo());
        Assert.assertNull(event.getGeoIp());
        Assert.assertEquals(event.getTargetLinkName(), "Test Link Name");
        Assert.assertNull(event.getSubaccountId());
        Assert.assertEquals(event.getTransmissionId(), "65832150921904138");
        Assert.assertEquals(event.getCampaignId(), "Test Campaign Name");
        Assert.assertEquals(event.getUserAgent(), "test");
        Assert.assertEquals(event.getTimestamp(), "1416500167.241");
        Assert.assertNull(event.getRcptMeta());
        Assert.assertEquals(event.getMessageId(), "54863dba-3c9f-42f2-880a-07fc85a5158d");
        Assert.assertNull(event.getIpAddress());
        Assert.assertNull(event.getRcptType());
        Assert.assertNull(event.getQueueTime());
        Assert.assertNull(event.getEventId());
        Assert.assertNull(event.getRoutingDomain());
        Assert.assertNull(event.getSendingIp());
        Assert.assertEquals(event.getTargetLinkUrl(), "http://example.com");
        Assert.assertEquals(event.getTemplateId(), "Test Template Name");
        Assert.assertEquals(event.getDelvMethod(), "esmtp");
        Assert.assertEquals(event.getCustomerId(), "1");
        Assert.assertNull(event.getMsgSize());
    }
}
