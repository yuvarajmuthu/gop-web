package com.gop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gop.services.UserServiceImpl;

@Controller
@RequestMapping("/user")

public class UserController 
{
	
	@Autowired
	UserServiceImpl userServiceImpl;
	
	
	@RequestMapping(value="/addUser", method=RequestMethod.POST,headers="Accept=application/json")
	public @ResponseBody String addUser(@RequestBody String jsonUser)
	{
		String jsonUserReturn = null;
		try
		{
			jsonUserReturn = userServiceImpl.addUser(jsonUser);
		}
		catch(Exception e)
		{
			System.out.println("Error in addUser controller:"+e.getMessage());
		}
		return jsonUserReturn;
	}
	
	@RequestMapping(value="/getUser/{userName}", method=RequestMethod.GET)
	public @ResponseBody String getUser(@PathVariable String userName)
	{
		String jsonUserReturn = null;
		try
		{
			jsonUserReturn = userServiceImpl.getUser(userName);
		}
		catch(Exception e)
		{
			System.out.println("Error in getUser controller:"+e.getMessage());
		}
		return jsonUserReturn;
	}

}
