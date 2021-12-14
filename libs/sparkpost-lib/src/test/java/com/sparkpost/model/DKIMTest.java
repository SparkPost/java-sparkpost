
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

public class DKIMTest {

    private static final String DKIM_JSON = "{\n"
            + "      \"headers\": \"from:to:subject:date\",\n"
            + "      \"public\": \"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC+W6scd3XWwvC/hPRksfDYFi3ztgyS9OSqnnjtNQeDdTSD1DRx/xFar2wjmzxp2+SnJ5pspaF77VZveN3P/HVmXZVghr3asoV9WBx/uW1nDIUxU35L4juXiTwsMAbgMyh3NqIKTNKyMDy4P8vpEhtH1iv/BrwMdBjHDVCycB8WnwIDAQAB\",\n"
            + "      \"private\": \"some private key\",\n"
            + "      \"selector\": \"hello_selector\"\n"
            + "    }";

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
    public void testDecodeDKIM() {
        Gson gson = new Gson();
        DKIM dkim = gson.fromJson(DKIM_JSON, DKIM.class);

        Assert.assertNotNull(dkim);

        /**
         * "{\n" +
         * " \"headers\": \"from:to:subject:date\",\n" +
         * " \"public\":
         * \"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC+W6scd3XWwvC/hPRksfDYFi3ztgyS9OSqnnjtNQeDdTSD1DRx/xFar2wjmzxp2+SnJ5pspaF77VZveN3P/HVmXZVghr3asoV9WBx/uW1nDIUxU35L4juXiTwsMAbgMyh3NqIKTNKyMDy4P8vpEhtH1iv/BrwMdBjHDVCycB8WnwIDAQAB\",\n"
         * +
         * " \"private\": \"some private key\",\n" +
         * " \"selector\": \"hello_selector\"\n" +
         * " }";
         */
        Assert.assertNotNull(dkim);
        Assert.assertEquals(dkim.getHeaders(), "from:to:subject:date");
        Assert
                .assertEquals(
                        dkim.getPublicKey(),
                        "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC+W6scd3XWwvC/hPRksfDYFi3ztgyS9OSqnnjtNQeDdTSD1DRx/xFar2wjmzxp2+SnJ5pspaF77VZveN3P/HVmXZVghr3asoV9WBx/uW1nDIUxU35L4juXiTwsMAbgMyh3NqIKTNKyMDy4P8vpEhtH1iv/BrwMdBjHDVCycB8WnwIDAQAB");
        Assert.assertEquals(dkim.getPrivateKey(), "some private key");
        Assert.assertEquals(dkim.getSelector(), "hello_selector");
    }

    /**
     *
     */
    @Test
    public void testDKIMRoundTrip() {
        Gson gson = new Gson();

        DKIM dkim = gson.fromJson(DKIM_JSON, DKIM.class);
        Assert.assertNotNull(dkim);

        String dkim_json = dkim.toJson();
        DKIM dkim2 = gson.fromJson(dkim_json, DKIM.class);
        Assert.assertNotNull(dkim2);

        /**
         * "{\n" +
         * " \"headers\": \"from:to:subject:date\",\n" +
         * " \"public\":
         * \"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC+W6scd3XWwvC/hPRksfDYFi3ztgyS9OSqnnjtNQeDdTSD1DRx/xFar2wjmzxp2+SnJ5pspaF77VZveN3P/HVmXZVghr3asoV9WBx/uW1nDIUxU35L4juXiTwsMAbgMyh3NqIKTNKyMDy4P8vpEhtH1iv/BrwMdBjHDVCycB8WnwIDAQAB\",\n"
         * +
         * " \"private\": \"some private key\",\n" +
         * " \"selector\": \"hello_selector\"\n" +
         * " }";
         */
        Assert.assertEquals(dkim.getHeaders(), dkim2.getHeaders());
        Assert.assertEquals(dkim.getPublicKey(), dkim2.getPublicKey());
        Assert.assertEquals(dkim.getPrivateKey(), dkim2.getPrivateKey());
        Assert.assertEquals(dkim.getSelector(), dkim2.getSelector());
    }

}
