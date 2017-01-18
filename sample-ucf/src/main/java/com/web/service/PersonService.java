package com.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.web.model.Person;
import com.web.model.UserFieldType;
import com.web.model.UserFieldValue;

@Stateless
public class PersonService {

	@Inject
	UserEntityFieldService newField;
	
	private Map<Integer, Person> map = new HashMap<Integer, Person>();

	@PostConstruct
	public void init() {
		newField.create(1, "Person", "Citizenship", UserFieldType.TEXT, Boolean.FALSE);
		newField.create(2, "Person", "Bank", UserFieldType.TEXT, Boolean.FALSE);
		
		List<UserFieldValue> fieldList1 = new ArrayList<UserFieldValue>();
		fieldList1.add(new UserFieldValue(1, "INDIA"));
		fieldList1.add(new UserFieldValue(2, "SBI"));
		
		create(new Person(1, "Gavin", "Mark", fieldList1));
		
		List<UserFieldValue> fieldList2 = new ArrayList<UserFieldValue>();
		fieldList2.add(new UserFieldValue(1, "CANADA"));
		fieldList2.add(new UserFieldValue(2, "BMO"));
		
		create(new Person(2, "Bob", "Smith", fieldList2));
		
	}

	public void create(Person person) {
		map.put(person.getId(), person);
	}

	public Person get(Integer id) {
		return map.get(id);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Person> getAll() {
		List<Person> ret = new ArrayList<Person>();
		Iterator it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, Person> person = (Map.Entry<Integer, Person>) it
					.next();
			ret.add(person.getValue());
		}
		return ret;
	}
	
	
	
}
