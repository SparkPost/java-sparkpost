
package com.sparkpost.model.responses;

import java.util.List;
import java.util.Map;

import com.google.gson.annotations.SerializedName;
import com.sparkpost.model.SuppressionListEntry;
import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SupressionListResponse extends Response {

    @Description(value = "Type of suppression record.", sample = {"transactional or non_transactional"})
    private String type;

    @Description(value = "List of TemplateItems", sample = {""})
    private List<SuppressionListEntry> results;

    @Description(value = "links", sample = {""})
    @SerializedName("links")
    private Map<String, String> links;

    @Description(value = "total_count", sample = {"{ \"total_count\": 0 }"})
    @SerializedName("total_count")
    private int totalCount;

    public boolean hasNext() {
        String next = nextPageUrl();
        return next != null && nextPageUrl().length() > 0;
    }

    public String nextPageUrl() {
        if (this.links == null || this.links.isEmpty()) {
            return "";
        }

        String value = this.links.get("next");
        return value;
    }

}
