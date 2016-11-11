 package com.yusuf.spring.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.type.LobType;

import com.mysql.jdbc.Blob;



@Entity
@Table(name="document")
public class Document {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="docID")
	private int docID;

	@Column(name="description")
	private String description;

	@Column(name="fileName")
	private String fileName;
	
	@Column(name="fileData")
	@Lob()
	private byte[] fileData;
	
	//Getter and Setter methods
	
	

	public byte[] getFileData() {
		return fileData;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}

public Document() {
	// TODO Auto-generated constructor stub
}




public int getDocID() {
	return docID;
}

public void setDocID(int docID) {
	this.docID = docID;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}
	
}