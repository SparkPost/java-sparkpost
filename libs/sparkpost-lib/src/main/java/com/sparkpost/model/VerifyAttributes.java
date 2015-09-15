package com.sparkpost.model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO for storing a Sending Domain verification request.
 *
 * @author grava
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class VerifyAttributes extends Base {
	
	/**
	 * Request verification of DKIM record
	 */
	@SerializedName("dkim_verify")
	private Boolean dkimVerify = null;
	
	/**
	 * Request verification of SPF record
	 */
	@SerializedName("spf_verify")
	private Boolean spfVerify = null;
}
