
package com.sparkpost.model.responses;

import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TemplateRetrieveResponse extends Response {

    @Data
    public static final class Results {

        private TemplateItemResponse templateItem;
    }

    @Description(value = "", sample = {""})
    private TemplateItemResponse results;

}
