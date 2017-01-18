package com.web.model;

public class UserFieldValue {
	
	private Integer fieldId;
	private String value;
	
	
	public UserFieldValue(Integer fieldId, String value) {
		this.fieldId = fieldId;
		this.value = value;
	}

	public UserFieldValue() {
	}

	
	public Integer getFieldId() {
		return fieldId;
	}

	public void setFieldId(Integer fieldId) {
		this.fieldId = fieldId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	
}
