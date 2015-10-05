
package com.sparkpost.model.responses;

import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TransmissionRetrieveResults extends Response {

    @Description(value = "The transmission result", sample = {""})
    private TransmissionRetrieveResponseContainer results;
}
