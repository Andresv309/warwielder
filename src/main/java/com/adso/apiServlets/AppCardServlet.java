package com.adso.apiServlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.adso.dao.DAOManagerImp;
import com.adso.dao.interfaces.AppDAO;
import com.adso.entities.Card;
import com.adso.exceptions.cards.NotFoundCardException;
import com.adso.exceptions.validations.NotValidPathPatternException;
import com.adso.utils.JsonResponseBuilder;
import com.adso.utils.Utils;

@WebServlet(name = "appCardServlet", urlPatterns = "/api/v1/cards/*") 
public class AppCardServlet extends HttpServlet {
	private static final long serialVersionUID = -8402341218471453441L;
	private AppDAO appDao;

	@Override
	public void init(ServletConfig config) throws ServletException {
		this.appDao = new DAOManagerImp().getAppDAO();
		super.init(config);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JsonResponseBuilder jsonBuilder  = JsonResponseBuilder.create();

		try {			
			String pathInfo = Utils.extractPathInfoFromRequest(request);
			Long cardId = Long.parseLong(pathInfo);
			Card cardFound = appDao.getAppCard(cardId);
			
			jsonBuilder.addField("data", cardFound);

		} catch (NumberFormatException e) {
			jsonBuilder.addField("error", "Invalid card ID format.");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		} catch (NotFoundCardException e) {
			jsonBuilder.addField("error", e.getMessage());
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		} catch (NotValidPathPatternException e) {
			jsonBuilder.addField("error", e.getMessage());
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonBuilder.build());
	}

}

