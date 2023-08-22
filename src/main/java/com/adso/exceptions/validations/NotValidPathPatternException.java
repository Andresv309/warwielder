package com.adso.exceptions.validations;

public class NotValidPathPatternException extends Exception {
	private static final long serialVersionUID = 8004396283865594254L;

	public NotValidPathPatternException () {
		super("Invalid path pattern.");
	}
}
