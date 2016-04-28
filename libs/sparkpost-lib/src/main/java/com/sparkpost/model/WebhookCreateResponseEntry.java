package com.sparkpost.model;

import com.yepher.jsondoc.annotations.Description;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class WebhookCreateResponseEntry extends Base {

    @Description(value = "Created webhook id.")
    private String id;
}
