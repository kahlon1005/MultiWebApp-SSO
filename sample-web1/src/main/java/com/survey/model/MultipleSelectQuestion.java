package com.survey.model;

import java.util.ArrayList;
import java.util.List;

public class MultipleSelectQuestion extends ResponseListQuestion{

	private List<String> values ; 

	
	public MultipleSelectQuestion(SimpleSurveyQuestion q) {
		
	}


	public MultipleSelectQuestion() {
		this.setType(QuestionType.MULTIPLE);
		values = new ArrayList<String>();
	}


	public List<String> getValues() {
		return values;
	}


	public void setValues(List<String> values) {
		this.values = values;
	}
	
	
	
}