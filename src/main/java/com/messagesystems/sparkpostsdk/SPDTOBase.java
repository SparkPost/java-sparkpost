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

/** Base class for all DTOs. DO NOT USE DIRECTLY. 
 *  This base class takes care of the JSON serialization.
 * @author grava
 */
public class SPDTOBase {

    public String toJson() {
        GsonBuilder gsonBuilder = new GsonBuilder()
                //.excludeFieldsWithoutExposeAnnotation()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Gson gson = gsonBuilder.setPrettyPrinting().create();
        return gson.toJson(this) ;
    }
}
