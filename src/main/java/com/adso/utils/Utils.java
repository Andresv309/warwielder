package com.adso.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public class Utils {

	public static String getJwtTokenFromCookies(HttpServletRequest httpRequest) {
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
        
        return jwtToken;
	}
	
	public static Long getUserIdFromCookies(HttpServletRequest httpRequest) {
		String jwtToken = getJwtTokenFromCookies(httpRequest);
		Long userId = null;
		
		if (jwtToken != null) {
			final DecodedJWT decodedJwt = JWT.decode(jwtToken);
			userId = Long.parseLong(decodedJwt.getClaim("id").asString());
		}
		
		return userId;
	}
}
