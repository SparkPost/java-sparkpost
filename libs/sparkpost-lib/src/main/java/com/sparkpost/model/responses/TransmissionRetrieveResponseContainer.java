
package com.sparkpost.model.responses;

import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TransmissionRetrieveResponseContainer extends Response {

    @Description(value = "The transmission information", sample = {""})
    private TransmissionResponseInfo transmission;
}
