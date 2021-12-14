
package com.sparkpost.model.responses;

import com.google.gson.annotations.SerializedName;
import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SubaccountCreateResponse extends Response {

    @Data
    @EqualsAndHashCode
    public static final class CreateResult {

        private String name;

        private String status;

        @SerializedName("compliance_status")
        private String complianceStatus;

        @SerializedName("ip_pool")
        private String ipPool;

        @Description(value = "The id of the subaccount that was created", sample = {"123"})
        @SerializedName("subaccount_id")
        private Integer id;

        private String key;

        private String label;

        @SerializedName("short_key")
        private String shortKey;

    }

    @Description(value = "A Create subaccount result", sample = {""})
    private CreateResult results;

    /*
     * {
     * "results": {
     * "subaccount_id": 888,
     * "key": "cf806c8c472562ab98ad5acac1d1b06cbd1fb438",
     * "label": "API Key for Sparkle Ponies Subaccount",
     * "short_key": "cf80"
     * }
     * }
     */

}
