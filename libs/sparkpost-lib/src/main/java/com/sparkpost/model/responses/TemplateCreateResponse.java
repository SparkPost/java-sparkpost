
package com.sparkpost.model.responses;

import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TemplateCreateResponse extends Response {

    @Data
    @EqualsAndHashCode
    public static final class CreateResult {

        @Description(value = "The id of the template that was created", sample = {"some-template-id"})
        private String id;
    }

    @Description(value = "A Create template result", sample = {""})
    private CreateResult results;
}
