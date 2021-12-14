package com.sparkpost.model;

import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class WebhookIdResponseEntry extends Base {

    @Description(value = "Created or updated webhook id.")
    private String id;
}
