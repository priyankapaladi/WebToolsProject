package com.yusuf.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.yusuf.spring.dao.DocumentsDAO;
import com.yusuf.spring.exception.AdException;
import com.yusuf.spring.pojo.Document;

@Controller


public class UpdateDocumentDetailsController {
	
	@Autowired
	private DocumentsDAO documentsDAO;
	
	
	private ModelAndView mv;

	@RequestMapping(value="/updateFile.htm" , method = RequestMethod.GET)
	public ModelAndView updateFileInitial(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("document") Document document){
		mv = new ModelAndView();
		String docId = request.getParameter("docid");
		System.out.println("UpdateDocumentController-----------" + docId);
		Document docList = documentsDAO.getDocument(docId);
		if(docList != null){
			 mv.addObject("searchResultDocList", docList);
		}else {
			mv.addObject("noSearchResult", "No documents found which match the keyword!");
		}
		
		mv.addObject("oneDocument", docList);
		mv.setViewName("updateDocument");
		return mv;
	}

	@RequestMapping(value = "/updateFile.htm", method = RequestMethod.POST)
	public ModelAndView updateFileAfter(HttpServletRequest request, HttpServletResponse response,@RequestParam CommonsMultipartFile[] fileData
			) throws AdException

	{
		List<Document> list = null;

		
		mv = new ModelAndView();
		   String docid = request.getParameter("docid");
	 	String name = request.getParameter("fileName");
	 	String description = request.getParameter("description");
        if (fileData != null && fileData.length > 0) {
            for (CommonsMultipartFile aFile : fileData){
                  
            	System.out.println("Saving file: " + aFile.getOriginalFilename());
                documentsDAO.updateFile(docid, name, aFile, description);               
            }
        }
  		
        list = documentsDAO.getAllFiles();
        mv.addObject("fileList", list);
		mv.setViewName("displayFiles");
		return mv;
	}

	
	@RequestMapping(value="/deleteFile.htm" , method = RequestMethod.GET)
	public ModelAndView deleteFileInitial(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("Document") Document document){
		mv = new ModelAndView();
		String docID = request.getParameter("docidDelete");
		
		Document docList = documentsDAO.getDocument(docID);
		if(docList != null){
			 mv.addObject("searchResultDocList", docList);
		}else {
			mv.addObject("noSearchResult", "No files found which match the keyword!");
		}
		
		mv.addObject("oneDocument", docList);
		mv.setViewName("deleteFile");
		return mv;
	}
	@RequestMapping(value = "/deleteFile.htm", method = RequestMethod.POST)
	public ModelAndView deleteFileAfter(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("document") Document document) throws AdException

	{
		List<Document> list = null;

		mv = new ModelAndView();
		String docid = request.getParameter("docid");
		System.out.println("Delete file deleteFileAfter ----------" + docid);
					
		documentsDAO.deleteFileFromSystem(docid);
					
				
		list =	documentsDAO.getAllFiles();
		
		mv.addObject("fileList", list);
		mv.setViewName("displayFiles");
		return mv;
	}

}
