package com.adso.apiServlets;

import java.io.IOException;

import com.adso.entities.Card;
import com.adso.entities.Pet;
import com.adso.exceptions.app.NotFoundException;
import com.adso.exceptions.app.NotResultsToShowException;
import com.adso.exceptions.app.RequiredPayloadException;
import com.adso.exceptions.app.items.AlreadyUnlockedItemException;
import com.adso.exceptions.auth.NotAuthorizedException;
import com.adso.exceptions.purchases.NotAllowedPurchaseException;
import com.adso.exceptions.purchases.NotEnoughCoinsException;
import com.adso.services.CardAcquisitionService;
import com.adso.services.PetsAcquisitionService;
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

@WebServlet(name = "purchasePetServlet", urlPatterns = "/api/v1/user/purchase/pet") 
public class PurchaseItemServlet extends HttpServlet {
	private static final long serialVersionUID = -2700672559692607306L;
	private PetsAcquisitionService petAcquisitionService;
	private CardAcquisitionService cardAcquisitionService;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		this.petAcquisitionService = new PetsAcquisitionService();
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
	        
	        if (!jsonObject.has("cardId") && !jsonObject.has("petId")) {
	        	throw new RequiredPayloadException("cardId or petId");
	        }
	        
	        Object purchaseItem = null;
	        
	        if (jsonObject.has("cardId") ) {
	        	Long cardId = jsonObject.get("cardId").getAsLong();
	        	Card card = new Card();
	        	card.setId(cardId);
	        	purchaseItem = cardAcquisitionService.purchaseCard(card, userId);
	        	
	        } else if (jsonObject.has("petId")) {
	        	Long petId = jsonObject.get("petId").getAsLong();
	        	Pet pet = new Pet();
	        	pet.setId(petId);
	        	purchaseItem = petAcquisitionService.purchasePet(pet, userId);
	        }

        	
        	jsonBuilder.addField("data", purchaseItem);
        	
        } catch (
			NotEnoughCoinsException |
			AlreadyUnlockedItemException |
			RequiredPayloadException |
			NotFoundException |
			NotAllowedPurchaseException |
			NotResultsToShowException
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
