
package com.sparkpost.resources;

import java.util.ArrayList;
import java.util.List;

import com.sparkpost.exception.SparkPostException;
import com.sparkpost.model.SubaccountInfo;
import com.sparkpost.model.responses.Response;
import com.sparkpost.model.responses.SubaccountCreateResponse;
import com.sparkpost.transport.IRestConnection;

/**
 * <br>
 * <br>
 * See <a href="https://developers.sparkpost.com/api/subaccounts/#subaccounts">Subaccounts API</a>
 */
public class ResourceSubAccountCrud {

    public static SubaccountCreateResponse create(IRestConnection conn, SubaccountInfo subaccountInfo) throws SparkPostException {

        String json = subaccountInfo.toJson();
        Endpoint ep = new Endpoint("subaccounts");
        Response response = conn.post(ep, json);
        SubaccountCreateResponse createResponse = SubaccountCreateResponse.decode(response, SubaccountCreateResponse.class);
        return createResponse;
    }

    public static SubaccountCreateResponse get(IRestConnection conn, int subaccountId) throws SparkPostException {

        Endpoint ep = new Endpoint("subaccounts/" + subaccountId);
        Response response = conn.get(ep);
        SubaccountCreateResponse createResponse = SubaccountCreateResponse.decode(response, SubaccountCreateResponse.class);
        return createResponse;
    }

    public static List<SubaccountCreateResponse> list(IRestConnection conn) throws SparkPostException {
        Endpoint ep = new Endpoint("subaccounts");
        Response response = conn.get(ep);
        SubaccountCreateResponse createResponse = SubaccountCreateResponse.decode(response, SubaccountCreateResponse.class);
        List<SubaccountCreateResponse> responseList = new ArrayList<SubaccountCreateResponse>();
        responseList.add(createResponse);
        return responseList;
    }

}
