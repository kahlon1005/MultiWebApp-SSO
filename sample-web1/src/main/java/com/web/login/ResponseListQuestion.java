package com.web.login;

import java.util.List;

public class ResponseListQuestion extends SimpleSurveyQuestion {
	private List<SimpleResponseList> responseList;
	
	public List<SimpleResponseList> getResponseList() {
		return responseList;
	}
	public void setResponseList(List<SimpleResponseList> responseList) {
		this.responseList = responseList;
	}
}
