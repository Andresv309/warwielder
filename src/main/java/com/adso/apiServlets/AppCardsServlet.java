package com.adso.apiServlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.adso.dao.DAOManagerImp;
import com.adso.dao.interfaces.AppDAO;
import com.adso.entities.Card;
import com.adso.utils.JsonResponseBuilder;

@WebServlet(name = "appCardsServlet", urlPatterns = "/api/v1/cards") 
public class AppCardsServlet extends HttpServlet {
	private static final long serialVersionUID = 8908987536957011242L;
	private AppDAO appDao;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		this.appDao = new DAOManagerImp().getAppDAO();
		super.init(config);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JsonResponseBuilder jsonBuilder  = JsonResponseBuilder.create();
		List<Card> appCards = appDao.getAppCards();
		jsonBuilder.addField("data", appCards);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonBuilder.build());
	}

}
