package com.adso.exceptions.user;

public class UserAlreadyExistsException extends Exception {
	private static final long serialVersionUID = -9007204291289724576L;

	public UserAlreadyExistsException (String username) {
		super("Username (" + username + ") already exists.");
	}
}
