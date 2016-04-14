package com.gop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gop.services.PersonServiceImpl;

@Controller
@RequestMapping("/person")

public class PersonController 
{
	
	@Autowired
	PersonServiceImpl personServiceImpl;
	
	
	@RequestMapping(value="/addPerson", method=RequestMethod.POST,headers="Accept=application/json")
	public @ResponseBody String addPerson(@RequestBody String jsonPerson)
	{
		String jsonPersonReturn = null;
		try
		{
			jsonPersonReturn = personServiceImpl.addPerson(jsonPerson);
		}
		catch(Exception e)
		{
			System.out.println("Error in addPerson controller:"+e.getMessage());
		}
		return jsonPersonReturn;
	}
	
	@RequestMapping(value="/getPerson/{firstName}", method=RequestMethod.GET)
	public @ResponseBody String getPerson(@PathVariable String firstName)
	{
		String jsonPersonReturn = null;
		try
		{
			jsonPersonReturn = personServiceImpl.getPerson(firstName);
		}
		catch(Exception e)
		{
			System.out.println("Error in getPerson controller:"+e.getMessage());
		}
		return jsonPersonReturn;
	}

}
