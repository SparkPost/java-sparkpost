package com.sparkpost.sdk.dto;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.Gson;

public class DKIMResultsTest {

	private String DKIM_RESULT_JSON = "{\n" + 
			"    \"status\": {\n" + 
			"      \"ownership_verified\": false,\n" + 
			"      \"spf_status\": \"pending\",\n" + 
			"      \"abuse_at_status\": \"pending\",\n" + 
			"      \"dkim_status\": \"pending\",\n" + 
			"      \"compliance_status\": \"pending\",\n" + 
			"      \"postmaster_at_status\": \"pending\"\n" + 
			"    },\n" + 
			"    \"dkim\": {\n" + 
			"      \"headers\": \"from:to:subject:date\",\n" + 
			"      \"public\": \"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC+W6scd3XWwvC/hPRksfDYFi3ztgyS9OSqnnjtNQeDdTSD1DRx/xFar2wjmzxp2+SnJ5pspaF77VZveN3P/HVmXZVghr3asoV9WBx/uW1nDIUxU35L4juXiTwsMAbgMyh3NqIKTNKyMDy4P8vpEhtH1iv/BrwMdBjHDVCycB8WnwIDAQAB\",\n" +
			"      \"private\": \"some private key\",\n" +
			"      \"selector\": \"hello_selector\"\n" + 
			"    }\n" + 
			"}";
	
	@BeforeClass
	public static void setUpClass() {
		Logger.getRootLogger().setLevel(Level.DEBUG);
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
	public void testDecodeDKIMResults() {
		Gson gson = new Gson();
		DKIMResults results = gson.fromJson(DKIM_RESULT_JSON, DKIMResults.class);
		Assert.assertNotNull(results);

		/**
		 * \"status\": {\n" + 
			"      \"ownership_verified\": false,\n" + 
			"      \"spf_status\": \"pending\",\n" + 
			"      \"abuse_at_status\": \"pending\",\n" + 
			"      \"dkim_status\": \"pending\",\n" + 
			"      \"compliance_status\": \"pending\",\n" + 
			"      \"postmaster_at_status\": \"pending\"\n" + 
			"    },\n" + 
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
		 * "    \"dkim\": {\n" + 
			"      \"headers\": \"from:to:subject:date\",\n" + 
			"      \"public\": \"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC+W6scd3XWwvC/hPRksfDYFi3ztgyS9OSqnnjtNQeDdTSD1DRx/xFar2wjmzxp2+SnJ5pspaF77VZveN3P/HVmXZVghr3asoV9WBx/uW1nDIUxU35L4juXiTwsMAbgMyh3NqIKTNKyMDy4P8vpEhtH1iv/BrwMdBjHDVCycB8WnwIDAQAB\",\n" + 
			"      \"selector\": \"hello_selector\"\n" + 
			"    }\n" + 
		 */
		DKIM dkim = results.getDkim(); 
		Assert.assertNotNull(dkim);
		Assert.assertEquals(dkim.getHeaders(), "from:to:subject:date");
		Assert.assertEquals(dkim.getPublicKey(), "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC+W6scd3XWwvC/hPRksfDYFi3ztgyS9OSqnnjtNQeDdTSD1DRx/xFar2wjmzxp2+SnJ5pspaF77VZveN3P/HVmXZVghr3asoV9WBx/uW1nDIUxU35L4juXiTwsMAbgMyh3NqIKTNKyMDy4P8vpEhtH1iv/BrwMdBjHDVCycB8WnwIDAQAB");
		Assert.assertEquals(dkim.getPrivateKey(), "some private key");
		Assert.assertEquals(dkim.getSelector(), "hello_selector");
	}
	
	
	/**
	 * 
	 */
	@Test
	public void testDKIMResultsRoundTrip() {
		Gson gson = new Gson();
		DKIMResults result1 = gson.fromJson(DKIM_RESULT_JSON, DKIMResults.class);
		Assert.assertNotNull(result1);
		
		String dkimResultsJson = result1.toJson();
		DKIMResults result2 = gson.fromJson(dkimResultsJson, DKIMResults.class);
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
		Assert.assertEquals(dkim1.getPrivateKey(), dkim2.getPrivateKey());
		Assert.assertEquals(dkim1.getSelector(), dkim2.getSelector());
	}
	
}
