
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

public class BounceEventTest {

    private static final String EVENT_DATA = "{\n"
            + "    \"msg_from\": \"msprvs1=17287cL4dnk4m=bounces-1521@spmailt.com\",\n"
            + "    \"campaign_id\": \"test\",\n"
            + "    \"routing_domain\": \"example.com\",\n"
            + "    \"type\": \"bounce\",\n"
            + "    \"customer_id\": \"1111\",\n"
            + "    \"ip_address\": \"74.125.28.26\",\n"
            + "    \"ip_pool\": \"shared\",\n"
            + "    \"queue_time\": \"160\",\n"
            + "    \"subject\": \"My Sample Subject\",\n"
            + "    \"rcpt_meta\": {\n"
            + "        \"myKey\": \"myValue\"\n"
            + "    },\n"
            + "    \"timestamp\": \"1493050627\",\n"
            + "    \"msg_size\": \"3125\",\n"
            + "    \"rcpt_to\": \"nonExist@example.com\",\n"
            + "    \"bounce_class\": \"10\",\n"
            + "    \"transmission_id\": \"84579375438919573\",\n"
            + "    \"error_code\": \"550\",\n"
            + "    \"template_version\": \"0\",\n"
            + "    \"sending_ip\": \"35.160.4.15\",\n"
            + "    \"template_id\": \"template_84579375438919573\",\n"
            + "    \"message_id\": \"00040325fe58f1b2ca62\",\n"
            + "    \"reason\": \"550-5.1.1 The email account that you tried to reach does not exist\",\n"
            + "    \"friendly_from\": \"test@test.example.com\",\n"
            + "    \"raw_reason\": \"550-5.1.1 The email account that you tried to reach does not exist.\",\n"
            + "    \"event_id\": \"84579375510121478\",\n"
            + "    \"num_retries\": \"0\",\n"
            + "    \"rcpt_tags\": [],\n"
            + "    \"raw_rcpt_to\": \"nonExist@example.com\"\n"
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
        BounceEvent event = gson.fromJson(BounceEventTest.EVENT_DATA, BounceEvent.class);

        Assert.assertNotNull(event);

        Assert.assertEquals(event.getReason(), "550-5.1.1 The email account that you tried to reach does not exist");
        Assert.assertNull(event.getSmsSrcNpi());
        Assert.assertEquals(event.getTemplateVersion(), "0");
        Assert.assertEquals(event.getFriendlyFrom(), "test@test.example.com");
        Assert.assertEquals(event.getSubject(), "My Sample Subject");
        Assert.assertEquals(event.getIpPool(), "shared");
        Assert.assertNotNull(event.getRcptTags());
        Assert.assertEquals(event.getType(), "bounce");
        Assert.assertEquals(event.getNumRetries(), "0");
        Assert.assertNull(event.getSmsDstNpi());
        Assert.assertEquals(event.getBounceClass(), "10");
        Assert.assertEquals(event.getRawRcptTo(), "nonExist@example.com");
        Assert.assertNull(event.getSmsSrc());
        Assert.assertEquals(event.getMsgFrom(), "msprvs1=17287cL4dnk4m=bounces-1521@spmailt.com");
        Assert.assertEquals(event.getRcptTo(), "nonExist@example.com");
        Assert.assertNull(event.getSubaccountId());
        Assert.assertEquals(event.getTransmissionId(), "84579375438919573");
        Assert.assertEquals(event.getCampaignId(), "test");
        Assert.assertEquals(event.getTimestamp(), "1493050627");
        Assert.assertNull(event.getSmsCoding());
        Assert.assertNotNull(event.getRcptMeta());
        Assert.assertEquals(event.getMessageId(), "00040325fe58f1b2ca62");
        Assert.assertEquals(event.getIpAddress(), "74.125.28.26");
        Assert.assertNull(event.getRcptType());
        Assert.assertNull(event.getSmsDst());
        Assert.assertEquals(event.getEventId(), "84579375510121478");
        Assert.assertEquals(event.getRoutingDomain(), "example.com");
        Assert.assertEquals(event.getSendingIp(), "35.160.4.15");
        Assert.assertNull(event.getSmsSrcTon());
        Assert.assertNull(event.getDeviceToken());
        Assert.assertEquals(event.getRawReason(), "550-5.1.1 The email account that you tried to reach does not exist.");
        Assert.assertNull(event.getSmsDstTon());
        Assert.assertEquals(event.getErrorCode(), "550");
        Assert.assertEquals(event.getTemplateId(), "template_84579375438919573");
        Assert.assertNull(event.getDelvMethod());
        Assert.assertEquals(event.getCustomerId(), "1111");
        Assert.assertEquals(event.getMsgSize(), "3125");

    }
}
