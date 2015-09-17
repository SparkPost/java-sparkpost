# SparkPost Protocol Library

## AddressAttributes

Fields:

Type|Name|Serialized Name|Sample|Description
:---|:---|:-------|:-----|:----------
String|email|email|someone@example.com|Valid email address
String|name|name|John Doe|User-friendly name for the email address
String|headerTo|header_to|someone@example.com|Email address to display in the "To" header instead of address.email (for BCC)


## DKIM

Fields:

Type|Name|Serialized Name|Sample|Description
:---|:---|:-------|:-----|:----------
String|privateKey|private||The private key will be used to create the DKIM Signature.
String|publicKey|public||The public key will be retrieved from DNS of the sending domain.
String|selector|selector||The DomainKey selector will be used to indicate the DKIM public key location.
String|headers|headers|from:to:subject:date|Header fields are separated by a colon.


## DKIMResults

Fields:

Type|Name|Serialized Name|Sample|Description
:---|:---|:-------|:-----|:----------
StatusAttributes|status|status||DKIM status result.
DKIM|dkim|dkim||DKIM data


## DNSAttributes

Fields:

Type|Name|Serialized Name|Sample|Description
:---|:---|:-------|:-----|:----------
String|dkimRecord|dkim_record||DNS DKIM record for the registered Sending Domain
String|spfRecord|spf_record||DNS SPF record for the registered Sending Domain
String|dkimError|dkim_error||Error message describing reason for DKIM verification failure
String|spfError|spf_error||Error message describing reason for SPF verification failure


## OptionsAttributes

Fields:

Type|Name|Serialized Name|Sample|Description
:---|:---|:-------|:-----|:----------
String|startTime|start_time|2015-02-11T08:00:00-04:00|Format YYYY-MM-DDTHH:MM:SS+-HH:MM or "now".
Boolean|openTracking|open_tracking||Whether open tracking is enabled for this transmission. defaults to true
Boolean|clickTracking|click_tracking||Whether click tracking is enabled for this transmission. defaults to true
Boolean|transactional|transactional||Whether message is transactional or non-transactional for unsubscribe and suppression purposes
Boolean|skipSuppression|skip_suppression||Unlike most other options, this flag is omitted on a GET transmission response unless the flag's value is true.


## RecipientAttributes

Fields:

Type|Name|Serialized Name|Sample|Description
:---|:---|:-------|:-----|:----------
AddressAttributes|address|address|recipient@example.com|Address information for a recipient
String|returnPath|return_path||Email to use for envelope FROM ( Note: SparkPost Elite only )
List&lt;String>|tags|tags||Array of text labels associated with a recipient Tags are available in Webhook events. Maximum number of tags - 10 per recipient, 100 system wide. Any tags over the limits are ignored.
Map|metadata|metadata||Key/value pairs associated with a recipient
Map|substitutionData|substitution_data||Key/value pairs associated with a recipient that are provided to the substitution engine


## RecipientList

Fields:

Type|Name|Serialized Name|Sample|Description
:---|:---|:-------|:-----|:----------
String|id|id||Short, unique, recipient list identifier. Maximum length - 64 bytes
String|name|name|| Short, pretty/readable recipient list display name, not required to be unique. Maximum length - 64 bytes
String|description|description||Detailed description of the recipient list. Maximum length - 1024 bytes
Map|attributes|attributes|| Recipient list attribute object
List&lt;RecipientAttributes>|recipients|recipients|| List of recipient objects


## SendingDomain

Fields:

Type|Name|Serialized Name|Sample|Description
:---|:---|:-------|:-----|:----------
String|domain|domain|from_address@example.com|Name of the sending domain. The domain name will be used as the "From:" header address in the email.
StatusAttributes|status|status||Status details, including whether this domain's ownership has been verified
DKIM|dkim|dkim||DKIM key configuration. For a full description, see the DKIM Attributes.


## StatusAttributes

Fields:

Type|Name|Serialized Name|Sample|Description
:---|:---|:-------|:-----|:----------
Boolean|ownershipVerified|ownership_verified||True if ownership is verified
String|spfStatus|spf_status||Current SPF Status
String|complianceStatus|compliance_status||DKIM Compliance status
String|dkimStatus|dkim_status||DKIM status
String|abuseAtStatus|abuse_at_status||Abuse status
String|postmasterAtStatus|postmaster_at_status||Postmaster status


## StoredRecipientList

Fields:

Type|Name|Serialized Name|Sample|Description
:---|:---|:-------|:-----|:----------
String|listId|list_id||Identifier of the stored recipient list to use. Specify this field when using a stored recipient list.


## StoredTemplate

Fields:

Type|Name|Serialized Name|Sample|Description
:---|:---|:-------|:-----|:----------
String|templateId|template_id||ID of the stored template to use.  Specify this field when using a stored template. Maximum length -- 64 bytes
Boolean|useDraftTemplate|use_draft_template||Whether or not to use a draft template. If this field is set to true and no draft template exists, the transmission will fail.


## SuppressionList

Fields:

Type|Name|Serialized Name|Sample|Description
:---|:---|:-------|:-----|:----------
List&lt;SuppressionListEntry>|recipients|recipients||A suppression list - or exclusion list, as it is sometimes called - stores a recipient's opt-out preferences.


## SuppressionListEntry

Fields:

Type|Name|Serialized Name|Sample|Description
:---|:---|:-------|:-----|:----------
String|email|email||
boolean|transactional|transactional||Whether the recipient requested to not receive any transactional messages. At a minimum, transactional or non_transactional is required upon creation of the entry.
boolean|nonTransactional|non_transactional||Whether the recipient requested to not receive any non-transactional messages. At a minimum, transactional or non_transactional is required upon creation of the entry.
String|source|source||Source responsible for inserting the list entry. Valid values include: FBL, List Unsubscribe, Bounce Rule, Unsubscribe Link, Manually Added, Compliance. Defaults to Manually Added on create
String|description|description||Short explanation of the suppression


## TemplateAttributes

Fields:

Type|Name|Serialized Name|Sample|Description
:---|:---|:-------|:-----|:----------
String|id|id||Short, unique, alphanumeric ID used to reference the template. After a template has been created, this property cannot be changed. Maximum length - 64 bytes
TemplateContentAttributes|content|content||Content that will be used to construct a message. For a full description, see the Content Attributes. Maximum length - 15 MBs
Boolean|published|published||Whether the template is published or is a draft version. A template cannot be changed from published to draft.
String|name|name||Editable display name. The name does not have to be unique. Maximum length - 1024 bytes
String|description|description||Detailed description of the template. Maximum length - 1024 bytes.
OptionsAttributes|options|options||TemplateOptions in which template options are defined. For a full description, see the Options Attributes.


## TemplateContentAttributes

Fields:

Type|Name|Serialized Name|Sample|Description
:---|:---|:-------|:-----|:----------
String|templateId|template_id|| ID of the stored template to use. Specify this field when using a stored template. Maximum length -- 64 bytes
Boolean|useDraftTemplate|use_draft_template||Whether or not to use a draft template. If this field is set to true and no draft template exists, the transmission will fail.
String|html|html||HTML Content of email
String|text|text||Text content of email
String|subject|subject||Subject of email
AddressAttributes|from|from||
String|replyTo|reply_to||
Map|headers|headers||Extra email headers to send
String|emailRFC822|email_rfc822||Alternatively, the email_rfc822 may be used *instead* of all the other fields. The email_rfc822 field is mutually exclusive with all of the above fields.


## TemplateItem

Fields:

Type|Name|Serialized Name|Sample|Description
:---|:---|:-------|:-----|:----------
String|name|name||The name of the template
String|id|id||The ID of the template
String|lastUpdateTime|last_update_time||Format YYYY-MM-DDTHH:MM:SS+-HH:MM or "now". Example: '2015-02-11T08:00:00-04:00'.
String|description|description||Template Description
Boolean|published|published||true if template is published otherwise it is a draft


## TemplateListResponse

Fields:

Type|Name|Serialized Name|Sample|Description
:---|:---|:-------|:-----|:----------
List&lt;TemplateItem>|results|results||List of TemplateItems
String|request|request||The URI of the request
String|requestId|requestId||Request Identifier
int|responseCode|responseCode|200|HTTP Response Code generated by request
String|responseMessage|responseMessage|OK|The HTTP Message generated by request
String|responseBody|json||The json of the response


## TemplateSubstitutionData

Fields:

Type|Name|Serialized Name|Sample|Description
:---|:---|:-------|:-----|:----------
Map|substitutionData|substitution_data||Data the will be substituted into the template


## TransmissionBase

Fields:

Type|Name|Serialized Name|Sample|Description
:---|:---|:-------|:-----|:----------
String|id|id||ID of the transmission. A unique ID is generated for each transmission on submission.
String|state|state||State of the transmission. Valid responses are "submitted", "Generating", "Success", or "Canceled".
OptionsAttributes|options|options||Object in which transmission options are defined. For a full description, see the Options Attributes.
String|campaignId|campaign_id||Name of the campaign. Maximum length - 64 bytes
String|description|description||Description of the transmission. Maximum length - 1024 bytes
Map|metadata|metadata||Transmission level metadata containing key/value pairs
Map|substitutionData|substitution_data||Key/value pairs that are provided to the substitution engine
String|returnPath|return_path||Email to use for envelope FROM ( Note: SparkPost Elite only )
TemplateContentAttributes|contentAttributes|content||Content that will be used to construct a message. Maximum size - 15MBs
Integer|totalRecipients|total_recipients||Computed total recipients
Integer|numGenerated|num_generated||Computed total number of messages generated
Integer|numFailedGeneration|num_failed_generation||Computed total number of failed messages
Integer|numInvalidRecipients|num_invalid_recipients||Number of recipients that failed input validation


## TransmissionWithRecipientArray

Fields:

Type|Name|Serialized Name|Sample|Description
:---|:---|:-------|:-----|:----------
List&lt;RecipientAttributes>|recipientArray|recipients||Inline recipient objects or object containing stored recipient list ID
String|id|id||ID of the transmission. A unique ID is generated for each transmission on submission.
String|state|state||State of the transmission. Valid responses are "submitted", "Generating", "Success", or "Canceled".
OptionsAttributes|options|options||Object in which transmission options are defined. For a full description, see the Options Attributes.
String|campaignId|campaign_id||Name of the campaign. Maximum length - 64 bytes
String|description|description||Description of the transmission. Maximum length - 1024 bytes
Map|metadata|metadata||Transmission level metadata containing key/value pairs
Map|substitutionData|substitution_data||Key/value pairs that are provided to the substitution engine
String|returnPath|return_path||Email to use for envelope FROM ( Note: SparkPost Elite only )
TemplateContentAttributes|contentAttributes|content||Content that will be used to construct a message. Maximum size - 15MBs
Integer|totalRecipients|total_recipients||Computed total recipients
Integer|numGenerated|num_generated||Computed total number of messages generated
Integer|numFailedGeneration|num_failed_generation||Computed total number of failed messages
Integer|numInvalidRecipients|num_invalid_recipients||Number of recipients that failed input validation


## TransmissionWithRecipientList

Fields:

Type|Name|Serialized Name|Sample|Description
:---|:---|:-------|:-----|:----------
StoredRecipientList|recipientList|recipients|| DTO for a transmission using a recipient list id (a recipient list stored at the server)
String|id|id||ID of the transmission. A unique ID is generated for each transmission on submission.
String|state|state||State of the transmission. Valid responses are "submitted", "Generating", "Success", or "Canceled".
OptionsAttributes|options|options||Object in which transmission options are defined. For a full description, see the Options Attributes.
String|campaignId|campaign_id||Name of the campaign. Maximum length - 64 bytes
String|description|description||Description of the transmission. Maximum length - 1024 bytes
Map|metadata|metadata||Transmission level metadata containing key/value pairs
Map|substitutionData|substitution_data||Key/value pairs that are provided to the substitution engine
String|returnPath|return_path||Email to use for envelope FROM ( Note: SparkPost Elite only )
TemplateContentAttributes|contentAttributes|content||Content that will be used to construct a message. Maximum size - 15MBs
Integer|totalRecipients|total_recipients||Computed total recipients
Integer|numGenerated|num_generated||Computed total number of messages generated
Integer|numFailedGeneration|num_failed_generation||Computed total number of failed messages
Integer|numInvalidRecipients|num_invalid_recipients||Number of recipients that failed input validation


## VerifyAttributes

Fields:

Type|Name|Serialized Name|Sample|Description
:---|:---|:-------|:-----|:----------
Boolean|dkimVerify|dkim_verify||Request verification of DKIM record
Boolean|spfVerify|spf_verify||Request verification of SPF record


## VerifyResponse

Fields:

Type|Name|Serialized Name|Sample|Description
:---|:---|:-------|:-----|:----------
DKIMResults|results|results||


## Webhook

Fields:

Type|Name|Serialized Name|Sample|Description
:---|:---|:-------|:-----|:----------
String|name|name||
String|target|target||
String|authToken|auth_token||
List&lt;String>|events|events||



SparkPost Protocol Library markdown generated by [json-doc](https://github.com/yepher/json-doc)

