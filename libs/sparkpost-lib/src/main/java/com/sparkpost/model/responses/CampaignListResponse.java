
package com.sparkpost.model.responses;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CampaignListResponse extends Response {

    Map<String, List<String>> results;

    public List<String> getCampaignList() {
        if (this.results == null) {
            return new ArrayList<String>();
        }
        return this.results.get("campaigns");
    }
}
