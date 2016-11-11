package com.yusuf.spring.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="requestbook")

public class RequestBook {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="rid")
	private int rid;
	
	@Column(name="requestBy")
	 private String requestBy;
	

	@ManyToOne
	@JoinColumn(name="bookID")
	private Books bookIDRequested;

	
	

	public int getRid() {
		return rid;
	}


	public void setRid(int rid) {
		this.rid = rid;
	}


	public String getRequestBy() {
		return requestBy;
	}


	public void setRequestBy(String requestBy) {
		this.requestBy = requestBy;
	}


	public Books getBookIDRequested() {
		return bookIDRequested;
	}


	public void setBookIDRequested(Books bookIDRequested) {
		this.bookIDRequested = bookIDRequested;
	}


	public  RequestBook() {
		
	}
	
	
	 public  RequestBook(Books bookRequested, String  requestBy){
		 
		 this.bookIDRequested = bookRequested;
		 this.requestBy = requestBy;
	 }
		
	
	
	
}
