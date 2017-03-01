
package com.sparkpost.model.responses;

import com.sparkpost.model.Base;
import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ServerErrorResponse extends Base {

    @Description(value = "Error message from server", sample = {""})
    private String message;

    @Description(value = "Error descrption from server", sample = {""})
    private String description;

    @Description(value = "Error code from server", sample = {""})
    private String code;

}
