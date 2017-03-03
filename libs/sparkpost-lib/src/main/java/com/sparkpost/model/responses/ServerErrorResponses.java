
package com.sparkpost.model.responses;

import java.util.List;

import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ServerErrorResponses extends Response {

    @Description(value = "An array errors from the server.", sample = {""})
    List<ServerErrorResponse> errors;
}
