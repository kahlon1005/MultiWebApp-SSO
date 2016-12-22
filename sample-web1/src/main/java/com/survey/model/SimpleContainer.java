package com.survey.model;

import java.util.ArrayList;
import java.util.List;

import org.JCaiF.ComponentContainer;

import com.survey.model.SimpleSurveyQuestion.QuestionType;

public class SimpleContainer{
	
	private String name = "";
	private String label = "";
	private String text = "";		
	List<SimpleSurveyQuestion> questions = new ArrayList<SimpleSurveyQuestion>();
	private SimpleContainer parent;
	private List<SimpleContainer> children = new ArrayList<SimpleContainer>();
	
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
	
	public SimpleContainer getParent() {
		return parent;
	}
	public void setParent(SimpleContainer parent) {
		this.parent = parent;
	}
	
	public List<SimpleContainer> getChildren() {
		return children;
	}
	public void setChildren(List<SimpleContainer> children) {
		this.children = children;
	}
	
	public Boolean hasParent(){
		return this.parent != null;
	}
	
	public Boolean hasChild(){
		return !this.children.isEmpty();
	}
	public void addChildren(SimpleContainer selected) {
		this.getChildren().add(selected);		
	}
	
	public void addQuestion(SimpleSurveyQuestion q){
		this.getQuestions().add(q);			
	}
	
}