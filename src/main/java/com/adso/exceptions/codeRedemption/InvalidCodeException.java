package com.adso.exceptions.codeRedemption;

import com.adso.exceptions.app.CustomResponseException;
import com.adso.utils.CustomResponseError;

public class InvalidCodeException extends CustomResponseException {
	private static final long serialVersionUID = -2804835098345311079L;
	private static final int ERROR_CODE = 9647;

	public InvalidCodeException (String cause) {
		 super(new CustomResponseError("Invalid code.", ERROR_CODE, cause));
	}
	
}
