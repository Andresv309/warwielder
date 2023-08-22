package com.adso.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

public class JsonResponseBuilder {
    private Map<String, Object> responseMap = new HashMap<>();

    public JsonResponseBuilder addField(String key, Object value) {
        responseMap.put(key, value);
        return this;
    }

    public JsonResponseBuilder addNestedFieldList(String key, Map<String, Object> nestedMap) {
    	responseMap.put(key, List.of(nestedMap));
        return this;
    }

    public String build() {
        if (responseMap.isEmpty()) {
        	// Return an empty JSON object if no data is added
            return "{}"; 
        }
        
        Gson gson = new Gson();
        return gson.toJson(responseMap);
    }

    public static JsonResponseBuilder create() {
        return new JsonResponseBuilder();
    }
}
