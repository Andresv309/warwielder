package com.adso.utils;

import io.github.thibaultmeyer.cuid.CUID;

public class CuidGenerator {
	
	public static String generate () {
		final int customLength = 12;  
		final CUID cuid = CUID.randomCUID2(customLength);
		return cuid.toString();
	}

}

