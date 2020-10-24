
package com.sparkpost.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

public class SendingDomainTest {

    private String SENDING_DOMAIN_JSON = "{\n" + "    \"domain\": \"domain.com\",\n" + "    \"status\": { },\n" + "    \"dkim\": { }\n" + "}";

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
        SendingDomain sendingDomain = gson.fromJson(this.SENDING_DOMAIN_JSON, SendingDomain.class);
        Assert.assertNotNull(sendingDomain);

        Assert.assertEquals(sendingDomain.getDomain(), "domain.com");

        StatusAttributes status = sendingDomain.getStatus();
        // Internal decoding of this data is tested in it's own tests
        Assert.assertNotNull(status);

        DKIM dkim = sendingDomain.getDkim();
        // Internal decoding of this data is tested in it's own tests
        Assert.assertNotNull(dkim);

    }

}
