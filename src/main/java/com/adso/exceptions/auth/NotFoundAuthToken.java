package com.adso.exceptions.auth;

public class NotFoundAuthToken extends Exception {
	private static final long serialVersionUID = -1933173492500469413L;

	public NotFoundAuthToken () {
		super("An authorization token is required for this operation.");

	}
}
