package com.adso.exceptions.auth;

public class NotValidCredentials extends Exception {
	private static final long serialVersionUID = 1242685112442835763L;

	public NotValidCredentials () {
		super("Incorrect username or password.");

	}
}
