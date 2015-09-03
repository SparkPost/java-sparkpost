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

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO for storing the 'options' field in a template.
 *
 * @author grava
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TemplateOptions extends Base {

	private Boolean open_tracking = null;
	
	private Boolean click_tracking = null;
	
	private Boolean transactional = null;
	
	private Boolean sandbox = null;
}
