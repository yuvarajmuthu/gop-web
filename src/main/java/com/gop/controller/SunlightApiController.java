package com.gop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.gop.util.ExternalUrlProperties;

@RestController
@RequestMapping("/sunlight")
public class SunlightApiController {

private static final Logger logger = LoggerFactory.getLogger(SunlightApiController.class);
	
	@Autowired
	private ExternalUrlProperties properties;
	
	@Autowired
	private Gson gson;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(value = "/getBoundaries", method=RequestMethod.GET)
	public String getBoundaries(@RequestParam(value="boundaryId", required=false, defaultValue="ocd-division/country:us/state:oh/sldl:25") String boundaryId,
			@RequestParam(value="apiKey", required=false, defaultValue="fd1d412896f54a8583fd039670983e59") String apiKey){
	//http://openstates.org/api/v1/districts/boundary/ocd-division/country:us/state:oh/sldl:25/?apikey=fd1d412896f54a8583fd039670983e59
		logger.info("in SunlightApiController");
		String url = properties.getSunlight_url()+"districts/boundary/"+boundaryId+"/?apikey="+apiKey;
		System.out.println(url);
		ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
		String body = entity.getBody();
		JsonObject boundaries = gson.fromJson(body , JsonObject.class);
		if(boundaries != null){
			JsonArray bboxArray = boundaries.get("bbox").getAsJsonArray();
			return bboxArray.toString();
		}
		return "";
	}
	
	@RequestMapping(value = "/getBoundaryId", method=RequestMethod.GET)
	public String getDistrict(@RequestParam(value="state", required=false, defaultValue="oh") String state,
								@RequestParam(value="chamber", required=false, defaultValue="lower") String chamber,
								@RequestParam(value="apiKey", required=false, defaultValue="fd1d412896f54a8583fd039670983e59") String apiKey){
		//http://openstates.org/api/v1/districts/oh/lower/?apikey=fd1d412896f54a8583fd039670983e59
		String url = properties.getSunlight_url()+"districts/"+state+"/"+chamber+"/?apikey="+apiKey;
		System.out.println(url);
		ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
		String body = entity.getBody();
		JsonArray divisionJson = gson.fromJson(body, JsonArray.class);
		if(divisionJson != null){
			JsonObject division = divisionJson.get(0).getAsJsonObject();
			return division.get("boundary_id").getAsString();
		}
		return "ocd-division/country:us/state:oh/sldl:25";
	}
}
