
package com.sparkpost.model;

import com.google.gson.annotations.SerializedName;
import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO for storing a Sending Domain verification request.
 *
 * @author grava
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class VerifyAttributes extends Base {

    /**
     * Request verification of DKIM record
     */
    @Description(value = "Request verification of DKIM record", sample = {"true"})
    @SerializedName("dkim_verify")
    private Boolean dkimVerify = null;

    /**
     * Request verification of SPF record
     */
    @Description(value = "Request verification of SPF record", sample = {"true"})
    @SerializedName("spf_verify")
    private Boolean spfVerify = null;
}
