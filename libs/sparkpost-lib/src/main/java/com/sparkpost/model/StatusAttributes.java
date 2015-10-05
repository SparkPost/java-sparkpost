
package com.sparkpost.model;

import com.google.gson.annotations.SerializedName;
import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class StatusAttributes extends Base {

    /**
     * 
     */
    @Description(value = "True if ownership is verified", sample = {"true"})
    @SerializedName("ownership_verified")
    private Boolean ownershipVerified;

    /**
     *  
     */
    @Description(value = "Current SPF Status. Valid values are \"unverified\", \"pending\", \"invalid\" or \"valid\".", sample = {"valid"})
    @SerializedName("spf_status")
    private String spfStatus;

    /**
     * 
     */
    @Description(value = "DKIM Compliance status. Valid values are \"pending\", \"valid\", or \"blocked\".", sample = {"valid"})
    @SerializedName("compliance_status")
    private String complianceStatus;

    /**
     * 
     */
    @Description(value = "DKIM status. Valid values are \"unverified\", \"pending\", \"invalid\" or \"valid\".", sample = {"valid"})
    @SerializedName("dkim_status")
    private String dkimStatus;

    /**
     * 
     */
    @Description(value = "Abuse status. Valid values are \"unverified\", \"pending\", \"invalid\" or \"valid\".", sample = {"valid"})
    @SerializedName("abuse_at_status")
    private String abuseAtStatus;

    /**
     * 
     */
    @Description(value = "Postmaster status. Valid values are \"unverified\", \"pending\", \"invalid\" or \"valid\".", sample = {"valid"})
    @SerializedName("postmaster_at_status")
    private String postmasterAtStatus;
}
