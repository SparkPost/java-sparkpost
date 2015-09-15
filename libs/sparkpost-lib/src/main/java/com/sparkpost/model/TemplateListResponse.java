package com.sparkpost.model;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class TemplateListResponse extends Response {

	private List<TemplateItem> results;

}
