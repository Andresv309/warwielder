package com.adso.apiServlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.adso.dao.DAOManagerImp;
import com.adso.dao.interfaces.UserDAO;
import com.adso.entities.User;
import com.adso.exceptions.auth.NotValidAuthParams;
import com.adso.exceptions.user.UserAlreadyExistsException;
import com.adso.utils.AuthCookieGenerator;
import com.adso.utils.JsonResponseBuilder;
import com.adso.utils.Utils;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@WebServlet(name = "userRegisterServlet", urlPatterns = "/api/v1/register") 
public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 450179035056830663L;
	private UserDAO userDao;

	@Override
	public void init(ServletConfig config) throws ServletException {
		this.userDao = new DAOManagerImp().getUserDAO();
		super.init(config);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JsonResponseBuilder jsonBuilder  = JsonResponseBuilder.create();
        String jsonBody = Utils.stringifyJsonBody(request);
        
        try {
	        JsonObject jsonObject = JsonParser.parseString(jsonBody).getAsJsonObject();
	        
	        if (!jsonObject.has("username") || !jsonObject.has("password")) {
	        	throw new NotValidAuthParams();
	        }
	        
			String username = jsonObject.get("username").getAsString();
			String password = jsonObject.get("password").getAsString();
	        
	        User userCreated = userDao.addNewUser(username, password);
	
			Cookie authCookie = new AuthCookieGenerator().generateAuthCookie(userCreated);
            response.addCookie(authCookie);
			response.sendRedirect(request.getContextPath() + "/account");

        } catch (UserAlreadyExistsException e) {
			jsonBuilder.addField("error", e.getMessage());
			response.setStatus(HttpServletResponse.SC_CONFLICT);
        } catch (NotValidAuthParams e) {
			jsonBuilder.addField("error", e.getMessage());
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonBuilder.build());
	}

}

