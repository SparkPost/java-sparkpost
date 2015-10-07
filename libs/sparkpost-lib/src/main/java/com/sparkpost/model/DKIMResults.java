
package com.sparkpost.model;

import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DKIMResults extends Base {

    @Description(value = "DKIM status result.", sample = {""})
    private StatusAttributes status;

    @Description(value = "DKIM data", sample = {""})
    private DKIM dkim;

}
