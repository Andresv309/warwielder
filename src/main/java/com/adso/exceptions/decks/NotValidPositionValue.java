package com.adso.exceptions.decks;

public class NotValidPositionValue extends Exception {
	private static final long serialVersionUID = -7124530419938719040L;

	public NotValidPositionValue (int position) {
		super("Position: (" + position + ") for deck is not valid.");
	}
}
