package com.yusuf.spring.dao;


import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.yusuf.spring.exception.AdException;
import com.yusuf.spring.pojo.Books;
import com.yusuf.spring.pojo.Document;


public class DocumentsDAO extends DAO {
	
	public DocumentsDAO(){
		
		
	}

	
	
	public List<Document> getAllFiles() {
		begin();
		Query query = getSession().createQuery("from Document");
		 List<Document> list = query.list();
		 
		 if(list.size() >= 1){
			  System.out.println("DAO Class" + list.size());
			  return  list;
			  
			  
		  }
		commit();
		close();
		return null;
	}
	  
	public void saveFile(String fileName, CommonsMultipartFile aFile, String description){
		begin();
		
		 Document uploadFile = new Document();
         uploadFile.setFileName(fileName);
         uploadFile.setDescription(description);
         uploadFile.setFileData(aFile.getBytes());	
         getSession().save(uploadFile);
         
         commit();
         close();
		
	}
	
	public Document getDocument(String docId){
		Query query = getSession().createQuery("from Document where docID = :docId");
				query.setInteger("docId", Integer.parseInt(docId));
		 Document document = (Document) query.uniqueResult();
		 
		 if(document != null){
			 return document;
		 }
		return null;
	}  
	public void updateFile(String docid, String fileName, CommonsMultipartFile aFile, String description){
		
		begin();
		Document document = 
                (Document)getSession().get(Document.class, Integer.parseInt(docid)); 
     document.setFileName(fileName);
     document.setDescription(description);;
     document.setFileData(aFile.getBytes());
     getSession().update(document);
      
         commit();
         close();
		
	}
	
	public void deleteFileFromSystem(String docid) throws AdException {
		
		 try {
	          begin();
	          
	          Document document = 
	                  (Document)getSession().get(Document.class, Integer.parseInt(docid)); 
	    	 getSession().delete(document); 
	         
	          commit();
	          
	      } catch (HibernateException e) {
	          rollback();
	          //throw new AdException("Could not updating  book", e);
	          throw new AdException("Exception while deleting book: " + e.getMessage());
	      }finally {
				close();
			}
		
	}
	
	
	
	
}
