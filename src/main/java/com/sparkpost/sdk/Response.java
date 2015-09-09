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

/** The response for the SparkPost server, as returned by @a SPRestConn
 *
 * @author grava
 */
public class Response {

    private String request = null;
    private String requestId = null;
    private int responseCode = -1;
    private String responseMessage = null;
    private String json = null;

    public void setRequest(String request) {
        this.request = request;
    }

    public String getRequest() {
        return request;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseBody(String json) {
        this.json = json;
    }

    public String getResponseBody() {
        return this.json;
    }

    public void reset() {
        request = null;
        requestId = null;
        responseCode = -1;
        responseMessage = null;
        json = null;
    }

    @Override
    public String toString() {
        return "Request:" + request
                + ",RequestId:" + requestId
                + ",ResponseCode:" + responseCode
                + ",ResponseMessage:" + responseMessage
                + ",json=" + json;
    }

}
