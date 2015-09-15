package com.sparkpost.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DKIMResults extends Base {

	private StatusAttributes status;
	
	private DKIM dkim;
	
}
