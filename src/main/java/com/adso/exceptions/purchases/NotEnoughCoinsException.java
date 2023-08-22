package com.adso.exceptions.purchases;

public class NotEnoughCoinsException extends Exception {
	private static final long serialVersionUID = -5754025853420060155L;

	public NotEnoughCoinsException (String item) {
		super("User doesn't have enough coins for (" + item + ").");

	}
}
