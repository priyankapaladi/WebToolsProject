package com.yusuf.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.yusuf.spring.pojo.Books;
import com.yusuf.spring.pojo.LoginModel;
import com.yusuf.spring.dao.BooksDAO;
import com.yusuf.spring.dao.LoginDAO;

@Controller
public class ViewDescription {
	

	@Autowired
	private BooksDAO booksDAO;
	
	
	private ModelAndView model;

	
	@RequestMapping(value="/viewDescription.htm" , method = RequestMethod.GET)
	public ModelAndView initializeForm(@ModelAttribute("books")Books books, HttpServletRequest request) {

		model = new ModelAndView();
		String bid = request.getParameter("bid");
		Books bookById = booksDAO.searchById(bid);

		model.addObject("oneBook", bookById);
		return model;
	}

	@RequestMapping(value = "/viewDescription.htm", method = RequestMethod.POST)
	public ModelAndView executeLogin(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("books") Books books)

	{

		model = new ModelAndView();

		try

		{	
			
			
			
		}

		catch (Exception e)

		{

			e.printStackTrace();

		}
		
		model.setViewName("registeredStudent");
		return model;

	}

	}