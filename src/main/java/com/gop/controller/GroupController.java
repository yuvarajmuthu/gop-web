package com.gop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gop.services.GroupServiceImpl;

@Controller
@RequestMapping("/group")
public class GroupController {

	@Autowired
	GroupServiceImpl groupServiceImpl;
	
	@RequestMapping(value="/addGroup", method=RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody String addGroup(@RequestBody String jsonGroup){
		String jsonGroupReturn = null;
		try
		{
			jsonGroupReturn = groupServiceImpl.addGroup(jsonGroup);
		}
		catch(Exception e)
		{
			System.out.println("Error in addGroup controller: "+e.getMessage());
		}
		return jsonGroupReturn;
	}
	
	@RequestMapping(value="/getGroup/{groupName}", method=RequestMethod.GET)	
	public @ResponseBody String getGroup(@PathVariable String groupName)
	{
		String jsonGroupReturn = null;
		try
		{
			jsonGroupReturn = groupServiceImpl.findGroupByName(groupName);
		}
		catch(Exception e)
		{
			System.out.println("Error in getGroup controller: "+e.getMessage());
		}
		return jsonGroupReturn;
	}
}
