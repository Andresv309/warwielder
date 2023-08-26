package com.adso.exceptions.user;

import com.adso.exceptions.app.CustomResponseException;
import com.adso.utils.CustomResponseError;

public class UserAlreadyExistsException extends CustomResponseException {
	private static final long serialVersionUID = -9007204291289724576L;
	private static final int ERROR_CODE = 2584;
	
	public UserAlreadyExistsException (String cause) {
		 super(new CustomResponseError("Username already exists.", ERROR_CODE, cause));
	}
}
