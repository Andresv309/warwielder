package com.adso.exceptions.codeRedemption;

public class CodeAlreadyRedeemedException extends Exception {
	private static final long serialVersionUID = -4955594239395067234L;
	
	public CodeAlreadyRedeemedException (String code) {
		super("The code: (" + code + ") has already been redeem.");

	}
}
