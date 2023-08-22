package com.adso.exceptions.pets;

public class NotFoundPetException extends Exception {
	private static final long serialVersionUID = -363642315908673494L;

	public NotFoundPetException (Long petId) {
		super("The pet with the id: (" + petId + ") was not found.");

	}
}
