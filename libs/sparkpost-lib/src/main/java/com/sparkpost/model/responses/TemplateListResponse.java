
package com.sparkpost.model.responses;

import java.util.List;

import com.sparkpost.model.TemplateItem;
import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TemplateListResponse extends Response {

    @Description(value = "List of TemplateItems", sample = {""})
    private List<TemplateItem> results;

}
