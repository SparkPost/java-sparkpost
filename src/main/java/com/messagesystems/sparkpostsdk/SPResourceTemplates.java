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

package com.messagesystems.sparkpostsdk;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/** Resource collection that is a 1-to-1 match to the Templates  SparkPost API.<br>
 * <br>
 * See <a href="https://www.sparkpost.com/api#/reference/templates/">Templates API</a>
 *
 * @author grava
 */
public class SPResourceTemplates {

    public static SPResponse create(
            SPRestConn conn, SPDTOTemplate tpl)
            throws SparkpostSdkException {

        String json = tpl.toJson();
        return conn.post("templates", json);
    }

    static public SPResponse retrieve(
            SPRestConn conn, String id, Boolean draft)
            throws SparkpostSdkException {

        SPEndpoint ep = new SPEndpoint("templates/" + id);
        ep.addParam("draft", draft);
        return conn.get(ep.toString());
    }

    static public SPResponse listAll(SPRestConn conn)
            throws SparkpostSdkException {

        return conn.get("templates/");
    }

    static public SPResponse update(
            SPRestConn conn, String id, Boolean updatePublished, SPDTOTemplate tpl)
            throws SparkpostSdkException {

        SPEndpoint ep = new SPEndpoint("templates/" + id);
        ep.addParam("update_published", updatePublished);
        String json = tpl.toJson();
        return conn.put(ep.toString(), json);
    }

    static public SPResponse preview(
            SPRestConn conn, String id, Boolean draft,
            SPDTOTemplateSubstitutionData subst) 
            throws SparkpostSdkException {

        SPEndpoint ep = new SPEndpoint("templates/" + id + "/preview");
        ep.addParam("draft", draft);
        String json = subst.toJson();
        return conn.put(ep.toString(), json);
    }

    static public SPResponse delete(
            SPRestConn conn, String id) 
            throws SparkpostSdkException {

        return conn.delete("templates/"+id);
    }
}
