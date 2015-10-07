package com.sparkpost.resources;

import com.sparkpost.exception.SparkPostException;
import com.sparkpost.model.responses.Response;
import com.sparkpost.transport.RestConnection;

/**
 * Resource collection that is a 1-to-1 match to the Metrics SparkPost API.<br>
 * <br>
 * See <a href="https://www.sparkpost.com/api#/reference/metrics/">Metrics
 * API</a>
 *
 * @author grava
 */
public class ResourceMetrics {

	public static Response getDiscoverabilityLinks(RestConnection conn) throws SparkPostException {
		return conn.get("metrics/");
	}

	public static Response getDeliverabilityMetricsSummary(RestConnection conn, String from, String to, String domains,
			String campaigns, String templates,String metrics, String timezone)
					throws SparkPostException {

		Endpoint ep = new Endpoint("metrics/deliverability");
		ep.addParam("from", from);
		ep.addParam("to", to);
		ep.addParam("domains", domains);
		ep.addParam("campaigns", campaigns);
		ep.addParam("templates", templates);
		ep.addParam("metrics", metrics);
		ep.addParam("timezone", timezone);

		Response response = conn.get(ep.toString());
		return response;
	}

	public static Response getDeliverabilityMetricsByDomain(RestConnection conn, String from, String to, String domains,
			String campaigns, String templates, String metrics, String timezone, String limit,
			String order_by) throws SparkPostException {

		Endpoint ep = new Endpoint("metrics/deliverability/domain");
		ep.addParam("from", from);
		ep.addParam("to", to);
		ep.addParam("domains", domains);
		ep.addParam("campaigns", campaigns);
		ep.addParam("templates", templates);
		ep.addParam("metrics", metrics);
		ep.addParam("timezone", timezone);
		ep.addParam("limit", limit);
		ep.addParam("order_by", order_by);

		Response response = conn.get(ep.toString());
		return response;

	}

	public static Response getDeliverabilityMetricsByCampaign(RestConnection conn, String from, String to, String domains,
			String campaigns, String templates, String metrics, String timezone, String limit,
			String order_by) throws SparkPostException {

		Endpoint ep = new Endpoint("metrics/deliverability/campaign");
		ep.addParam("from", from);
		ep.addParam("to", to);
		ep.addParam("domains", domains);
		ep.addParam("campaigns", campaigns);
		ep.addParam("templates", templates);
		ep.addParam("metrics", metrics);
		ep.addParam("timezone", timezone);
		ep.addParam("limit", limit);
		ep.addParam("order_by", order_by);

		Response response = conn.get(ep.toString());
		return response;
	}

	public static Response getDeliverabilityMetricsByTemplate(RestConnection conn, String from, String to, String domains,
			String campaigns, String templates, String metrics, String timezone, String limit,
			String order_by) throws SparkPostException {

		Endpoint ep = new Endpoint("metrics/deliverability/template");
		ep.addParam("from", from);
		ep.addParam("to", to);
		ep.addParam("domains", domains);
		ep.addParam("campaigns", campaigns);
		ep.addParam("templates", templates);
		ep.addParam("metrics", metrics);
		ep.addParam("timezone", timezone);
		ep.addParam("limit", limit);
		ep.addParam("order_by", order_by);

		Response response = conn.get(ep.toString());
		return response;
	}

	public static Response getDeliverabilityMetricsByWatchedDomain(RestConnection conn, String from, String to,
			String domains, String campaigns, String templates, String metrics, String timezone,
			String limit, String order_by) throws SparkPostException {

		Endpoint ep = new Endpoint("metrics/deliverability/watched-domain");
		ep.addParam("from", from);
		ep.addParam("to", to);
		ep.addParam("domains", domains);
		ep.addParam("campaigns", campaigns);
		ep.addParam("templates", templates);
		ep.addParam("metrics", metrics);
		ep.addParam("timezone", timezone);
		ep.addParam("limit", limit);
		ep.addParam("order_by", order_by);

		Response response = conn.get(ep.toString());
		return response;
	}

	public static Response getTimeSeriesMetrics(RestConnection conn, String from, String to, String domains, String campaigns,
			String templates, String precision, String metrics, String timezone)
					throws SparkPostException {

		Endpoint ep = new Endpoint("metrics/deliverability/time-series");
		ep.addParam("from", from);
		ep.addParam("to", to);
		ep.addParam("domains", domains);
		ep.addParam("campaigns", campaigns);
		ep.addParam("templates", templates);
		ep.addParam("precision", precision);
		ep.addParam("metrics", metrics);
		ep.addParam("timezone", timezone);

		Response response = conn.get(ep.toString());
		return response;
	}

	public static Response getBounceReasonMetrics(RestConnection conn, String from, String to, String domains,
			String campaigns, String templates, String metrics, String timezone, String limit)
					throws SparkPostException {

		Endpoint ep = new Endpoint("metrics/deliverability/bounce-reason");
		ep.addParam("from", from);
		ep.addParam("to", to);
		ep.addParam("domains", domains);
		ep.addParam("campaigns", campaigns);
		ep.addParam("templates", templates);
		ep.addParam("metrics", metrics);
		ep.addParam("timezone", timezone);
		ep.addParam("limit", limit);

		Response response = conn.get(ep.toString());
		return response;
	}

	public static Response getBounceReasonMetricsByDomain(RestConnection conn, String from, String to, String domains,
			String campaigns, String templates, String metrics, String timezone, String limit)
					throws SparkPostException {

		Endpoint ep = new Endpoint("metrics/deliverability/bounce-reason/domain");
		ep.addParam("from", from);
		ep.addParam("to", to);
		ep.addParam("domains", domains);
		ep.addParam("campaigns", campaigns);
		ep.addParam("templates", templates);
		ep.addParam("metrics", metrics);
		ep.addParam("timezone", timezone);
		ep.addParam("limit", limit);

		Response response = conn.get(ep.toString());
		return response;
	}

	public static Response getBounceClassificationMetrics(RestConnection conn, String from, String to, String domains,
			String campaigns, String templates, String metrics, String timezone, String limit)
					throws SparkPostException {

		Endpoint ep = new Endpoint("metrics/deliverability/bounce-classification");
		ep.addParam("from", from);
		ep.addParam("to", to);
		ep.addParam("domains", domains);
		ep.addParam("campaigns", campaigns);
		ep.addParam("templates", templates);
		ep.addParam("metrics", metrics);
		ep.addParam("timezone", timezone);
		ep.addParam("limit", limit);

		Response response = conn.get(ep.toString());
		return response;
	}

	public static Response getRejectionReasonMetrics(RestConnection conn, String from, String to, String domains,
			String campaigns, String templates, String timezone, String limit)
					throws SparkPostException {

		Endpoint ep = new Endpoint("metrics/deliverability/rejection-reason");
		ep.addParam("from", from);
		ep.addParam("to", to);
		ep.addParam("domains", domains);
		ep.addParam("campaigns", campaigns);
		ep.addParam("templates", templates);
		ep.addParam("timezone", timezone);
		ep.addParam("limit", limit);

		Response response = conn.get(ep.toString());
		return response;
	}

	public static Response getRejectionReasonMetricsByDomain(RestConnection conn, String from, String to, String domains,
			String campaigns, String templates, String timezone, String limit)
					throws SparkPostException {

		Endpoint ep = new Endpoint("metrics/deliverability/rejection-reason/domain");
		ep.addParam("from", from);
		ep.addParam("to", to);
		ep.addParam("domains", domains);
		ep.addParam("campaigns", campaigns);
		ep.addParam("templates", templates);
		ep.addParam("timezone", timezone);
		ep.addParam("limit", limit);

		Response response = conn.get(ep.toString());
		return response;
	}

	public static Response getDelayReasonMetrics(RestConnection conn, String from, String to, String domains,
			String campaigns, String templates, String timezone, String limit)
					throws SparkPostException {

		Endpoint ep = new Endpoint("metrics/deliverability/delay-reason");
		ep.addParam("from", from);
		ep.addParam("to", to);
		ep.addParam("domains", domains);
		ep.addParam("campaigns", campaigns);
		ep.addParam("templates", templates);
		ep.addParam("timezone", timezone);
		ep.addParam("limit", limit);

		Response response = conn.get(ep.toString());
		return response;
	}

	public static Response getDelayReasonMetricsByDomain(RestConnection conn, String from, String to, String domains,
			String campaigns, String templates, String timezone, String limit)
					throws SparkPostException {

		Endpoint ep = new Endpoint("metrics/deliverability/delay-reason/domain");
		ep.addParam("from", from);
		ep.addParam("to", to);
		ep.addParam("domains", domains);
		ep.addParam("campaigns", campaigns);
		ep.addParam("templates", templates);
		ep.addParam("timezone", timezone);
		ep.addParam("limit", limit);

		Response response = conn.get(ep.toString());
		return response;
	}

	public static Response getEngagementDetails(RestConnection conn, String from, String to, String timezone, String metrics,
			String campaigns, String templates, String limit) throws SparkPostException {

		Endpoint ep = new Endpoint("metrics/deliverability/link-name");
		ep.addParam("from", from);
		ep.addParam("to", to);
		ep.addParam("timezone", timezone);
		ep.addParam("metrics", metrics);
		ep.addParam("campaigns", campaigns);
		ep.addParam("templates", templates);
		ep.addParam("limit", limit);

		Response response = conn.get(ep.toString());
		return response;
	}

	public static Response getDeliveriesByAttempt(RestConnection conn, String from, String to, String domains,
			String campaigns, String templates, String timezone) throws SparkPostException {

		Endpoint ep = new Endpoint("metrics/deliverability/attempt");
		ep.addParam("from", from);
		ep.addParam("to", to);
		ep.addParam("domains", domains);
		ep.addParam("campaigns", campaigns);
		ep.addParam("templates", templates);
		ep.addParam("timezone", timezone);
		Response response = conn.get(ep.toString());
		return response;
	}

	public static Response getCampaignsList(RestConnection conn, String match, String limit) throws SparkPostException {

		Endpoint ep = new Endpoint("metrics/campaigns");
		ep.addParam("match", match);
		ep.addParam("limit", limit);

		Response response = conn.get(ep.toString());
		return response;
	}

	public static Response getDomainsList(RestConnection conn, String match, String limit) throws SparkPostException {

		Endpoint ep = new Endpoint("metrics/domains");
		ep.addParam("match", match);
		ep.addParam("limit", limit);

		Response response = conn.get(ep.toString());
		return response;
	}

}
