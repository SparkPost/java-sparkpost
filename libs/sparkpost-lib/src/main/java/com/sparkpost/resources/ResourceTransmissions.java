
package com.sparkpost.resources;

import com.sparkpost.exception.SparkPostException;
import com.sparkpost.model.TransmissionBase;
import com.sparkpost.model.TransmissionWithRecipientArray;
import com.sparkpost.model.TransmissionWithRecipientList;
import com.sparkpost.model.responses.Response;
import com.sparkpost.model.responses.TransmissionCreateResponse;
import com.sparkpost.model.responses.TransmissionListResponse;
import com.sparkpost.model.responses.TransmissionRetrieveResults;
import com.sparkpost.transport.RestConnection;

/**
 * Resource collection that is a 1-to-1 match to the Transmissions SparkPost
 * API.<br>
 * <br>
 * See <a href="https://www.sparkpost.com/api#/reference/transmissions/">
 * Transmissions API</a>
 *
 * @author grava
 */
public class ResourceTransmissions {

    public static TransmissionCreateResponse create(RestConnection conn, Integer numRcptErrors, TransmissionWithRecipientArray trans)
            throws SparkPostException {

        return ResourceTransmissions.createTransmission(conn, numRcptErrors, trans);
    }

    public static TransmissionCreateResponse create(RestConnection conn, Integer numRcptErrors, TransmissionWithRecipientList trans) throws SparkPostException {

        return ResourceTransmissions.createTransmission(conn, numRcptErrors, trans);
    }

    private static <T extends TransmissionBase> TransmissionCreateResponse createTransmission(RestConnection conn, Integer numRcptErrors, T trans)
            throws SparkPostException {

        Endpoint ep = new Endpoint("transmissions");
        ep.addParam("num_rcpt_errors", numRcptErrors);
        String json = trans.toJson();
        Response response = conn.post(ep.toString(), json);

        TransmissionCreateResponse newResult = TransmissionCreateResponse.decode(response, TransmissionCreateResponse.class);
        return newResult;
    }

    public static TransmissionRetrieveResults retrieve(RestConnection conn, String id) throws SparkPostException {
        Response response = conn.get("transmissions/" + id);

        TransmissionRetrieveResults newResult = TransmissionRetrieveResults.decode(response, TransmissionRetrieveResults.class);
        return newResult;
    }

    public static TransmissionListResponse list(RestConnection conn, String campaignId, String templateId) throws SparkPostException {

        Endpoint ep = new Endpoint("transmissions");
        ep.addParam("campaign_id", campaignId);
        ep.addParam("template_id", templateId);
        Response response = conn.get(ep.toString());

        TransmissionListResponse transmissionResponse = TransmissionListResponse.decode(response, TransmissionListResponse.class);
        return transmissionResponse;
    }

    public static Response delete(RestConnection conn, String id) throws SparkPostException {
        Response response = conn.delete("transmissions/" + id);

        return response;
    }
}
