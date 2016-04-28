package com.sparkpost.model.responses;

import com.sparkpost.model.Webhook;
import com.yepher.jsondoc.annotations.Description;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class WebhookDescribeResponse extends Response {

    @Description(value = "", sample = "")
    private Webhook results;
}
