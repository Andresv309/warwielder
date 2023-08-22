package com.adso.exceptions.user;

public class NotValidUserParamsException extends Exception {
	private static final long serialVersionUID = 8619740179339986196L;

	public NotValidUserParamsException () {
		super("PetId param is required.");

	}
}
