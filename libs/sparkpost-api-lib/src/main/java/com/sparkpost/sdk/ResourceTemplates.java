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

import com.sparkpost.sdk.dto.Template;
import com.sparkpost.sdk.dto.TemplateSubstitutionData;

/** Resource collection that is a 1-to-1 match to the Templates  SparkPost API.<br>
 * <br>
 * See <a href="https://www.sparkpost.com/api#/reference/templates/">Templates API</a>
 *
 * @author grava
 */
public class ResourceTemplates {

    public static Response create(
            RestConn conn, Template tpl)
            throws SparkpostSdkException {

        String json = tpl.toJson();
        return conn.post("templates", json);
    }

    static public Response retrieve(
            RestConn conn, String id, Boolean draft)
            throws SparkpostSdkException {

        Endpoint ep = new Endpoint("templates/" + id);
        ep.addParam("draft", draft);
        return conn.get(ep.toString());
    }

    static public Response listAll(RestConn conn)
            throws SparkpostSdkException {

        return conn.get("templates/");
    }

    static public Response update(
            RestConn conn, String id, Boolean updatePublished, Template tpl)
            throws SparkpostSdkException {

        Endpoint ep = new Endpoint("templates/" + id);
        ep.addParam("update_published", updatePublished);
        String json = tpl.toJson();
        return conn.put(ep.toString(), json);
    }

    static public Response preview(
            RestConn conn, String id, Boolean draft,
            TemplateSubstitutionData subst) 
            throws SparkpostSdkException {

        Endpoint ep = new Endpoint("templates/" + id + "/preview");
        ep.addParam("draft", draft);
        String json = subst.toJson();
        return conn.put(ep.toString(), json);
    }

    static public Response delete(
            RestConn conn, String id) 
            throws SparkpostSdkException {

        return conn.delete("templates/"+id);
    }
}
