package com.yusuf.spring.controller;

import java.util.List;

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

import com.yusuf.spring.pojo.Answers;
import com.yusuf.spring.pojo.LoginModel;
import com.yusuf.spring.pojo.Question;
import com.yusuf.spring.dao.ForumDAO;
import com.yusuf.spring.exception.AdException;

@Controller
public class ForumController {
	

	@Autowired
	private ForumDAO forumDAO;
	
	@Autowired
	@Qualifier("questionValidator")
	QuestionValidator questionValidator;
	
	@InitBinder("question")
	private void initBinder(WebDataBinder binder)
	{
		binder.setValidator(questionValidator);
	}
	
	
	private ModelAndView model;



	
	@RequestMapping(value="/forum.htm" , method = RequestMethod.GET)
	public ModelAndView initializeForm(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("loginModel")LoginModel loginModel) {
		
		 model = new ModelAndView();
			List<Question> list = null;
			
	      list = forumDAO.getAllQuestions();

	      model.addObject("questions", list);
	      model.setViewName("forumForStudents");
		return model;
	}

	@RequestMapping(value = "/answersToQuestion.htm", method = RequestMethod.GET)
	public ModelAndView answerList(HttpServletRequest request, HttpServletResponse response)

	{

		model = new ModelAndView();
		
		 String qid = request.getParameter("quesid");

		System.out.println("qqqqqqqqqqqqqqqqiiiiiiiiiiiiiiiddddddddddddddd   " + qid) ;
		Question question = forumDAO.getQuestionByID(qid);
		List<Answers> answerList = forumDAO.getAnswersToThisQuestion(qid);
		model.addObject("question", question);
		model.addObject("answerList", answerList);
		model.setViewName("answersToQuestion");
				
		
		return model;

	}
	
	@RequestMapping(value = "/yourAnswer.htm", method = RequestMethod.GET)
	public ModelAndView yourAnswer(HttpServletRequest request, HttpServletResponse response)

	{

		model = new ModelAndView();
		
		String qid = request.getParameter("questionid");

		System.out.println("yourAnswer qqqqqqqquuuuuuuuuiiiiiiiiiiissssssss   " + qid) ;
		Question question = forumDAO.getQuestionByID(qid);
		model.addObject("question", question);
		
		model.setViewName("yourAnswer");
				
		
		return model;

	}
	

	@RequestMapping(value = "/yourAnswer.htm", method = RequestMethod.POST)
	public ModelAndView addYourAnswer(HttpServletRequest request, HttpServletResponse response, HttpSession session)

	{

		model = new ModelAndView();
		
		 String myAnswer = request.getParameter("myAnswer");
		 String qid = request.getParameter("qid");
		String answerBy = (String) session.getAttribute("NUID");
		 Question question = forumDAO.getQuestionByID(qid);
		 try {
			forumDAO.addAnswer(question,answerBy ,myAnswer);
		} catch (AdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
		 
		 model.setViewName("answerAdded");
				
		
		return model;

	}
	
	
	@RequestMapping(value = "/postQuestion.htm", method = RequestMethod.GET)
	public String postQuestionInitial(@ModelAttribute("question") Question question) {
		
		return "postQuestion";
	}

	
	@RequestMapping(value = "/postQuestion.htm", method = RequestMethod.POST)
	public ModelAndView postQuestion(HttpServletRequest request,@ModelAttribute("question") Question question,
			 BindingResult result) {
		 
		model = new ModelAndView();
		 questionValidator.validate(question, result);
			if(result.hasErrors())
			{
				model.setViewName("postQuestion");
				return model;
			}
	    	
			


		String questionToBePosted = request.getParameter("question");
				try {
			forumDAO.postQuestion(questionToBePosted);
		} catch (AdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				List<Question> list = null;
				
			      list = forumDAO.getAllQuestions();

			      model.addObject("questions", list);
			      model.setViewName("forumForStudents");

		
		return model;
	}

	}