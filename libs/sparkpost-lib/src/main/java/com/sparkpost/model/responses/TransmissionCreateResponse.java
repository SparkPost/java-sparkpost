package com.sparkpost.model.responses;

import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true) 
public class TransmissionCreateResponse extends Response {

	@Description(value="Result of transmission")
	private TransmissionCreateItem results;
}
