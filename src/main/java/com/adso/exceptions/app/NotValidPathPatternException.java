package com.adso.exceptions.app;

import com.adso.utils.CustomResponseError;

public class NotValidPathPatternException extends CustomResponseException {
	private static final long serialVersionUID = 8004396283865594254L;
	private static final int ERROR_CODE = 8724;

	public NotValidPathPatternException (String cause) {
		 super(new CustomResponseError("Invalid path pattern.", ERROR_CODE, cause));
	}
}
