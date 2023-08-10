package com.adso.utils;

import com.google.gson.Gson;

public class ExceptionToJsonError {
	
	public static String generateUniqueSQLException() {
		JsonMessage jsonMessage = new JsonMessage();
		jsonMessage.setMessage("El registro no pudo ser guardado. Ya existe un registro igual");
		
		Gson gson = new Gson();
		String jsonResponse = gson.toJson(jsonMessage);			
		
		return jsonResponse;
	}
}
