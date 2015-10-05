
package com.sparkpost.model;

import com.google.gson.annotations.SerializedName;
import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TemplateItem extends Base {

    @Description(value = "The name of the template", sample = {"Template Name"})
    private String name;

    @Description(value = "The ID of the template", sample = {"AbC123"})
    private String id;

    @Description(value = "Format YYYY-MM-DDTHH:MM:SS+-HH:MM or \"now\"..", sample = {"2015-02-11T08:00:00-04:00"})
    @SerializedName("last_update_time")
    private String lastUpdateTime;

    @Description(value = "Template Description", sample = {""})
    private String description;

    @Description(value = "true if template is published otherwise it is a draft", sample = {"true"})
    private Boolean published;
}
