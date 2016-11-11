package com.yusuf.spring.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
@Entity
@Table(name="userlogin")
@Scope("session")

public class LoginModel {
	 
	@Id
	@Column(name="userID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userID;
	
	@Column(name="username")
    private String userName;
	
	@Column(name="password")
    private String password;
	
	@Column(name="roles")
	private String roles; 
	
	@Column(name = "nuID")
	private String nuID;
 
    public String getNuID() {
		return nuID;
	}

	public void setNuID(String nuID) {
		this.nuID = nuID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getUserName() {
        return userName;
    }
 
    public void setUserName(String userName) {
        this.userName = userName;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
 
    
     public LoginModel(String userName, String password, String roles) {
        this.userName = userName;
        this.password = password;
        this.roles = roles;
       
    
	}
     
     public LoginModel(){
    	 
     }

}