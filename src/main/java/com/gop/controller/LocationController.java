package com.gop.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.gop.util.ExternalUrlProperties;
import com.gop.util.JSoupTest;
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
import com.google.gson.JsonObject;

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
		System.out.println(url);
		try {
			ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
			 String body = entity.getBody();
			 List<String> elements = JSoupTest.getStringsFromUrl(body, null);
			 String script = elements.get(0);
			 Pattern p = Pattern.compile("\"([^\"]*)\"");
			 Matcher m = p.matcher(script);
			 if (m.find()) {
			   String returnedResponse= StringEscapeUtils.unescapeJava(m.group(1));
			   JsonObject json = gson.fromJson(returnedResponse, JsonObject.class);
			   String name= json.get("objects").getAsJsonArray().get(0).getAsJsonObject().get("name").getAsString();
			   return name;
			 }
		}catch(Exception exception){
			logger.error("no return from rest call",exception);
		}
		return "No distrcit location found for current location";
	}
	
	@RequestMapping(value="/getLatAndLong", method=RequestMethod.GET)
	public String getDirectionsUsingDistrict(){
		return "";
	}

	@ExceptionHandler
	public void handleException(Exception exception){
		System.out.println(exception.getStackTrace());
	}
}
