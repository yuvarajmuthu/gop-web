package com.gop.controller;

//import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gop.domain.Party;
import com.gop.repository.PartyRepository;
import com.gop.services.PartyServiceImpl;

@Controller
@RequestMapping(value = "/party")
public class PartyController {
	
	@Autowired	
    private PartyServiceImpl partyServiceImpl;
			
	@RequestMapping(value="/addParty", method=RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody String addParty(@RequestBody String jsonParty) {
		String jsonPartyReturn = null;
		try
		{								
			jsonPartyReturn = partyServiceImpl.addParty(jsonParty);							
		}
		catch(Exception e)
		{
			System.out.println("Error in addParty controller: "+e.getMessage());
		}
		return jsonPartyReturn;
	}
	
	@RequestMapping(value="/getParty/{partyName}", method=RequestMethod.GET)
	public @ResponseBody String getParty(@PathVariable String partyName) {
		String jsonPartyReturn = null;
		try
		{								
	        jsonPartyReturn = partyServiceImpl.findPartyByName(partyName);							
		}
		catch(Exception e)
		{
			System.out.println("Error in getParty controller: "+e.getMessage());
		}
		 return jsonPartyReturn;
	}
}