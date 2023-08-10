package com.adso.utils;

import java.util.HashMap;
import java.util.Map;

import com.adso.entities.User;

import jakarta.servlet.http.Cookie;

public class AuthCookieGenerator {
	
	public Cookie generateAuthCookie(User user) {
		Cookie cookie = null;
		
		try {
			JwtGenerator generator = new JwtGenerator();
			
			Map<String, String> claims = new HashMap<>();
			
			claims.put("id", user.getId().toString());
			claims.put("username", user.getUsername());
			claims.put("role", "USER");
			
			String token = generator.generateJwt(claims);
			System.out.println(token);
			
	        cookie = new Cookie("jwt-token", token);
	        cookie.setHttpOnly(true);
	        cookie.setPath("/warwielder");
	
	        cookie.setMaxAge(60 * 60 * 24);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cookie;
	}
}
