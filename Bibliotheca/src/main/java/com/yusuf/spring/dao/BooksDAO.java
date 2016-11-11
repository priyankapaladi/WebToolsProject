package com.yusuf.spring.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.yusuf.spring.exception.AdException;
import com.yusuf.spring.pojo.Advert;
import com.yusuf.spring.pojo.Books;
import com.yusuf.spring.pojo.LoginModel;
import com.yusuf.spring.pojo.RequestBook;
import com.yusuf.spring.pojo.User;

public class BooksDAO extends DAO {
	
	public BooksDAO(){
		
		
	}

	  public Books create(String bookName, String author, String edition, String description)
	            throws AdException {
	        try {
	            begin();
	            Books books = new Books(bookName, author, edition, description);
	            getSession().save(books);
	            commit();
	            return books;
	        } catch (HibernateException e) {
	            rollback();
	            //throw new AdException("Could not create  book", e);
	            throw new AdException("Exception while creating book: " + e.getMessage());
	        }finally {
				close();
			}
	    }

	    public void delete(Books books)
	            throws AdException {
	        try {
	            begin();
	            getSession().delete(books);
	            commit();
	        } catch (HibernateException e) {
	            rollback();
	            throw new AdException("Could not delete advert", e);
	        }finally {
				close();
			}
	    }
	    
	    
public List<Books> searchByBookName (String nameOfTheBook){
	
	
	Query query = getSession().createQuery("from Books where lower(bookName) like :nameOfTheBook");
	  query.setString("nameOfTheBook", "%"+nameOfTheBook+"%");
	  
	  List<Books> list = query.list();
	  if(list.size() >= 1){
		  System.out.println("DAO Class" + list.size());
		  return  list;
		  
		  
	  }
	  
	 return null;
}


public List<Books> searchByAuthor (String nameOfAuthor){
	
	Query query = getSession().createQuery("from Books where lower(author) like :nameOfAuthor");
	  query.setString("nameOfAuthor", "%"+nameOfAuthor+"%");
	  
	  List<Books> list = query.list();
	  if(list.size() >= 1){
		  
		  return  list;
		  
		  
	  }
	  
	 return null;
}

public Books searchById(String bid){
	
	
	Query query = getSession().createQuery("from Books where bookID = :bid");
	query.setInteger("bid", Integer.parseInt(bid));
	 Books book = (Books) query.uniqueResult();
	  if(book != null){
		  
		  return  book;
		  
		  
	  }
	
	
	return null;
	
}

public void updateBookDetails(String bid, String bookName, String edition, String author, String description) throws AdException {
        try {
            begin();
            
            Books book = (Books)getSession().get(Books.class, Integer.parseInt(bid)); 
         book.setBookName(bookName);
         book.setAuthor(author);
         book.setEdition(edition);
         book.setDescription(description);
		 getSession().update(book); 
           
            commit();
            
        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not updating  book", e);
            throw new AdException("Exception while updating book: " + e.getMessage());
        }finally {
			close();
		}
}


public void deleteBook(String bid) throws AdException{
	  try {
          begin();
          
          Books book = 
                  (Books)getSession().get(Books.class, Integer.parseInt(bid)); 
    	 getSession().delete(book); 
         
          commit();
          
      } catch (HibernateException e) {
          rollback();
          //throw new AdException("Could not updating  book", e);
          throw new AdException("Exception while updating book: " + e.getMessage());
      }finally {
			close();
		}
	
}


public void reserveBook(Books books, String nuID) throws AdException{
    try {
        begin();
        RequestBook request = new RequestBook(books,nuID);
        getSession().save(request);
        commit();
    } catch (HibernateException e) {
        rollback();
        //throw new AdException("Could not create  book", e);
        throw new AdException("Exception while creating request: " + e.getMessage());
    }finally {
		close();
	}
	
	
}
public List<Books> getAllBooks(){
	
	Query query = getSession().createQuery("from Books");
	  
	  List<Books> list = query.list();
	  if(list.size() >= 1){
		  return  list;
		  
		  
	  }
	return null;
}

public RequestBook getRequest(Books book, String nuid){
	
	Query query = getSession().createQuery("from RequestBook where bookID = :bid and requestBy = :nuid");
	query.setInteger("bid",book.getBookID());
	query.setString("nuid", nuid);
	
	RequestBook requestBook = (RequestBook) query.uniqueResult();
	
	return requestBook;
	
}

public List<RequestBook> getAllrequest(){
	Query query = getSession().createQuery("from RequestBook");
	  
	  List<RequestBook> list = query.list();
	  if(list.size() >= 1){
		  return  list;
		  
		  
	  }
	return null;
}

public void deleteRequest(String rid) throws AdException {
//	Query query = getSession().createQuery("from RequestBook where rid = :rid");
//	query.setInteger("rid", Integer.parseInt(rid));
//
	 try {
         begin();
         
         RequestBook requestBook = 
                 (RequestBook)getSession().get(RequestBook.class, Integer.parseInt(rid)); 
   	 getSession().delete(requestBook); 
        
         commit();
         
     } catch (HibernateException e) {
         rollback();
         //throw new AdException("Could not delete  book", e);
         throw new AdException("Exception while deleting book: " + e.getMessage());

     }finally {
			close();
		}

	
	
}
}
