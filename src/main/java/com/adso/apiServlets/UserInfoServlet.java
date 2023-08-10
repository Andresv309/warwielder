package com.adso.apiServlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.hibernate.exception.ConstraintViolationException;

import com.adso.dao.DAOManagerImp;
import com.adso.dao.interfaces.UserDAO;
import com.adso.entities.User;
import com.adso.utils.ExceptionToJsonError;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@WebServlet(name = "userInfoServlet", urlPatterns = "/api/v1/users/*")
public class UserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;     
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = request.getRequestURL().toString();
		String userDecks = "{}";

		try {
			if (url != null && !url.isEmpty()) {
				String[] parts = url.split("/");
				String id = parts[parts.length - 1];
				
				UserDAO userDao = new DAOManagerImp().getUserDAO();
				String jsonResponse = userDao.getUserInfo(Long.parseLong(id));
				if (jsonResponse != null) {
					userDecks = jsonResponse;
				}

			}
		} catch (NumberFormatException e) {}

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(userDecks);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
