package com.adso.exceptions.codeRedemption;

public class NotValidCodeRedemptionParams extends Exception {
	private static final long serialVersionUID = -8725555123995014366L;

	public NotValidCodeRedemptionParams () {
		super("Code param is required.");

	}
}
