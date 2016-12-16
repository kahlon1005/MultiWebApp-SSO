package com.survey.model;

import com.survey.model.SimpleSurveyQuestion.QuestionType;

public class SingleSelectQuestion extends ResponseListQuestion{

	public SingleSelectQuestion() {
		this.setType(QuestionType.SINGLE);
	}
	
}