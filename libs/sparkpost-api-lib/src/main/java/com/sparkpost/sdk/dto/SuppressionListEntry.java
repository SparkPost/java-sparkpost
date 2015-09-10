package com.sparkpost.sdk.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO for storing an entry in a suppression list.
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SuppressionListEntry extends Base {

	public static final class StatusTypes {
		public static final String FBL = "FBL";
		public static final String LIST_UNSUBSCRIBE = "List Unsubscribe";
		public static final String BOUNCE_RULE = "Bounce Rule";
		public static final String UNSUBSCRIBE_LINK = "Unsubscribe Link";
		public static final String MANUALLY_ADDED = "Manually Added";
		public static final String COMPLIANCE = "Compliance";
	}
	
	public SuppressionListEntry() {
		
	}
	
	public SuppressionListEntry(SuppressionListEntry entry) {
		this.email = entry.email;
		this.transactional = entry.transactional;
		this.non_transactional = entry.non_transactional;
		this.source = entry.source;
		this.description = entry.description;
		
	}
	
	/**
	 * 
	 */
	private String email;

	/**
	 * Whether the recipient requested to not receive any transactional messages
	 * 
	 * At a minimum, transactional or non_transactional is required upon creation of the entry.
	 */
	private boolean transactional;

	/**
	 * Whether the recipient requested to not receive any non-transactional messages
	 * 
	 * At a minimum, transactional or non_transactional is required upon creation of the entry.
	 */
	private boolean non_transactional;

	/**
	 * Source responsible for inserting the list entry. Valid values include: FBL, List Unsubscribe, Bounce Rule, Unsubscribe Link, Manually Added, Compliance
	 * 
	 * defaults to Manually Added on create
	 * 
	 * See StatusTypes
	 */
	private String source = StatusTypes.MANUALLY_ADDED;

	/**
	 * Short explanation of the suppression
	 */
	private String description;
		
}
