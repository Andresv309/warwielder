package com.adso.apiServlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.adso.dao.DAOManagerImp;
import com.adso.dao.interfaces.AppDAO;
import com.adso.entities.Card;
import com.adso.enums.Race;
import com.adso.enums.Rarity;
import com.adso.exceptions.app.NotResultsToShowException;
import com.adso.utils.JsonResponseBuilder;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "appStoreItemsServlet", urlPatterns = "/api/v1/store") 
public class AppStoreItemsServlet extends HttpServlet {
	private static final long serialVersionUID = -3745889666211140088L;
	private AppDAO appDao;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		this.appDao = new DAOManagerImp().getAppDAO();
		super.init(config);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JsonResponseBuilder jsonBuilder  = JsonResponseBuilder.create();
		
		try {

			Map<String, Object> items = appDao.getStoreItems();
	        jsonBuilder.addField("data", items);

		} catch (IllegalArgumentException e) {
			jsonBuilder.addField("error", "Invalid query params format.");
		}

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonBuilder.build());
		
		
		
		
	}
	
	
}
