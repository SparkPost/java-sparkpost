package com.sparkpost.model.responses;

import com.sparkpost.model.WebhookIdResponseEntry;
import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class WebhookIdContainerResponse extends Response {


    @Description(value = "Created or updated webhook id description")
    WebhookIdResponseEntry results;
}
