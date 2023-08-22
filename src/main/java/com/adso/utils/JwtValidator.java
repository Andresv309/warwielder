package com.adso.utils;

import java.security.InvalidParameterException;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

public class JwtValidator {

	private final String JWT_SECRET_KEY;
    private static final Logger logger = LoggerFactory.getLogger(JwtValidator.class);
    private static final List<String> allowedIsses = Collections.singletonList("com.adso");

    public JwtValidator() {    	
  	  	this.JWT_SECRET_KEY = "my-super-secret-key";
    }
    
    
    /**
     * Validate a JWT token
     * @param token
     * @return decoded token
     */
    public DecodedJWT validate(String token) {
        try {
            final DecodedJWT jwt = JWT.decode(token);

            if (!allowedIsses.contains(jwt.getIssuer())) {
                throw new InvalidParameterException(String.format("Unknown Issuer %s", jwt.getIssuer()));
            }

            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET_KEY);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(jwt.getIssuer())
                    .build();

            // This verification will throw an error if not valid token.
            verifier.verify(token);
            return jwt;

        } catch (Exception e) {
            logger.error("Failed to validate JWT", e);
            throw new InvalidParameterException("JWT validation failed: " + e.getMessage());
        }
    }
}

