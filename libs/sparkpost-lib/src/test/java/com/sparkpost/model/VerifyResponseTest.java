
package com.sparkpost.model;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.Gson;

public class VerifyResponseTest {

    private String VERIFY_RESPONSE_JSON = "{\n"
            + "  \"results\": {\n"
            + "    \"status\": {\n"
            + "      \"ownership_verified\": false,\n"
            + "      \"spf_status\": \"pending\",\n"
            + "      \"abuse_at_status\": \"pending\",\n"
            + "      \"dkim_status\": \"pending\",\n"
            + "      \"compliance_status\": \"pending\",\n"
            + "      \"postmaster_at_status\": \"pending\"\n"
            + "    },\n"
            + "    \"dkim\": {\n"
            + "      \"headers\": \"from:to:subject:date\",\n"
            + "      \"public\": \"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC+W6scd3XWwvC/hPRksfDYFi3ztgyS9OSqnnjtNQeDdTSD1DRx/xFar2wjmzxp2+SnJ5pspaF77VZveN3P/HVmXZVghr3asoV9WBx/uW1nDIUxU35L4juXiTwsMAbgMyh3NqIKTNKyMDy4P8vpEhtH1iv/BrwMdBjHDVCycB8WnwIDAQAB\",\n"
            + "      \"selector\": \"hello_selector\"\n"
            + "    }\n"
            + "  }\n"
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
    public void testDecodeVerifyResponse() {
        Gson gson = new Gson();
        VerifyResponse verifyResponse = gson.fromJson(this.VERIFY_RESPONSE_JSON, VerifyResponse.class);

        DKIMResults results = verifyResponse.getResults();
        Assert.assertNotNull(results);

        /**
         * \"status\": {\n" +
         * " \"ownership_verified\": false,\n" +
         * " \"spf_status\": \"pending\",\n" +
         * " \"abuse_at_status\": \"pending\",\n" +
         * " \"dkim_status\": \"pending\",\n" +
         * " \"compliance_status\": \"pending\",\n" +
         * " \"postmaster_at_status\": \"pending\"\n" +
         * " },\n" +
         */
        StatusAttributes status = results.getStatus();
        Assert.assertNotNull(status);
        Assert.assertFalse(status.getOwnershipVerified());
        Assert.assertEquals(status.getSpfStatus(), "pending");
        Assert.assertEquals(status.getAbuseAtStatus(), "pending");
        Assert.assertEquals(status.getDkimStatus(), "pending");
        Assert.assertEquals(status.getComplianceStatus(), "pending");
        Assert.assertEquals(status.getPostmasterAtStatus(), "pending");

        /**
         * " \"dkim\": {\n" +
         * " \"headers\": \"from:to:subject:date\",\n" +
         * " \"public\":
         * \"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC+W6scd3XWwvC/hPRksfDYFi3ztgyS9OSqnnjtNQeDdTSD1DRx/xFar2wjmzxp2+SnJ5pspaF77VZveN3P/HVmXZVghr3asoV9WBx/uW1nDIUxU35L4juXiTwsMAbgMyh3NqIKTNKyMDy4P8vpEhtH1iv/BrwMdBjHDVCycB8WnwIDAQAB\",\n"
         * +
         * " \"selector\": \"hello_selector\"\n" +
         * " }\n" +
         */
        DKIM dkim = results.getDkim();
        Assert.assertNotNull(dkim);
        Assert.assertEquals(dkim.getHeaders(), "from:to:subject:date");
        Assert
                .assertEquals(
                        dkim.getPublicKey(),
                        "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC+W6scd3XWwvC/hPRksfDYFi3ztgyS9OSqnnjtNQeDdTSD1DRx/xFar2wjmzxp2+SnJ5pspaF77VZveN3P/HVmXZVghr3asoV9WBx/uW1nDIUxU35L4juXiTwsMAbgMyh3NqIKTNKyMDy4P8vpEhtH1iv/BrwMdBjHDVCycB8WnwIDAQAB");
        Assert.assertEquals(dkim.getSelector(), "hello_selector");
    }

    /**
     *
     */
    @Test
    public void testVerifyRoundTrip() {
        Gson gson = new Gson();
        VerifyResponse verifyResponse = gson.fromJson(this.VERIFY_RESPONSE_JSON, VerifyResponse.class);
        Assert.assertNotNull(verifyResponse);

        String verifyJson = verifyResponse.toJson();
        VerifyResponse otherVerifyResponse = gson.fromJson(verifyJson, VerifyResponse.class);
        Assert.assertNotNull(otherVerifyResponse);

        DKIMResults result1 = verifyResponse.getResults();
        Assert.assertNotNull(result1);

        DKIMResults result2 = otherVerifyResponse.getResults();
        Assert.assertNotNull(result2);

        StatusAttributes status1 = result1.getStatus();
        Assert.assertNotNull(status1);
        StatusAttributes status2 = result2.getStatus();
        Assert.assertNotNull(status2);

        //Assert.assertFalse(status.getOwnershipVerified());
        Assert.assertEquals(status1.getOwnershipVerified(), status2.getOwnershipVerified());
        Assert.assertEquals(status1.getSpfStatus(), status2.getSpfStatus());
        Assert.assertEquals(status1.getAbuseAtStatus(), status2.getAbuseAtStatus());
        Assert.assertEquals(status1.getDkimStatus(), status2.getDkimStatus());
        Assert.assertEquals(status1.getComplianceStatus(), status2.getComplianceStatus());
        Assert.assertEquals(status1.getPostmasterAtStatus(), status2.getPostmasterAtStatus());

        DKIM dkim1 = result1.getDkim();
        Assert.assertNotNull(dkim1);
        DKIM dkim2 = result2.getDkim();
        Assert.assertNotNull(dkim2);

        Assert.assertEquals(dkim1.getHeaders(), dkim2.getHeaders());
        Assert.assertEquals(dkim1.getPublicKey(), dkim2.getPublicKey());
        Assert.assertEquals(dkim1.getSelector(), dkim2.getSelector());
    }

}
