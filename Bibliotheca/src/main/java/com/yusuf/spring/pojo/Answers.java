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
@Table(name="answers")

public class Answers {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ansID")
	private int ansID;
	
	@Column(name="answerBy")
	 private String answerBy;
	
	@Column()
	private String answer;
	
	public int getAnsID() {
		return ansID;
	}


	public void setAnsID(int ansID) {
		this.ansID = ansID;
	}


	public String getAnswerBy() {
		return answerBy;
	}


	public void setAnswerBy(String answerBy) {
		this.answerBy = answerBy;
	}


	public String getAnswer() {
		return answer;
	}


	public void setAnswer(String answer) {
		this.answer = answer;
	}


	public Question getQuesID() {
		return quesID;
	}


	public void setQuesID(Question quesID) {
		this.quesID = quesID;
	}


	@ManyToOne
	@JoinColumn(name="quesID")
	private Question quesID;

		public  Answers() {
		
	}
	
	
	 public  Answers(Question quesID, String  answerBy, String answer){
		 
		 this.quesID = quesID;
		 this.answerBy = answerBy;
		 this.answer =answer;
	 }
		
	
	
	
}
