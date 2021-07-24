
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

public class DeliveryEventTest {

    private static final String EVENT_DATA = "{\n"
            + "    \"routing_domain\": \"example.com\",\n"
            + "    \"subject\": \"Sample Subject\",\n"
            + "    \"timestamp\": \"1493047462\",\n"
            + "    \"event_id\": \"66567146992683119\",\n"
            + "    \"customer_id\": \"1111\",\n"
            + "    \"rcpt_tags\": [],\n"
            + "    \"friendly_from\": \"test@test.example.com\",\n"
            + "    \"ip_pool\": \"shared\",\n"
            + "    \"rcpt_meta\": {},\n"
            + "    \"transmission_id\": \"66567146975691922\",\n"
            + "    \"type\": \"injection\",\n"
            + "    \"message_id\": \"0003a618fe58a7d2b6f3\",\n"
            + "    \"msg_size\": \"2907\",\n"
            + "    \"campaign_id\": \"test\",\n"
            + "    \"template_id\": \"template_66567146975691922\",\n"
            + "    \"msg_from\": \"msprvs1=17287cL4dnk4m=bounces-1521@spmailt.com\",\n"
            + "    \"template_version\": \"0\",\n"
            + "    \"sending_ip\": \"52.39.182.248\",\n"
            + "    \"rcpt_to\": \"test@example.com\",\n"
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
        DeliveryEvent event = gson.fromJson(EVENT_DATA, DeliveryEvent.class);

        Assert.assertNotNull(event);

        Assert.assertNull(event.getSmsSrcNpi());
        Assert.assertEquals(event.getTemplateVersion(), "0");
        Assert.assertEquals(event.getFriendlyFrom(), "test@test.example.com");
        Assert.assertEquals(event.getSubject(), "Sample Subject");
        Assert.assertEquals(event.getIpPool(), "shared");
        // Assert.assertNotNull(getRcptTags());
        Assert.assertEquals(event.getType(), "injection");
        Assert.assertNull(event.getNumRetries());
        Assert.assertNull(event.getSmsDstNpi());
        Assert.assertEquals(event.getRawRcptTo(), "test@example.com");
        Assert.assertNull(event.getSmsSrc());
        Assert.assertEquals(event.getMsgFrom(), "msprvs1=17287cL4dnk4m=bounces-1521@spmailt.com");
        Assert.assertEquals(event.getRcptTo(), "test@example.com");
        Assert.assertNull(event.getSubaccountId());
        Assert.assertEquals(event.getTransmissionId(), "66567146975691922");
        // private List<String> smsRemoteids(), "");
        Assert.assertEquals(event.getCampaignId(), "test");
        Assert.assertEquals(event.getTimestamp(), "1493047462");
        Assert.assertNull(event.getSmsCoding());
        // private Map<String, String> rcptMeta(), "");
        Assert.assertEquals(event.getMessageId(), "0003a618fe58a7d2b6f3");
        Assert.assertNull(event.getIpAddress());
        Assert.assertNull(event.getRcptType());
        Assert.assertNull(event.getQueueTime());
        Assert.assertNull(event.getSmsDst());
        Assert.assertEquals(event.getEventId(), "66567146992683119");
        Assert.assertEquals(event.getRoutingDomain(), "example.com");
        Assert.assertEquals(event.getSendingIp(), "52.39.182.248");
        Assert.assertNull(event.getSmsSrcTon());
        Assert.assertNull(event.getDeviceToken());
        Assert.assertNull(event.getSmsDstTon());
        Assert.assertEquals(event.getSmsSegments(), 0);
        Assert.assertEquals(event.getTemplateId(), "template_66567146975691922");
        Assert.assertNull(event.getDelvMethod());
        Assert.assertEquals(event.getCustomerId(), "1111");
        Assert.assertEquals(event.getMsgSize(), "2907");
    }
}
