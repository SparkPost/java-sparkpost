
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

public class OpenEventTest {

    private static final String EVENT_DATA = "{\n"
            + "    \"rcpt_meta\": {\n"
            + "        \"myKey\": \"myVal\"\n"
            + "    },\n"
            + "    \"ip_address\": \"66.102.8.147\",\n"
            + "    \"rcpt_tags\": [],\n"
            + "    \"rcpt_to\": \"test@example.com\",\n"
            + "    \"template_version\": \"0\",\n"
            + "    \"sending_ip\": \"52.39.26.144\",\n"
            + "    \"message_id\": \"0001d25bfe58f914d695\",\n"
            + "    \"event_id\": \"102595298805510121\",\n"
            + "    \"template_id\": \"template_30539097546350056\",\n"
            + "    \"timestamp\": \"1493064668\",\n"
            + "    \"campaign_id\": \"test\",\n"
            + "    \"delv_method\": \"esmtp\",\n"
            + "    \"transmission_id\": \"30539097546350056\",\n"
            + "    \"type\": \"open\",\n"
            + "    \"user_agent\": \"Mozilla/5.0 (Windows NT 5.1; rv:11.0) Gecko Firefox/11.0 (via ggpht.com GoogleImageProxy)\",\n"
            + "    \"ip_pool\": \"shared\",\n"
            + "    \"customer_id\": \"1111\",\n"
            + "    \"geo_ip\": {\n"
            + "        \"country\": \"US\",\n"
            + "        \"region\": \"NY\",\n"
            + "        \"city\": \"Bronx\",\n"
            + "        \"latitude\": 40.8499,\n"
            + "        \"longitude\": -73.8769\n"
            + "    },\n"
            + "    \"raw_rcpt_to\": \"test@example.com\"\n"
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
        OpenEvent event = gson.fromJson(OpenEventTest.EVENT_DATA, OpenEvent.class);

        Assert.assertNotNull(event);

        Assert.assertEquals(event.getTemplateVersion(), "0");
        Assert.assertNull(event.getFriendlyFrom());
        Assert.assertNull(event.getSubject());
        Assert.assertEquals(event.getIpPool(), "shared");
        Assert.assertNotNull(event.getRcptTags());
        Assert.assertEquals(event.getType(), "open");
        Assert.assertNull(event.getNumRetries());
        Assert.assertEquals(event.getRawRcptTo(), "test@example.com");
        Assert.assertNull(event.getMsgFrom());
        Assert.assertEquals(event.getRcptTo(), "test@example.com");
        Assert.assertNotNull(event.getGeoIp());
        Assert.assertNull(event.getSubaccountId());
        Assert.assertEquals(event.getTransmissionId(), "30539097546350056");
        Assert.assertEquals(event.getCampaignId(), "test");
        Assert.assertEquals(event.getUserAgent(), "Mozilla/5.0 (Windows NT 5.1; rv:11.0) Gecko Firefox/11.0 (via ggpht.com GoogleImageProxy)");
        Assert.assertEquals(event.getTimestamp(), "1493064668");
        Assert.assertNotNull(event.getRcptMeta());
        Assert.assertEquals(event.getMessageId(), "0001d25bfe58f914d695");
        Assert.assertEquals(event.getIpAddress(), "66.102.8.147");
        Assert.assertNull(event.getRcptType());
        Assert.assertNull(event.getQueueTime());
        Assert.assertEquals(event.getEventId(), "102595298805510121");
        Assert.assertNull(event.getRoutingDomain());
        Assert.assertEquals(event.getSendingIp(), "52.39.26.144");
        Assert.assertEquals(event.getTemplateId(), "template_30539097546350056");
        Assert.assertEquals(event.getDelvMethod(), "esmtp");
        Assert.assertEquals(event.getCustomerId(), "1111");
        Assert.assertNull(event.getMsgSize());
    }
}
