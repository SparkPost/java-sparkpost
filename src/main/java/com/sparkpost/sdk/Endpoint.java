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

/** Used internally to the Sparkpost SDK to write URL queries.
 *
 * @author grava
 */
class Endpoint {

    private StringBuilder sb;
    private int paramCount;

    public Endpoint(String endpoint) {
        sb = new StringBuilder();
        sb.append(endpoint);
        paramCount = 0;
    }

    private void addString(String name, String val) {
        if (paramCount == 0) {
            sb.append('?');
        } else {
            sb.append('&');
        }
        sb.append(name);
        sb.append('=');
        sb.append(val);
        paramCount++;
    }

    public Endpoint addParam(String name, String val) {
        if (val != null) {
            addString(name, val);
        }
        return this;
    }

    public Endpoint addParam(String name, Integer val) {
        if (val != null) {
            addString(name, val.toString());
        }
        return this;
    }

    public Endpoint addParam(String name, Boolean val) {
        if (val != null) {
            addString(name, val.toString());
        }
        return this;
    }

    @Override
    public String toString() {
        return sb.toString();
    }

}
