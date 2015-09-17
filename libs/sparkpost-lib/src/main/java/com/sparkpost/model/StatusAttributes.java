package com.sparkpost.model;

import com.google.gson.annotations.SerializedName;
import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class StatusAttributes extends Base {
	/**
	 * 
	 */
	@Description(value="True if ownership is verified")
	@SerializedName("ownership_verified")
	private Boolean ownershipVerified;
	
	/**
	 * 
	 */
	@Description(value="Current SPF Status")
	@SerializedName("spf_status")
	private String spfStatus;
	
	
	/**
	 * 
	 */
	@Description(value="DKIM Compliance status")
	@SerializedName("compliance_status")
	private String complianceStatus;
	
	/**
	 * 
	 */
	@Description(value="DKIM status")
	@SerializedName("dkim_status")
	private String dkimStatus;
	
	/**
	 * 
	 */
	@Description(value="Abuse status")
	@SerializedName("abuse_at_status")
	private String abuseAtStatus;
	
	/**
	 * 
	 */
	@Description(value="Postmaster status")
	@SerializedName("postmaster_at_status")
	private String postmasterAtStatus;
}
