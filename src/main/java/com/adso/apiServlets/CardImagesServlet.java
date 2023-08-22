package com.adso.apiServlets;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

import com.adso.exceptions.validations.NotValidPathPatternException;
import com.adso.utils.JsonResponseBuilder;
import com.adso.utils.Utils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "cardImagesServlet", urlPatterns = "/api/v1/cardImages/*") 
public class CardImagesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// List of allowed image extensions
	List<String> allowedExtensions = Arrays.asList("jpg", "jpeg", "png", "gif");

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JsonResponseBuilder jsonBuilder  = JsonResponseBuilder.create();

	    try {
			String pathInfo = Utils.extractPathInfoFromRequest(request);
			
		    // Get the real path of the image using the ServletContext, it converts a web application-relative path to a filesystem path.
		    String imagePath = getServletContext().getRealPath("/cardImages/") + pathInfo;
		    System.out.println(imagePath);
		    
		    // Extract the extension from the pathInfo
		    String requestedExtension = pathInfo.substring(pathInfo.lastIndexOf(".") + 1);
	    	
	    	
			// Validate the pathInfo to prevent directory traversal
			if (pathInfo.contains("..")) {
				throw new FileNotFoundException();
			}
			
			// Validate the requested extension against the allowed list
			if (!allowedExtensions.contains(requestedExtension.toLowerCase())) {
				throw new FileNotFoundException();
			}
	    	
	    	// Read the image file
	    	FileInputStream fis = new FileInputStream(imagePath);	
	    	
		    // Set the content type based on the image format
		    String contentType = getServletContext().getMimeType(imagePath);
		    response.setContentType(contentType);

		    // Get the output stream of the response
		    OutputStream out = response.getOutputStream();

		    // Write the image data to the output stream
		    byte[] buffer = new byte[1024];
		    int bytesRead;
		    while ((bytesRead = fis.read(buffer)) != -1) {
		        out.write(buffer, 0, bytesRead);
		    }

		    // Close the streams
		    fis.close();
		    out.close();
		    
		    return;
	    	
	    } catch (FileNotFoundException e) {
			jsonBuilder.addField("error", "Requested image doesn't exist.");
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);

	    } catch (NotValidPathPatternException e) {
			jsonBuilder.addField("error", e.getMessage());
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}

	    
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonBuilder.build());
	}

}

