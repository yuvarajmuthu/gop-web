package com.gop.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.gop.util.ExternalUrlProperties;
import com.gop.util.JSoupTest;

import groovy.json.StringEscapeUtils;

@RestController
@EnableConfigurationProperties(ExternalUrlProperties.class)
public class LocationController {
	
	private static final Logger logger = LoggerFactory.getLogger(LocationController.class);
	
	@Autowired
	private ExternalUrlProperties properties;
	
	@Autowired
	private Gson gson;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(value="/getDistrict", method=RequestMethod.GET)
	public String getDistrictNameUsingLatAndLong(@RequestParam(value="directions", required=false, defaultValue="34.539,-81.020") String directions){
		logger.info("in LocationController");
		String url = properties.getLocation_url()+"?contains="+directions+"&format=apibrowser";
		JsonObject jsonObject = getResponseFromRest(url); 
		String name= jsonObject.get("objects").getAsJsonArray().get(0).getAsJsonObject().get("name").getAsString();
		return name;
	}
	
	@RequestMapping(value="/getLatAndLong", method=RequestMethod.GET)
	public String getDirectionsUsingDistrict(@RequestParam(value="districtName", required=false, defaultValue="sc-05") String districtName){
		String url = properties.getLocation_url()+districtName+"/?format=apibrowser";
		logger.info(url);
		JsonObject jsonObject = getResponseFromRest(url); 
		JsonElement jsonElement = jsonObject.get("centroid");
		jsonObject = jsonElement.getAsJsonObject();
		JsonArray array = jsonObject.get("coordinates").getAsJsonArray();
		return array.toString();
	}
	
	private JsonObject getResponseFromRest(String url){
		ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
		String body = entity.getBody();
		JsonObject jsonObject = new JsonObject();
		try {
			List<String> elements = JSoupTest.getStringsFromUrl(body, null);
			String script = elements.get(0);
			 Pattern p = Pattern.compile("\"([^\"]*)\"");
			 Matcher m = p.matcher(script);
			 if (m.find()) {
			   String returnedResponse= StringEscapeUtils.unescapeJava(m.group(1));
			   jsonObject= gson.fromJson(returnedResponse, JsonObject.class);
			   return jsonObject;
			 }
		}
		catch(Exception exception){
			logger.error(exception.getMessage());
		}
		return jsonObject;
	}
	
	@ExceptionHandler
	public void handleException(Exception exception){
		logger.error(exception.getMessage());
	}
}
