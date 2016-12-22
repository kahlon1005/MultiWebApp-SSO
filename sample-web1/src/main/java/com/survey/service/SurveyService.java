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
	
	private void data(){
		SimpleContainer c1 = new SimpleContainer();
		c1.setText("Loan application");
		containers.add(c1);

		SimpleContainer c2 = new SimpleContainer();
		c2.setText("Travel Insurance");
		containers.add(c2);		
		
	}
	
	public List<SimpleContainer> getAll(){
		return containers; 
	}
	
	public void addContainer(SimpleContainer selected) {
		containers.add(selected);
	}
	
	

}
