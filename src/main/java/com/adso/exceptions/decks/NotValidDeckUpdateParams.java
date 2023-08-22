package com.adso.exceptions.decks;

public class NotValidDeckUpdateParams extends Exception {
	private static final long serialVersionUID = 4555904918938441626L;

	public NotValidDeckUpdateParams () {
		super("Position and deckId params are required.");
	}
}
