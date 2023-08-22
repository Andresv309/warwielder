package com.adso.exceptions.codeRedemption;

public class InvalidCodeException extends Exception {
	private static final long serialVersionUID = -2804835098345311079L;

	public InvalidCodeException (String code) {
		super("The code: (" + code + ") is not valid.");
	}
}
