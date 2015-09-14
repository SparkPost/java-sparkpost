package com.sparkpost.sdk.dto;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DKIM uses a pair of public and private keys to authenticate your emails.
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DKIM extends Base {

	/**
	 * DKIM private key
	 * 
	 * The private key will be used to create the DKIM Signature.
	 */
	@SerializedName("private")
	private String privateKey;

	/**
	 * DKIM public key
	 * 
	 * The public key will be retrieved from DNS of the sending domain.
	 */
	@SerializedName("public")
	private String publicKey;

	/**
	 * DomainKey selector
	 * 
	 * The DomainKey selector will be used to indicate the DKIM public key location.
	 */
	private String selector;

	/**
	 * Header fields to be included in the DKIM signature
	 * 
	 * Header fields are separated by a colon. Example: "from:to:subject:date"
	 */
	private String headers;
}
