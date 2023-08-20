package com.adso.apiServlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.adso.dao.DAOManagerImp;
import com.adso.dao.interfaces.UserDAO;
import com.adso.utils.Utils;

@WebServlet(name = "userDecksCardsServlet", urlPatterns = "/api/v1/user/deckCards") 
public class UserDecksCardsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long userId = Utils.getUserIdFromCookies(request);
		String userUnlockedCards = "{}";
		
		if (userId != null) {
			try {
				UserDAO userDao = new DAOManagerImp().getUserDAO();
				String jsonResponse = userDao.getUserDecksCards(userId);
				if (jsonResponse != null) {
					userUnlockedCards = jsonResponse;
				}
			} catch (NumberFormatException e) {}
		}

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(userUnlockedCards);
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long userId = Utils.getUserIdFromCookies(request);
		String userUnlockedCards = "{}";
		
//		if (userId != null) {
			try {
		        // Read JSON data from the request body
		        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
		        StringBuilder jsonBodyBuilder = new StringBuilder();
		        String line;

		        while ((line = reader.readLine()) != null) {
		            jsonBodyBuilder.append(line);
		        }

		        String jsonBody = jsonBodyBuilder.toString();
				
				
				
				
				
				
				UserDAO userDao = new DAOManagerImp().getUserDAO();
				String jsonResponse = userDao.updateDeck(jsonBody, userId);
				if (jsonResponse != null) {
					userUnlockedCards = jsonResponse;
				}
			} catch (NumberFormatException e) {}
//		}

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(userUnlockedCards);
	}
	
	

}
