package com.adso.apiServlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
		
		try {
			Map<String, Object> queryParams = new HashMap<>();	        
	        String offsetAsString = request.getParameter("offset");
	        String limitAsString = request.getParameter("limit");
	        String rarityAsString = request.getParameter("rarity");
	        String raceAsString = request.getParameter("race");
	        
	        // Default values
	        int offset = 0;
	        int limit = 20;
	                
	        if (offsetAsString != null) {
		        offset = Integer.parseInt(offsetAsString);
	        }
	        
	        if (limitAsString != null) {
	        	limit =  Integer.parseInt(limitAsString);
	        	if (limit < 1 || 20 < limit) limit = 20;
	        }
	        
	        if (rarityAsString != null) {
	        	Rarity rarity = Rarity.valueOf(rarityAsString);	        	
	        	queryParams.put("rarity", rarity);
	        }
	        
	        if (raceAsString != null) {
	        	Race race = Race.valueOf(raceAsString);      	
	        	queryParams.put("race", race);
	        }
	        
	        queryParams.put("offset", offset);
	        queryParams.put("limit", limit);	       
			List<Card> appCards = appDao.getAppCards(queryParams);

			Map<String, Object> metadata = new HashMap<>();

			int currentPage = (int) Math.floor((double) offset / limit); // 0 for the first page
			int resultCount = appCards.size();
			
			
			if (resultCount <= 0) {
				throw new NotResultsToShowException("Cards");
			}
			
			
		    // Add next and previous page links if applicable
		    if (limit == resultCount) {
		        int nextPage = currentPage + 1;
		        String nextLink = "/api/v1/cards?offset=" + nextPage * limit + "&limit=" + limit;
		        metadata.put("nextPageLink", nextLink);
		    }

		    if (currentPage > 0) {
		        int prevPage = currentPage - 1;
		        String prevLink = "/api/v1/cards?offset=" + prevPage * limit + "&limit=" + limit;
		        metadata.put("prevPageLink", prevLink);
		    }

			
			metadata.put("currentPage", currentPage + 1);
			metadata.put("pageSize", Math.min(limit, resultCount));
			        
	        jsonBuilder.addField("data", appCards);
	        jsonBuilder.addField("metadata", metadata);

	        
		} catch (IllegalArgumentException e) {
			jsonBuilder.addField("error", "Invalid query params format.");
		} catch (NotResultsToShowException e) {
			jsonBuilder.addField("error", e.getCustomError());
		}

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonBuilder.build());
	}

}

