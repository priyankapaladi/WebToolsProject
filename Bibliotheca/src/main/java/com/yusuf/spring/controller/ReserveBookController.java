package com.yusuf.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.yusuf.spring.dao.BooksDAO;
import com.yusuf.spring.exception.AdException;
import com.yusuf.spring.pojo.Books;
import com.yusuf.spring.pojo.RequestBook;

@Controller


public class ReserveBookController {
	
	@Autowired
	private BooksDAO booksDAO;
	
	 @RequestMapping(value = "/reserveBook.htm", method = RequestMethod.GET)
	    public String reserveBookOnRequest(HttpServletRequest request, HttpSession session) {
		 
		 String reserveBookId = request.getParameter("reserveid");
		 System.out.println("ReserveBook-----" + reserveBookId);
		 String nuID = (String) session.getAttribute("NUID");
		
		 try {
			Books bookRequested = booksDAO.searchById(reserveBookId);
			RequestBook requestBook = booksDAO.getRequest(bookRequested, nuID);
			if(requestBook != null){
				
				return "bookAlreadyReservedByYou";
				
			}else{
				booksDAO.reserveBook(bookRequested, nuID);

			}
		} catch (AdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	        return "bookReserved";
	    }
	 
	 
	 @RequestMapping(value = "/reserveBook.htm", method = RequestMethod.POST)
	    public String handleFileUpload(HttpServletRequest request     ) throws Exception {
	          
		    
	  
	        return "successLogin";
	    }  
	

}
