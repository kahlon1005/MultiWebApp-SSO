package com.web.model;

public class UserEntityField{
	
	private Integer id;
	private String entity;
	private String fieldName;
	private UserFieldType fieldType;
	private boolean required;
	
	public UserEntityField(Integer id, String entity, String fieldName) {
		this.id = id;
		this.entity = entity;
		this.fieldName = fieldName;
		this.required = Boolean.FALSE;
	}
	
	
	
	public UserEntityField(Integer id, String entity, String fieldName,	UserFieldType fieldType, boolean required) {
		this.id = id;
		this.entity = entity;
		this.fieldName = fieldName;
		this.fieldType = fieldType;
		this.required = required;
	}



	public UserEntityField() {
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public UserFieldType getFieldType() {
		return fieldType;
	}


	public void setFieldType(UserFieldType fieldType) {
		this.fieldType = fieldType;
	}

	
	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	@Override
	public String toString() {
		return "EntityUserField [fieldName=" + fieldName + "]";
	}
	
	
}
