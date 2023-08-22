package com.adso.exceptions.routes;

public class InvalidPathException extends Exception {
	private static final long serialVersionUID = -4381153848891817676L;

	public InvalidPathException (String path) {
		super("The path: (" + path + ") is not valid.");

	}
}
