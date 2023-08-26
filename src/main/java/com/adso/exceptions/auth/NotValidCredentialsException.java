package com.adso.exceptions.auth;

import com.adso.exceptions.app.CustomResponseException;
import com.adso.utils.CustomResponseError;

public class NotValidCredentialsException extends CustomResponseException {
	private static final long serialVersionUID = -7068578785518941402L;
	private static final int ERROR_CODE = 1234;

	public NotValidCredentialsException (String cause) {
		 super(new CustomResponseError("Invalid Credentials.", ERROR_CODE, cause));
	}
	
}
