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
import com.adso.exceptions.app.NotFoundException;
import com.adso.exceptions.app.NotResultsToShowException;
import com.adso.exceptions.app.RequiredPayloadException;
import com.adso.exceptions.auth.NotAuthorizedException;
import com.adso.exceptions.codeRedemption.InvalidCodeException;
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
		JsonResponseBuilder jsonBuilder  = JsonResponseBuilder.create();

		try {
			Long userId = Utils.getUserIdFromCookies(request);
	        String jsonBody = Utils.stringifyJsonBody(request);
	        
	        // Parse the JSON data using Gson
	        JsonObject jsonObject = JsonParser.parseString(jsonBody).getAsJsonObject();
	        
	        if (!jsonObject.has("code")) {
	        	throw new RequiredPayloadException("code");
	        }

        	String code = jsonObject.get("code").getAsString();
        	
        	Map<String, Object> cardRedemptionInfo = codeRedemptionService.redeemCardFromCode(code, userId);
        	

			jsonBuilder.addField("data", cardRedemptionInfo.get("unlockedCard"));
			jsonBuilder.addField("messages", cardRedemptionInfo.get("messages"));

        } catch (
    		NotResultsToShowException |
    		NotFoundException |
    		RequiredPayloadException |
    		InvalidCodeException
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

