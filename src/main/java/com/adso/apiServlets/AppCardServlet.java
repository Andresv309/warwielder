package com.adso.apiServlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.adso.dao.DAOManagerImp;
import com.adso.dao.interfaces.DeckDAO;

@WebServlet(name = "appCardServlet", urlPatterns = "/api/v1/card/*") 
public class AppCardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURL().toString();
		String appCards = "{}";

		try {
			if (url != null && !url.isEmpty()) {
				String[] parts = url.split("/");
				String id = parts[parts.length - 1];
				
				DeckDAO deckDao = new DAOManagerImp().getDeckDAO();
				String jsonResponse = deckDao.getDecksForUser(Long.parseLong(id));
				if (jsonResponse != null) {
					appCards = jsonResponse;
				}

			}
		} catch (NumberFormatException e) {}

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(appCards);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
