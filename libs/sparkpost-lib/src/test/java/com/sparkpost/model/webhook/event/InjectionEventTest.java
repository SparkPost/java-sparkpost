
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

public class InjectionEventTest {

    private static final String EVENT_DATA = "{\n"
            + "        \"template_id\": \"template_48550572361967639\",\n"
            + "        \"customer_id\": \"1521\",\n"
            + "        \"rcpt_tags\": [],\n"
            + "        \"ip_pool\": \"shared\",\n"
            + "        \"message_id\": \"0002ebedfd581727d390\",\n"
            + "        \"rcpt_meta\": {},\n"
            + "        \"rcpt_to\": \"test@example.com\",\n"
            + "        \"transmission_id\": \"48550572361967639\",\n"
            + "        \"timestamp\": \"1493036523\",\n"
            + "        \"type\": \"injection\",\n"
            + "        \"msg_size\": \"2912\",\n"
            + "        \"template_version\": \"0\",\n"
            + "        \"sending_ip\": \"52.39.182.248\",\n"
            + "        \"subject\": \"My Sample Subject\",\n"
            + "        \"event_id\": \"48550572425541961\",\n"
            + "        \"routing_domain\": \"example.com\",\n"
            + "        \"friendly_from\": \"test@test.example.com\",\n"
            + "        \"msg_from\": \"msprvs1=17287cL4dnk4m=bounces-1521@spmailt.com\",\n"
            + "        \"campaign_id\": \"test\",\n"
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
        InjectionEvent event = gson.fromJson(EVENT_DATA, InjectionEvent.class);

        Assert.assertNotNull(event);

        Assert.assertNull(event.getSmsSrcNpi());
        Assert.assertEquals(event.getTemplateVersion(), "0");
        Assert.assertEquals(event.getFriendlyFrom(), "test@test.example.com");
        Assert.assertEquals(event.getSubject(), "My Sample Subject");
        Assert.assertEquals(event.getIpPool(), "shared");
        //Assert.assertNull(event.getRcptTags());
        Assert.assertEquals(event.getType(), "injection");
        Assert.assertNull(event.getSmsDstNpi());
        Assert.assertEquals(event.getRawRcptTo(), "test@example.com");
        Assert.assertNull(event.getSmsText());
        Assert.assertNull(event.getSmsSrc());
        Assert.assertEquals(event.getMsgFrom(), "msprvs1=17287cL4dnk4m=bounces-1521@spmailt.com");
        Assert.assertEquals(event.getRcptTo(), "test@example.com");
        Assert.assertNull(event.getSubaccountId());
        Assert.assertEquals(event.getTransmissionId(), "48550572361967639");
        Assert.assertEquals(event.getCampaignId(), "test");
        Assert.assertEquals(event.getTimestamp(), "1493036523");
        Assert.assertNull(event.getSmsCoding());
        Assert.assertNotNull(event.getRcptMeta());
        Assert.assertEquals(event.getMessageId(), "0002ebedfd581727d390");
        Assert.assertNull(event.getRcptType());
        Assert.assertNull(event.getSmsDst());
        Assert.assertEquals(event.getEventId(), "48550572425541961");
        Assert.assertEquals(event.getRoutingDomain(), "example.com");
        Assert.assertEquals(event.getSendingIp(), "52.39.182.248");
        Assert.assertNull(event.getSmsSrcTon());
        Assert.assertNull(event.getSmsDstTon());
        Assert.assertEquals(event.getSmsSegments(), 0);
        Assert.assertEquals(event.getTemplateId(), "template_48550572361967639");
        Assert.assertEquals(event.getCustomerId(), "1521");
        Assert.assertEquals(event.getMsgSize(), "2912");

    }
}
