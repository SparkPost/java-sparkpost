
package com.sparkpost.model.responses;

import java.util.List;

import com.sparkpost.model.TransmissionBase;
import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TransmissionListResponse extends Response {

    @Description(value = "List of TransmissionsBase items", sample = {""})
    private List<TransmissionBase> results;

}
