
package com.sparkpost.model;

import com.google.gson.annotations.SerializedName;
import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DNSAttributes extends Base {

    /**
     * DNS DKIM record for the registered Sending Domain
     */
    @Description(value = "DNS DKIM record for the registered Sending Domain", sample = {""})
    @SerializedName("dkim_record")
    private String dkimRecord;

    /**
     * DNS SPF record for the registered Sending Domain
     */
    @Description(value = "DNS SPF record for the registered Sending Domain", sample = {""})
    @SerializedName("spf_record")
    private String spfRecord;

    /**
     * Error message describing reason for DKIM verification failure
     */
    @Description(value = "Error message describing reason for DKIM verification failure", sample = {""})
    @SerializedName("dkim_error")
    private String dkimError;

    /**
     * Error message describing reason for SPF verification failure
     */
    @Description(value = "Error message describing reason for SPF verification failure", sample = {""})
    @SerializedName("spf_error")
    private String spfError;
}
