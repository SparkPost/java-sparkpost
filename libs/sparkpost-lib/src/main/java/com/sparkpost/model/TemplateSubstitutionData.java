
package com.sparkpost.model;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.annotations.SerializedName;
import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO for storing substitution data (list of key=value).
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TemplateSubstitutionData extends Base {

    @Description(value = "Data the will be substituted into the template", sample = {"Dictionary of substitution data"})
    @SerializedName("substitution_data")
    private Map<String, Object> substitutionData = new HashMap<String, Object>();

}
