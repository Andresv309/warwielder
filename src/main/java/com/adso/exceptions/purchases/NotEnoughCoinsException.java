package com.adso.exceptions.purchases;

import com.adso.exceptions.app.CustomResponseException;
import com.adso.utils.CustomResponseError;

public class NotEnoughCoinsException extends CustomResponseException {
	private static final long serialVersionUID = 6102674465644620789L;
	private static final int ERROR_CODE = 1234;

	public NotEnoughCoinsException (String cause) {
		 super(new CustomResponseError("Not enough coins for operation.", ERROR_CODE, cause));
	}
	
}
