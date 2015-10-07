
package com.sparkpost.model;

import com.yepher.jsondoc.annotations.Description;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The verify resource validates the specified verification field types.
 * Including the fields "dkim_verify" and "spf_verify" in the request initiates
 * a check against the associated DNS record type for the specified sending
 * domain. The domain's "status" object is returned on success.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class VerifyResponse extends Base {

    @Description(value = "DKIM results", sample = {"DKIMResult Dictionary"})
    private DKIMResults results;

}
