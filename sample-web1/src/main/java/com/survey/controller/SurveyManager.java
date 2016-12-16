package com.survey.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.survey.model.MultipleSelectQuestion;
import com.survey.model.ResponseListQuestion;
import com.survey.model.SimpleContainer;
import com.survey.model.SimpleResponseList;
import com.survey.model.SimpleSurveyQuestion;
import com.survey.model.SingleSelectQuestion;
import com.survey.model.TextInputQuestion;
import com.survey.model.SimpleSurveyQuestion.QuestionType;
import com.survey.service.SurveyService;

@Named("survey")
@ViewScoped
public class SurveyManager implements Serializable{
	private static final long serialVersionUID = 7934702053668909263L;

	@Inject 
	SurveyService service;
	
	SimpleContainer container;

	SimpleContainer newContainer = new SimpleContainer();	
	List<SimpleResponseList>  responseList = new ArrayList<SimpleResponseList>();
	
	private SimpleContainer selected;
	private List<SimpleContainer> containers = new ArrayList<SimpleContainer>();
	
	
	SimpleSurveyQuestion newQuestion = new MultipleSelectQuestion();
	SimpleResponseList newResponse = new SimpleResponseList();
	List<SimpleResponseList> responses = new ArrayList<SimpleResponseList>();
	
	QuestionType questionType = QuestionType.MULTIPLE;
	
	@PostConstruct
	public void init(){
		containers = service.getAll();
	}
		
	public String doEdit(){
		return "";
	}
	
	
	public void onQuestionTypeChange(ValueChangeEvent e){
		this.questionType = (QuestionType) e.getNewValue();
		addNewQuestion();
	}
	
	public void addNewQuestion(){
		if (QuestionType.MULTIPLE.equals(questionType)){
			this.newQuestion = new MultipleSelectQuestion();
		}else if(QuestionType.SINGLE.equals(questionType)){
			this.newQuestion = new SingleSelectQuestion();
		}else if(QuestionType.TEXT.equals(questionType)){
			this.newQuestion = new TextInputQuestion();
		}else{
			this.newQuestion = new SimpleSurveyQuestion();
		}
		
	}
	
	public void addNewResponse(){		
		responses.add(newResponse);
		newResponse = new SimpleResponseList();
	}
	
	public void removeResponse(){
		responses.remove(0);
	}
	
	public String addSurvey(){
		System.out.println("add new survey ...");
		return "addsurveyquestion.xhtml?faces-redirect=true";
	}
	
	public void addSurveyQuestion(){		
		System.out.println("add new question ..." + this.newQuestion.getType());
		if(newQuestion instanceof ResponseListQuestion){
			newQuestion.setResponseList(responses);
			responses = new ArrayList<SimpleResponseList>();
		}
		newContainer.addQuestion(newQuestion);
		addNewQuestion();
	}
	
	public void removeSurveyQuestion(){
		System.out.println("remove question ...");
	}
	
	public void addResponseList(){
		System.out.println("add response list ...");
	}
	
	public String save(){
		print("Save Questions ...");
		print(container.getText());
		for(SimpleSurveyQuestion q : container.getQuestions()){
			if(QuestionType.MULTIPLE.equals(q.getType())){				
				List<String> e = q.getList();;
				for(String s : e ){
					print(s);
				}				
			}else{
				print(q.getText() +"  : "+ q.getValue());
			}
		}
		init();
		return "success.xhtml?faces-redirect=true";
	}
	
	private void print(String msg){
		System.out.println(msg);
	}
	
	public SimpleContainer getContainer() {
		return container;
	}

	public void setContainer(SimpleContainer container) {
		this.container = container;
	}

	public SimpleContainer getNewContainer() {
		return newContainer;
	}

	public void setNewContainer(SimpleContainer newContainer) {
		this.newContainer = newContainer;
	}

	public SimpleSurveyQuestion getNewQuestion() {
		return newQuestion;
	}

	public void setNewQuestion(SimpleSurveyQuestion newQuestion) {
		this.newQuestion = newQuestion;
	}

	public QuestionType getQuestionType() {
		return questionType;
	}

	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}

	public List<SimpleResponseList> getResponseList() {
		return responseList;
	}

	public void setResponseList(List<SimpleResponseList> responseList) {
		this.responseList = responseList;
	}

	public SimpleResponseList getNewResponse() {
		return newResponse;
	}

	public void setNewResponse(SimpleResponseList newResponse) {
		this.newResponse = newResponse;
	}

	public List<SimpleResponseList> getResponses() {
		return responses;
	}

	public void setResponses(List<SimpleResponseList> responses) {
		this.responses = responses;
	}
	
	public List<SimpleContainer> getContainers() {
		return containers;
	}

	public void setContainers(List<SimpleContainer> containers) {
		this.containers = containers;
	}

	public SimpleContainer getSelected() {
		return selected;
	}

	public void setSelected(SimpleContainer selected) {
		this.selected = selected;
	}
	
}
