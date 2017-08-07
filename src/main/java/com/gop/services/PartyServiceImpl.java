package com.gop.services;

import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.gop.domain.Party;
import com.gop.repository.PartyRepository;
import com.gop.util.GopLuceneSearchUtility;
import com.gop.util.GopUtils;

@Service
public class PartyServiceImpl
{
	@Autowired
    private PartyRepository partyRepository;
	
	@Autowired
	private GopLuceneSearchUtility luceneUtility;
	
	public String addParty(String partyJson) throws Exception
	{
		String jsonPartyReturn = null;
		Party party = null;
		try
		{			
			party = GopUtils.generateClassFromJson(partyJson,Party.class);
			partyRepository.save(party);
			jsonPartyReturn = findPartyByName(party.getPartyName());
			luceneUtility.openWriter();
			Document partyDoc = luceneUtility.createDocument(party.getPartyName(), jsonPartyReturn, 1);
			luceneUtility.getIndexWriter().addDocument(partyDoc);
			luceneUtility.closeWriter();
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
	
	public JsonArray findPartiesByName(String search) throws Exception
	{
		JsonArray jsonArray = new JsonArray();
		Gson gson = new Gson();
		String queryString = new String();
		queryString.concat(search).concat("*");
		luceneUtility.openWriter();
		luceneUtility.closeWriter();
		Query query = luceneUtility.getQueryParser().parse(queryString);
		TopDocs topDocs = luceneUtility.getIndexSearcher().search(query, 20);
		for(ScoreDoc scoreDoc : topDocs.scoreDocs){
			int docId = scoreDoc.doc;
			Document doc = luceneUtility.getIndexSearcher().doc(docId);
			String cacheValue = doc.get("json");
			jsonArray.add(gson.fromJson(cacheValue, JsonObject.class));
		}
		return jsonArray;
	}
}