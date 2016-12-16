package com.survey.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

import com.survey.model.SimpleContainer;


@Stateless
public class SurveyService {
	
	List<SimpleContainer> containers = new ArrayList<SimpleContainer>();
	
	@PostConstruct
	private void init(){
		data();
	}
	
	public List<SimpleContainer> getAll(){
		return containers; 
	}
	
	private void data(){
		addContainer("Loan application");
		addContainer("Travel Insurance");
	}
	
	private void addContainer(String text){		
		SimpleContainer c1 = new SimpleContainer();
		c1.setText(text);
		containers.add(c1);
	}
	
	

}
