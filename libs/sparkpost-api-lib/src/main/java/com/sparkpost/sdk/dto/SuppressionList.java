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

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * A suppression list - or exclusion list, as it is sometimes called - stores a
 * recipient's opt-out preferences. It is a list of recipient email addresses to
 * which you do NOT want to send email. The Suppression List API is used to
 * manage your customer-specific exclusion list entries. An entry indicates
 * whether the recipient requested to receive one of the following:
 * 
 * - Transactional and non-transactional messages from a given customer
 * - Transactional messages only from a given customer
 * - Non-transactional messages only from a given customer
 * - No messages from a given customer
 * 
 * Transactional messages are single recipient messages that are used operationally, 
 * e.g. to reset a password or confirm a purchase; while non-transactional messages 
 * are used to run email campaigns where a list of recipients are targeted, e.g. advertising 
 * a sales event.
 *
 * In addition to the customer-specific exclusion list, Message Systems maintains a global 
 * suppression list across all customers.
 *
 * @author grava
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SuppressionList extends Base {

	private List<SuppressionListEntry> recipients = null;

}
