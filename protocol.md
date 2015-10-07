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


## Match

Fields:

Type|Name|Serialized Name|Sample|Description
:---|:---|:-------|:-----|:----------
String|protocol|protocol|SMTP|Inbound messaging protocol associated with this webhook
String|domain|domain|example.com|Inbound domain associated with this webhook


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
Map|metadata|metadata||Key/value pairs associated with a recipient,sample={""}
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
int|totalAcceptedRecipients|total_accepted_recipients||The number of recipients in this list that have been accepted
int|totalRejectedRecipients|total_rejected_recipients||The number of recipients in this list that have been rejected


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
Boolean|ownershipVerified|ownership_verified|true|True if ownership is verified
String|spfStatus|spf_status|valid|Current SPF Status. Valid values are "unverified", "pending", "invalid" or "valid".
String|complianceStatus|compliance_status|valid|DKIM Compliance status. Valid values are "pending", "valid", or "blocked".
String|dkimStatus|dkim_status|valid|DKIM status. Valid values are "unverified", "pending", "invalid" or "valid".
String|abuseAtStatus|abuse_at_status|valid|Abuse status. Valid values are "unverified", "pending", "invalid" or "valid".
String|postmasterAtStatus|postmaster_at_status|valid|Postmaster status. Valid values are "unverified", "pending", "invalid" or "valid".


## StoredRecipientList

Fields:

Type|Name|Serialized Name|Sample|Description
:---|:---|:-------|:-----|:----------
String|listId|list_id|AbC123|Identifier of the stored recipient list to use. Specify this field when using a stored recipient list.


## StoredTemplate

Fields:

Type|Name|Serialized Name|Sample|Description
:---|:---|:-------|:-----|:----------
String|templateId|template_id|AbC123|ID of the stored template to use.  Specify this field when using a stored template. Maximum length -- 64 bytes
Boolean|useDraftTemplate|use_draft_template|false|Whether or not to use a draft template. If this field is set to true and no draft template exists, the transmission will fail.


## SuppressionList

Fields:

Type|Name|Serialized Name|Sample|Description
:---|:---|:-------|:-----|:----------
List&lt;SuppressionListEntry>|recipients|recipients|Array of SuppressionListEntrys|A suppression list - or exclusion list, as it is sometimes called - stores a recipient's opt-out preferences.


## SuppressionListEntry

Fields:

Type|Name|Serialized Name|Sample|Description
:---|:---|:-------|:-----|:----------
String|email|email|address@example.com|Email Address
boolean|transactional|transactional|true|Whether the recipient requested to not receive any transactional messages. At a minimum, transactional or non_transactional is required upon creation of the entry.
boolean|nonTransactional|non_transactional|false|Whether the recipient requested to not receive any non-transactional messages. At a minimum, transactional or non_transactional is required upon creation of the entry.
String|source|source|Manually Added|Source responsible for inserting the list entry. Valid values include: FBL, List Unsubscribe, Bounce Rule, Unsubscribe Link, Manually Added, Compliance. Defaults to Manually Added on create
String|description|description||Short explanation of the suppression


## TemplateAttributes

Fields:

Type|Name|Serialized Name|Sample|Description
:---|:---|:-------|:-----|:----------
String|id|id|AbC123|Short, unique, alphanumeric ID used to reference the template. After a template has been created, this property cannot be changed. Maximum length - 64 bytes
TemplateContentAttributes|content|content|TemplateContentAttributes Dictionary|Content that will be used to construct a message. For a full description, see the Content Attributes. Maximum length - 15 MBs
Boolean|published|published|true|Whether the template is published or is a draft version. A template cannot be changed from published to draft.
String|name|name|Template Name|Editable display name. The name does not have to be unique. Maximum length - 1024 bytes
String|description|description|Template Description|Detailed description of the template. Maximum length - 1024 bytes.
OptionsAttributes|options|options|TemplateOptions Dictionary|TemplateOptions in which template options are defined. For a full description, see the Options Attributes.


## TemplateContentAttributes

Fields:

Type|Name|Serialized Name|Sample|Description
:---|:---|:-------|:-----|:----------
String|templateId|template_id|AbC124|ID of the stored template to use. Specify this field when using a stored template. Maximum length -- 64 bytes
Boolean|useDraftTemplate|use_draft_template|false|Whether or not to use a draft template. If this field is set to true and no draft template exists, the transmission will fail.
String|html|html|HTML Content|HTML Content of email
String|text|text|Text Content|Text content for the email's text/plain MIME part
String|subject|subject|Subject of message|Subject of email. Expected in the UTF-8 charset without RFC2047 encoding. Substitution syntax is supported.
AddressAttributes|from|from|"from" : { "name" : "My Company", "email" : "deals@company.com" }|Address used to compose the email's "From" header
String|replyTo|reply_to|reply_name@example.com|Email address used to compose the email's "Reply-To" header
Map|headers|headers|Dictionary of Email Headers|Extra email headers to send
String|emailRFC822|email_rfc822||Alternatively, the email_rfc822 may be used *instead* of all the other fields. The email_rfc822 field is mutually exclusive with all of the above fields.


## TemplateItem

Fields:

Type|Name|Serialized Name|Sample|Description
:---|:---|:-------|:-----|:----------
String|name|name|Template Name|The name of the template
String|id|id|AbC123|The ID of the template
String|lastUpdateTime|last_update_time|2015-02-11T08:00:00-04:00|Format YYYY-MM-DDTHH:MM:SS+-HH:MM or "now"..
String|description|description||Template Description
Boolean|published|published|true|true if template is published otherwise it is a draft


## TemplateListResponse

Fields:

Type|Name|Serialized Name|Sample|Description
:---|:---|:-------|:-----|:----------
List&lt;TemplateItem>|results|results||List of TemplateItems
String|request|request||The URI of the request
String|requestId|requestId||Request Identifier
int|responseCode|responseCode|200|HTTP Response Code generated by request
String|contentType|contentType|application/json|The 'Content-Type' returne by the server
String|responseMessage|responseMessage|OK|The HTTP Message generated by request
String|responseBody|json||The json of the response


## TemplateSubstitutionData

Fields:

Type|Name|Serialized Name|Sample|Description
:---|:---|:-------|:-----|:----------
Map|substitutionData|substitution_data|Dictionary of ssubstitution data|Data the will be substituted into the template


## TransmissionBase

Fields:

Type|Name|Serialized Name|Sample|Description
:---|:---|:-------|:-----|:----------
String|id|id|AbC123|ID of the transmission. A unique ID is generated for each transmission on submission.
String|state|state|Generating|State of the transmission. Valid responses are "submitted", "Generating", "Success", or "Canceled".
OptionsAttributes|options|options|OptionsAttributes Object|Object in which transmission options are defined. For a full description, see the Options Attributes.
String|campaignId|campaign_id|AbC123|Name of the campaign. Maximum length - 64 bytes
String|description|description||Description of the transmission. Maximum length - 1024 bytes
Map|metadata|metadata||Transmission level metadata containing key/value pairs. Metadata is available during events through the Webhooks and is provided to the substitution engine. A maximum of 1000 bytes of merged metadata (transmission level + recipient level) is available with recipient metadata taking precedence over transmission metadata when there are conflicts.
Map|substitutionData|substitution_data||Key/value pairs that are provided to the substitution engine. Recipient substitution data takes precedence over transmission substitution data. Unlike metadata, substitution data is not included in Webhook events.
String|returnPath|return_path|return_name@example.com|Email to use for envelope FROM ( Note: SparkPost Elite only )
TemplateContentAttributes|contentAttributes|content||Content that will be used to construct a message. Maximum size - 15MBs
Integer|totalRecipients|total_recipients|1000|Computed total recipients
Integer|numGenerated|num_generated|1000|Computed total number of messages generated
Integer|numFailedGeneration|num_failed_generation|0|Computed total number of failed messages
Integer|numInvalidRecipients|num_invalid_recipients|0|Number of recipients that failed input validation


## TransmissionWithRecipientArray

Fields:

Type|Name|Serialized Name|Sample|Description
:---|:---|:-------|:-----|:----------
List&lt;RecipientAttributes>|recipientArray|recipients||Inline recipient objects or object containing stored recipient list ID
String|id|id|AbC123|ID of the transmission. A unique ID is generated for each transmission on submission.
String|state|state|Generating|State of the transmission. Valid responses are "submitted", "Generating", "Success", or "Canceled".
OptionsAttributes|options|options|OptionsAttributes Object|Object in which transmission options are defined. For a full description, see the Options Attributes.
String|campaignId|campaign_id|AbC123|Name of the campaign. Maximum length - 64 bytes
String|description|description||Description of the transmission. Maximum length - 1024 bytes
Map|metadata|metadata||Transmission level metadata containing key/value pairs. Metadata is available during events through the Webhooks and is provided to the substitution engine. A maximum of 1000 bytes of merged metadata (transmission level + recipient level) is available with recipient metadata taking precedence over transmission metadata when there are conflicts.
Map|substitutionData|substitution_data||Key/value pairs that are provided to the substitution engine. Recipient substitution data takes precedence over transmission substitution data. Unlike metadata, substitution data is not included in Webhook events.
String|returnPath|return_path|return_name@example.com|Email to use for envelope FROM ( Note: SparkPost Elite only )
TemplateContentAttributes|contentAttributes|content||Content that will be used to construct a message. Maximum size - 15MBs
Integer|totalRecipients|total_recipients|1000|Computed total recipients
Integer|numGenerated|num_generated|1000|Computed total number of messages generated
Integer|numFailedGeneration|num_failed_generation|0|Computed total number of failed messages
Integer|numInvalidRecipients|num_invalid_recipients|0|Number of recipients that failed input validation


## TransmissionWithRecipientList

Fields:

Type|Name|Serialized Name|Sample|Description
:---|:---|:-------|:-----|:----------
StoredRecipientList|recipientList|recipients||Transmission using a recipient list id (a recipient list stored at the server)
String|id|id|AbC123|ID of the transmission. A unique ID is generated for each transmission on submission.
String|state|state|Generating|State of the transmission. Valid responses are "submitted", "Generating", "Success", or "Canceled".
OptionsAttributes|options|options|OptionsAttributes Object|Object in which transmission options are defined. For a full description, see the Options Attributes.
String|campaignId|campaign_id|AbC123|Name of the campaign. Maximum length - 64 bytes
String|description|description||Description of the transmission. Maximum length - 1024 bytes
Map|metadata|metadata||Transmission level metadata containing key/value pairs. Metadata is available during events through the Webhooks and is provided to the substitution engine. A maximum of 1000 bytes of merged metadata (transmission level + recipient level) is available with recipient metadata taking precedence over transmission metadata when there are conflicts.
Map|substitutionData|substitution_data||Key/value pairs that are provided to the substitution engine. Recipient substitution data takes precedence over transmission substitution data. Unlike metadata, substitution data is not included in Webhook events.
String|returnPath|return_path|return_name@example.com|Email to use for envelope FROM ( Note: SparkPost Elite only )
TemplateContentAttributes|contentAttributes|content||Content that will be used to construct a message. Maximum size - 15MBs
Integer|totalRecipients|total_recipients|1000|Computed total recipients
Integer|numGenerated|num_generated|1000|Computed total number of messages generated
Integer|numFailedGeneration|num_failed_generation|0|Computed total number of failed messages
Integer|numInvalidRecipients|num_invalid_recipients|0|Number of recipients that failed input validation


## VerifyAttributes

Fields:

Type|Name|Serialized Name|Sample|Description
:---|:---|:-------|:-----|:----------
Boolean|dkimVerify|dkim_verify|true|Request verification of DKIM record
Boolean|spfVerify|spf_verify|true|Request verification of SPF record


## VerifyResponse

Fields:

Type|Name|Serialized Name|Sample|Description
:---|:---|:-------|:-----|:----------
DKIMResults|results|results|DKIMResult Dictionary|DKIM results


## Webhook

Fields:

Type|Name|Serialized Name|Sample|Description
:---|:---|:-------|:-----|:----------
String|name|name|Inbound Customer Replies|User-friendly name
String|target|target|https://webhooks.customer.example/replies|URL of the target to which to POST relay batches
String|authToken|auth_token|5ebe2294ecd0e0f08eab7690d2a6ee69|Authentication token to present in the X-MessageSystems-Webhook-Token header of POST requests to target
List&lt;String>|events|events||Array of events
Match|match|match|"match": { "protocol": "SMTP", "domain": "replies.customer.example" }|Restrict which inbound messages will be relayed to the target


## ListAllSendingDomiansResponse

Fields:

Type|Name|Serialized Name|Sample|Description
:---|:---|:-------|:-----|:----------
List&lt;Domain>|domains|results|Array of sending domains|List of Sending Domains
String|request|request||The URI of the request
String|requestId|requestId||Request Identifier
int|responseCode|responseCode|200|HTTP Response Code generated by request
String|contentType|contentType|application/json|The 'Content-Type' returne by the server
String|responseMessage|responseMessage|OK|The HTTP Message generated by request
String|responseBody|json||The json of the response


## Response

Fields:

Type|Name|Serialized Name|Sample|Description
:---|:---|:-------|:-----|:----------
String|request|request||The URI of the request
String|requestId|requestId||Request Identifier
int|responseCode|responseCode|200|HTTP Response Code generated by request
String|contentType|contentType|application/json|The 'Content-Type' returne by the server
String|responseMessage|responseMessage|OK|The HTTP Message generated by request
String|responseBody|json||The json of the response


## TemplateCreateResponse

Fields:

Type|Name|Serialized Name|Sample|Description
:---|:---|:-------|:-----|:----------
CreateResult|results|results||A Create template result
String|request|request||The URI of the request
String|requestId|requestId||Request Identifier
int|responseCode|responseCode|200|HTTP Response Code generated by request
String|contentType|contentType|application/json|The 'Content-Type' returne by the server
String|responseMessage|responseMessage|OK|The HTTP Message generated by request
String|responseBody|json||The json of the response


## TemplateListResponse

Fields:

Type|Name|Serialized Name|Sample|Description
:---|:---|:-------|:-----|:----------
List&lt;TemplateItem>|results|results||List of TemplateItems
String|request|request||The URI of the request
String|requestId|requestId||Request Identifier
int|responseCode|responseCode|200|HTTP Response Code generated by request
String|contentType|contentType|application/json|The 'Content-Type' returne by the server
String|responseMessage|responseMessage|OK|The HTTP Message generated by request
String|responseBody|json||The json of the response


## TemplatePreviewResponse

Fields:

Type|Name|Serialized Name|Sample|Description
:---|:---|:-------|:-----|:----------
TemplatePreviewData|results|results||
String|request|request||The URI of the request
String|requestId|requestId||Request Identifier
int|responseCode|responseCode|200|HTTP Response Code generated by request
String|contentType|contentType|application/json|The 'Content-Type' returne by the server
String|responseMessage|responseMessage|OK|The HTTP Message generated by request
String|responseBody|json||The json of the response


## TransmissionCreateItem

Fields:

Type|Name|Serialized Name|Sample|Description
:---|:---|:-------|:-----|:----------
int|totalRejectedRecipients|total_rejected_recipients||Number of recipients that were rejected
int|totalAcceptedRecipients|total_accepted_recipients||Number of recipients accepted
String|id|id||Unique ID of transmission
String|request|request||The URI of the request
String|requestId|requestId||Request Identifier
int|responseCode|responseCode|200|HTTP Response Code generated by request
String|contentType|contentType|application/json|The 'Content-Type' returne by the server
String|responseMessage|responseMessage|OK|The HTTP Message generated by request
String|responseBody|json||The json of the response


## TransmissionCreateResponse

Fields:

Type|Name|Serialized Name|Sample|Description
:---|:---|:-------|:-----|:----------
TransmissionCreateItem|results|results||Result of transmission
String|request|request||The URI of the request
String|requestId|requestId||Request Identifier
int|responseCode|responseCode|200|HTTP Response Code generated by request
String|contentType|contentType|application/json|The 'Content-Type' returne by the server
String|responseMessage|responseMessage|OK|The HTTP Message generated by request
String|responseBody|json||The json of the response


## TransmissionListResponse

Fields:

Type|Name|Serialized Name|Sample|Description
:---|:---|:-------|:-----|:----------
List&lt;TransmissionBase>|results|results||List of TransmissionsBase items
String|request|request||The URI of the request
String|requestId|requestId||Request Identifier
int|responseCode|responseCode|200|HTTP Response Code generated by request
String|contentType|contentType|application/json|The 'Content-Type' returne by the server
String|responseMessage|responseMessage|OK|The HTTP Message generated by request
String|responseBody|json||The json of the response


## TransmissionResponseInfo

Fields:

Type|Name|Serialized Name|Sample|Description
:---|:---|:-------|:-----|:----------
String|description|description||Maximum length - 1024 bytes
String|generationEndTime|generation_end_time||When the generation ended
int|rcptListTotalChunks|rcpt_list_total_chunks||
int|rcptListChunkSize|rcpt_list_chunk_size||The size to use for recipient lists
int|numRecipients|num_rcpts||Number of recipients in transmission
int|numGenerated|num_generated||Number of emails generated
OptionsAttributes|options|options||Transmission options
String|id|id||Transmission id
String|returnPath|return_path||Transmission return path
String|generationStartTime|generation_start_time||The start time for the generation
String|metadata|metadata||Transmission metadata
String|name|name||Transmission names
int|numFailedGen|num_failed_gen||Number of message that failed to generate
String|rcptListId|rcpt_list_id||The recipient list id for this transmission
String|campaignId|campaign_id||Transmission campaign id
TemplateContentAttributes|content|content||Template contents
String|request|request||The URI of the request
String|requestId|requestId||Request Identifier
int|responseCode|responseCode|200|HTTP Response Code generated by request
String|contentType|contentType|application/json|The 'Content-Type' returne by the server
String|responseMessage|responseMessage|OK|The HTTP Message generated by request
String|responseBody|json||The json of the response


## TransmissionRetrieveResponseContainer

Fields:

Type|Name|Serialized Name|Sample|Description
:---|:---|:-------|:-----|:----------
TransmissionResponseInfo|transmission|transmission||The transmission information
String|request|request||The URI of the request
String|requestId|requestId||Request Identifier
int|responseCode|responseCode|200|HTTP Response Code generated by request
String|contentType|contentType|application/json|The 'Content-Type' returne by the server
String|responseMessage|responseMessage|OK|The HTTP Message generated by request
String|responseBody|json||The json of the response


## TransmissionRetrieveResults

Fields:

Type|Name|Serialized Name|Sample|Description
:---|:---|:-------|:-----|:----------
TransmissionRetrieveResponseContainer|results|results||The transmission result
String|request|request||The URI of the request
String|requestId|requestId||Request Identifier
int|responseCode|responseCode|200|HTTP Response Code generated by request
String|contentType|contentType|application/json|The 'Content-Type' returne by the server
String|responseMessage|responseMessage|OK|The HTTP Message generated by request
String|responseBody|json||The json of the response


## WebhookListAllResponse

Fields:

Type|Name|Serialized Name|Sample|Description
:---|:---|:-------|:-----|:----------
List&lt;Webhook>|results|results||An array of Webhook data
String|request|request||The URI of the request
String|requestId|requestId||Request Identifier
int|responseCode|responseCode|200|HTTP Response Code generated by request
String|contentType|contentType|application/json|The 'Content-Type' returne by the server
String|responseMessage|responseMessage|OK|The HTTP Message generated by request
String|responseBody|json||The json of the response



SparkPost Protocol Library markdown generated by [json-doc](https://github.com/yepher/json-doc)

