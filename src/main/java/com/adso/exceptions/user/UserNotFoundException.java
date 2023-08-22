package com.adso.exceptions.user;

public class UserNotFoundException extends Exception {
	private static final long serialVersionUID = -4736332441014256747L;

	public UserNotFoundException () {
		super("User doesn't exists.");
	}
}
