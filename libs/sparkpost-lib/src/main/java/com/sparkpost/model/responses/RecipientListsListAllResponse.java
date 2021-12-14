package com.sparkpost.model.responses;

import java.util.List;

import com.sparkpost.model.RecipientList;
import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RecipientListsListAllResponse extends Response {

    @Description(value = "List of RecipientList", sample = {""})
    private List<RecipientList> results;
}
