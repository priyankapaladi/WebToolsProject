package com.yusuf.spring.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.annotations.SourceType;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.yusuf.spring.dao.BooksDAO;

import com.yusuf.spring.pojo.Books;
import com.yusuf.spring.pojo.LoginModel;
import com.yusuf.spring.pojo.User;

@Controller


public class SearchBooksController {
	
	@Autowired
	@Qualifier("booksDao")
	private BooksDAO booksDAO;
	
	
	private ModelAndView mv;
	

	@RequestMapping(value="/searchBooks.htm" , method = RequestMethod.GET)
	public String initializeForm(@ModelAttribute("books")Books books) {

		return "searchBooks";
	}

	@RequestMapping(value = "/searchBooks.htm", method = RequestMethod.POST)
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


		}else if (selectedValue != null && selectedValue.equalsIgnoreCase("nameOfAuthor")) {
			
			list = booksDAO.searchByAuthor(keyword);
			
		}else {
			
			System.out.println("Fuuuuuuuuuuuuccccccccccccckkkkkkkkkkkkkkkkkkkkkkk");
		}
		
		
		if(list != null){
			 mv.addObject("searchResultList", list);
		}else {
			
			System.out.println("SearchBooksController.executeLogin()!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			mv.addObject("noSearchResult", "No books found which match the keyword!");
		}
		
	     String roleOfTheCurrentUser=(String)request.getSession().getAttribute("userInSession");
	     System.out.println("Roooooleeeeeeeeeeeeeeeeeeeeeee" + roleOfTheCurrentUser);
		if(roleOfTheCurrentUser != null ){
			if(roleOfTheCurrentUser.equals("admin")){
				mv.setViewName("searchResult");
			}else if(roleOfTheCurrentUser.equals("student")){
				mv.setViewName("searchResultStudent");

			}else if(roleOfTheCurrentUser.equals("staff")){
				mv.setViewName("searchResultStaff");

			}

		
		}	else{
			
			System.out.println("Fuuuuuuuuuuuuuuuuuuccccccccccccccckkkkkkkkkkkk");
		}
		return mv;
	}
	
	
	@RequestMapping(value = "/searchForKeyWord.htm", method = RequestMethod.POST)
	public void searchUsingKeyword(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException {
     
		response.setContentType("application/json");

		PrintWriter out = response.getWriter();

		String key = request.getParameter("key");
		
		List<Books> bookList = new ArrayList<Books>(); 
				
		bookList = booksDAO.searchByBookName(key);
		System.out.println("list size"+bookList.size());
//    	JSONObject jsonObject = new JSONObject();
//      	jsonObject.put("bookList",bookList);
		       
		String jsonString = "{\"bookList\":[";
		for(Books book : bookList){
			jsonString  += "\""+book.getBookName()+"\",";
		}
		jsonString = jsonString.substring(0,jsonString.length()-1)+"]}";
		System.out.println(jsonString);
		out.println(jsonString);
//		out.println(jsonObject);
		out.flush();

		
		//return null;
	}

}
