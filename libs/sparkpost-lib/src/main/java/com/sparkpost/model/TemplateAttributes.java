package com.sparkpost.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO for storing a template.
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TemplateAttributes extends Base {

	/**
	 * Short, unique, alphanumeric ID used to reference the template
	 * 
	 * After a template has been created, this property cannot be changed. Maximum length - 64 bytes
	 */
	private String id;

	/**
	 * Content that will be used to construct a message
	 * 
	 * For a full description, see the Content Attributes. Maximum length - 15 MBs
	 */
	private TemplateContentAttributes content;

	/**
	 * Whether the template is published or is a draft version
	 * 
	 * A template cannot be changed from published to draft.
	 */
	private Boolean published;

	/**
	 * Editable display name
	 * 
	 * The name does not have to be unique. Maximum length - 1024 bytes
	 */
	private String name;

	/**
	 * Detailed description of the template
	 * 
	 * Maximum length - 1024 bytes
	 */
	private String description;

	/**
	 * TemplateOptions in which template options are defined
	 * 
	 * For a full description, see the Options Attributes.
	 */
	private OptionsAttributes options;

}
