package com.adso.exceptions.purchases;

import com.adso.exceptions.app.CustomResponseException;
import com.adso.utils.CustomResponseError;

public class NotAllowedPurchaseException extends CustomResponseException {
	private static final long serialVersionUID = 1509863387419124563L;
	private static final int ERROR_CODE = 2485;

	public NotAllowedPurchaseException (String cause) {
		 super(new CustomResponseError("Item not allowed for purchase.", ERROR_CODE, cause));
	}
	
}
