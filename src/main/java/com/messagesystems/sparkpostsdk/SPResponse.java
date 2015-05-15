/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.messagesystems.sparkpostsdk;

/**
 *
 * @author grava
 */
public class SPResponse {

    private String request = null;
    private String requestId = null;
    private int responseCode = -1;
    private String responseMessage = null;
    private String json = null;

    public void setRequest(String request) {
        this.request = request;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public void setResponseBody(String json) {
        this.json = json;
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
