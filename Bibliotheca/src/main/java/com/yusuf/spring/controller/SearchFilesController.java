package com.yusuf.spring.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.hibernate.annotations.SourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.yusuf.spring.dao.DocumentsDAO;
import com.yusuf.spring.pojo.Books;
import com.yusuf.spring.pojo.Document;

@Controller


public class SearchFilesController {
	
	@Autowired
	@Qualifier("documentsDao")
	private DocumentsDAO documentsDAO;
	
	ModelAndView mv;
	 @RequestMapping(value = "/searchDocuments.htm", method = RequestMethod.GET)
	    public ModelAndView displayAllFiles(HttpServletRequest request, HttpServletResponse response,
				@ModelAttribute("document") Document document)
 {
		 
		 mv = new ModelAndView();
			List<Document> list = null;
			
	      list = documentsDAO.getAllFiles();
		 mv.addObject("fileList", list);
		  String roleOfTheCurrentUser=(String)request.getSession().getAttribute("userInSession");
		     System.out.println("Roooooleeeeeeeeeeeeeeeeeeeeeee" + roleOfTheCurrentUser);
			if(roleOfTheCurrentUser != null ){
				if(roleOfTheCurrentUser.equals("admin")){
					System.out.println("Display files admin");
					mv.setViewName("displayFiles");
				}else if(roleOfTheCurrentUser.equals("student")){
					System.out.println("Display files student has to come");
					mv.setViewName("studentDisplayFiles");

				}else if(roleOfTheCurrentUser.equals("staff")){
					System.out.println("Display files staff has to come");
					mv.setViewName("studentDisplayFiles");

				}


			
			}	else{
				
				System.out.println("Fuuuuuuuuuuuuuuuuuuccccccccccccccckkkkkkkkkkkk");
			}
 
		 return mv;		 
 }
	 
	 
	 @RequestMapping(value = "/documentLink.htm", method = RequestMethod.GET)
	    public String download(HttpServletRequest request, HttpServletResponse response,
	    		@ModelAttribute("document") Document document) {
	        System.out.println("inside");
		 String docId = request.getParameter("docid");
	      
		 Document doc = documentsDAO.getDocument(docId);
	        System.out.println("SearchFiles Controller ---" + docId);
	        try {
	            response.setContentType("application/pdf");

	            response.setHeader("Content-Disposition", "inline;filename=\"" +doc.getFileName()+ "\"");
	            byte[] fileDataInBytes= doc.getFileData();
	            response.getOutputStream().write(fileDataInBytes, 0, fileDataInBytes.length);
//	            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//	            ObjectOutputStream oos = new ObjectOutputStream(baos);
//
//
//	            oos.writeObject(doc);
//
//	            oos.flush();
//	            oos.close();
//
//	            InputStream is = new ByteArrayInputStream(baos.toByteArray()); 
//	            OutputStream out = response.getOutputStream();
//	            IOUtils.copy( is , out);
//	            out.flush();
//	            out.close();  
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	         
	        return "successLogin";
	    }
	 
	 
	 

}
