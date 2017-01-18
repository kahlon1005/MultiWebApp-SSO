package com.web.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.web.model.UserEntityField;
import com.web.model.UserFieldType;

public class UserEntityFieldService {
	
	private Map<String, UserEntityField> map = new HashMap<>();
	
	@PostConstruct
	public void init(){
		create(1, "Person", "Citizenship", UserFieldType.TEXT, Boolean.FALSE);
		create(1, "Person", "Bank", UserFieldType.TEXT, Boolean.FALSE);
	}
	
	public void create(Integer id, String entity, String fieldName, UserFieldType fieldType, boolean required){
		map.put(entity, new UserEntityField(id, entity, fieldName, fieldType, required));
	}

	
}
