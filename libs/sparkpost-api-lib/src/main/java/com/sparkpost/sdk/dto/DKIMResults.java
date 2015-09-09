package com.sparkpost.sdk.dto;

import lombok.Data;

@Data
public class DKIMResults {

	/**
	 * 
	 */
	private Boolean ownershipVerified;
	
	/**
	 * 
	 */
	private String spfStatus;
	
	/**
	 * 
	 */
	private DNSAttributes dns;
	
	/**
	 * 
	 */
	private String complianceStatus;
	
	/**
	 * 
	 */
	private String dkimStatus;
	
	/**
	 * 
	 */
	private String abuseAtStatus;
	
	/**
	 * 
	 */
	private String postmasterAtStatus;
	
}
