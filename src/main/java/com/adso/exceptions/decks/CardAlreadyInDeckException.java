package com.adso.exceptions.decks;

public class CardAlreadyInDeckException extends Exception {
	private static final long serialVersionUID = 5685371916338672935L;

	public CardAlreadyInDeckException () {
		super("The card is already in the deck.");
	}
}
