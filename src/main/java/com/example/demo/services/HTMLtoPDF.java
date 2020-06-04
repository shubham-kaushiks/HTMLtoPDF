package com.example.demo.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.StringTemplateResolver;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

@Service
public class HTMLtoPDF {
	
	private static final Logger logger = LoggerFactory.getLogger(HTMLtoPDF.class);

//	private TemplateEngine getTemplateEngine() {
//        if(null == templateEngine){
//            templateEngine = new TemplateEngine();
//            StringTemplateResolver templateResolver = new StringTemplateResolver();
//            templateResolver.setTemplateMode(TemplateMode.HTML);
//            templateEngine.setTemplateResolver(templateResolver);
//        }
//        return templateEngine;
//    }
		
	public File exportToPdfBox(Map<String, Object> variables, String html, String out) {
	    try (OutputStream os = new FileOutputStream(out);) {
	        PdfRendererBuilder builder = new PdfRendererBuilder();
	        builder.withHtmlContent(getHtmlString(variables, html), "file:");
	        builder.toStream(os);
	        builder.run();
	    } 
	    catch (Exception e) {
	        logger.error("Exception while generating pdf : {}", e);
	    }
	    return new File(out);
	}
	
	public String getHtmlString(Map<String, Object> variables, String html) {
	    try {
	        final Context ctx = new Context();
	        ctx.setVariables(variables);
	        TemplateEngine templateEngine = new TemplateEngine();
            StringTemplateResolver templateResolver = new StringTemplateResolver();
            templateResolver.setTemplateMode(TemplateMode.HTML);
            templateEngine.setTemplateResolver(templateResolver);
	        return templateEngine.process(html, ctx);
	    } 
	    catch (Exception e) {
	        logger.error("Exception while getting html string from template engine : {}", e);
	        return null;
	    }
	}
	
}
