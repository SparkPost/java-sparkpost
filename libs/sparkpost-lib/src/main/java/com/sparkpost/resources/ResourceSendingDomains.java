
package com.sparkpost.resources;

import com.sparkpost.exception.SparkPostException;
import com.sparkpost.model.SendingDomain;
import com.sparkpost.model.VerifyAttributes;
import com.sparkpost.model.responses.Response;
import com.sparkpost.transport.IRestConnection;

/**
 * Resource collection that is a 1-to-1 match to the Sending Domains SparkPost
 * API.<br>
 * <br>
 * See
 * <a href="https://www.sparkpost.com/api#/reference/sending-domains/">Sending
 * Domains API</a>
 *
 * @author grava
 */
public class ResourceSendingDomains {

    public static Response create(IRestConnection conn, SendingDomain domain) throws SparkPostException {

        String json = domain.toJson();
        Endpoint ep = new Endpoint("sending-domains");
        Response response = conn.post(ep, json);
        return response;
    }

    public static Response retrieve(IRestConnection conn, String domainName) throws SparkPostException {

        Endpoint ep = new Endpoint("sending-domains/" + domainName);
        Response response = conn.get(ep);
        return response;
    }

    public static Response list(IRestConnection conn) throws SparkPostException {
        Endpoint ep = new Endpoint("sending-domains/");
        Response response = conn.get(ep);
        return response;
    }

    public static Response update(IRestConnection conn, String domainName, SendingDomain domain) throws SparkPostException {

        String json = domain.toJson();
        Endpoint ep = new Endpoint("sending-domains/" + domainName);
        Response response = conn.put(ep, json);
        return response;
    }

    public static Response verify(IRestConnection conn, String domainName, VerifyAttributes verify) throws SparkPostException {

        String json = verify.toJson();
        Endpoint ep = new Endpoint("sending-domains/" + domainName + "/verify");
        Response response = conn.post(ep, json);
        return response;
    }
}
