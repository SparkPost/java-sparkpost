
package com.sparkpost.model.responses;

import java.util.List;
import java.util.Map;

import com.google.gson.annotations.SerializedName;
import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DeliverabiltyMetricsResponse extends Response {

    @Description(value = "results", sample = {"\"results\": [ {\"count_injected\": 633,...}]"})
    @SerializedName("results")
    private List<Map<String, Object>> results;

    @Description(value = "links", sample = {"{ \"href\": \"/api/v1/metrics/deliverability\", \"rel\": \"deliverability\", \"method\": \"GET\" }"})
    @SerializedName("links")
    private List<MetricLinkResponse> links;

}
