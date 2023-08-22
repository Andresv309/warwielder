package com.adso.exceptions.auth;

public class NotValidAuthParams extends Exception {
	private static final long serialVersionUID = 632323668912632570L;

	public NotValidAuthParams () {
		super("Username and password params are required.");

	}
}
