package com.adso.apiServlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.adso.dao.DAOManagerImp;
import com.adso.dao.interfaces.UserDAO;
import com.adso.utils.Utils;

@WebServlet(name = "userDecksServlet", urlPatterns = "/api/v1/decks")
public class UserDecksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long userId = Utils.getUserIdFromCookies(request);
		String userDecks = "{}";
		
		if (userId != null) {
			try {
				UserDAO userDao = new DAOManagerImp().getUserDAO();
				String jsonResponse = userDao.getUserDecks(userId);
				if (jsonResponse != null) {
					userDecks = jsonResponse;
				}
			} catch (NumberFormatException e) {}
		}

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(userDecks);
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long userId = Utils.getUserIdFromCookies(request);
		String userDecks = "{}";
		
		if (userId != null) {
			try {
				UserDAO userDao = new DAOManagerImp().getUserDAO();
				String jsonResponse = userDao.getUserDecks(userId);
				if (jsonResponse != null) {
					userDecks = jsonResponse;
				}
			} catch (NumberFormatException e) {}
		}

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(userDecks);
	}

}