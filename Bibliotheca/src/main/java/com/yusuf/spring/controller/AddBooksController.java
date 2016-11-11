package com.yusuf.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.yusuf.spring.dao.BooksDAO;

import com.yusuf.spring.pojo.Books;
import com.yusuf.spring.pojo.LoginModel;
import com.yusuf.spring.pojo.User;

@Controller


public class AddBooksController {
	
	@Autowired
	@Qualifier("booksDao")
	private BooksDAO booksDAO;
	
	@Autowired
	@Qualifier("bookValidator")
	BookValidator bookValidator;
	
	@InitBinder("loginBean")
	private void initBinder(WebDataBinder binder)
	{
		binder.setValidator(bookValidator);
	}
	
	

	@RequestMapping(value="/addBooks.htm" , method = RequestMethod.GET)
	public String initializeForm(@ModelAttribute("books")Books books) {

		return "addBooks";
	}

	@RequestMapping(value = "/addBooks.htm", method = RequestMethod.POST)
	protected String doSubmitAction(@ModelAttribute("books")Books books, BindingResult result) throws Exception {
				
		
		bookValidator.validate(books, result);
		if(result.hasErrors())
		{
			return "addBooks";

		}
    	
		booksDAO.create(books.getBookName(), books.getAuthor(),books.getEdition(), books.getDescription());
		return "booksAdded";
	}

}
