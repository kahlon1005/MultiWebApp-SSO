package com.web.model;

public class UserFieldValue {

	private Integer Id;
	private String field;
	private String value;
	
	public UserFieldValue(String field, String value) {
		this.field = field;
		this.value = value;
	}

	public UserFieldValue() {
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
