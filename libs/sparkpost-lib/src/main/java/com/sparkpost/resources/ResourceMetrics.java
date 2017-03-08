
package com.sparkpost.resources;

import com.sparkpost.exception.SparkPostException;
import com.sparkpost.model.responses.CampaignListResponse;
import com.sparkpost.model.responses.DeliverabiltyMetricsResponse;
import com.sparkpost.model.responses.DomainListResponse;
import com.sparkpost.model.responses.Response;
import com.sparkpost.transport.IRestConnection;

/**
 * Resource collection that is a 1-to-1 match to the Metrics SparkPost API.<br>
 * <br>
 * See <a href="https://www.sparkpost.com/api#/reference/metrics/">Metrics
 * API</a>
 *
 * @author grava
 */
public class ResourceMetrics {

    public static Response getDiscoverabilityLinks(IRestConnection conn) throws SparkPostException {
        Endpoint ep = new Endpoint("metrics/");
        return conn.get(ep);
    }

    public static DeliverabiltyMetricsResponse getDeliverabilityMetricsSummary(
            IRestConnection conn,
            String from,
            String to,
            String domains,
            String campaigns,
            String templates,
            String metrics,
            String timezone) throws SparkPostException {

        Endpoint ep = new Endpoint("metrics/deliverability");
        ep.addCommonParams(from, to, domains, campaigns, templates, metrics, timezone, null, null);

        Response response = conn.get(ep);
        DeliverabiltyMetricsResponse newResponse = DeliverabiltyMetricsResponse.decode(response, DeliverabiltyMetricsResponse.class);

        return newResponse;
    }

    public static DeliverabiltyMetricsResponse getDeliverabilityMetricsByDomain(
            IRestConnection conn,
            String from,
            String to,
            String domains,
            String campaigns,
            String templates,
            String metrics,
            String timezone,
            String limit,
            String orderBy) throws SparkPostException {

        Endpoint ep = new Endpoint("metrics/deliverability/domain");
        ep.addCommonParams(from, to, domains, campaigns, templates, metrics, timezone, limit, orderBy);

        Response response = conn.get(ep);
        DeliverabiltyMetricsResponse newResponse = DeliverabiltyMetricsResponse.decode(response, DeliverabiltyMetricsResponse.class);

        return newResponse;

    }

    public static DeliverabiltyMetricsResponse getDeliverabilityMetricsByCampaign(
            IRestConnection conn,
            String from,
            String to,
            String domains,
            String campaigns,
            String templates,
            String metrics,
            String timezone,
            String limit,
            String orderBy) throws SparkPostException {

        Endpoint ep = new Endpoint("metrics/deliverability/campaign");
        ep.addCommonParams(from, to, domains, campaigns, templates, metrics, timezone, limit, orderBy);

        Response response = conn.get(ep);
        DeliverabiltyMetricsResponse newResponse = DeliverabiltyMetricsResponse.decode(response, DeliverabiltyMetricsResponse.class);

        return newResponse;

    }

    public static DeliverabiltyMetricsResponse getDeliverabilityMetricsByTemplate(
            IRestConnection conn,
            String from,
            String to,
            String domains,
            String campaigns,
            String templates,
            String metrics,
            String timezone,
            String limit,
            String orderBy) throws SparkPostException {

        Endpoint ep = new Endpoint("metrics/deliverability/template");
        ep.addCommonParams(from, to, domains, campaigns, templates, metrics, timezone, limit, orderBy);

        Response response = conn.get(ep);
        DeliverabiltyMetricsResponse newResponse = DeliverabiltyMetricsResponse.decode(response, DeliverabiltyMetricsResponse.class);

        return newResponse;
    }

    public static DeliverabiltyMetricsResponse getDeliverabilityMetricsByWatchedDomain(
            IRestConnection conn,
            String from,
            String to,
            String domains,
            String campaigns,
            String templates,
            String metrics,
            String timezone,
            String limit,
            String orderBy) throws SparkPostException {

        Endpoint ep = new Endpoint("metrics/deliverability/watched-domain");
        ep.addCommonParams(from, to, domains, campaigns, templates, metrics, timezone, limit, orderBy);

        Response response = conn.get(ep);
        DeliverabiltyMetricsResponse newResponse = DeliverabiltyMetricsResponse.decode(response, DeliverabiltyMetricsResponse.class);

        return newResponse;
    }

    public static DeliverabiltyMetricsResponse getTimeSeriesMetrics(
            IRestConnection conn,
            String from,
            String to,
            String domains,
            String campaigns,
            String templates,
            String precision,
            String metrics,
            String timezone) throws SparkPostException {

        Endpoint ep = new Endpoint("metrics/deliverability/time-series");

        ep.addParam("precision", precision);
        ep.addCommonParams(from, to, domains, campaigns, templates, metrics, timezone, null, null);

        Response response = conn.get(ep);
        DeliverabiltyMetricsResponse newResponse = DeliverabiltyMetricsResponse.decode(response, DeliverabiltyMetricsResponse.class);

        return newResponse;
    }

    public static DeliverabiltyMetricsResponse getBounceReasonMetrics(
            IRestConnection conn,
            String from,
            String to,
            String domains,
            String campaigns,
            String templates,
            String metrics,
            String timezone,
            String limit) throws SparkPostException {

        Endpoint ep = new Endpoint("metrics/deliverability/bounce-reason");

        ep.addCommonParams(from, to, domains, campaigns, templates, metrics, timezone, limit, null);

        Response response = conn.get(ep);
        DeliverabiltyMetricsResponse newResponse = DeliverabiltyMetricsResponse.decode(response, DeliverabiltyMetricsResponse.class);

        return newResponse;
    }

    public static DeliverabiltyMetricsResponse getBounceReasonMetricsByDomain(
            IRestConnection conn,
            String from,
            String to,
            String domains,
            String campaigns,
            String templates,
            String metrics,
            String timezone,
            String limit) throws SparkPostException {

        Endpoint ep = new Endpoint("metrics/deliverability/bounce-reason/domain");

        ep.addCommonParams(from, to, domains, campaigns, templates, metrics, timezone, limit, null);

        Response response = conn.get(ep);
        DeliverabiltyMetricsResponse newResponse = DeliverabiltyMetricsResponse.decode(response, DeliverabiltyMetricsResponse.class);

        return newResponse;
    }

    public static DeliverabiltyMetricsResponse getBounceClassificationMetrics(
            IRestConnection conn,
            String from,
            String to,
            String domains,
            String campaigns,
            String templates,
            String metrics,
            String timezone,
            String limit) throws SparkPostException {

        Endpoint ep = new Endpoint("metrics/deliverability/bounce-classification");

        ep.addCommonParams(from, to, domains, campaigns, templates, metrics, timezone, limit, null);

        Response response = conn.get(ep);
        DeliverabiltyMetricsResponse newResponse = DeliverabiltyMetricsResponse.decode(response, DeliverabiltyMetricsResponse.class);

        return newResponse;
    }

    public static DeliverabiltyMetricsResponse getRejectionReasonMetrics(
            IRestConnection conn,
            String from,
            String to,
            String domains,
            String campaigns,
            String templates,
            String timezone,
            String limit) throws SparkPostException {

        Endpoint ep = new Endpoint("metrics/deliverability/rejection-reason");

        ep.addCommonParams(from, to, domains, campaigns, templates, null, timezone, limit, null);

        Response response = conn.get(ep);
        DeliverabiltyMetricsResponse newResponse = DeliverabiltyMetricsResponse.decode(response, DeliverabiltyMetricsResponse.class);

        return newResponse;
    }

    public static DeliverabiltyMetricsResponse getRejectionReasonMetricsByDomain(
            IRestConnection conn,
            String from,
            String to,
            String domains,
            String campaigns,
            String templates,
            String timezone,
            String limit) throws SparkPostException {

        Endpoint ep = new Endpoint("metrics/deliverability/rejection-reason/domain");

        ep.addCommonParams(from, to, domains, campaigns, templates, null, timezone, limit, null);

        Response response = conn.get(ep);
        DeliverabiltyMetricsResponse newResponse = DeliverabiltyMetricsResponse.decode(response, DeliverabiltyMetricsResponse.class);

        return newResponse;
    }

    public static DeliverabiltyMetricsResponse getDelayReasonMetrics(
            IRestConnection conn,
            String from,
            String to,
            String domains,
            String campaigns,
            String templates,
            String timezone,
            String limit) throws SparkPostException {

        Endpoint ep = new Endpoint("metrics/deliverability/delay-reason");

        ep.addCommonParams(from, to, domains, campaigns, templates, null, timezone, limit, null);

        Response response = conn.get(ep);
        DeliverabiltyMetricsResponse newResponse = DeliverabiltyMetricsResponse.decode(response, DeliverabiltyMetricsResponse.class);

        return newResponse;
    }

    public static DeliverabiltyMetricsResponse getDelayReasonMetricsByDomain(
            IRestConnection conn,
            String from,
            String to,
            String domains,
            String campaigns,
            String templates,
            String timezone,
            String limit) throws SparkPostException {

        Endpoint ep = new Endpoint("metrics/deliverability/delay-reason/domain");

        ep.addCommonParams(from, to, domains, campaigns, templates, null, timezone, limit, null);

        Response response = conn.get(ep);
        DeliverabiltyMetricsResponse newResponse = DeliverabiltyMetricsResponse.decode(response, DeliverabiltyMetricsResponse.class);

        return newResponse;
    }

    public static DeliverabiltyMetricsResponse getEngagementDetails(
            IRestConnection conn,
            String from,
            String to,
            String timezone,
            String metrics,
            String campaigns,
            String templates,
            String limit) throws SparkPostException {

        Endpoint ep = new Endpoint("metrics/deliverability/link-name");

        ep.addCommonParams(from, to, null, campaigns, templates, metrics, timezone, limit, null);

        Response response = conn.get(ep);
        DeliverabiltyMetricsResponse newResponse = DeliverabiltyMetricsResponse.decode(response, DeliverabiltyMetricsResponse.class);

        return newResponse;
    }

    public static DeliverabiltyMetricsResponse getDeliveriesByAttempt(
            IRestConnection conn,
            String from,
            String to,
            String domains,
            String campaigns,
            String templates,
            String timezone) throws SparkPostException {

        Endpoint ep = new Endpoint("metrics/deliverability/attempt");
        ep.addCommonParams(from, to, domains, campaigns, templates, null, timezone, null, null);

        Response response = conn.get(ep);
        DeliverabiltyMetricsResponse newResponse = DeliverabiltyMetricsResponse.decode(response, DeliverabiltyMetricsResponse.class);

        return newResponse;
    }

    public static CampaignListResponse getCampaignsList(IRestConnection conn, String match, String limit) throws SparkPostException {

        Endpoint ep = new Endpoint("metrics/campaigns");
        ep.addParam("match", match);
        ep.addParam("limit", limit);

        Response response = conn.get(ep);
        CampaignListResponse newResponse = CampaignListResponse.decode(response, CampaignListResponse.class);

        return newResponse;
    }

    public static DomainListResponse getDomainsList(IRestConnection conn, String match, String limit) throws SparkPostException {

        Endpoint ep = new Endpoint("metrics/domains");
        ep.addParam("match", match);
        ep.addParam("limit", limit);

        Response response = conn.get(ep);
        DomainListResponse newResponse = DomainListResponse.decode(response, DomainListResponse.class);

        return newResponse;
    }

}
