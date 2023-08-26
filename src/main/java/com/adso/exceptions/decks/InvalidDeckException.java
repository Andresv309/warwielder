package com.adso.exceptions.decks;

import com.adso.exceptions.app.CustomResponseException;
import com.adso.utils.CustomResponseError;

public class InvalidDeckException extends CustomResponseException{
	private static final long serialVersionUID = -1509029132647283029L;
	private static final int ERROR_CODE = 9357;

	public InvalidDeckException (String cause) {
		 super(new CustomResponseError("Deck definition is invalid.", ERROR_CODE, cause));
	}
	
}
