package com.sparkpost.sdk.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DNSAttributes extends Base {

	/**
	 * DNS DKIM record for the registered Sending Domain
	 */
	private String dkimRecord;
	
	/**
	 * DNS SPF record for the registered Sending Domain
	 */
	private String spfRecord;
	
	/**
	 * Error message describing reason for DKIM verification failure
	 */
	private String dkimError;
	
	/**
	 * Error message describing reason for SPF verification failure
	 */
	private String spfError;
	
	
}
