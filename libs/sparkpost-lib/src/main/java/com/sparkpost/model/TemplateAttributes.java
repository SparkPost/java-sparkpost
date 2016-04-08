
package com.sparkpost.model;

import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO for storing a template.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TemplateAttributes extends Base {

    public TemplateAttributes() {

    }

    /**
     * Short, unique, alphanumeric ID used to reference the template
     * After a template has been created, this property cannot be changed. Maximum length - 64 bytes
     */
    @Description(
            value = "Short, unique, alphanumeric ID used to reference the template. After a template has been created, this property cannot be changed. Maximum length - 64 bytes",
            sample = {"AbC123"})
    private String id;

    /**
     * Content that will be used to construct a message
     * For a full description, see the Content Attributes. Maximum length - 15 MBs
     */
    @Description(
            value = "Content that will be used to construct a message. For a full description, see the Content Attributes. Maximum length - 15 MBs",
            sample = {"TemplateContentAttributes Dictionary"})
    private TemplateContentAttributes content;

    /**
     * Whether the template is published or is a draft version
     * A template cannot be changed from published to draft.
     */
    @Description(value = "Whether the template is published or is a draft version. A template cannot be changed from published to draft.", sample = {"true"})
    private Boolean published;

    /**
     * Editable display name
     * The name does not have to be unique. Maximum length - 1024 bytes
     */
    @Description(value = "Editable display name. The name does not have to be unique. Maximum length - 1024 bytes", sample = {"Template Name"})
    private String name;

    /**
     * Detailed description of the template
     * Maximum length - 1024 bytes
     */
    @Description(value = "Detailed description of the template. Maximum length - 1024 bytes.", sample = {"Template Description"})
    private String description;

    /**
     * TemplateOptions in which template options are defined
     * For a full description, see the Options Attributes.
     */
    @Description(
            value = "TemplateOptions in which template options are defined. For a full description, see the Options Attributes.",
            sample = {"TemplateOptions Dictionary"})
    private OptionsAttributes options;

}
