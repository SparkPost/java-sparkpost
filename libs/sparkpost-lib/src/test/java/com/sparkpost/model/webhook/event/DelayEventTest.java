
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

public class DelayEventTest {

    private static final String EVENT_DATA = "{\n"
            + "        \"raw_reason\": \"454 4.4.4 [internal] no MX or A for domain\",\n"
            + "        \"rcpt_meta\": {},\n"
            + "        \"message_id\": \"0002ebedfd581727d390\",\n"
            + "        \"template_id\": \"template_48550572361967639\",\n"
            + "        \"campaign_id\": \"123456\",\n"
            + "        \"queue_time\": \"28\",\n"
            + "        \"transmission_id\": \"48550572361967639\",\n"
            + "        \"bounce_class\": \"21\",\n"
            + "        \"type\": \"delay\",\n"
            + "        \"rcpt_to\": \"test@example.com\",\n"
            + "        \"ip_pool\": \"shared\",\n"
            + "        \"timestamp\": \"1493036523\",\n"
            + "        \"template_version\": \"0\",\n"
            + "        \"customer_id\": \"111111\",\n"
            + "        \"msg_size\": \"2912\",\n"
            + "        \"subject\": \"My Sample Subject\",\n"
            + "        \"error_code\": \"454\",\n"
            + "        \"routing_domain\": \"example.com\",\n"
            + "        \"sending_ip\": \"52.39.182.248\",\n"
            + "        \"next_attempt\": \"1493037723\",\n"
            + "        \"reason\": \"454 4.4.4 [internal] no MX or A for domain\",\n"
            + "        \"friendly_from\": \"test@test.example.com\",\n"
            + "        \"event_id\": \"48550572425541972\",\n"
            + "        \"num_retries\": \"3\",\n"
            + "        \"msg_from\": \"msprvs1=17287cL4dnk4m=bounces-1521@spmailt.com\",\n"
            + "        \"rcpt_tags\": [],\n"
            + "        \"raw_rcpt_to\": \"test@example.com\"\n"
            + "      }";

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
        DelayEvent event = gson.fromJson(DelayEventTest.EVENT_DATA, DelayEvent.class);

        Assert.assertNotNull(event);

        Assert.assertEquals(event.getReason(), "454 4.4.4 [internal] no MX or A for domain");
        Assert.assertNull(event.getSmsSrcNpi());
        Assert.assertEquals(event.getTemplateVersion(), "0");
        Assert.assertEquals(event.getFriendlyFrom(), "test@test.example.com");
        Assert.assertEquals(event.getSubject(), "My Sample Subject");
        Assert.assertEquals(event.getIpPool(), "shared");
        Assert.assertEquals(event.getRcptTags().size(), 0);
        Assert.assertEquals(event.getType(), "delay");
        Assert.assertEquals(event.getNumRetries(), "3");
        Assert.assertNull(event.getSmsDstNpi());
        Assert.assertEquals(event.getBounceClass(), "21");
        Assert.assertEquals(event.getRawRcptTo(), "test@example.com");
        Assert.assertNull(event.getSmsSrc());
        Assert.assertEquals(event.getMsgFrom(), "msprvs1=17287cL4dnk4m=bounces-1521@spmailt.com");
        Assert.assertEquals(event.getRcptTo(), "test@example.com");
        Assert.assertNull(event.getSubaccountId());

        Assert.assertEquals(event.getTransmissionId(), "48550572361967639");
        Assert.assertEquals(event.getCampaignId(), "123456");
        Assert.assertEquals(event.getTimestamp(), "1493036523");
        Assert.assertNull(event.getSmsCoding());
        //Assert.assertEquals(event.getRcptMeta(), "");
        Assert.assertEquals(event.getMessageId(), "0002ebedfd581727d390");
        Assert.assertNull(event.getIpAddress());
        Assert.assertNull(event.getRcptType());
        Assert.assertEquals(event.getQueueTime(), "28");
        Assert.assertNull(event.getSmsDst());
        Assert.assertEquals(event.getEventId(), "48550572425541972");
        Assert.assertEquals(event.getRoutingDomain(), "example.com");
        Assert.assertEquals(event.getSendingIp(), "52.39.182.248");
        Assert.assertNull(event.getSmsSrcTon());
        Assert.assertNull(event.getDeviceToken());
        Assert.assertEquals(event.getRawReason(), "454 4.4.4 [internal] no MX or A for domain");
        Assert.assertNull(event.getSmsDstTon());
        Assert.assertEquals(event.getErrorCode(), "454");
        Assert.assertEquals(event.getTemplateId(), "template_48550572361967639");
        Assert.assertNull(event.getDelvMethod());
        Assert.assertEquals(event.getCustomerId(), "111111");
        Assert.assertEquals(event.getMsgSize(), "2912");
    }
}
