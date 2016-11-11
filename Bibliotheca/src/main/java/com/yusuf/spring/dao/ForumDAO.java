package com.yusuf.spring.dao;


import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.yusuf.spring.exception.AdException;
import com.yusuf.spring.pojo.Answers;
import com.yusuf.spring.pojo.Books;
import com.yusuf.spring.pojo.Document;
import com.yusuf.spring.pojo.Question;
import com.yusuf.spring.pojo.RequestBook;


public class ForumDAO extends DAO {
	
	public ForumDAO(){
		
		
	}

	
	
	public List<Question> getAllQuestions() {
		begin();
		Query query = getSession().createQuery("from Question");
		 List<Question> list = query.list();
		 
		 if(list.size() >= 1){
			  System.out.println("DAO Class" + list.size());
			  return  list;
			  
			  
		  }
		commit();
		close();
		return null;
	}
	
	public List<Answers> getAnswersToThisQuestion(String quesID) {
		Query query = getSession().createQuery("from Answers where quesID = :quesID");
		query.setInteger("quesID", Integer.parseInt(quesID));
		List<Answers> answerList = query.list();
		 if(answerList.size() >= 1){
			  System.out.println("ForumDAO Class" + answerList.size());
			  return  answerList;
			  
			  
		  }
		
		return null;
		
		
		
	}
	
	public Question getQuestionByID(String questionID){
		
		Query query = getSession().createQuery("from Question where questionID = :questionID");
		query.setInteger("questionID", Integer.parseInt(questionID));
		
		Question question = (Question) query.uniqueResult();
		if(question != null){
			return question;

		}
	
		return null;
		
	}
	
	public void addAnswer(Question question, String answerBy, String answer) throws AdException {
		 try {
		        begin();
		        
		        Answers answers = new Answers(question,answerBy,answer);
		        getSession().save(answers);
		        commit();
		    } catch (HibernateException e) {
		        rollback();
		        //throw new AdException("Could not create  book", e);
		        throw new AdException("Exception while creating request: " + e.getMessage());

		    }finally {
				close();
			}
		
	}
	  
	public void postQuestion(String ques) throws AdException {
		
		
	
	 try {
	        begin();
	        Question question = new Question(ques);
	        getSession().save(question);
	        commit();
	    } catch (HibernateException e) {
	        rollback();
	        //throw new AdException("Could not create  book", e);
	        throw new AdException("Exception while creating request: " + e.getMessage());

	    }finally {
			close();
		}
	}	

}
