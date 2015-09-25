package com.sparkpost.model.responses;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class TemplateCreateResponse extends Response {

	@Data
	public static final class CreateResult {
		private String id;
	}
	
	private CreateResult results;
}
