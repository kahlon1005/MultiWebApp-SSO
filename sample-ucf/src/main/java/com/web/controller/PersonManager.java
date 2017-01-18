package com.web.controller;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.web.model.Person;
import com.web.service.PersonService;

@Named("person")
@ViewScoped
public class PersonManager implements Serializable{
	
	private static final long serialVersionUID = 7934702053668909263L;
	
	@Inject
	PersonService service;
	
	private Person newPerson;
	private Person selectedPerson;
	
	private List<Person> persons;
	
	@PostConstruct
	public void init(){		
		persons = service.getAll();
		newPerson = new Person();
		
	}
	
	public void create(){
		service.create(newPerson);
	}
	
	public void update(){
		
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
	
	
	
}
