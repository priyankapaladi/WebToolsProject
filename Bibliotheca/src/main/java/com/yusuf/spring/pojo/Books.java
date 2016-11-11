package com.yusuf.spring.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;





@Entity
@Table(name="books")
public class Books {
	 
	@Id
	@Column(name="bookID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int bookID;
	
	@Column(name="bookName")
    private String bookName;
	
	@Column(name="author")
    private String author;
	
	@Column(name = "edition")
	private String edition;

	@Column(name = "description")
	private String description;
	
	@OneToMany(mappedBy = "bookIDRequested")
	private Set<RequestBook> bookRequest = new HashSet<RequestBook>();

	
	public String getDescription() {
		return description;
	}

	public Set<RequestBook> getBookRequest() {
		return bookRequest;
	}

	public void setBookRequest(Set<RequestBook> bookRequest) {
		this.bookRequest = bookRequest;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getBookID() {
		return bookID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}
	
	public Books(String bookName, String author,String edition, String description) {
        this.bookName = bookName;
        this.author = author;
        this.edition = edition;
        this.description = description;
        bookRequest = new HashSet<RequestBook>();
        
       
    }

public Books() {
	// TODO Auto-generated constructor stub
}
	
}