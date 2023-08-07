
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






//package com.adso.utils;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.JWTVerifier;
//import com.auth0.jwt.exceptions.JWTVerificationException;
//import com.auth0.jwt.interfaces.DecodedJWT;
//
//import java.util.Date;
//
//public class JwtGenerator {
//
//    private static final String SECRET_KEY = "your-secret-key"; // Replace with a strong secret key
//    private static final long EXPIRATION_TIME_MS = 86400000; // Token validity time in milliseconds (e.g., 24 hours)
//
//    public static String generateToken(String subject) {
//        Date now = new Date();
//        Date expirationDate = new Date(now.getTime() + EXPIRATION_TIME_MS);
//
//        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
//        return JWT.create()
//                .withSubject(subject)
//                .withIssuedAt(now)
//                .withExpiresAt(expirationDate)
//                .sign(algorithm);
//    }
//
//    public static boolean validateToken(String token) {
//        try {
//            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
//            JWTVerifier verifier = JWT.require(algorithm).build();
//            DecodedJWT jwt = verifier.verify(token);
//            return true;
//        } catch (JWTVerificationException e) {
//            return false;
//        }
//    }
//
//    public static String extractUsernameFromToken(String token) {
//        try {
//            DecodedJWT jwt = JWT.decode(token);
//            return jwt.getSubject();
//        } catch (JWTVerificationException e) {
//            return null;
//        }
//    }
//}
