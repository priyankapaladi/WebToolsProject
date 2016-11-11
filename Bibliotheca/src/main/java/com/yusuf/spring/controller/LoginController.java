package com.yusuf.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

import com.yusuf.spring.dao.LoginDAO;
import com.yusuf.spring.pojo.LoginModel;

@Controller

public class LoginController {

	
	@Autowired
	private LoginDAO loginDAO;
	
	
	@Autowired
	@Qualifier("loginModelValidator")
	LoginModelValidator loginModelValidator;
	
	@InitBinder("loginBean")
	private void initBinder(WebDataBinder binder)
	{
		binder.setValidator(loginModelValidator);
	}
	
	
	private ModelAndView model;


	@RequestMapping(value="/news.htm" , method = RequestMethod.GET)
	public String news() {

		return "twitterNews";
	}

	
	@RequestMapping(value="/login.htm" , method = RequestMethod.GET)
	public String initializeForm(@ModelAttribute("loginBean")LoginModel loginBean,BindingResult result) {
		
		System.out.println("In get");
		return "loginForm";
	}

	@RequestMapping(value = "/login.htm", method = RequestMethod.POST)
	public ModelAndView executeLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@ModelAttribute("loginBean") LoginModel loginBean, BindingResult result)

	{


		model = new ModelAndView();
		loginModelValidator.validate(loginBean, result);
		if(result.hasErrors())
		{
			model.setViewName("loginForm");
			return model;
		}
    	
		

		try

		{
			String name = request.getParameter("userName");
			String password = request.getParameter("password");
			LoginModel validUser = loginDAO.getUser(name, password);
			
			if(validUser == null){
				model.addObject("invalid", "Invalid credentials");
				model.setViewName("loginForm");
				
			}
			String check = validUser.getRoles();
			String memberID = validUser.getNuID();
			if(check.equals("admin")){
				
				//model.addObject("name", name);
				session.setAttribute("userInSession", check);
				session.setAttribute("NUID", memberID);
				session.setAttribute("name", name);
				model.setViewName("successLogin");
				
				
			}else if(check.equals("student")){
				
				//model.addObject("name", name);
				session.setAttribute("userInSession", check);
				session.setAttribute("NUID", memberID);
				session.setAttribute("name", name);


				model.setViewName("successStudentLogin");
				
			}else if(check.equals("staff")){
				
				model.addObject("name", name);
				session.setAttribute("userInSession", check);
				session.setAttribute("NUID", memberID);
				session.setAttribute("name", name);


				model.setViewName("successStaffLogin");
				
			}
			String sessionNUID = (String) session.getAttribute("NUID");
			if(sessionNUID == null){
				request.getRequestDispatcher("/WEB-INF/views/loginForm.jsp").forward(request, response);
			}
					}

		catch (Exception e)

		{

			e.printStackTrace();

		}

		return model;

	}
	
	@RequestMapping(value = "/logOut.htm", method = RequestMethod.GET)
	public ModelAndView logOutAfter(HttpServletRequest request, HttpServletResponse response, HttpSession session
			) {
		
		model = new ModelAndView();
		
		response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
		response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
		response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
		response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility
		

		request.getSession().invalidate();
		
		
		model.setViewName("successLogOut");
		return model;
	}


	@RequestMapping(value="/home.htm" , method = RequestMethod.GET)
	public ModelAndView home(@ModelAttribute("loginModel")LoginModel loginModel,HttpServletRequest request) {
		
		
		model = new ModelAndView();
		 String roleOfTheCurrentUser=(String)request.getSession().getAttribute("userInSession");
	     System.out.println("Roooooleeeeeeeeeeeeeeeeeeeeeee" + roleOfTheCurrentUser);
		
	     if(roleOfTheCurrentUser != null ){
			if(roleOfTheCurrentUser.equals("admin")){
				model.setViewName("successLogin");
			}else if(roleOfTheCurrentUser.equals("student")){
				model.setViewName("successStudentLogin");

			}else if(roleOfTheCurrentUser.equals("staff")){
				model.setViewName("successStaffLogin");
			}

		
		}	else{
			
			System.out.println("Fuuuuuuuuuuuuuuuuuuccccccccccccccckkkkkkkkkkkk");
		}

		return model;
	}
	
	
	

}
