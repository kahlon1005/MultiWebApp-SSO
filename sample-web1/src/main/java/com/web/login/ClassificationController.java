package com.web.login;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;

import com.web.login.SimpleSurveyQuestion.QuestionType;

@Named("classification")
@SessionScoped
public class ClassificationController implements Serializable {

	private static final long serialVersionUID = 7934702053668909263L;

	SimpleContainer container;

	SimpleContainer newContainer = new SimpleContainer();	
	List<SimpleResponseList>  responseList = new ArrayList<SimpleResponseList>();
	
	QuestionType questionType = QuestionType.MULTIPLE;
	SimpleSurveyQuestion newQuestion = new MultipleSelectQuestion();
	SimpleResponseList newResponse = new SimpleResponseList();
	List<SimpleResponseList> responses = new ArrayList<SimpleResponseList>();
	
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
	
	@PostConstruct
	public void init() {
		initialize();	
	}
	
	private void initialize(){
		container = new SimpleContainer();
		container.setText("Loan application");
		
		List<SimpleSurveyQuestion> questions = new ArrayList<SimpleSurveyQuestion>();
		
		ResponseListQuestion q1 = new SingleSelectQuestion();
		q1.setText("Are you 19 or over?");
		
		List<SimpleResponseList> rspLst = new ArrayList<SimpleResponseList>();
		SimpleResponseList e1 = new SimpleResponseList();
		e1.setText("Yes");
		e1.setValue("Y");
		rspLst.add(e1);

		SimpleResponseList e2 = new SimpleResponseList();
		e2.setText("No");
		e2.setValue("N");
		rspLst.add(e2);

		q1.setResponseList(rspLst);
		questions.add(q1);
		
		ResponseListQuestion q2 = new MultipleSelectQuestion();
		q2.setText("No of co-applicant?");
		
		List<SimpleResponseList> rspLst2 = new ArrayList<SimpleResponseList>();
		SimpleResponseList e3 = new SimpleResponseList();
		e3.setText("One");
		e3.setValue("1");
		rspLst2.add(e3);

		SimpleResponseList e4 = new SimpleResponseList();
		e4.setText("Two");
		e4.setValue("2");
		rspLst2.add(e4);

		q2.setResponseList(rspLst2);
		questions.add(q2);
		
		SimpleSurveyQuestion q3 = new TextInputQuestion();
		q3.setText("Enter your name");
		questions.add(q3);
		
		container.setQuestions(questions);

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
		initialize();
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
	
}
