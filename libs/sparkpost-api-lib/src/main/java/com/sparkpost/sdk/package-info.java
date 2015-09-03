/**
 * com.messagesystems.sparkpostsdk provides an interface to the SparkPost
 * REST API.<br>
 * <br>
 * This java API is designed to follow closely the design of the SparkPost
 * REST API. As a result, this java API is composed of 3 groups of java classes:<br>
 * <ul>
 * <li> Resources classes
 * <li> DTO classes (Data Transfer Objects)
 * <li> REST mechanism classes.
 * </ul>
 * <br>
 * <h1> Naming conventions</h1><br>
 * <ul>
 * <li> SP stands for SparkPost
 * <li> All classes starting with "SPResource" are resources (endpoint collections, see below)
 * <li> All classes starting with "SPDTO" are Data Transfer Object (see below)
 * </ul>
 * <br>
 * <h1> Resources classes </h1><br>
 * A resource class is a collection of static methods under one class.<br>
 * All of the methods are a 1-to-1 match with an endpoint within a SparkPost API.<br>
 * <br>
 * For instance, the SPResourceTransmissions class contains 3 methods:
 * <ul>
 * <li> create() matches the create endpoint in the transmission API
 * <li> retrieve() matches the retrieve endpoint in the transmission API
 * <li> list() matches the list endpoint in the transmission API
 * </ul>
 * See the 
 * <a href="https://www.sparkpost.com/api#/reference/transmissions"> Sparkpost Transmission API</a>.  <br>
 * <br>
 * <h1> Data Transfer Objects </h1><br>
 * A DTO class is a container of fields which intent is to be serialized into
 * a JSON string when sending a request to a SparkPost API.<br>
 * <br>
 * For instance, the Sending Domains resource ( class SPResourceSendingDomains )
 * has a create() method that matches the create endpoint in the SparkPost 
 * Sending Domains API.<br>
 * To create a domain, the JSON request to the SparkPost API is made of 2 fields:<br>
 * <ul>
 * <li> the domain name
 * <li> the DKIM information
 * </ul>
 * We could have designed this java API to merely accept these fields in the 
 * method signature , for instance here SPResourceSendingDomains.create would
 * have become SPResourceSendingDomains.create( String domain, String dkim_info).<br>
 * Only in most cases the request parameters are numerous, and passing them
 * directly as method parameter would become very cumbersome. <br>
 * Instead we use DTOs which represent *exactly* how the JSON request is expressed.<br>
 * As a result, in this example, the create method's signature is :<br>
 * SPResourceSendingDomains.create( SPRestConn conn, SPDTOSendingDomain domain)<br>
 * (conn is the server connection to use to make the request, see below)<br>
 * <br>
 * Note that because the DTO classes match exactly how the JSON is formulated,
 * they do not strictly respect java field naming conventions, and you will see
 * for example fields like :<br>
 * String reply_to ; <br>
 * Because the JSON field is "reply_to" <br>
 * <br>
 * <h1>REST mechanism classes</h1><br>
 * There are 4 classes necessary to put in place our REST system :
 * <ul>
 * <li> SPClient
 * <li> SPRestConn
 * <li> SPResponse
 * <li> SparkpostSdkException
 * </ul>
 * <br>
 * SPClient define information specific to the SparkPost client: the
 * authorization key and the from email.<br>
 * The SPRestConn does all of the work : it sends requests and receives responses
 * from the SparkPost server. All of the resources methods (see above) take
 * for first parameter an SPRestConn object.<br>
 * The SPResponse class describes a response received by the SparkPost server.<br>
 * And finally the SparkpostSdkException is the custom exception for this API.<br>
 * <br>
 * <br>
 * See The SPSampleApplication class for an example of creating a template,
 * storing it, and creating a transmission with it.<br>
 * It also implements a simple JSON-to-Java deserialization using Google's
 * Gson class.
 */
package com.sparkpost.sdk ;


