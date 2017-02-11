package com.web.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.web.model.Person;
import com.web.model.UserFieldValue;

@Stateless
public class PersonService implements Serializable {

	private static final long serialVersionUID = -3212816715532737599L;

	@Inject
	UserEntityFieldService newField;

	private Map<Integer, Person> map;

	@PostConstruct
	public void init() {
		if (map == null || map.isEmpty()) {
			
			map = new HashMap<Integer, Person>();
			
			List<UserFieldValue> fieldList1 = new ArrayList<UserFieldValue>();
			fieldList1.add(new UserFieldValue("citizenship", "INDIA"));
			fieldList1.add(new UserFieldValue("bank", "SBI"));

			create(new Person(1, "Gavin", "Mark", fieldList1));

			List<UserFieldValue> fieldList2 = new ArrayList<UserFieldValue>();
			fieldList2.add(new UserFieldValue("citizenship", "CANADA"));
			fieldList2.add(new UserFieldValue("bank", "BMO"));

			create(new Person(2, "Bob", "Smith", fieldList2));
		}

	}

	public List<Person> create(Person person) {
		map.put(map.size() + 1, person);
		return getAll();
	}

	public Person get(Integer id) {
		return map.get(id);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Person> getAll() {
		List<Person> ret = new ArrayList<Person>();
		Iterator it = map.entrySet().iterator();
		while (it.hasNext()) {			
			Map.Entry<Integer, Person> person = (Map.Entry<Integer, Person>) it.next();
			ret.add(person.getValue());
		}
		return ret;
	}

}
