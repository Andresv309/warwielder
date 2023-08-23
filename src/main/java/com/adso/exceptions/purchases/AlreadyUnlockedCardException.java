package com.adso.exceptions.purchases;

public class AlreadyUnlockedCardException extends Exception {
	private static final long serialVersionUID = 6422898931297980126L;

	public AlreadyUnlockedCardException () {
		super("User Already owns the card");

	}
}
