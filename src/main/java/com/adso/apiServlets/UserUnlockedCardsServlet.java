package com.adso.apiServlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

import com.adso.dao.DAOManagerImp;
import com.adso.dao.interfaces.UserDAO;
import com.adso.entities.Card;
import com.adso.exceptions.auth.NotAuthorizedException;
import com.adso.utils.JsonResponseBuilder;
import com.adso.utils.Utils;

@WebServlet(name = "userUnlockedCardsServlet", urlPatterns = "/api/v1/user/cards") 
public class UserUnlockedCardsServlet extends HttpServlet {
	private static final long serialVersionUID = -3284280078992764211L;
	private UserDAO userDao;

	@Override
	public void init(ServletConfig config) throws ServletException {
		this.userDao = new DAOManagerImp().getUserDAO();
		super.init(config);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JsonResponseBuilder jsonBuilder  = JsonResponseBuilder.create();
		Long userId;
		try {
			userId = Utils.getUserIdFromCookies(request);
			
			Set<Card> unlockedCards = userDao.getUserUnlockedCards(userId);
			jsonBuilder.addField("data", unlockedCards);

		} catch (NotAuthorizedException e) {
			jsonBuilder.addField("error", e.getCustomError());
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
		

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonBuilder.build());
		
	}
}

