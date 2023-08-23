package com.adso.exceptions.app;

import com.adso.utils.CustomResponseError;

public class CustomResponseException extends Exception {
	private static final long serialVersionUID = -8387318452823115827L;
	private final CustomResponseError customResponseError;

    public CustomResponseException(CustomResponseError customResponseError) {
        super(customResponseError.getMessage() + " " + customResponseError.getCause());
        this.customResponseError = customResponseError;
    }

    public CustomResponseError getCustomError() {
        return customResponseError;
    }
}
