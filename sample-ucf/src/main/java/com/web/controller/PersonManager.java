package com.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.web.model.Person;
import com.web.model.UserEntityField;
import com.web.model.UserFieldValue;
import com.web.service.PersonService;
import com.web.service.UserEntityFieldService;

@Named("person")
@SessionScoped
public class PersonManager implements Serializable{
	
	private static final long serialVersionUID = 7934702053668909263L;
	
	@Inject
	PersonService service;
	
	@Inject
	UserEntityFieldService service2;
	
	private Person newPerson;
	private Person selectedPerson;
	
	private List<Person> persons;
	
	private List<UserEntityField> columns = new ArrayList<UserEntityField>();
	
	@PostConstruct
	public void init(){		
		persons = service.getAll();
		newPerson = new Person();
		createDynamicColumns();
	}

	private void createDynamicColumns() {
		columns = service2.get("Person");
		
		List<UserFieldValue> userFields = new ArrayList<UserFieldValue>();
		for(UserEntityField col : columns){			
			userFields.add(new UserFieldValue(col.getFieldName(), null));
		}
		newPerson.setUserFields(userFields);
	}

	public String save(){
		persons = service.create(newPerson);
		return "home";
	}
	
	public void create(){
		persons.add(newPerson);
	}
	
	public Person get(Integer id){
		return service.get(id);
	}
	
	public List<Person> getAll(){
		return service.getAll();
	}

	public Person getNewPerson() {
		return newPerson;
	}

	public void setNewPerson(Person newPerson) {
		this.newPerson = newPerson;
	}

	public Person getSelectedPerson() {
		return selectedPerson;
	}

	public void setSelectedPerson(Person selectedPerson) {
		this.selectedPerson = selectedPerson;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public List<UserEntityField> getColumns() {
		return columns;
	}

	public void setColumns(List<UserEntityField> columns) {
		this.columns = columns;
	}
	
	
}
