package com.adso.exceptions.auth;

import com.adso.exceptions.app.CustomResponseException;
import com.adso.utils.CustomResponseError;

public class NotAuthorizedException extends CustomResponseException {
	private static final long serialVersionUID = -6019724527836511463L;
	private static final int ERROR_CODE = 7548;

	public NotAuthorizedException (String cause) {
		 super(new CustomResponseError("User is not authorized for this operation.", ERROR_CODE, cause));
	}
}
