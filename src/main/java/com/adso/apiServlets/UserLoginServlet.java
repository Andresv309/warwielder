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
import com.adso.exceptions.app.RequiredPayloadException;
import com.adso.exceptions.auth.NotValidCredentialsException;
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
	        	throw new RequiredPayloadException("username and password");
	        }
	        
			String username = jsonObject.get("username").getAsString();
			String password = jsonObject.get("password").getAsString();
	        
			User validatedUser = userAuthenticationService.validateUser(username, password);
			
			Cookie cookie = new AuthCookieGenerator().generateAuthCookie(validatedUser);
            response.addCookie(cookie);
			response.sendRedirect(request.getContextPath() + "/account");

		} catch (NotValidCredentialsException e) {
			jsonBuilder.addField("error", e.getCustomError());
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		} catch (RequiredPayloadException e) {
			jsonBuilder.addField("error", e.getCustomError());
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonBuilder.build());
	}

}

