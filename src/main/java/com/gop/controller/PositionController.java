package com.gop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gop.services.PositionServiceImpl;

@Controller
@RequestMapping("/position")
public class PositionController {

	@Autowired
	PositionServiceImpl positionServiceImpl;
	
	@RequestMapping(value="/addPosition", method=RequestMethod.POST,headers="Accept=application/json")
	public @ResponseBody String addPosition(@RequestBody String jsonPosition)
	{
		String jsonPositionReturn = null;
		try
		{
			jsonPositionReturn = positionServiceImpl.addPosition(jsonPosition);
		}
		catch(Exception e)
		{
			System.out.println("Error in addPosition controller:"+e.getMessage());
		}
		return jsonPositionReturn;
	}
	
	@RequestMapping(value="/getPosition/{positionName}", method=RequestMethod.GET)
	public @ResponseBody String getPosition(@PathVariable String positionName)
	{
		String jsonPositionReturn = null;
		try
		{
			jsonPositionReturn = positionServiceImpl.getPosition(positionName);
		}
		catch(Exception e)
		{
			System.out.println("Error in getPosition controller:"+e.getMessage());
		}
		return jsonPositionReturn;
	}
}
