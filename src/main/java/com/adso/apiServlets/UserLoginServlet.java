package com.adso.apiServlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.adso.entities.User;
import com.adso.exceptions.auth.NotValidAuthParams;
import com.adso.exceptions.auth.NotValidCredentials;
import com.adso.services.CodeRedemptionService;
import com.adso.services.UserAuthenticationService;
import com.adso.utils.AuthCookieGenerator;
import com.adso.utils.JsonResponseBuilder;
import com.adso.utils.Utils;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@WebServlet(name = "userLoginServlet", urlPatterns = "/api/v1/login") 
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 9143184879641256565L;
	private UserAuthenticationService userAuthenticationService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		this.userAuthenticationService = new UserAuthenticationService();
		super.init(config);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JsonResponseBuilder jsonBuilder  = JsonResponseBuilder.create();
		String jsonBody = Utils.stringifyJsonBody(request);
		

		try {	
			
			JsonObject jsonObject = JsonParser.parseString(jsonBody).getAsJsonObject();
	        if (!jsonObject.has("username") || !jsonObject.has("password")) {
	        	throw new NotValidAuthParams();
	        }
	        
			String username = jsonObject.get("username").getAsString();
			String password = jsonObject.get("password").getAsString();
	        
			User validatedUser = userAuthenticationService.validateUser(username, password);
			
			Cookie cookie = new AuthCookieGenerator().generateAuthCookie(validatedUser);
            response.addCookie(cookie);
			response.sendRedirect(request.getContextPath() + "/account");

		} catch (NotValidCredentials e) {
			jsonBuilder.addField("error", e.getMessage());
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		} catch (NotValidAuthParams e) {
			jsonBuilder.addField("error", e.getMessage());
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonBuilder.build());
	}

}


//package com.adso.apiServlets;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.Cookie;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//
//import org.hibernate.exception.ConstraintViolationException;
//
//import com.adso.entities.User;
//import com.adso.services.UserAuthenticationService;
//import com.adso.utils.AuthCookieGenerator;
//import com.adso.utils.ExceptionToJsonError;
//import com.adso.utils.Utils;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//
//@WebServlet(name = "userLoginServlet", urlPatterns = "/api/v1/login") 
//public class UserLoginServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String jsonResponseToClient = "{}";
//		
//		try {	
//			String username = null;
//			String password = null;
//			
//			String jsonBody = Utils.stringifyJsonBody(request);
//			
//			// Parse the JSON data using Gson
//			JsonObject jsonObject = JsonParser.parseString(jsonBody).getAsJsonObject();
//			
//			if (jsonObject.has("username") && jsonObject.has("password")) {
//				username = jsonObject.get("username").getAsString();
//				password = jsonObject.get("password").getAsString();
//				
//				UserAuthenticationService userAuthenticationService = new UserAuthenticationService();
//				User validatedUser = userAuthenticationService.validateUser(username, password);
//				
//				if (validatedUser != null) {
//					Cookie cookie = new AuthCookieGenerator().generateAuthCookie(validatedUser);
//					
//					response.addCookie(cookie);
//					response.sendRedirect(request.getContextPath() + "/account");
//				}
//				
//			} else {
//				JsonObject responseJson = new JsonObject();
//				responseJson.addProperty("error", "Incorrect username or password");
//				
//				jsonResponseToClient = responseJson.toString();
//			}
//			
//		} catch (ConstraintViolationException e) {
//			jsonResponseToClient = ExceptionToJsonError.generateUniqueSQLException();			
//			response.setStatus(HttpServletResponse.SC_CONFLICT);
//		}
//		
//		System.out.println(jsonResponseToClient);
//		
//		response.setContentType("application/json");
//		response.setCharacterEncoding("UTF-8");
//		response.getWriter().write(jsonResponseToClient);
//	}
//	
//}
