package com.sparkpost.sdk.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DKIMResults extends Base {

	private VerifyStatus status;
	
	private DKIM dkim;
	
}
