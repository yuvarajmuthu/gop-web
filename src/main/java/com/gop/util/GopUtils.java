package com.gop.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class GopUtils {
	
	public static JsonElement getMember(String memberName, JsonObject jsonObject){
		if(null != jsonObject){
			JsonElement jsonElement = jsonObject.get(memberName);
			if(jsonElement != null) {
				return jsonElement;
			}
		}
		return null;
	}
	public static <T> T generateClassFromJson(String jsonString, Class<T> className){
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.setDateFormat("yyyy-MM-dd").create();				
		T target = gson.fromJson(jsonString, className);
		return target;			
	}
	
	public static String generateJsonFromClass(Object obj) {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String json =  gson.toJson(obj);
	    return json;
	}
}
