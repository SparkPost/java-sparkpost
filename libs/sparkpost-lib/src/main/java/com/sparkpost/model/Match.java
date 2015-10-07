
package com.sparkpost.model;

import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Match extends Base {

    @Description(value = "Inbound messaging protocol associated with this webhook", sample = {"SMTP"})
    private String protocol;

    @Description(value = "Inbound domain associated with this webhook", sample = {"example.com"})
    private String domain;
}
