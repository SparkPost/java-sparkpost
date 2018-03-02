
package com.sparkpost.model.responses;

import com.sparkpost.model.AddressAttributes;
import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TemplatePreviewResponse extends Response {

    @Data
    public static final class TemplatePreviewData {

        @Description(value = "", sample = {""})
        private String subject;

        @Description(value = "", sample = {""})
        private AddressAttributes from;

        @Description(value = "", sample = {""})
        private String html;

        @Description(value = "", sample = {""})
        private String text;
    }

    @Description(value = "", sample = {""})
    private TemplatePreviewData results;
}
