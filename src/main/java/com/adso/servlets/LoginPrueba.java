package com.adso.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.adso.entities.User;
import com.adso.services.UserAuthenticationService;
import com.adso.utils.AuthCookieGenerator;
;

@WebServlet(name = "logintest", urlPatterns = "/logintest")
public class LoginPrueba extends HttpServlet {     
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Estoy en el loginPage\n");

		getServletContext().getRequestDispatcher("/pages/logintest.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		System.out.println("---------------------------");
		System.out.println(username);
		System.out.println(password);
		
		if (username.isBlank() || password.isBlank()) {
			request.setAttribute("error", "Hay campos faltantes");
			doGet(request, response);
		} else {
    		try {
			UserAuthenticationService userAuthenticationService = new UserAuthenticationService();
			User validatedUser = userAuthenticationService.validateUser(username, password);
			
	        if (validatedUser != null) {
	            System.out.println("Valid user!");
	            
		            
	    			Cookie cookie = new AuthCookieGenerator().generateAuthCookie(validatedUser);
	    			
		            response.addCookie(cookie);
					response.sendRedirect(request.getContextPath() + "/account");

	            
	            
	        } else {
	            System.out.println("Invalid user or incorrect password.");
	            request.setAttribute("error", "Usuario o contraseña incorrectos");
	            doGet(request, response);
	        }
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
	}

}
