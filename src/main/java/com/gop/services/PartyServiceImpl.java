package com.gop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gop.domain.Party;
import com.gop.repository.*;
import com.gop.util.GopUtils;

@Service
public class PartyServiceImpl
{
	@Autowired
    private PartyRepository partyRepository;
	
	public String addParty(String partyJson) throws Exception
	{
		String jsonPartyReturn = null;
		Party party = null;
		try
		{			
			party = GopUtils.generateClassFromJson(partyJson,Party.class);
			partyRepository.save(party);
			jsonPartyReturn = findPartyByName(party.getPartyName());
		}
		catch(Exception e)
		{
			System.out.println("Error in addParty: "+e.getMessage());
			throw new Exception(e);
		}
		return jsonPartyReturn;
	}
	
	public String findPartyByName(String partyName) throws Exception
	{
		List<Party> partyList = null;
		String jsonPartyReturn = null;
		try
		{
			partyList = partyRepository.findPartyByName(partyName);			
			jsonPartyReturn = GopUtils.generateJsonFromClass(partyList.get(0));
		}
		catch(Exception e)
		{
			System.out.println("Error in findPartyByName: "+e.getMessage());
			throw new Exception(e);
		}	
		return jsonPartyReturn;
	}
}