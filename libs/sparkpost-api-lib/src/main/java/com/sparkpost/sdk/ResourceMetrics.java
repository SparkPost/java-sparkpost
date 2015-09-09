/* Copyright 2014 Message Systems, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this software except in compliance with the License.
 *
 * A copy of the License is located at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0.html
 *
 * or in the "license" file accompanying this software. This file is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF
 * ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */

package com.sparkpost.sdk;

import com.sparkpost.sdk.dto.Response;
import com.sparkpost.sdk.dto.SparkpostSdkException;

/**
 * Resource collection that is a 1-to-1 match to the Metrics SparkPost API.<br>
 * <br>
 * See <a href="https://www.sparkpost.com/api#/reference/metrics/">Metrics
 * API</a>
 *
 * @author grava
 */
public class ResourceMetrics {

	public static Response getDiscoverabilityLinks(RestConn conn) throws SparkpostSdkException {
		return conn.get("metrics/");
	}

	public static Response getDeliverabilityMetricsSummary(RestConn conn, String from, String to, String domains,
			String campaigns, String templates, String sandbox, String metrics, String timezone)
					throws SparkpostSdkException {

		Endpoint ep = new Endpoint("metrics/deliverability");
		ep.addParam("from", from);
		ep.addParam("to", to);
		ep.addParam("domains", domains);
		ep.addParam("campaigns", campaigns);
		ep.addParam("templates", templates);
		ep.addParam("sandbox", sandbox);
		ep.addParam("metrics", metrics);
		ep.addParam("timezone", timezone);

		return conn.get(ep.toString());
	}

	public static Response getDeliverabilityMetricsByDomain(RestConn conn, String from, String to, String domains,
			String campaigns, String templates, String sandbox, String metrics, String timezone, String limit,
			String order_by) throws SparkpostSdkException {

		Endpoint ep = new Endpoint("metrics/deliverability/domain");
		ep.addParam("from", from);
		ep.addParam("to", to);
		ep.addParam("domains", domains);
		ep.addParam("campaigns", campaigns);
		ep.addParam("templates", templates);
		ep.addParam("sandbox", sandbox);
		ep.addParam("metrics", metrics);
		ep.addParam("timezone", timezone);
		ep.addParam("limit", limit);
		ep.addParam("order_by", order_by);

		return conn.get(ep.toString());

	}

	public static Response getDeliverabilityMetricsByCampaign(RestConn conn, String from, String to, String domains,
			String campaigns, String templates, String sandbox, String metrics, String timezone, String limit,
			String order_by) throws SparkpostSdkException {

		Endpoint ep = new Endpoint("metrics/deliverability/campaign");
		ep.addParam("from", from);
		ep.addParam("to", to);
		ep.addParam("domains", domains);
		ep.addParam("campaigns", campaigns);
		ep.addParam("templates", templates);
		ep.addParam("sandbox", sandbox);
		ep.addParam("metrics", metrics);
		ep.addParam("timezone", timezone);
		ep.addParam("limit", limit);
		ep.addParam("order_by", order_by);

		return conn.get(ep.toString());
	}

	public static Response getDeliverabilityMetricsByTemplate(RestConn conn, String from, String to, String domains,
			String campaigns, String templates, String sandbox, String metrics, String timezone, String limit,
			String order_by) throws SparkpostSdkException {

		Endpoint ep = new Endpoint("metrics/deliverability/template");
		ep.addParam("from", from);
		ep.addParam("to", to);
		ep.addParam("domains", domains);
		ep.addParam("campaigns", campaigns);
		ep.addParam("templates", templates);
		ep.addParam("sandbox", sandbox);
		ep.addParam("metrics", metrics);
		ep.addParam("timezone", timezone);
		ep.addParam("limit", limit);
		ep.addParam("order_by", order_by);

		return conn.get(ep.toString());
	}

	public static Response getDeliverabilityMetricsByWatchedDomain(RestConn conn, String from, String to,
			String domains, String campaigns, String templates, String sandbox, String metrics, String timezone,
			String limit, String order_by) throws SparkpostSdkException {

		Endpoint ep = new Endpoint("metrics/deliverability/watched-domain");
		ep.addParam("from", from);
		ep.addParam("to", to);
		ep.addParam("domains", domains);
		ep.addParam("campaigns", campaigns);
		ep.addParam("templates", templates);
		ep.addParam("sandbox", sandbox);
		ep.addParam("metrics", metrics);
		ep.addParam("timezone", timezone);
		ep.addParam("limit", limit);
		ep.addParam("order_by", order_by);

		return conn.get(ep.toString());
	}

	public static Response getTimeSeriesMetrics(RestConn conn, String from, String to, String domains, String campaigns,
			String templates, String sandbox, String precision, String metrics, String timezone)
					throws SparkpostSdkException {

		Endpoint ep = new Endpoint("metrics/deliverability/time-series");
		ep.addParam("from", from);
		ep.addParam("to", to);
		ep.addParam("domains", domains);
		ep.addParam("campaigns", campaigns);
		ep.addParam("templates", templates);
		ep.addParam("sandbox", sandbox);
		ep.addParam("precision", precision);
		ep.addParam("metrics", metrics);
		ep.addParam("timezone", timezone);

		return conn.get(ep.toString());
	}

	public static Response getBounceReasonMetrics(RestConn conn, String from, String to, String domains,
			String campaigns, String templates, String sandbox, String metrics, String timezone, String limit)
					throws SparkpostSdkException {

		Endpoint ep = new Endpoint("metrics/deliverability/bounce-reason");
		ep.addParam("from", from);
		ep.addParam("to", to);
		ep.addParam("domains", domains);
		ep.addParam("campaigns", campaigns);
		ep.addParam("templates", templates);
		ep.addParam("sandbox", sandbox);
		ep.addParam("metrics", metrics);
		ep.addParam("timezone", timezone);
		ep.addParam("limit", limit);

		return conn.get(ep.toString());
	}

	public static Response getBounceReasonMetricsByDomain(RestConn conn, String from, String to, String domains,
			String campaigns, String templates, String sandbox, String metrics, String timezone, String limit)
					throws SparkpostSdkException {

		Endpoint ep = new Endpoint("metrics/deliverability/bounce-reason/domain");
		ep.addParam("from", from);
		ep.addParam("to", to);
		ep.addParam("domains", domains);
		ep.addParam("campaigns", campaigns);
		ep.addParam("templates", templates);
		ep.addParam("sandbox", sandbox);
		ep.addParam("metrics", metrics);
		ep.addParam("timezone", timezone);
		ep.addParam("limit", limit);

		return conn.get(ep.toString());
	}

	public static Response getBounceClassificationMetrics(RestConn conn, String from, String to, String domains,
			String campaigns, String templates, String sandbox, String metrics, String timezone, String limit)
					throws SparkpostSdkException {

		Endpoint ep = new Endpoint("metrics/deliverability/bounce-classification");
		ep.addParam("from", from);
		ep.addParam("to", to);
		ep.addParam("domains", domains);
		ep.addParam("campaigns", campaigns);
		ep.addParam("templates", templates);
		ep.addParam("sandbox", sandbox);
		ep.addParam("metrics", metrics);
		ep.addParam("timezone", timezone);
		ep.addParam("limit", limit);

		return conn.get(ep.toString());
	}

	public static Response getRejectionReasonMetrics(RestConn conn, String from, String to, String domains,
			String campaigns, String templates, String sandbox, String timezone, String limit)
					throws SparkpostSdkException {

		Endpoint ep = new Endpoint("metrics/deliverability/rejection-reason");
		ep.addParam("from", from);
		ep.addParam("to", to);
		ep.addParam("domains", domains);
		ep.addParam("campaigns", campaigns);
		ep.addParam("templates", templates);
		ep.addParam("sandbox", sandbox);
		ep.addParam("timezone", timezone);
		ep.addParam("limit", limit);

		return conn.get(ep.toString());
	}

	public static Response getRejectionReasonMetricsByDomain(RestConn conn, String from, String to, String domains,
			String campaigns, String templates, String sandbox, String timezone, String limit)
					throws SparkpostSdkException {

		Endpoint ep = new Endpoint("metrics/deliverability/rejection-reason/domain");
		ep.addParam("from", from);
		ep.addParam("to", to);
		ep.addParam("domains", domains);
		ep.addParam("campaigns", campaigns);
		ep.addParam("templates", templates);
		ep.addParam("sandbox", sandbox);
		ep.addParam("timezone", timezone);
		ep.addParam("limit", limit);

		return conn.get(ep.toString());
	}

	public static Response getDelayReasonMetrics(RestConn conn, String from, String to, String domains,
			String campaigns, String templates, String sandbox, String timezone, String limit)
					throws SparkpostSdkException {

		Endpoint ep = new Endpoint("metrics/deliverability/delay-reason");
		ep.addParam("from", from);
		ep.addParam("to", to);
		ep.addParam("domains", domains);
		ep.addParam("campaigns", campaigns);
		ep.addParam("templates", templates);
		ep.addParam("sandbox", sandbox);
		ep.addParam("timezone", timezone);
		ep.addParam("limit", limit);

		return conn.get(ep.toString());
	}

	public static Response getDelayReasonMetricsByDomain(RestConn conn, String from, String to, String domains,
			String campaigns, String templates, String sandbox, String timezone, String limit)
					throws SparkpostSdkException {

		Endpoint ep = new Endpoint("metrics/deliverability/delay-reason/domain");
		ep.addParam("from", from);
		ep.addParam("to", to);
		ep.addParam("domains", domains);
		ep.addParam("campaigns", campaigns);
		ep.addParam("templates", templates);
		ep.addParam("sandbox", sandbox);
		ep.addParam("timezone", timezone);
		ep.addParam("limit", limit);

		return conn.get(ep.toString());
	}

	public static Response getEngagementDetails(RestConn conn, String from, String to, String timezone, String metrics,
			String campaigns, String templates, String limit) throws SparkpostSdkException {

		Endpoint ep = new Endpoint("metrics/deliverability/link-name");
		ep.addParam("from", from);
		ep.addParam("to", to);
		ep.addParam("timezone", timezone);
		ep.addParam("metrics", metrics);
		ep.addParam("campaigns", campaigns);
		ep.addParam("templates", templates);
		ep.addParam("limit", limit);

		return conn.get(ep.toString());
	}

	public static Response getDeliveriesByAttempt(RestConn conn, String from, String to, String domains,
			String campaigns, String templates, String sandbox, String timezone) throws SparkpostSdkException {

		Endpoint ep = new Endpoint("metrics/deliverability/attempt");
		ep.addParam("from", from);
		ep.addParam("to", to);
		ep.addParam("domains", domains);
		ep.addParam("campaigns", campaigns);
		ep.addParam("templates", templates);
		ep.addParam("sandbox", sandbox);
		ep.addParam("timezone", timezone);
		return conn.get(ep.toString());
	}

	public static Response getCampaignsList(RestConn conn, String match, String limit) throws SparkpostSdkException {

		Endpoint ep = new Endpoint("metrics/campaigns");
		ep.addParam("match", match);
		ep.addParam("limit", limit);

		return conn.get(ep.toString());
	}

	public static Response getDomainsList(RestConn conn, String match, String limit) throws SparkpostSdkException {

		Endpoint ep = new Endpoint("metrics/domains");
		ep.addParam("match", match);
		ep.addParam("limit", limit);

		return conn.get(ep.toString());
	}

}
