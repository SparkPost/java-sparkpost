
package com.sparkpost.model.responses;

import java.util.List;
import java.util.Map;

import com.google.gson.annotations.SerializedName;
import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MessageEventsResponse extends Response {

    // {"results":[],"total_count":0,"links":[]}
    @Description(value = "results", sample = {"\"results\": [ {\"count_injected\": 633,...}]"})
    @SerializedName("results")
    private List<Map<String, Object>> results;

    @Description(value = "links", sample = {"{ \"href\": \"/api/v1/message-events\", \"rel\": \"message-events\", \"method\": \"GET\" }"})
    @SerializedName("links")
    private List<Map<String, String>> links;

    @Description(value = "total_count", sample = {"{ \"total_count\": 0 }"})
    @SerializedName("total_count")
    private int totalCount;

    public boolean hasNext() {
        String next = nextPageUrl();
        return next != null && nextPageUrl().length() > 0;
    }

    public String nextPageUrl() {
        if (this.links.size() == 0) {
            return "";
        }

        for (Map<String, String> element : this.links) {
            String value = element.get("rel");
            if ("next".equalsIgnoreCase(value)) {
                return element.get("href");
            }
        }

        return "";
    }

}
