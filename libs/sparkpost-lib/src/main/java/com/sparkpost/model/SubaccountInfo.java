
package com.sparkpost.model;

import java.util.HashSet;
import java.util.Set;

import com.google.gson.annotations.SerializedName;
import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;

/** DTO for storing a subaccount. */

@Data
@EqualsAndHashCode(callSuper = true)
public class SubaccountInfo extends Base {

    /**
     * Key Grants
     */
    public transient static final String SMTP_INJECTION_GRANT = "smtp/inject";
    public transient static final String SENDING_DOMAIN_MANAGE_GRANT = "sending_domains/manage";
    public transient static final String TRACKING_DOMAINS_VIEW_GRANT = "tracking_domains/view";
    public transient static final String TRACKING_DOMAINS_MANAGE_GRANT = "tracking_domains/manage";
    public transient static final String MESSAGE_EVENTS_VIEW_GRANT = "message_events/view";
    public transient static final String SUPPRESSION_LISTS_MANAGE_GRANT = "suppression_lists/manage";
    public transient static final String TRANSMISSIONS_VIEW_GRANT = "*transmissions/view";
    public transient static final String TRANSMISSION_MODIFY_GRANT = "transmissions/modify";
    public transient static final String WEBHOOKS_VIEW_GRANT = "webhooks/view";
    public transient static final String WEBHOOKS_MODIFY_GRANT = "webhooks/modify";

    public SubaccountInfo() {

    }

    /**
     * ip_pool string
     * The ID of an IP Pool in which to restrict this subaccount's mail deliveries. If the supplied ip_pool is a empty or not present, this subaccount may use
     * any of the account's IP Pools.
     * Enterprise customers: please consult with your TAM on IP management.
     */
    @Description(value = "The ID of an IP Pool in which to restrict this subaccount's mail deliveries", sample = {"Pool Name"})
    @SerializedName("ip_pool")
    private String ipPool;

    /**
     * setup_api_key boolean, default is true
     * Whether or not to create an API key for the subaccount. An API key can be created a later time.
     */
    @Description(value = " Whether or not to create an API key for the subaccount. An API key can be created a later time. default is true", sample = {"true"})
    @SerializedName("setup_api_key")
    private Boolean setupApiKey = true;

    /**
     * key_label string
     * User friendly identifier for the initial subaccount api key. Required if setup_api_key is true.
     */
    @Description(
            value = "User friendly identifier for the initial subaccount api key. Required if setup_api_key is true",
            sample = {"API Key for Sparkle Ponies Subaccount"})
    @SerializedName("key_label")
    private String keyLabel;

    /**
     * key_grants array of strings
     * List of grants to give to the initial subaccount api key. Required if setup_api_key is true.
     * Valid values are: smtp/inject, sending_domains/manage, tracking_domains/view, tracking_domains/manage, message_events/view, suppression_lists/manage,
     * transmissions/view, transmissions/modify, webhooks/view, webhooks/modify
     */
    @Description(value = "List of grants to give to the initial subaccount api key", sample = {"SubaccountInfo.SMTP_INJECTION_GRANT"})
    @SerializedName("key_grants")
    private Set<String> keyGrants = new HashSet<String>();

    /**
     * Subaccount display name.
     * Max length: 64 characters
     */
    @Description(value = "Subaccount display name Max length: 64 characters", sample = {"Sparkle Ponies"})
    private String name;

    /**
     * key_valid_ips array
     * List of IP's that the initial subaccount API key can be used from. If not supplied, the API key will be usable from any IP address.
     *
     * @param keyGrant
     */

    public void addKeyGrant(String keyGrant) {
        this.keyGrants.add(keyGrant);
    }

}
