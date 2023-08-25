package com.adso.exceptions.app;

import com.adso.utils.CustomResponseError;

public class NotResultsToShowException extends CustomResponseException {
	private static final long serialVersionUID = 711075500133280751L;
	private static final int ERROR_CODE = 2957;

	public NotResultsToShowException (String cause) {
		 super(new CustomResponseError("No results were found.", ERROR_CODE, cause));
	}
	
}
