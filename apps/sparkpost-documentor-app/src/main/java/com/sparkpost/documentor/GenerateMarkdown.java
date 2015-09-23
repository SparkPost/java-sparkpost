package com.sparkpost.documentor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.sparkpost.model.AddressAttributes;
import com.sparkpost.model.DKIM;
import com.sparkpost.model.DKIMResults;
import com.sparkpost.model.DNSAttributes;
import com.sparkpost.model.OptionsAttributes;
import com.sparkpost.model.RecipientAttributes;
import com.sparkpost.model.RecipientList;
import com.sparkpost.model.SendingDomain;
import com.sparkpost.model.StatusAttributes;
import com.sparkpost.model.StoredRecipientList;
import com.sparkpost.model.StoredTemplate;
import com.sparkpost.model.SuppressionList;
import com.sparkpost.model.SuppressionListEntry;
import com.sparkpost.model.TemplateAttributes;
import com.sparkpost.model.TemplateContentAttributes;
import com.sparkpost.model.TemplateItem;
import com.sparkpost.model.TemplateListResponse;
import com.sparkpost.model.TemplateSubstitutionData;
import com.sparkpost.model.TransmissionBase;
import com.sparkpost.model.TransmissionWithRecipientArray;
import com.sparkpost.model.TransmissionWithRecipientList;
import com.sparkpost.model.VerifyAttributes;
import com.sparkpost.model.VerifyResponse;
import com.sparkpost.model.Webhook;
import com.yepher.jsondoc.ClassListDriverBase;

public class GenerateMarkdown  extends ClassListDriverBase {
	 
	private String outputPath = "protocol.md";
	
	private String title = "SparkPost Protocol Library";
	
	private Set<Class<?>> pdusToExclude = new HashSet<Class<?>>();
	
	private List<Class<?>>      pdusToDocument = new ArrayList<Class<?>>(Arrays.asList(
            AddressAttributes.class,
            DKIM.class,
            DKIMResults.class,
            DNSAttributes.class,
            OptionsAttributes.class,
            RecipientAttributes.class,
            RecipientList.class,
            SendingDomain.class,
            StatusAttributes.class,
            StoredRecipientList.class,
            StoredTemplate.class,
            SuppressionList.class,
            SuppressionListEntry.class,
            TemplateAttributes.class,
            TemplateContentAttributes.class,
            TemplateItem.class,
            TemplateListResponse.class,
            TemplateSubstitutionData.class,
            TransmissionBase.class,
            TransmissionWithRecipientArray.class,
            TransmissionWithRecipientList.class,
            VerifyAttributes.class,
            VerifyResponse.class,
            Webhook.class

        ));
	
	public static void main(String[] args) throws Exception {
		
		GenerateMarkdown generateMarkdown = new GenerateMarkdown();
		generateMarkdown.run(args);
		
		System.out.println("Done...");
		
	}
	
	@Override
	public void addPduToDocument(Class<?> clazz) {
		System.out.println("addPduToDocument: " + clazz);
	}
	

	@Override
	protected Set<Class<?>> getPdusToExclude() {
		System.out.println("getPdusToExclude: ");
		return pdusToExclude;
	}

	@Override
	protected List<Class<?>> getPdusToDocument() {
		System.out.println("getPdusToDocument: ");
		
//		Set<Class<?>> annotatedClasses;
//		Reflections reflections = new Reflections("com.sparkpost.model");
//		annotatedClasses = reflections.getTypesAnnotatedWith(RequestPDU.class, false);
//		annotatedClasses.addAll(reflections.getTypesAnnotatedWith(ResponsePDU.class, false));
//		annotatedClasses.addAll(reflections.getTypesAnnotatedWith(Description.class, false));

		return pdusToDocument;
	}

	@Override
	protected String getTitle() {
		return title;
	}

	@Override
	protected void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
	}

	@Override
	protected String getOutputPath() {
		return outputPath;
	}
	
	public String getHeader() {
		return "<a href=\"http://sparkpost.com\"><img src=\"https://www.sparkpost.com/sites/default/files/attachments/SparkPost_Logo_2-Color_Gray-Orange_RGB.svg\" width=\"200px\"/></a>";
	}
	
	
}
