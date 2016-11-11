package com.yusuf.spring.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import com.yusuf.spring.dao.DocumentsDAO;
import com.yusuf.spring.pojo.Document;

@Controller


public class DocumentController {
	
	@Autowired
	private DocumentsDAO documentsDAO;
		
	
	 @RequestMapping(value = "/documentUpload.htm", method = RequestMethod.GET)
	    public String showUploadForm() {
	        return "fileUpload";
	    }
	 
	 
	 @RequestMapping(value = "/documentUpload.htm", method = RequestMethod.POST)
	    public String handleFileUpload(HttpServletRequest request,
	            @RequestParam CommonsMultipartFile[] fileData) throws Exception {
	          
		 	String name = request.getParameter("fileName");
		 	String description = request.getParameter("description");
	        if (fileData != null && fileData.length > 0) {
	            for (CommonsMultipartFile aFile : fileData){
	                  
	            	System.out.println("Saving file: " + aFile.getOriginalFilename());
//	                 
//	                Document uploadFile = new Document();
//	                uploadFile.setFileName("a");
//	                uploadFile.setFileData(aFile.getBytes());
	                documentsDAO.saveFile(name, aFile, description);               
	            }
	        }
	  
	        return "successLogin";
	    }  
	

}
