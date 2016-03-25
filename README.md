<a href="https://www.sparkpost.com"><img src="https://www.sparkpost.com/sites/default/files/attachments/SparkPost_Logo_2-Color_Gray-Orange_RGB.svg" width="200px"/></a>

[Sign up](https://app.sparkpost.com/sign-up?src=Dev-Website&sfdcid=70160000000pqBb) for a SparkPost account and visit our [Developer Hub](https://developers.sparkpost.com) for even more content.

# SparkPost Java Library

[![Build Status](https://travis-ci.org/SparkPost/java-sparkpost.svg?branch=master)](https://travis-ci.org/SparkPost/java-sparkpost) [![Slack Status](http://slack.sparkpost.com/badge.svg)](http://slack.sparkpost.com)

Use this library in Java applications to easily access the SparkPost Email API in your application.

## Version Compatibility Note

### Version 0.12 -> 0.13

Although we try to maintain library backward compatibility this migration may require some minor changes to your code. Substitution data was changed from `Map<String, String>` to `Map<String, Object>`. Most client code will just need to change their map to this new signature.

## Getting Started

The SparkPost Java Library is available in this [Maven Repository](http://maven.apache.org/download.cgi):


```xml
<dependency>
	<groupId>com.sparkpost</groupId>
	<artifactId>sparkpost-lib</artifactId>
	<version>0.13</version>
</dependency>
```

## Building SparkPost4J

## Basic Send Email Example

```java

package com.sparkpost;

import com.sparkpost.exception.SparkPostException;

public class SparkPost {

    public static void main(String[] args) throws SparkPostException {
        String API_KEY = "YOUR API KEY HERE!!!";
        Client client = new Client(API_KEY);

        client.sendMessage(
                "you@yourdomain.com",
                "to@sparkpost.com",
                "The subject of the message",
                "The text part of the email",
                "<b>The HTML part of the email</b>");

    }
}

```



## Advanced Send Email Example

With SparkPost you have complete control over all aspects of an email and a powerful tempting solution.

```java

private void sendEmail(String from, String[] recipients, String email) throws SparkPostException {
	TransmissionWithRecipientArray transmission = new TransmissionWithRecipientArray();

	// Populate Recipients
	List<RecipientAttributes> recipientArray = new ArrayList<RecipientAttributes>();
	for (String recipient : recipients) {
	RecipientAttributes recipientAttribs = new RecipientAttributes();
		recipientAttribs.setAddress(new AddressAttributes(recipient));
		recipientArray.add(recipientAttribs);
	}
	transmission.setRecipientArray(recipientArray);

	transmission.setReturnPath(from);

	// Populate Substitution Data
	Map<String, String> substitutionData = new HashMap<String, String>();
	substitutionData.put("from", from);
	transmission.setSubstitutionData(substitutionData);

	// Populate Email Body
	TemplateContentAttributes contentAttributes = new TemplateContentAttributes();
	contentAttributes.setEmailRFC822(email);
	transmission.setContentAttributes(contentAttributes);

	// Send the Email
	RestConnection connection = new RestConnection(client, getEndPoint());
	Response response = ResourceTransmissions.create(connection, 0, transmission);

	logger.debug("Transmission Response: " + response);
}

```
