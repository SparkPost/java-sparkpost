
package com.sparkpost.model;

import com.google.gson.annotations.SerializedName;
import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DKIM uses a pair of public and private keys to authenticate your emails.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DKIM extends Base {

    /**
     * DKIM private key
     * The private key will be used to create the DKIM Signature.
     */
    @Description(value = "The private key will be used to create the DKIM Signature.", sample = {""})
    @SerializedName("private")
    private String privateKey;

    /**
     * DKIM public key
     * The public key will be retrieved from DNS of the sending domain.
     */
    @Description(value = "The public key will be retrieved from DNS of the sending domain.", sample = {""})
    @SerializedName("public")
    private String publicKey;

    /**
     * DomainKey selector
     * The DomainKey selector will be used to indicate the DKIM public key location.
     */
    @Description(value = "The DomainKey selector will be used to indicate the DKIM public key location.", sample = {""})
    private String selector;

    /**
     * Header fields to be included in the DKIM signature
     * Header fields are separated by a colon. Example: "from:to:subject:date"
     */
    @Description(value = "Header fields are separated by a colon.", sample = {"from:to:subject:date"})
    private String headers;
}
