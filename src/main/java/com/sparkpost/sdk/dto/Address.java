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

package com.sparkpost.sdk.dto;

/** DTO for storing an address (email, name, header_to)
 *
 * @author grava
 */
public class Address extends Base {

    public String email = null;
    public String name = null;
    public String header_to = null;

    public Address(String email, String name, String header_to) {
        this.email = email;
        this.name = name;
        this.header_to = header_to;
    }
}
