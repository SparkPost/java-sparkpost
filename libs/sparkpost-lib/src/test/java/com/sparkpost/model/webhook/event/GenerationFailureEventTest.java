
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

public class GenerationFailureEventTest {

    private static final String EVENT_DATA = "{\n"
            + "                \"type\": \"generation_failure\",\n"
            + "                \"recv_method\": \"esmtp\",\n"
            + "                \"timestamp\": \"1416500167.241\",\n"
            + "                \"node_name\": \"Test-Node\",\n"
            + "                \"reason\": \"Example Generation Failed Message\",\n"
            + "                \"campaign_id\": \"Test Campaign Name\",\n"
            + "                \"template_id\": \"Test Template Name\",\n"
            + "                \"message_id\": \"778ff6d9-c412-4f58-a4fa-91d7cf5cc3dd\",\n"
            + "                \"error_code\": \"554\",\n"
            + "                \"binding_group\": \"default\",\n"
            + "                \"rcpt_to\": \"rcptTo@example.com\",\n"
            + "                \"binding\": \"default\",\n"
            + "                \"rcpt_meta\": {\n"
            + "                },\n"
            + "                \"template_version\": \"0\",\n"
            + "                \"rcpt_subs\": {\n"
            + "                },\n"
            + "                \"transmission_id\": \"65832150921904141\",\n"
            + "                \"customer_id\": \"1\",\n"
            + "                \"routing_domain\": \"example.com\",\n"
            + "                \"rcpt_tags\": [\n"
            + "                ]\n"
            + "            }";

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
        GenerationFailureEvent event = gson.fromJson(EVENT_DATA, GenerationFailureEvent.class);

        Assert.assertNotNull(event);

        Assert.assertEquals(event.getReason(), "Example Generation Failed Message");
        Assert.assertEquals(event.getTemplateVersion(), "0");
        Assert.assertNull(event.getFriendlyFrom());
        Assert.assertNull(event.getIpPool());
        Assert.assertNotNull(event.getRcptMeta());
        Assert.assertNotNull(event.getRcptTags());
        Assert.assertEquals(event.getType(), "generation_failure");
        Assert.assertNull(event.getRawRcptTo());
        Assert.assertNull(event.getEventId());
        Assert.assertEquals(event.getRcptTo(), "rcptTo@example.com");
        Assert.assertEquals(event.getRoutingDomain(), "example.com");
        Assert.assertNull(event.getSendingIp());
        Assert.assertNotNull(event.getRcptSubs());
        Assert.assertNull(event.getRawReason());
        Assert.assertEquals(event.getErrorCode(), "554");
        Assert.assertNull(event.getSubaccountId());
        Assert.assertEquals(event.getTemplateId(), "Test Template Name");
        Assert.assertEquals(event.getTransmissionId(), "65832150921904141");
        Assert.assertEquals(event.getCustomerId(), "1");
        Assert.assertEquals(event.getCampaignId(), "Test Campaign Name");
        Assert.assertEquals(event.getTimestamp(), "1416500167.241");

    }
}
