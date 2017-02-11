package com.survey.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import com.survey.model.SimpleContainer;


@Stateless
public class SurveyService {
	
	List<SimpleContainer> containers = new ArrayList<SimpleContainer>();
	
	private static final Logger logger = Logger.getLogger(SurveyService.class.getName());
	
			
	@PostConstruct
	private void init(){
		logger.info("INFO message prineted");
		
		logger.warn("WARNING message printed");
		
		logger.error("SEVERE message printed");
		
		data();
	}
	
	private void data(){
		SimpleContainer c1 = new SimpleContainer();
		c1.setText("Loan application");
		c1.setLabel("1");
		containers.add(c1);

		SimpleContainer c2 = new SimpleContainer();
		c2.setText("Travel Insurance");
		c1.setLabel("1");
		containers.add(c2);		
		
	}
	
	public List<SimpleContainer> getAll(){
		return containers; 
	}
	
	public void addContainer(SimpleContainer selected) {
		containers.add(selected);
	}
	
	

}
