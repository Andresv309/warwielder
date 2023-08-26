package com.adso.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.adso.exceptions.app.NotFoundException;
import com.adso.exceptions.app.NotValidPathPatternException;
import com.adso.exceptions.auth.NotAuthorizedException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public class Utils {

	public static String getJwtTokenFromCookies(HttpServletRequest httpRequest) throws NotAuthorizedException {
		Cookie[] cookies = httpRequest.getCookies();
		String jwtToken = null;
		
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("jwt-token")) {
                    jwtToken = cookie.getValue();
                    break;
                }
            }
        }
        
        if (jwtToken == null) {
        	throw new NotAuthorizedException("An authorization token is required for this operation.");
        }
        
        return jwtToken;
	}
	
	public static Long getUserIdFromCookies(HttpServletRequest httpRequest) throws NotAuthorizedException {
		String jwtToken = getJwtTokenFromCookies(httpRequest);
		Long userId = null;
		
		try {
			final DecodedJWT decodedJwt = JWT.decode(jwtToken);
			userId = Long.parseLong(decodedJwt.getClaim("id").asString());
			
		} catch (NumberFormatException e) {
			throw new NotAuthorizedException("The token passed is not valid.");
		}
		
		return userId;
	}
	
    public static String extractPathInfoFromRequest(HttpServletRequest request) throws NotValidPathPatternException {
    	// Remove the leading "/"
    	String pathInfo = request.getPathInfo().substring(1);
    	
    	// Define a regular expression for valid pathInfo
    	String validPathPattern = "^[a-zA-Z0-9_/.-]+$";

    	// Validate the pathInfo using the regular expression
    	if (!pathInfo.matches(validPathPattern)) {
    	    throw new NotValidPathPatternException(pathInfo);
    	}
    	
    	return pathInfo;
    }
	
    
    public static String extractPathFromRequest(HttpServletRequest request) {
    	// Get the context path of the application
        String contextPath = request.getContextPath();
        // Get the URI excluding the context path
        String path = request.getRequestURI().substring(contextPath.length());
        return path;
    }
    
    public static String stringifyJsonBody(HttpServletRequest request) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        StringBuilder jsonBodyBuilder = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            jsonBodyBuilder.append(line);
        }

        return jsonBodyBuilder.toString();
    }
    
 
}

