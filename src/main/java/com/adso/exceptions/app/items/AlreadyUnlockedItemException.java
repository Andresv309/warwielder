package com.adso.exceptions.app.items;

import com.adso.exceptions.app.CustomResponseException;
import com.adso.utils.CustomResponseError;

public class AlreadyUnlockedItemException extends CustomResponseException {
	private static final long serialVersionUID = -5668442241296267574L;
	private static final int ERROR_CODE = 5678;

	public AlreadyUnlockedItemException (String cause) {
		 super(new CustomResponseError("Resource already unlocked.", ERROR_CODE, cause));
	}
	
}
