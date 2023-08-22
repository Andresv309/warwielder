package com.adso.exceptions.cards;

public class NotFoundCardException extends Exception {
	private static final long serialVersionUID = -7378513325912449470L;

	public NotFoundCardException (Long cardId) {
		super("The card with the id: (" + cardId + ") was not found.");

	}
}
