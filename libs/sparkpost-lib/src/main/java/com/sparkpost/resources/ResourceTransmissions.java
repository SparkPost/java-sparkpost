
package com.sparkpost.resources;

import com.sparkpost.exception.SparkPostException;
import com.sparkpost.model.TransmissionBase;
import com.sparkpost.model.TransmissionWithRecipientArray;
import com.sparkpost.model.TransmissionWithRecipientList;
import com.sparkpost.model.responses.Response;
import com.sparkpost.model.responses.TransmissionCreateResponse;
import com.sparkpost.model.responses.TransmissionListResponse;
import com.sparkpost.model.responses.TransmissionRetrieveResults;
import com.sparkpost.transport.IRestConnection;

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

    public static TransmissionCreateResponse create(IRestConnection conn, Integer numRcptErrors, TransmissionWithRecipientArray trans)
            throws SparkPostException {

        return ResourceTransmissions.createTransmission(conn, numRcptErrors, trans);
    }

    public static TransmissionCreateResponse create(IRestConnection conn, Integer numRcptErrors, TransmissionWithRecipientList trans)
            throws SparkPostException {

        return ResourceTransmissions.createTransmission(conn, numRcptErrors, trans);
    }

    private static <T extends TransmissionBase> TransmissionCreateResponse createTransmission(IRestConnection conn, Integer numRcptErrors, T trans)
            throws SparkPostException {

        Endpoint ep = new Endpoint("transmissions");
        ep.addParam("num_rcpt_errors", numRcptErrors);
        String json = trans.toJson();
        Response response = conn.post(ep, json);

        TransmissionCreateResponse newResult = TransmissionCreateResponse.decode(response, TransmissionCreateResponse.class);
        return newResult;
    }

    public static TransmissionRetrieveResults retrieve(IRestConnection conn, String id) throws SparkPostException {
        Endpoint ep = new Endpoint("transmissions/" + id);
        Response response = conn.get(ep);

        TransmissionRetrieveResults newResult = TransmissionRetrieveResults.decode(response, TransmissionRetrieveResults.class);
        return newResult;
    }

    public static TransmissionListResponse list(IRestConnection conn, String campaignId, String templateId) throws SparkPostException {

        Endpoint ep = new Endpoint("transmissions");
        ep.addParam("campaign_id", campaignId);
        ep.addParam("template_id", templateId);
        Response response = conn.get(ep);

        TransmissionListResponse transmissionResponse = TransmissionListResponse.decode(response, TransmissionListResponse.class);
        return transmissionResponse;
    }

    public static TransmissionListResponse delete(IRestConnection conn, String campaignId) throws SparkPostException {

        Endpoint ep = new Endpoint("transmissions");
        ep.addParam("campaign_id", campaignId);
        Response response = conn.delete(ep);

        TransmissionListResponse transmissionResponse = TransmissionListResponse.decode(response, TransmissionListResponse.class);
        return transmissionResponse;
    }
}
