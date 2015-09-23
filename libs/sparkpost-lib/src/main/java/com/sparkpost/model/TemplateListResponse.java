package com.sparkpost.model;

import java.util.List;

import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class TemplateListResponse extends Response {

	@Description(value="List of TemplateItems")
	private List<TemplateItem> results;

}
