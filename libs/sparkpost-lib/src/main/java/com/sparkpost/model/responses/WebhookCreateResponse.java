package com.sparkpost.model.responses;

import com.sparkpost.model.WebhookCreateResponseEntry;
import com.yepher.jsondoc.annotations.Description;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class WebhookCreateResponse extends Response {


    @Description(value = "Created webhook description")
    WebhookCreateResponseEntry results;
}
