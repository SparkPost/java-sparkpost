
package com.sparkpost.model.responses;

import java.util.List;

import com.sparkpost.model.Webhook;
import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class WebhookListAllResponse extends Response {

    @Description(value = "An array of Webhook data", sample = {""})
    List<Webhook> results;
}
