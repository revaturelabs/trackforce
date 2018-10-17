package com.revature.services;
import static com.revature.utils.LogUtil.logger;
import java.io.IOException;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import org.json.JSONObject;
import com.revature.entity.TfUser;
import gherkin.lexer.Da;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

/**
 * @author Michael Tseng
 * @editors Adam L. 
 *       Service for creating and verifying JWT tokens Based on the tutorial
 *       found here: https://stormpath.com/blog/jwt-java-create-verify And
 *       resources from Sean Vaeth
 * @version v6.18.06.13
 *		 Note: made minor updates to allow continued use of these tokens */
public class JWTService {
	
	UserService userService;
	
	private static final String SECRET_KEY = getKey();
	private static Long EXPIRATION = 30L; //expiration time in minutes

	/**
	 * Validates a token
	 * 
	 * @param token
	 * 
	 * @return true if the token is valid, otherwise false
	 * @throws IOException
	 *             because of the use of connection pools that requires some files
	 */
	public Boolean validateToken(String token) {
		String tokenUsername = null;
		TfUser tfUser = null;
		Claims claims = null;
		boolean verified = false;

		if (token != null) {
			claims = processToken(token);
		}
		if (claims != null) {
			tokenUsername = claims.getSubject();
		}
		if (tokenUsername != null) {
			tfUser = userService.getUser(tokenUsername);
		}
		if (tfUser != null) {
			// makes sure the token is fresh and usernames are equal
			verified = (!isTokenExpired(token) && tfUser.getUsername().equals(tokenUsername));
		}

		return verified;
	}

	/**
	 * Creates a token with an encoded username An expiration date has been set as
	 * well
	 * 
	 * @param username
	 * 
	 * @return the token
	 */
	public static String createToken(String username, int tfroleid) {
		SignatureAlgorithm signAlgorithm = SignatureAlgorithm.HS256;
		Key key = new SecretKeySpec(getSecret(), signAlgorithm.getJcaName());

		JwtBuilder token = Jwts.builder().setSubject(username)
				.claim("roleID" , "" + tfroleid)
				.setExpiration(generateExpirationDate()).signWith(signAlgorithm, key);

		return token.compact();
	}

	/**
	 * @author Ian Buitrago
	 * @param token
	 * @return the payload ie the user name and roleID, or null if token invalid
	 */
	public static Claims processToken(String token) {
		Claims payload = null;

		try {
			if (token == null) {
				throw new UnsupportedJwtException("token null");
			}
			payload = Jwts.parser().setSigningKey(getSecret()).parseClaimsJws(token).getBody();
			
		}catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException
		
				| IllegalArgumentException | NullPointerException e) {

//			e.printStackTrace();
			logger.info("Invalid token.");

		}

		return payload;
	}

	/**
	 * Extracts the expiration date from the token
	 * 
	 * @param token
	 * 
	 * @return the Date if it exists, otherwise null
	 */
	public static Date getExpirationDateFromToken(String token) {
		Date expiration = null;
		
		try {
			final Claims claims = processToken(token);
			if (claims != null) {
				expiration = claims.getExpiration();
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return expiration;
	}

	/**
	 * Gets the secret key from System environments, under the 'KEY' label
	 * 
	 * @return key string
	 */
	public static String getKey() {
		String key = System.getenv("KEY");

		// in case, someone forgot to set their system environments
		// this will be the default key
		if (key == null) {
			key = "trackforcekey";
		}

		return key;
	}

	/**
	 * Creates the secret byte array needed for creating a SecretKeySpec
	 * 
	 * @return byte[]
	 */
	private static byte[] getSecret() {
		String base64Key = DatatypeConverter.printBase64Binary(SECRET_KEY.getBytes());

		return DatatypeConverter.parseBase64Binary(base64Key);
	}

	/**
	 * Creates the expiration date for the JWT
	 * 
	 * @return expiration Date object
	 */
	private static Date generateExpirationDate() {
		return new Date(System.currentTimeMillis() + EXPIRATION * 1000 * 60);
	}

	/**
	 * Check to see if token is expired
	 * 
	 * @param token
	 * 
	 * @return true if token is expired, otherwise false
	 */
	private static Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	/**
	 * @author 1806_Austin_Molina
	 * Decodes an expired token to determine the expiration time. 
	 * The {@link #processToken(String)} method will throw an 
	 * exception when attempting to decode a web token.
	 * 
	 * @param token
	 * @return expiration time of token in milliseconds
	 */
	private static long getExpiredTokenTime(String token) {

		if(token == null)
			return -1L;
		
		Base64.Decoder decoder = Base64.getUrlDecoder();

		String[] parts = token.split("\\."); // Splitting header, payload and signature
		
		if(parts.length < 2)
			return -1;
		
		JSONObject payload = new JSONObject(new String(decoder.decode(parts[1])));

		long exp = payload.getLong("exp");

		return exp*1000;
	}
	
	/**
	 * @author 1806_Austin_Molina
	 * 
	 * all 401 errors are expected to include the nature of the error.
	 * The front end handles errors differently depending on how old 
	 * the token is.
	 * 
	 * This method creates a JSON object to include in the response body
	 * which includes how long ago a JWT expired.
	 * 
	 * @param token
	 * @return -1 when the token was invalid or did not exist; 
	 * 	else number of minutes since the token expired
	 */
	public static String invalidTokenBody(String token) {
		
		JSONObject body = new JSONObject();
		body.put("status", "Unauthorized");
		
		final long expiration = getExpiredTokenTime(token);
		
		Date now = new Date();
		
		if(expiration == -1) {
			//arbitrary response; number of minutes in a day
			body.put("expirationtime", -1);
			return body.toString();
		}
		
		long diffMillies = now.getTime() - expiration;
		long diffMinutes = TimeUnit.MINUTES.convert(diffMillies, TimeUnit.MILLISECONDS);		
		
		body.put("expirationtime", diffMinutes);
		
		return body.toString();
	}
}