package com.adso.apiServlets;

import java.io.IOException;

import com.adso.dao.DAOManagerImp;
import com.adso.dao.interfaces.UserDAO;
import com.adso.entities.Pet;
import com.adso.entities.User;
import com.adso.exceptions.app.NotFoundException;
import com.adso.exceptions.app.RequiredPayloadException;
import com.adso.exceptions.auth.NotAuthorizedException;
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

@WebServlet(name = "userServlet", urlPatterns = "/api/v1/user/pet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 4981069403773795626L;
	private UserDAO userDao;

	@Override
	public void init(ServletConfig config) throws ServletException {
		this.userDao = new DAOManagerImp().getUserDAO();
		super.init(config);
	}
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        	
        	User userUpdated = userDao.updateUserInfo(pet, userId);
        	User userToSend = new User();
        	userToSend.setSelectedPet(userUpdated.getSelectedPet());
        	userToSend.setCoins(userUpdated.getCoins());
        	userToSend.setId(userUpdated.getId());
        	userToSend.setUsername(userUpdated.getUsername());

			jsonBuilder.addField("data", userToSend);

        } catch (NumberFormatException e) {
        	jsonBuilder.addField("error", "Invalid pet ID format.");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } catch (
    		RequiredPayloadException |
    		NotFoundException    		
    		e
		) {
        	jsonBuilder.addField("error", e.getCustomError());
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
