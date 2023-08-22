package com.adso.exceptions.auth;

public class NotValidAuthToken extends Exception {
	private static final long serialVersionUID = 3858608523472872983L;

	public NotValidAuthToken () {
		super("The token passed is not valid.");

	}
}
