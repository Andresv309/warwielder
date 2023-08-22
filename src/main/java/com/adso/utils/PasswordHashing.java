package com.adso.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordHashing {
	
    public static String hashPassword(String password) {
    	// Number of rounds to determine computational cost
        int saltRounds = 12;
        String salt = BCrypt.gensalt(saltRounds);
        return BCrypt.hashpw(password, salt);
    }

    public static boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
