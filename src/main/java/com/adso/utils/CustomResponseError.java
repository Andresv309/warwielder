package com.adso.utils;

public class CustomResponseError {
    private String message;
    private int code;
    private String cause;

    public CustomResponseError(String message, int code, String cause) {
        this.message = message;
        this.code = code;
        this.cause = cause;
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}
    
}

