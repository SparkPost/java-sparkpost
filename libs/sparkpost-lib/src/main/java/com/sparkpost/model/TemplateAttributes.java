
package com.sparkpost.model;

import com.sparkpost.model.responses.TemplateItemResponse;
import com.sparkpost.model.responses.TemplateItemResponse.TemplateOptionsData;
import com.sparkpost.model.responses.TemplateRetrieveResponse;
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

    public TemplateAttributes(TemplateRetrieveResponse response) {
        TemplateItemResponse template = response.getResults();
        if (template == null) {
            return;
        }

        setName(template.getName());
        setId(template.getId());
        setPublished(template.getPublished());
        setDescription(template.getDescription());

        TemplateContentAttributes content = new TemplateContentAttributes();
        TemplateContentAttributes otherContent = template.getContent();
        content.setSubject(otherContent.getSubject());
        content.setEmailRFC822(template.getContent().getEmailRFC822());
        content.setText(otherContent.getText());
        content.setHtml(otherContent.getHtml());
        template.setContent(content);

        OptionsAttributes options = new OptionsAttributes();
        TemplateOptionsData otherOptions = template.getOptions();
        options.setClickTracking(otherOptions.getClickTracking());
        options.setOpenTracking(otherOptions.getOpenTracking());
        //options.setInlineCss(otherOptions.getInlineCss());
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
