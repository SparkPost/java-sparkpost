
package com.sparkpost.model.responses;

import com.google.gson.annotations.SerializedName;
import com.sparkpost.model.TemplateContentAttributes;
import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TemplateItemResponse extends Response {

    @Data
    public static final class TemplateOptionsData {

        @Description(value = "Enable or disable open tracking", sample = {""})
        @SerializedName("open_tracking")
        private Boolean openTracking;

        @Description(value = "Enable or disable click tracking", sample = {""})
        @SerializedName("click_tracking")
        private Boolean clickTracking;

        @Description(value = "Distinguish between transactional and non-transactional messages for unsubscribe and suppression purposes", sample = {""})
        private Boolean transactional;
    }

    @Description(value = "Short, unique, alphanumeric ID used to reference the template", sample = {""})
    private String id;

    @Description(value = "Whether the template is published or is a draft version", sample = {""})
    private Boolean published;

    @Description(value = "Editable display name", sample = {""})
    private String name;

    @Description(value = "Detailed description of the template", sample = {""})
    private String description;

    @Description(value = "Content for a template", sample = {""})
    @SerializedName("content")
    private TemplateContentAttributes content;

    @Description(value = "object in which template options are defined", sample = {""})
    private TemplateOptionsData options;

    /**
     * Whether or not to perform CSS inlining in HTML content
     * Defaults to false
     */
    @Description(value = "Whether or not to perform CSS inlining in HTML content.", sample = {""})
    @SerializedName("inline_css")
    private Boolean inlineCss;
}
