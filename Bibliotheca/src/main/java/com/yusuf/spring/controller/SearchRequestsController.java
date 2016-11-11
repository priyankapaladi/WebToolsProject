package com.yusuf.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.yusuf.spring.dao.BooksDAO;
import com.yusuf.spring.exception.AdException;
import com.yusuf.spring.pojo.Books;
import com.yusuf.spring.pojo.LoginModel;
import com.yusuf.spring.pojo.RequestBook;
import com.yusuf.spring.pojo.User;

@Controller


public class SearchRequestsController {
	
	@Autowired
	@Qualifier("booksDao")
	private BooksDAO booksDAO;
	
	
	private ModelAndView mv;
	

	@RequestMapping(value="/searchRequests.htm" , method = RequestMethod.GET)
	public ModelAndView initializeForm(@ModelAttribute("books")Books books) {
		
		mv = new ModelAndView();
		
		List<RequestBook> bookRequest = booksDAO.getAllrequest();
		mv.addObject("bookRequest", bookRequest);
		mv.setViewName("allRequests");
		
		return mv;
	}
	
	

	@RequestMapping(value="/deleteRequest.htm" , method = RequestMethod.GET)
	public ModelAndView deleteRequestInitial(HttpServletRequest request) {
		
		mv = new ModelAndView();
		
		String rid = request.getParameter("requestid");
		
		 try {
			booksDAO.deleteRequest(rid);
			List<RequestBook> bookRequest = booksDAO.getAllrequest();
			mv.addObject("bookRequest", bookRequest);
			
			
		} catch (AdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		mv.setViewName("allRequests");
		
		return mv;
	}


	@RequestMapping(value = "/searchRequests.htm", method = RequestMethod.POST)
	public ModelAndView executeLogin(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("books") Books books)

	{
		
		mv = new ModelAndView();
		List<Books> list = null;
		String selectedValue = request.getParameter("searchOption");
		String keyword = request.getParameter("keyword").toLowerCase();
		 
		System.out.println("SearchBooksController Keyword" + keyword);
		System.out.println("Selected value" + selectedValue);
		 if (selectedValue != null && selectedValue.equalsIgnoreCase("nameOfTheBook")) {
			list =	booksDAO.searchByBookName(keyword);
			System.out.println("SearchBooksController List size " + list.size());

		}else if (selectedValue != null && selectedValue.equalsIgnoreCase("nameOfAuthor")) {
			
			list = booksDAO.searchByAuthor(keyword);
			System.out.println("SearchBooksController list size" + list.size());
			
		}else {
			
			System.out.println("Fuuuuuuuuuuuuccccccccccccckkkkkkkkkkkkkkkkkkkkkkk");
		}
		
		
		if(list != null){
			 mv.addObject("searchResultList", list);
		}else {
			mv.addObject("noSearchResult", "No books found which match the keyword!");
		}
		
	     String roleOfTheCurrentUser=(String)request.getSession().getAttribute("userInSession");
	     System.out.println("Roooooleeeeeeeeeeeeeeeeeeeeeee" + roleOfTheCurrentUser);
		if(roleOfTheCurrentUser != null ){
			if(roleOfTheCurrentUser.equals("admin")){
				mv.setViewName("searchResult");
			}else if(roleOfTheCurrentUser.equals("student")){
				mv.setViewName("searchResultStudent");

			}

		
		}	else{
			
			System.out.println("Fuuuuuuuuuuuuuuuuuuccccccccccccccckkkkkkkkkkkk");
		}
		return mv;
	}

}
