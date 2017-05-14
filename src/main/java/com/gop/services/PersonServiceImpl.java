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
import com.gop.domain.Person;
import com.gop.repository.PersonRepository;
import com.gop.util.GopLuceneSearchUtility;
import com.gop.util.GopUtils;

@Service
public class PersonServiceImpl {

	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	GopLuceneSearchUtility luceneUtility;
	
	public JsonArray getPersons(String search) throws Exception{
		JsonArray jsonArray = new JsonArray();
		Gson gson = new Gson();
		StringBuilder queryString = new StringBuilder();
		queryString.append(search).append("*");
		luceneUtility.openSearcher();
		int noDocsIndexed = luceneUtility.getIndexReader().numDocs();
		for (int i=0; i<luceneUtility.getIndexReader().maxDoc(); i++) {
		    Document doc = luceneUtility.getIndexReader().document(i);
		    System.err.println(doc.toString());
		}
		Query query = luceneUtility.getQueryParser().parse(queryString.toString());
		TopDocs topDocs = luceneUtility.getIndexSearcher().search(query, 20);
		for(ScoreDoc scoreDoc : topDocs.scoreDocs){
			int docId = scoreDoc.doc;
			Document doc = luceneUtility.getIndexSearcher().doc(docId);
			String cacheValue = doc.get("json");
			jsonArray.add(gson.fromJson(cacheValue, JsonObject.class));
		}
		luceneUtility.closeSearcher();
		return jsonArray;
		//return luceneUtility.getDocument(search);
	}
	
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
			String cacheKey = person.getFirstName().concat(",").concat(person.getLastName()).concat(",").concat(person.getEmailId());
			luceneUtility.openWriter();
			Document partyDoc = luceneUtility.createDocument(cacheKey, jsonPersonReturn, 1);
			luceneUtility.getIndexWriter().addDocument(partyDoc);
			luceneUtility.closeWriter();
			luceneUtility.openSearcher();
			int noDocsIndexed = luceneUtility.getIndexReader().numDocs();
			for (int i=0; i<luceneUtility.getIndexReader().maxDoc(); i++) {
			    Document doc = luceneUtility.getIndexReader().document(i);
			    System.err.println(doc.toString());
			}
			luceneUtility.closeSearcher();
			//luceneUtility.createIndex(cacheKey, jsonPersonReturn, 1);
		}
		catch(Exception e)
		{
			System.out.println("Error in addPerson:"+e.getMessage());
			throw new Exception();
		}
		return jsonPersonReturn;
	}

}
