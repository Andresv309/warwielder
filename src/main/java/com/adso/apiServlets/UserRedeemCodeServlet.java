package com.adso.apiServlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

import com.google.gson.JsonParser;
import com.google.gson.JsonObject;

import com.adso.utils.JsonResponseBuilder;
import com.adso.utils.Utils;
import com.adso.dao.DAOManagerImp;
import com.adso.dao.interfaces.AppDAO;
import com.adso.exceptions.auth.NotFoundAuthToken;
import com.adso.exceptions.auth.NotValidAuthToken;
import com.adso.exceptions.codeRedemption.CodeAlreadyRedeemedException;
import com.adso.exceptions.codeRedemption.InvalidCodeException;
import com.adso.exceptions.codeRedemption.NoCardsAvailableException;
import com.adso.exceptions.codeRedemption.NotValidCodeRedemptionParams;
import com.adso.exceptions.user.UserNotFoundException;
import com.adso.services.CodeRedemptionService;

@WebServlet(name = "userRedeemCodeServlet", urlPatterns = "/api/v1/user/redeem") 
public class UserRedeemCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1903741107061643427L;
	private CodeRedemptionService codeRedemptionService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		this.codeRedemptionService = new CodeRedemptionService();
		super.init(config);
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long userId;
		JsonResponseBuilder jsonBuilder  = JsonResponseBuilder.create();

		try {
			userId = Utils.getUserIdFromCookies(request);
	        String jsonBody = Utils.stringifyJsonBody(request);
	        
	        // Parse the JSON data using Gson
	        JsonObject jsonObject = JsonParser.parseString(jsonBody).getAsJsonObject();
	        
	        if (!jsonObject.has("code")) {
	        	throw new NotValidCodeRedemptionParams();
	        }

        	String code = jsonObject.get("code").getAsString();
        	
        	Map<String, Object> cardRedemptionInfo = codeRedemptionService.redeemCardFromCode(code, userId);
        	

			jsonBuilder.addField("data", cardRedemptionInfo.get("unlockedCard"));
			jsonBuilder.addField("messages", cardRedemptionInfo.get("messages"));


        } catch (
			InvalidCodeException |
			UserNotFoundException |
			NotFoundAuthToken |
			NotValidAuthToken |
			NotValidCodeRedemptionParams
			e
		) {
			jsonBuilder.addField("error", e.getMessage());
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } catch (NoCardsAvailableException | CodeAlreadyRedeemedException e) {
        	jsonBuilder.addField("error", e.getMessage());
        	response.setStatus(HttpServletResponse.SC_CONFLICT);
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonBuilder.build());
	}

}
