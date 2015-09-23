package com.sparkpost.model;

import java.util.List;

import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true) 
public class TransmissionListResponse extends Response {

	@Description(value="List of TransmissionsBase items")
	private List<TransmissionBase> results;

}
