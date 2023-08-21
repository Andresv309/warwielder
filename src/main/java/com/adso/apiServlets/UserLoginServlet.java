package com.adso.apiServlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.hibernate.exception.ConstraintViolationException;

import com.adso.entities.User;
import com.adso.services.UserAuthenticationService;
import com.adso.utils.AuthCookieGenerator;
import com.adso.utils.ExceptionToJsonError;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@WebServlet(name = "userLoginServlet", urlPatterns = "/api/v1/login") 
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jsonResponseToClient = "{}";

		try {	
			String username = null;
			String password = null;
			
	        // Read JSON data from the request body
	        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
	        StringBuilder jsonBodyBuilder = new StringBuilder();
	        String line;

	        while ((line = reader.readLine()) != null) {
	            jsonBodyBuilder.append(line);
	        }

	        String jsonBody = jsonBodyBuilder.toString();
	        
	        // Parse the JSON data using Gson
	        JsonObject jsonObject = JsonParser.parseString(jsonBody).getAsJsonObject();
	        
	        if (jsonObject.has("username") && jsonObject.has("password")) {
	        	username = jsonObject.get("username").getAsString();
	        	password = jsonObject.get("password").getAsString();
		        
				UserAuthenticationService userAuthenticationService = new UserAuthenticationService();
				User validatedUser = userAuthenticationService.validateUser(username, password);
				
				if (validatedUser != null) {
					Cookie cookie = new AuthCookieGenerator().generateAuthCookie(validatedUser);
					
		            response.addCookie(cookie);
					response.sendRedirect(request.getContextPath() + "/account");
				}
 
	        } else {
	            JsonObject responseJson = new JsonObject();
	            responseJson.addProperty("error", "Incorrect username or password");
	            
	            jsonResponseToClient = responseJson.toString();
	        }

		} catch (ConstraintViolationException e) {
			jsonResponseToClient = ExceptionToJsonError.generateUniqueSQLException();			
			response.setStatus(HttpServletResponse.SC_CONFLICT);
		}

		System.out.println(jsonResponseToClient);
		
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponseToClient);
	}

}
