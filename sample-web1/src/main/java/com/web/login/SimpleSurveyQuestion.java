package com.web.login;


public class SimpleSurveyQuestion{
	
	private QuestionType type = QuestionType.TEXT;
	
	private String value = "";
	private String text = "";
	private String id = "";
	
	enum QuestionType{SINGLE, MULTIPLE, TEXT}
	
	
	public QuestionType getType() {
		return type;
	}
	public void setType(QuestionType type) {
		this.type = type;
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}