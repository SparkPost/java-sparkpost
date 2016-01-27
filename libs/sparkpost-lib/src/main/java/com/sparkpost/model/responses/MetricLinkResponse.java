
package com.sparkpost.model.responses;

import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * child endpoints that contain aggregate data, which can be used as "group by" qualifiers.
 */
@Data
@EqualsAndHashCode
public class MetricLinkResponse {

    @Description(value = "endpoint reference", sample = {"/api/v1/metrics/deliverability"})
    private String href;

    @Description(value = "", sample = {"deliverability"})
    private String rel;

    @Description(value = "", sample = {"GET, POST, PUT, DELETE"})
    private String method;

}
