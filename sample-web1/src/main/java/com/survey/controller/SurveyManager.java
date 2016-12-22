package com.survey.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
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
	
	List<SimpleResponseList>  responseList = new ArrayList<SimpleResponseList>();
	
	private SimpleContainer selected;
	private List<SimpleContainer>selectedContainers = new ArrayList<SimpleContainer>();
	private List<SimpleContainer> containers = new ArrayList<SimpleContainer>();
	List<SimpleResponseList> responses = new ArrayList<SimpleResponseList>();
	
	SimpleSurveyQuestion newQuestion = new MultipleSelectQuestion();
	
	SimpleSurveyQuestion selectedQuestion;
	
	SimpleResponseList newResponse = new SimpleResponseList();
	
	QuestionType questionType = QuestionType.MULTIPLE;
	
	private String mode = "NONE";
	
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
	
	public void addPage(SimpleContainer c){
		SimpleContainer container = new SimpleContainer();
		container.setParent(c);
		container.setText(c.getText());
		c.addChildren(container);
		selected = container;		
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

	public void addContainer(){
		if(selected == null){
			selected = new SimpleContainer();
		} else{
			service.addContainer(selected);			
		}
		init();
	}
	
	public void addSurveyQuestion(){		
		if(newQuestion instanceof ResponseListQuestion){
			newQuestion.setResponseList(responses);
			responses = new ArrayList<SimpleResponseList>();
		}
		selected.addQuestion(newQuestion);
		addNewQuestion();
	}
	
	public void editSurveyQuestion(){
		
	}
	
	public void deleteSurveyQuestion(){
		
	}
	
	public void addResponseList(){
		System.out.println("add response list ...");
	}
	
	public String save(){
		for(SimpleSurveyQuestion q : selected.getQuestions()){
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
	
	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	
	public SimpleSurveyQuestion getSelectedQuestion() {
		return selectedQuestion;
	}

	public void setSelectedQuestion(SimpleSurveyQuestion selectedQuestion) {
		this.selectedQuestion = selectedQuestion;
	}

	public List<SimpleContainer> getSelectedContainers() {
		selectedContainers = new ArrayList<SimpleContainer>();		
		SimpleContainer rootNode = getRootNode(selected);
		selectedContainers.add(rootNode);
		addChildContainer(rootNode);
		
		return selectedContainers;
	}
	
	private SimpleContainer getRootNode(SimpleContainer container){
		if(container.hasParent()){
			container = container.getParent();
			getRootNode(container);
		}
		return container;
	}

	private void addChildContainer(SimpleContainer container){
		List<SimpleContainer> list = container.getChildren();
		Iterator<SimpleContainer> it = list.iterator();
		while (it.hasNext()) {
			SimpleContainer c = it.next();
			if(c.hasChild()){
				addChildContainer(c);				
			}
			selectedContainers.add(c);
		}
	}
	
	
}
