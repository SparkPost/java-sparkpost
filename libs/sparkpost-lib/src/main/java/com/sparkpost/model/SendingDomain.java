
package com.sparkpost.model;

import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Note: The Sending Domains API is available for SparkPost only.
 * A sending domain is a domain that is used to indicate who an email is from via the
 * "From:" header. Using a custom sending domain enables you to control what
 * recipients see as the From value in their email clients. DNS records can be
 * configured for a sending domain, which allows recipient mail servers to
 * authenticate your messages. The Sending Domains API provides the means to
 * create, list, retrieve, update, and verify a custom sending domain.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SendingDomain extends Base {

    /**
     * Name of the sending domain
     * The domain name will be used as the "From:" header address in the email.
     */
    @Description(
            value = "Name of the sending domain. The domain name will be used as the \"From:\" header address in the email.",
            sample = {"from_address@example.com"})
    private String domain;

    /**
     * Status details, including whether this domain's ownership has been verified
     */
    @Description(value = "Status details, including whether this domain's ownership has been verified", sample = {""})
    private StatusAttributes status;

    /**
     * DKIM key configuration
     * For a full description, see the DKIM Attributes.
     */
    @Description(value = "DKIM key configuration. For a full description, see the DKIM Attributes.", sample = {""})
    private DKIM dkim;

}
