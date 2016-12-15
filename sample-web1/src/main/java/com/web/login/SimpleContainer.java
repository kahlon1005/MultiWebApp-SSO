package com.web.login;

import java.util.ArrayList;
import java.util.List;

import com.web.login.SimpleSurveyQuestion.QuestionType;

public class SimpleContainer{
	
	private String name = "";
	private String label = "";
	private String text = "";		
	List<SimpleSurveyQuestion> questions = new ArrayList<SimpleSurveyQuestion>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<SimpleSurveyQuestion> getQuestions() {
		return questions;
	}
	public void setQuestions(List<SimpleSurveyQuestion> questions) {
		this.questions = questions;
	}
	
	public void addQuestion(SimpleSurveyQuestion q, QuestionType type){
		q.setType(type);
		System.out.println("Question Type " + q.getType());
		this.getQuestions().add(q);			
	}
	
	
}