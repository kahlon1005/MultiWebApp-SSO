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
	
	
	private SimpleContainer next;
	private SimpleContainer previous;
	
	@PostConstruct
	public void init(){
		containers = service.getAll();
	}
		
	public String doEdit(){
		populateContainers();
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
		container.setLabel(Integer.toString(selectedContainers.size() + 1));
		c.addChildren(container);
		selected = container;
		selectedContainers.add(selected);
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
		} else if(selected.getText() == null ){
			service.addContainer(selected);			
		} 
		init();
	}
	
	public void editContainer(){
		mode = "NONE";
	}
	
	public void removeContainer(){
		mode = "NONE";
		containers.remove(selected);
		selected = null;
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
		this.mode = "NONE";
	}
	
	public void deleteSurveyQuestion(SimpleSurveyQuestion question){
		selected.getQuestions().remove(question);
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

	public SimpleContainer getNext() {
		return next;
	}

	public void setNext(SimpleContainer next) {
		this.next = next;
	}

	public SimpleContainer getPrevious() {
		return previous;
	}

	public void setPrevious(SimpleContainer previous) {
		this.previous = previous;
	}

	public List<SimpleContainer> getSelectedContainers() {
		if(selectedContainers.isEmpty()){
			selectedContainers.add(selected);
		}
		return selectedContainers;
	}
	
	private void populateContainers(){
		selectedContainers = new ArrayList<SimpleContainer>();		
		getRootContainer(selected);
		selectedContainers.add(selected);
		addChildContainer(selected);		
	}
	
	private void getRootContainer(SimpleContainer container){
		if(container.hasParent()){
			container = container.getParent();
			getRootContainer(container);			
		}else{
			selected = container;
		}		
	}

	private void addChildContainer(SimpleContainer container){
		List<SimpleContainer> list = container.getChildren();
		Iterator<SimpleContainer> it = list.iterator();
		while (it.hasNext()) {
			SimpleContainer c = it.next();
			selectedContainers.add(c);
			if(c.hasChild()){
				addChildContainer(c);				
			}
			
		}
	}
	
	/**
	 * Preview Survey 
	 */
	
	
	
	public void doStart(SimpleContainer container){
		this.mode = "PR";
		getRootContainer(container);		
	}
	
	public boolean isBack(){
		return this.selected.hasParent();
	}
	
	public boolean isNext(){
		if(selectedContainers.indexOf(selected) == selectedContainers.size() - 1){
			return false;
		}
		return true; //this.selected.hasChild();
	}
	
	
	public boolean isFinish(){
		return ! isNext();
	}
	
	public void goNext(){
		if(selectedContainers.indexOf(selected) < selectedContainers.size()){
			int current = selectedContainers.indexOf(this.selected) + 1;
			this.selected = selectedContainers.get(current);
		}
	}
	
	public void goBack(){
		if(this.selected.hasParent()){
			int current = selectedContainers.indexOf(this.selected) - 1;
			this.selected = selectedContainers.get(current);
		}
	}
	
	public void doFinish(){
		this.mode = "NONE";
		this.selected = null;
	}
	
}
