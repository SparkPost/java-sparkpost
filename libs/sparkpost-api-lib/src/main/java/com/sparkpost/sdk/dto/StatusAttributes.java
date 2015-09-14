package com.sparkpost.sdk.dto;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class StatusAttributes extends Base {
	/**
	 * 
	 */
	@SerializedName("ownership_verified")
	private Boolean ownershipVerified;
	
	/**
	 * 
	 */
	@SerializedName("spf_status")
	private String spfStatus;
	
	
	/**
	 * 
	 */
	@SerializedName("compliance_status")
	private String complianceStatus;
	
	/**
	 * 
	 */
	@SerializedName("dkim_status")
	private String dkimStatus;
	
	/**
	 * 
	 */
	@SerializedName("abuse_at_status")
	private String abuseAtStatus;
	
	/**
	 * 
	 */
	@SerializedName("postmaster_at_status")
	private String postmasterAtStatus;
}
