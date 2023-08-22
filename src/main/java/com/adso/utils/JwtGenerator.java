
package com.adso.utils;

import java.sql.Date;
import java.time.Instant;
import java.util.Map;
import java.util.UUID;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.algorithms.Algorithm;


public class JwtGenerator {
	
  private final String JWT_SECRET_KEY;
  private static final long EXPIRATION_TIME_SG = 60 * 60 * 24;
  
  public JwtGenerator() {

	  this.JWT_SECRET_KEY = "my-super-secret-key";
  }
  
  public String generateJwt(Map<String, String> payload) throws Exception{
	  
	  Builder tokenBuilder = JWT.create()
			  .withIssuer("com.adso")
			  .withClaim("jti", UUID.randomUUID().toString())
			  .withExpiresAt(Date.from(Instant.now().plusSeconds(EXPIRATION_TIME_SG)))
			  .withIssuedAt(Date.from(Instant.now()));
	  
	  payload.entrySet().forEach(action -> tokenBuilder.withClaim(action.getKey(), action.getValue()));
	  
	  return tokenBuilder.sign(Algorithm.HMAC256(JWT_SECRET_KEY));
	  
  }

}

