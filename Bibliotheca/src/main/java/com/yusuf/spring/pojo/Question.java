 package com.yusuf.spring.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="question")
public class Question {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="questionID")
	private int questionID;

	@Column(name="question")
	private String question;

	@OneToMany(mappedBy = "quesID")
	private Set<Answers> answers = new HashSet<Answers>();

	
public Set<Answers> getAnswers() {
		return answers;
	}


	public void setAnswers(Set<Answers> answers) {
		this.answers = answers;
	}


public Question() {
	// TODO Auto-generated constructor stub
}

public Question(String question) {
	this.question =question;
	
}

public int getQuestionID() {
	return questionID;
}


public void setQuestionID(int questionID) {
	this.questionID = questionID;
}


public String getQuestion() {
	return question;
}


public void setQuestion(String question) {
	this.question = question;
}



	
}