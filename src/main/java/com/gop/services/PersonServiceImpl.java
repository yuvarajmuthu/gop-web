package com.gop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gop.domain.Person;

import com.gop.repository.PersonRepository;

import com.gop.util.GopUtils;

@Service
public class PersonServiceImpl {

	@Autowired
	PersonRepository personRepository;
	
	public String getPerson(String firstName) throws Exception{
		String jsonPersonReturn = null;
		List<Person> personList = null;
		try
		{
			personList = personRepository.findPersonByName(firstName);
			jsonPersonReturn = GopUtils.generateJsonFromClass(personList.get(0));
		}
		catch(Exception e)
		{
			System.out.println("Error in getPerson:"+e.getMessage());
			throw new Exception();
		}
		return jsonPersonReturn;
	}

	public String addPerson(String jsonPerson) throws Exception {
		String jsonPersonReturn = null;
		Person person = null;
		try
		{
			person = GopUtils.generateClassFromJson(jsonPerson, Person.class);
			personRepository.save(person);
			jsonPersonReturn = getPerson(person.getFirstName());
		}
		catch(Exception e)
		{
			System.out.println("Error in addPerson:"+e.getMessage());
			throw new Exception();
		}
		return jsonPersonReturn;
	}

}
