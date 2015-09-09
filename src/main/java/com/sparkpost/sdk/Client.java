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

import org.apache.log4j.Logger;

/** The SPClient class stores everything specific to the SparkPost client:<BR>
 *  <ul>
 *  <li> The Authorization Key
 *  <li> The "From:" email address
 *  </ul>
 *
 * @author grava
 */
public class Client {

    private static final Logger logger = Logger.getLogger(Client.class);
    private String authKey = null;
    private String fromEmail = null ;

    public Client() {
    }

    public Client(String key) {
        SetAuthKey(key);
    }

    /**
     *
     * @param key
     */
    public final void SetAuthKey(String key) {
        this.authKey = key;
        logger.debug("Auth key now: " + this.authKey);
    }

    public String GetAuthKey() {
        return this.authKey;
    }

    /**
     * @return the fromEmail
     */
    public String getFromEmail() {
        return fromEmail;
    }

    /**
     * @param fromEmail the fromEmail to set
     */
    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }
    
    @Override
    public String toString() {
        return "Auth key: " + this.authKey + ", From Email: " + this.fromEmail ;
    }

}
