package com.revature.services;

import java.io.IOException;
import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import com.revature.dao.UserDaoImpl;
import com.revature.entity.TfUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

/**
 * 
 * @author Michael Tseng
 * 
 * Service for creating and verifying JWT tokens
 * Based on the tutorial found here: https://stormpath.com/blog/jwt-java-create-verify
 * And resources from Sean Vaeth
 *
 */
public class JWTService {
	
	private static final String SECRET_KEY = getKey();
	private static Long EXPIRATION = 10L;
	
	/**
	 * Creates a token with an encoded username
	 * An expiration date has been set as well
	 * 
	 * @param username
	 * 
	 * @return the token
	 */
	public String createToken(String username) {
		
		SignatureAlgorithm signAlgorithm = SignatureAlgorithm.HS256;
		Key key = new SecretKeySpec(getSecret(), signAlgorithm.getJcaName());
		
		JwtBuilder token = Jwts.builder().setSubject(username).setExpiration(generateExpirationDate()).signWith(signAlgorithm,  key);
		
		return token.compact();
	}
	
	/**
	 * Creates the secret byte array needed for creating a SecretKeySpec
	 * 
	 * @return byte[]
	 */
	private byte[] getSecret() {
		String base64Key = DatatypeConverter.printBase64Binary(SECRET_KEY.getBytes());

		return DatatypeConverter.parseBase64Binary(base64Key);
	}
	
	/**
	 * Creates the expiration date for the JWT
	 * 
	 * @return expiration Date object
	 */
	private Date generateExpirationDate() {
		return new Date(System.currentTimeMillis() + EXPIRATION * 1000);
	}
	
	/**
	 * Check to see if token is expired
	 * 
	 * @param token
	 * 
	 * @return true if token is expired, otherwise false
	 */
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}
	
	/**
	 * Extracts the expiration date from the token
	 * 
	 * @param token
	 * 
	 * @return the Date if it exists, otherwise null
	 */
	public Date getExpirationDateFromToken(String token) {
		Date expiration;
		try {
			final Claims claims = getClaimsFromToken(token);
			expiration = claims.getExpiration();
		}
		catch(Exception e) {
			expiration = null;
		}
		return expiration;
	}
	
	/**
	 * Gets the claims object from the token
	 * Needed verification purposes
	 * 
	 * @param token
	 * 
	 * @return Claims object, or null
	 */
	private Claims getClaimsFromToken(String token) {
		Claims claims;
		try {
			claims = Jwts.parser().setSigningKey(getSecret()).parseClaimsJws(token).getBody();
		}
		catch(Exception e) {
			claims = null;
		}
		return claims;
	}
	
	/**
	 * Validates a token
	 * 
	 * @param token
	 * @param username
	 * 
	 * @return true if the token is valid, otherwise false
	 */
	public Boolean validateToken(String token) throws IOException {
		Claims claims = null;
		boolean verified = false;
		String tokenUsername = null;
		TfUser tfUser = null;
		UserDaoImpl udi = new UserDaoImpl();
		
		if(token == null) {
			return false;
		}
		
		try {
			claims = getClaimsFromToken(token);
			tokenUsername = claims.getSubject();
			tfUser = udi.getUser(tokenUsername);
			
			if(tfUser != null) {
				//makes sure the token is fresh and usernames are equal
				verified = (tfUser.getTfUserUsername().equals(tokenUsername) && !isTokenExpired(token));
			}
			
		}
		catch(SignatureException se) {
			se.printStackTrace();
		}
		
		return verified;
	}
	
	/**
	 * Gets the secret key from System environments,
	 * under the 'KEY' label 
	 * 
	 * @return key string
	 */
	public static String getKey() {
		String key = System.getenv("KEY");
		
		//in case, someone forgot to set their system environments
		if(key == null) {
			key = "trackforcekey";
		}
		System.out.println(key);
		return key;
	}
}
