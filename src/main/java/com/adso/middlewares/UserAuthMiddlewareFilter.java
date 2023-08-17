package com.adso.middlewares;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Set;

import com.adso.utils.JwtValidator;
import com.adso.utils.Utils;
import com.auth0.jwt.interfaces.DecodedJWT;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UserAuthMiddlewareFilter implements Filter {

	private final Set<String> protectedPaths = new HashSet<>();
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		Filter.super.init(filterConfig);
        protectedPaths.add("/warwielder/account");
        protectedPaths.add("/warwielder/logintest");
	}


	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		// Create http request and response variables to use through app logic.
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		
        // Get the requested URL
        String requestURI = httpServletRequest.getRequestURI();
		
        System.out.println("requestURI " + requestURI);
		
		
		if (!protectedPaths.contains(requestURI)) {
			chain.doFilter(request, response);
			return;
		}
 
        boolean isUserAuth = checkIfUserIsAuth(httpServletRequest);
        
        if (requestURI.equals("/warwielder/logintest")) {
        	if (isUserAuth) {
        		httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/home");
        		return;        	
        	}
        	chain.doFilter(request, response);
        	return;
        }
        
        if (!isUserAuth) {
        	httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/logintest");
        	return;
        }
        
        chain.doFilter(request, response);	
	}
	
	
	private boolean checkIfUserIsAuth(HttpServletRequest httpRequest) {
		String jwtToken = Utils.getJwtTokenFromCookies(httpRequest);
		boolean isAuth = false;
		
		if (jwtToken != null) {
			try {
				final JwtValidator validator = new JwtValidator();
				
				DecodedJWT decodedToken = validator.validate(jwtToken);
				System.out.println("Jwt is valid");
				System.out.println(decodedToken);
				isAuth = true;
				
			} catch (InvalidParameterException e) {
				System.out.println("Jwt is invalid");
				e.printStackTrace();
			}
		}
		
		return isAuth;
	}
	
}
