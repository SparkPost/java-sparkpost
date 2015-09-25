package com.sparkpost.model.responses;

import com.sparkpost.model.AddressAttributes;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class TemplatePreviewResponse extends Response {
	@Data
	public static final class TemplatePreviewData {
		private String subject;
		private AddressAttributes from;
		private String html;
	}
	
	private TemplatePreviewData results;
}
