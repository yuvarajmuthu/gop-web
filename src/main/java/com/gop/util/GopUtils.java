package com.gop.util;

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

}
