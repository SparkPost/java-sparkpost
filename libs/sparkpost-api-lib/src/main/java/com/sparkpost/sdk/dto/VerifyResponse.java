package com.sparkpost.sdk.dto;

import lombok.Data;

/**
 * The verify resource validates the specified verification field types.
 * Including the fields "dkim_verify" and "spf_verify" in the request initiates
 * a check against the associated DNS record type for the specified sending
 * domain. The domain's "status" object is returned on success.
 * 
 */
@Data
public class VerifyResponse {
	
	private DKIMResults results;
}
