package com.adso.exceptions.user;

public class UsesNotOwnPetException extends Exception {
	private static final long serialVersionUID = 4423511566783875717L;

	public UsesNotOwnPetException () {
		super("User don't own the pet.");

	}
}
