package com.adso.apiServlets;

import java.io.IOException;

import com.adso.entities.Card;
import com.adso.exceptions.app.NotFoundException;
import com.adso.exceptions.app.RequiredPayloadException;
import com.adso.exceptions.app.items.AlreadyUnlockedItemException;
import com.adso.exceptions.auth.NotAuthorizedException;
import com.adso.exceptions.purchases.NotEnoughCoinsException;
import com.adso.services.CardAcquisitionService;
import com.adso.utils.JsonResponseBuilder;
import com.adso.utils.Utils;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "purchaseCardServlet", urlPatterns = "/api/v1/user/purchase/card") 
public class PurchaseCardServlet extends HttpServlet {
	private static final long serialVersionUID = -6487452060200253168L;
	private CardAcquisitionService cardAcquisitionService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		this.cardAcquisitionService = new CardAcquisitionService();
		super.init(config);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JsonResponseBuilder jsonBuilder  = JsonResponseBuilder.create();

		try {
			Long userId = Utils.getUserIdFromCookies(request);
	        String jsonBody = Utils.stringifyJsonBody(request);
	        
	        // Parse the JSON data using Gson
	        JsonObject jsonObject = JsonParser.parseString(jsonBody).getAsJsonObject();
	        
	        if (!jsonObject.has("cardId")) {
	        	throw new RequiredPayloadException("cardId");
	        }

        	Long cardId = jsonObject.get("cardId").getAsLong();
        	Card card = new Card();
        	card.setId(cardId);
        	
        	Card cardPurchased = cardAcquisitionService.purchaseCard(card, userId);
        	jsonBuilder.addField("data", cardPurchased);
        	
        } catch (NotFoundException e) {
			jsonBuilder.addField("error", e.getCustomError());
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } catch (
			NotEnoughCoinsException |
			AlreadyUnlockedItemException |
			RequiredPayloadException
			e
        ) {
			jsonBuilder.addField("error", e.getCustomError());
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } catch (NumberFormatException e) {
			jsonBuilder.addField("error", "Invalid payload values.");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } catch (NotAuthorizedException e) {
			jsonBuilder.addField("error", e.getCustomError());
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonBuilder.build());
	}
}
