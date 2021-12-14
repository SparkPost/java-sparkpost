package com.sparkpost.model.responses;

import com.sparkpost.model.RecipientList;
import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RecipientListRetrieveResponse extends Response {

    @Description(value = "", sample = {""})
    private RecipientList results;
}
