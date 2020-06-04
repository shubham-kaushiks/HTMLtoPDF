package com.example.demo.controller;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.HTMLtoPDF;

@RestController
public class ConversionController {
	
	@Autowired
	private HTMLtoPDF htmltoPDF;
	
	@PostMapping("/")
    public String run(@RequestBody String htmlString) throws IOException {       
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("acknowledgementNo", "121");
		variables.put("paymentReceivedDate", "121");
		variables.put("customerId", "121");
		variables.put("timeStamp", "121");
		variables.put("applicantLast", "121");
		variables.put("applicantFirst", "121");
		variables.put("loanCurrency", "121");
		variables.put("paymentReceivedAmount", "121");
		variables.put("pan", "121");
		variables.put("dueAmount", "121");
		variables.put("dueDate", "121");
		variables.put("paymentReceivedMode", "121");
		variables.put("currentAssignee", "121");	
//		File htmlTemplateFile = new File("/home/shubham.k/Desktop/index.html");
//		String htmlString = FileUtils.readFileToString(htmlTemplateFile);
		return htmltoPDF.getHtmlString(variables, htmlString);
    }
	
	@RequestMapping("/pdf")
	public File run1() throws IOException {
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("acknowledgementNo", "121");
		variables.put("paymentReceivedDate", "121");
		variables.put("customerId", "121");
		variables.put("timeStamp", "121");
		variables.put("applicantLast", "121");
		variables.put("applicantFirst", "121");
		variables.put("loanCurrency", "121");
		variables.put("paymentReceivedAmount", "121");
		variables.put("pan", "121");
		variables.put("dueAmount", "121");
		variables.put("dueDate", "121");
		variables.put("paymentReceivedMode", "121");
		variables.put("currentAssignee", "121");		
		File htmlTemplateFile = new File("/home/shubham.k/Desktop/index.html");
		String htmlString = FileUtils.readFileToString(htmlTemplateFile);
		String out = "/home/shubham.k/Desktop/abcd.pdf";
		return htmltoPDF.exportToPdfBox(variables, htmlString, out);
		
	}
	
}
