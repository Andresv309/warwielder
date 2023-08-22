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
import com.adso.dao.interfaces.UserDAO;
import com.adso.entities.Card;
import com.adso.entities.Deck;
import com.adso.entities.DeckCard;
import com.adso.exceptions.auth.NotFoundAuthToken;
import com.adso.exceptions.auth.NotValidAuthToken;
import com.adso.exceptions.decks.CardAlreadyInDeckException;
import com.adso.exceptions.decks.NotValidDeckUpdateParams;
import com.adso.exceptions.decks.NotValidPositionValue;
import com.adso.exceptions.user.UserUnauthorizedForOperationException;
import com.adso.utils.JsonResponseBuilder;
import com.adso.utils.Utils;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@WebServlet(name = "userDecksCardsServlet", urlPatterns = "/api/v1/user/deckCards") 
public class UserDecksCardsServlet extends HttpServlet {
	private static final long serialVersionUID = 1957667027674850287L;
	private UserDAO userDao;

	@Override
	public void init(ServletConfig config) throws ServletException {
		this.userDao = new DAOManagerImp().getUserDAO();
		super.init(config);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JsonResponseBuilder jsonBuilder  = JsonResponseBuilder.create();
		
		try {
			Long userId = Utils.getUserIdFromCookies(request);
			List<Object> userDecksCards = userDao.getUserDecksCards(userId);
			
			jsonBuilder.addField("data", userDecksCards);
			
		} catch (NotFoundAuthToken | NotValidAuthToken e) {
			jsonBuilder.addField("error", e.getMessage());
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonBuilder.build());
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JsonResponseBuilder jsonBuilder  = JsonResponseBuilder.create();

		try {
			Long userId = Utils.getUserIdFromCookies(request);
			String jsonBody = Utils.stringifyJsonBody(request);
			
			JsonObject jsonObject = JsonParser.parseString(jsonBody).getAsJsonObject();
			DeckCard deckCard = new DeckCard();
	        Deck deck = new Deck();
	        Card card = new Card();
	        
			// Check if required parameters are passed.
	        if (!jsonObject.has("position") || !jsonObject.has("deckId")) {
	        	throw new NotValidDeckUpdateParams();
	        }
	        
	        // Required Parameters
	        Long deckId = jsonObject.get("deckId").getAsLong();
	        deck.setId(deckId);
	        
			int position = jsonObject.get("position").getAsInt();
	        
	        // Optional Parameters
	        if (jsonObject.has("cardId")) {
	        	Long cardId = jsonObject.get("cardId").getAsLong();
	        	card.setId(cardId);
	        }

			deckCard.setCard(card);
			deckCard.setDeck(deck);
			deckCard.setPosition(position);
			
			DeckCard deckCardUpdated = userDao.updateDeck(deckCard, userId);
			
			Map<String, Object> deckCardMap = new HashMap<>();
			deckCardMap.put("position", deckCardUpdated.getPosition());
			deckCardMap.put("card", deckCardUpdated.getCard() != null ? deckCardUpdated.getCard() : null);
			
			jsonBuilder.addField("data", deckCardMap);

		} catch (
			NotFoundAuthToken |
			NotValidAuthToken |
			NotValidDeckUpdateParams |
			NotValidPositionValue |
			CardAlreadyInDeckException
			e
		) {
			jsonBuilder.addField("error", e.getMessage());
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		} catch (NumberFormatException | UnsupportedOperationException e) {
			jsonBuilder.addField("error", "Invalid parameters values.");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		} catch (UserUnauthorizedForOperationException e) {
			jsonBuilder.addField("error", e.getMessage());
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonBuilder.build());
	}

}


