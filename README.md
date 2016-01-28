<a href="https://www.sparkpost.com"><img src="https://www.sparkpost.com/sites/default/files/attachments/SparkPost_Logo_2-Color_Gray-Orange_RGB.svg" width="200px"/></a>

[Sign up](https://app.sparkpost.com/sign-up?src=Dev-Website&sfdcid=70160000000pqBb) for a SparkPost account and visit our [Developer Hub](https://developers.sparkpost.com) for even more content.

# SparkPost Java Library

[![Build Status](https://travis-ci.org/SparkPost/java-sparkpost.svg?branch=master)](https://travis-ci.org/SparkPost/java-sparkpost)

Use this library in Java applications to easily access the SparkPost Email API in your application.

## Getting Started

The SparkPost Java Library is available in this [Maven Repository](http://maven.apache.org/download.cgi):


```xml
<dependency>
	<groupId>com.sparkpost</groupId>
	<artifactId>client-server-protocol-lib</artifactId>
	<version>0.9</version>
</dependency>
```

## Building SparkPost4J



## Send Email Example

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
