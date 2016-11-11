package com.yusuf.spring.controller;

import java.util.List;

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
import com.yusuf.spring.exception.AdException;
import com.yusuf.spring.pojo.Books;
import com.yusuf.spring.pojo.LoginModel;
import com.yusuf.spring.pojo.User;

@Controller


public class UpdateBookDetailsController {
	
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
	
	
	
	private ModelAndView mv;

	@RequestMapping(value="/update.htm" , method = RequestMethod.GET)
	public ModelAndView updateBooksInitial(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("books") Books books){
		mv = new ModelAndView();
		String bid = request.getParameter("bid");
		
		Books list = booksDAO.searchById(bid);
		if(list != null){
			 mv.addObject("searchResultList", list);
		}else {
			mv.addObject("noSearchResult", "No books found which match the keyword!");
		}
		
		mv.addObject("oneBook", list);
		mv.setViewName("updateBooksDetails");
		return mv;
	}

	@RequestMapping(value = "/update.htm", method = RequestMethod.POST)
	public ModelAndView updateBooksAfter(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("books") Books books, BindingResult result) throws AdException

	{
		mv = new ModelAndView();

		bookValidator.validate(books, result);
		if(result.hasErrors())
		{
			mv.setViewName("updateBooksDetails");
			return mv;

		}

		List<Books> list = null;
		

		String bid = request.getParameter("bid");
		System.out.println("UpdateBookDetails Page if ----------" + bid);
					
		booksDAO.updateBookDetails(bid,books.getBookName(), books.getEdition(),books.getAuthor(), books.getDescription());
					
				
		list =	booksDAO.getAllBooks();
		
		mv.addObject("searchResultList", list);
		mv.setViewName("searchResult");
		return mv;
	}

	
	@RequestMapping(value="/delete.htm" , method = RequestMethod.GET)
	public ModelAndView deleteBooksInitial(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("books") Books books){
		mv = new ModelAndView();
		String bid = request.getParameter("bidDelete");
		
		Books list = booksDAO.searchById(bid);
		if(list != null){
			 mv.addObject("searchResultList", list);
		}else {
			mv.addObject("noSearchResult", "No books found which match the keyword!");
		}
		
		mv.addObject("oneBook", list);
		mv.setViewName("deleteBook");
		return mv;
	}
	@RequestMapping(value = "/delete.htm", method = RequestMethod.POST)
	public ModelAndView deleteBooksAfter(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("books") Books books) throws AdException

	{
		List<Books> list = null;

		mv = new ModelAndView();
		String bid = request.getParameter("bid");
		System.out.println("UpdateBookDetails Page if ----------" + bid);
					
		booksDAO.deleteBook(bid);
		
		
	list =	booksDAO.getAllBooks();
	
	mv.addObject("searchResultList", list);
		
				
		//list =	booksDAO.searchByBookName(keyword);
		
		mv.setViewName("searchResult");
		return mv;
	}

}
