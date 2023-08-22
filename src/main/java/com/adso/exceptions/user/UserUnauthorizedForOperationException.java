package com.adso.exceptions.user;

public class UserUnauthorizedForOperationException extends Exception  {
	private static final long serialVersionUID = 5011671385380672326L;

	public UserUnauthorizedForOperationException (String operation) {
		super("User is not authorized for this operation: " + operation + ".");
	}
}
