package com.yusuf.spring.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import com.yusuf.spring.exception.AdException;
import com.yusuf.spring.pojo.Books;
import com.yusuf.spring.pojo.LoginModel;
import com.yusuf.spring.pojo.User;

public class LoginDAO extends DAO {
	
	public LoginDAO(){
		
		
	}

	
	public String findUser(String name, String pass) throws AdException{
		
		 try {
			 
			 String role = "unspecified";
			 System.out.println("Inside findUser");
			 begin();
		        Query query = getSession().createQuery("from LoginModel where username= :userName and password= :password");
	            query.setString("userName", name);
	            query.setString("password", pass);
	            commit();
	           System.out.println("After query");
	           List<LoginModel> l = query.list();

	           System.out.println("list size" + l.size());
	    		if (l.size() == 1) {
	 	           System.out.println("list size" + l.size());
	 	          
	 	           for(LoginModel lm : l){
	 	        	   lm.getUserName().equals(name);
	 	        	   lm.getPassword().equals(pass);
	 	        	   role = lm.getRoles();
	 	           }
	    			
	    		} 
	    		return role;
	        } catch (HibernateException e) {
	            rollback();
	            throw new AdException("Could not get user " + name, e);
	        }
		
	}
	
	public void registerStudent(String userName, String password, String nuid) throws AdException{
		
		try{
		begin();
		Query query = getSession().createQuery("from LoginModel where nuID= :nuID");
		query.setString("nuID", nuid);
		LoginModel loginModel = (LoginModel) query.uniqueResult();
		  loginModel.setUserName(userName);
		   loginModel.setPassword(password);
		   loginModel.setRoles("student");
		   
			 getSession().update(loginModel); 

        
		commit();
	 } catch (HibernateException e) {
         rollback();
         //throw new AdException("Could not create  book", e);
         throw new AdException("Exception while creating LoginModel: " + e.getMessage());
     }finally {
			close();
		}
		
		
	}
	
	public boolean checkForNUID(String nuID){
		
		Query query = getSession().createQuery("from LoginModel where nuID= :nuID");
		query.setString("nuID", nuID);
		LoginModel loginModel = (LoginModel) query.uniqueResult();
		
		if(loginModel != null){
			return true;
		}
		
		return false;
		
	}
	
	public LoginModel getUser(String name, String pass) throws AdException{

		 try {
			 
		        Query query = getSession().createQuery("from LoginModel where username= :userName and password= :password");
	            query.setString("userName", name);
	            query.setString("password", pass);
	           
	           LoginModel loginModel = (LoginModel) query.uniqueResult();
	           if (loginModel != null) {
	 	          
		    		return loginModel;

	 	           
	    			
	    		} 
	           
	        } catch (HibernateException e) {
	            rollback();
	            throw new AdException("Could not get user " + name, e);
	        }
		return null;
		

	}
	
	public boolean checkIfUserNameExists(String name) {
		
		Query query = getSession().createQuery("from LoginModel where userName = :userName");
		List<LoginModel> list = query.list();
		if(list.size() >= 1){
			return true;
		}
		
		return false;
	}
	
	public LoginModel getUserIfExists(String name) {
		
		Criteria criteria = getSession().createCriteria(LoginModel.class);
		criteria.add(Restrictions.eq("userName", name));
			return (LoginModel) criteria.uniqueResult();
		}


	public LoginModel checkIfAlreadyRegistered(String nuid) {
		Criteria criteria = getSession().createCriteria(LoginModel.class);
		criteria.add(Restrictions.eq("nuID", nuid));
			return (LoginModel) criteria.uniqueResult();
		
		
		
	}
	
}
