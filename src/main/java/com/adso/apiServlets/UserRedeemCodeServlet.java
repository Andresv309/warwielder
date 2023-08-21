package com.adso.apiServlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.adso.utils.JsonResponseBuilder;
import com.adso.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.adso.entities.Card;
import com.adso.exceptions.codeRedemption.CodeAlreadyRedeemedException;
import com.adso.exceptions.codeRedemption.InvalidCodeException;
import com.adso.exceptions.codeRedemption.NoCardsAvailableException;
import com.adso.exceptions.user.UserNotFoundException;
import com.adso.services.CodeRedemptionService;

@WebServlet(name = "userRedeemCodeServlet", urlPatterns = "/api/v1/user/redeem") 
public class UserRedeemCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long userId = Utils.getUserIdFromCookies(request);
		JsonResponseBuilder jsonBuilder  = JsonResponseBuilder.create();

		try {
	        // Read JSON data from the request body
	        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
	        StringBuilder jsonBodyBuilder = new StringBuilder();
	        String line;

	        while ((line = reader.readLine()) != null) {
	            jsonBodyBuilder.append(line);
	        }

	        String jsonBody = jsonBodyBuilder.toString();
	        
	        // Parse the JSON data using Gson
	        JsonObject jsonObject = JsonParser.parseString(jsonBody).getAsJsonObject();

	        if (jsonObject.has("code")) {
	        	String code = jsonObject.get("code").getAsString();
	        	
	        	CodeRedemptionService CodeRedemptionService = new CodeRedemptionService();
				String cardRedemptionJson = CodeRedemptionService.redeemCardFromCode(code, userId);
				
				JsonObject cardRedemptionJsonObj = JsonParser.parseString(cardRedemptionJson).getAsJsonObject();
				jsonBuilder.addField("data", cardRedemptionJsonObj);
	        }

        } catch (
			InvalidCodeException |
			CodeAlreadyRedeemedException |
			NoCardsAvailableException |
			UserNotFoundException
			e
		) {
			jsonBuilder.addField("error", e.getMessage());
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonBuilder.build());
	}

}
