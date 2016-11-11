package com.yusuf.spring.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
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

import com.yusuf.spring.pojo.LoginModel;
import com.yusuf.spring.dao.LoginDAO;

@Controller
public class AddStudents {
	

	@Autowired
	private LoginDAO loginDAO;
	

	@Autowired
	@Qualifier("registrationValidator")
	RegistrationValidator registrationValidator;
	
	@InitBinder
	private void initBinder(WebDataBinder binder)
	{
		binder.setValidator(registrationValidator);
	}
	
	
	private ModelAndView model;

	
	@RequestMapping(value="/register.htm" , method = RequestMethod.GET)
	public String initializeForm(@ModelAttribute("loginBean")LoginModel loginBean) {

		return "registration";
	}

	@RequestMapping(value = "/register.htm", method = RequestMethod.POST)
	public ModelAndView addStudentsAfter(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("loginBean") LoginModel loginBean,BindingResult result)

	{

		model = new ModelAndView();
		registrationValidator.validate(loginBean, result);
		if(result.hasErrors())
		{
			model.setViewName("registration");
			return model;
		}
    	
		String nuID = request.getParameter("nuID");
		String name = request.getParameter("userName");
		String password = request.getParameter("password");
		
		System.out.println("NUUUUUUUUUUUUUUUIIIIIIIIIIIDDDDDDDDDDdddddd" + nuID);
		System.out.println("uuuuuuuuusserrrrrrrrrrrrrrnnnnnnnnammmmmmmmme" + name);

		System.out.println("paaaassssssssssswwwwwwwwwoooooooooooorrrddddd" + password);

		try

		{
			boolean nuIDPresent = loginDAO.checkForNUID(nuID);
			if(nuIDPresent){
				loginDAO.registerStudent(name, password, nuID);
				model.setViewName("registeredStudent");
				
			}else {
				model.setViewName("registration");

			}
			
			}

		catch (Exception e)

		{

			e.printStackTrace();

		}
		
		
		return model;

	}
	
	
	
	@RequestMapping(value = "/searchForUsername.htm", method = RequestMethod.POST)
	public void searchUsingKeyword(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException {
     
		response.setContentType("application/json");

		PrintWriter out = response.getWriter();

		String user = request.getParameter("username");
		LoginModel loginModel = loginDAO.getUserIfExists(user);
		System.out.println("User exists man!");
		if(loginModel != null){
			out.println("Username already exists!");
		}
		
		out.flush();

		
	}


	@RequestMapping(value = "/searchForNuid.htm", method = RequestMethod.POST)
	public void searchUsingNuid(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException {
     
		response.setContentType("application/json");

		PrintWriter out = response.getWriter();

		String nuid = request.getParameter("nuid");
		boolean validNuid = loginDAO.checkForNUID(nuid);
		LoginModel loginModel = loginDAO.checkIfAlreadyRegistered(nuid);
		System.out.println("User exists man!");
		if(!validNuid){
			out.println("NUID is invalid!");
		}else if(validNuid && (loginModel.getUserName() !=null)){
			out.println("This NUID has already been registered!");
		}
		
		out.flush();

		
	}


	}