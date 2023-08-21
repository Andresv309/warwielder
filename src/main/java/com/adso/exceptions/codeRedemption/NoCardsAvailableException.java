package com.adso.exceptions.codeRedemption;

public class NoCardsAvailableException extends Exception {
	private static final long serialVersionUID = 3680276894499343388L;

	public NoCardsAvailableException () {
		super("Not available cards to redeem");
	}
}
