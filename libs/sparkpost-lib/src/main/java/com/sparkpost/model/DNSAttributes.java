package com.sparkpost.model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DNSAttributes extends Base {

	/**
	 * DNS DKIM record for the registered Sending Domain
	 */
	@SerializedName("dkim_record")
	private String dkimRecord;
	
	/**
	 * DNS SPF record for the registered Sending Domain
	 */
	@SerializedName("spf_record")
	private String spfRecord;
	
	/**
	 * Error message describing reason for DKIM verification failure
	 */
	@SerializedName("dkim_error")
	private String dkimError;
	
	/**
	 * Error message describing reason for SPF verification failure
	 */
	@SerializedName("spf_error")
	private String spfError;
}
