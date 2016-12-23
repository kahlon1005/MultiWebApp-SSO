package com.survey.model;

import java.util.ArrayList;
import java.util.List;


public class SimpleSurveyQuestion{
	
	private QuestionType type;
	
	private List<SimpleResponseList> responseList;
	
	private List<String> list = new ArrayList<String>();
	private String value = "";
	private String text = "";
	private String id = "";
	
	public enum QuestionType{SINGLE, MULTIPLE, TEXT}
	
	
	public QuestionType getType() {
		return type;
	}
	public void setType(QuestionType type) {
		this.type = type;
	}
	
	public List<SimpleResponseList> getResponseList() {
		return responseList;
	}
	public void setResponseList(List<SimpleResponseList> responseList) {
		this.responseList = responseList;
	}
	
	public List<String> getList() {
		return list;
	}
	public void setList(List<String> list) {
		this.list = list;
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