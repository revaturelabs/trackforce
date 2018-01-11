package com.revature.services;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import com.revature.model.UserJSON;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

public class JWTService {
	
	private static final String SECRET_KEY = "...";
	private static Long EXPIRATION = 10L;
	
	public String createToken(String username) {
		
		SignatureAlgorithm signAlgorithm = SignatureAlgorithm.HS256;
		Key key = new SecretKeySpec(getSecret(), signAlgorithm.getJcaName());
		
		JwtBuilder token = Jwts.builder().setSubject(username).setExpiration(generateExpirationDate()).signWith(signAlgorithm,  key);
		
		return token.compact();
	}
	
	private byte[] getSecret() {
		String base64Key = DatatypeConverter.printBase64Binary(SECRET_KEY.getBytes());

		return DatatypeConverter.parseBase64Binary(base64Key);
	}
	
	private Date generateExpirationDate() {
		return new Date(System.currentTimeMillis() + EXPIRATION * 1000);
	}
	
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}
	
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
	
	public Boolean validateToken(String token, String username) {
		Claims claims = null;
		boolean verified = false;
		String tokenUsername = null;
		
		if(token == null) {
			return false;
		}
		
		try {
			claims = getClaimsFromToken(token);
			tokenUsername = claims.getSubject();
			
			verified = (username.equals(tokenUsername) && !isTokenExpired(token));
			
		}
		catch(SignatureException se) {
			se.printStackTrace();
		}
		
		return verified;
	}
}
