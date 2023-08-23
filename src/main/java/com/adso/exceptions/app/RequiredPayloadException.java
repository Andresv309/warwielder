package com.adso.exceptions.app;

import com.adso.utils.CustomResponseError;

public class RequiredPayloadException extends CustomResponseException {
	private static final long serialVersionUID = -3150790033908705129L;
	private static final int ERROR_CODE = 9112;

	public RequiredPayloadException (String cause) {
		 super(new CustomResponseError("Payload required for operation.", ERROR_CODE, cause));
	}
}
