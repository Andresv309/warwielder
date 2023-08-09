package com.adso.apiServlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.adso.dao.DAOManagerImp;
import com.adso.dao.interfaces.AppDAO;

@WebServlet(name = "userUnlockedCardsServlet", urlPatterns = "/api/v1/user/unlockedCards/*") 
public class UserUnlockedCardsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String appPets = "{}";

		try {
			AppDAO appDao = new DAOManagerImp().getAppDAO();
			String jsonResponse = appDao.getAppPets();
			if (jsonResponse != null) {
				appPets = jsonResponse;
				
			}
		} catch (NumberFormatException e) {}

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(appPets);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
