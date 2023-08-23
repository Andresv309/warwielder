package com.adso.apiServlets;

import java.io.IOException;

import com.adso.entities.Pet;
import com.adso.exceptions.app.AlreadyUnlockedItemException;
import com.adso.exceptions.app.NotEnoughCoinsException;
import com.adso.exceptions.app.NotFoundException;
import com.adso.exceptions.app.RequiredPayloadException;
import com.adso.exceptions.auth.NotFoundAuthToken;
import com.adso.exceptions.auth.NotValidAuthToken;
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
public class PurchasePetServlet extends HttpServlet {
	private static final long serialVersionUID = 7726071293096703687L;
	private PetsAcquisitionService petAcquisitionService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		this.petAcquisitionService = new PetsAcquisitionService();
		super.init(config);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JsonResponseBuilder jsonBuilder  = JsonResponseBuilder.create();

		try {
			Long userId = Utils.getUserIdFromCookies(request);
	        String jsonBody = Utils.stringifyJsonBody(request);
	        
	        // Parse the JSON data using Gson
	        JsonObject jsonObject = JsonParser.parseString(jsonBody).getAsJsonObject();
	        
	        if (!jsonObject.has("petId")) {
	        	throw new RequiredPayloadException("petId");
	        }

        	Long petId = jsonObject.get("petId").getAsLong();
        	Pet pet = new Pet();
        	pet.setId(petId);
        	
        	Pet petPurchased = petAcquisitionService.purchasePet(pet, userId);
        	jsonBuilder.addField("data", petPurchased);
        	
		} catch (
				NotFoundAuthToken |
				NotValidAuthToken
				e
			) {
				jsonBuilder.addField("error", e.getMessage());
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
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
	        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonBuilder.build());
	}
}
