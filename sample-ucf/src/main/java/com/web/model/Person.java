package com.web.model;

import java.util.List;

public class Person {

	private Integer id;

	private String firstName;
	private String lastName;
	
	private List<UserFieldValue> userFields;

	public Person() {
	}

	public Person(Integer id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public Person(Integer id, String firstName, String lastName,List<UserFieldValue> userFields) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userFields = userFields;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
	public List<UserFieldValue> getUserFields() {
		return userFields;
	}

	public void setUserFields(List<UserFieldValue> userFields) {
		this.userFields = userFields;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + "]";
	}
	
	
	

}
