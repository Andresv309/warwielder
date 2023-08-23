package com.adso.exceptions.app;

import com.adso.utils.CustomResponseError;

public class NotFoundException extends CustomResponseException {
	private static final long serialVersionUID = 1953544789285698359L;
	private static final int ERROR_CODE = 1234;

	public NotFoundException (String cause) {
		 super(new CustomResponseError("Resource not found.", ERROR_CODE, cause));
	}
	
}
