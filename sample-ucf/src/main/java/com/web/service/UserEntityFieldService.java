package com.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

import com.web.model.UserEntityField;
import com.web.model.UserFieldType;

@Stateless
public class UserEntityFieldService {
	
	private Map<Integer, UserEntityField> map = new HashMap<>();
	
	@PostConstruct
	public void init(){
		create(1, "Person", "citizenship", "Citizenship", UserFieldType.TEXT, Boolean.FALSE);
		create(2, "Person", "bank", "Bank", UserFieldType.TEXT, Boolean.FALSE);
	}
	
	public void create(Integer id, String entity, String label, String fieldName, UserFieldType fieldType, boolean required){
		map.put(id, new UserEntityField(id, entity, label, fieldName, fieldType, required));
	}
	
	public List<UserEntityField> get(String entity){
		
		List<UserEntityField> ret = new ArrayList<UserEntityField>();
		Iterator it = map.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<Integer, UserEntityField> field = (Map.Entry<Integer, UserEntityField>) it.next();
			if(entity.equals(field.getValue().getEntity())){
				ret.add(field.getValue());
			}
		}
		return ret;
	}

	
}
