package com.revature.services;

import java.io.IOException;
import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import static com.revature.utils.LogUtil.logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.revature.dao.UserDAO;
import com.revature.dao.UserDaoImpl;
import com.revature.entity.TfRole;
import com.revature.entity.TfUser;
import com.revature.utils.HibernateUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

/**
 * 
 * @author Michael Tseng
 * 
 *         Service for creating and verifying JWT tokens Based on the tutorial
 *         found here: https://stormpath.com/blog/jwt-java-create-verify And
 *         resources from Sean Vaeth
 *
 */
public class JWTService {
	
	private static final String SECRET_KEY = getKey();
	private static Long EXPIRATION = 1000L;

	private SessionFactory sessionFactory;
	private UserDAO userDao;

    /**
     *
     * injectable dependencies for easier testing
     * @param userDao
     * @param sessionFactory
     */
	public JWTService(UserDAO userDao, SessionFactory sessionFactory) {
		this.userDao = userDao;
		this.sessionFactory = sessionFactory;
	}

	public JWTService() {
		this.sessionFactory = HibernateUtil.getSessionFactory();
        this.userDao = new UserDaoImpl();
	}

	/**
	 * Creates a token with an encoded username An expiration date has been set as
	 * well
	 * 
	 * @param username
	 * 
	 * @return the token
	 */
	public String createToken(String username) {

		SignatureAlgorithm signAlgorithm = SignatureAlgorithm.HS256;
		Key key = new SecretKeySpec(getSecret(), signAlgorithm.getJcaName());

		JwtBuilder token = Jwts.builder().setSubject(username).setExpiration(generateExpirationDate())
				.signWith(signAlgorithm, key);

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
		Date expiration = null;
		try {
			final Claims claims = getClaimsFromToken(token);
			if (claims != null) {
				expiration = claims.getExpiration();
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return expiration;
	}

	/**
	 * Gets the claims object from the token Needed verification purposes
	 * 
	 * @param token
	 * 
	 * @return Claims object, or null
	 */
	private Claims getClaimsFromToken(String token) {
		Claims claims = null;
		try {
			claims = Jwts.parser().setSigningKey(getSecret()).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			logger.error(e);
		}
		return claims;
	}

	/**
	 * Validates a token
	 * 
	 * @param token
	 * 
	 * @return true if the token is valid, otherwise false
	 * @throws IOException
	 *             because of the use of connection pools that requires some files
	 */
	public Boolean validateToken(String token) throws IOException {
		Claims claims = null;
		boolean verified = false;
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			String tokenUsername = null;
			TfUser tfUser = null;

			if (token == null) {
				return false;
			}

			try {
				claims = getClaimsFromToken(token);
				if (claims != null) {
					tokenUsername = claims.getSubject();
				}
				if (tokenUsername != null) {
					tfUser = userDao.getUser(tokenUsername);	
				}
				if (tfUser != null) {
					// makes sure the token is fresh and usernames are equal
					verified = (tfUser.getTfUserUsername().equals(tokenUsername) && !isTokenExpired(token));
				}

			} catch (SignatureException se) {
				logger.error(se);
			}

		} catch (Exception e) {
			logger.error(e);
			session.flush();
			tx.rollback();
		}
		return verified;
	}

	/**
	 * Gets the secret key from System environments, under the 'KEY' label Checks if
	 * the user is an admin
	 * 
	 * 
	 * @param token
	 * @return true if the user is an admin, otherwise false
	 * @throws IOException
	 *             because of the use of connection pools that requires some files
	 */
	public boolean isAdmin(String token) throws IOException {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			Claims claims = null;
			boolean verified = false;
			String tokenUsername = null;
			TfUser tfUser = null;
			TfRole tfRole = null;

			if (token == null) {
				return false;
			}

			claims = getClaimsFromToken(token);
			if (claims != null) {
				tokenUsername = claims.getSubject();
			}
			if (tokenUsername != null) {
				tfUser = userDao.getUser(tokenUsername);
			}
			if (tfUser != null) {
				tfRole = tfUser.getTfRole();
			}

			if (tfUser != null && tfRole != null) {
				// makes sure the token is fresh and usernames are equal
				// and user role is admin
				verified = (tfUser.getTfUserUsername().equals(tokenUsername) && !isTokenExpired(token)
						&& tfRole.getTfRoleName().equals("Admin"));
			}
			session.flush();
			tx.commit();
			return verified;
		} catch (Exception e) {
			session.flush();
			tx.rollback();
		} finally {
			session.close();
		}
		return false;
	}

	/**
	 * Checks if the user is an associate
	 * 
	 * 
	 * @param token
	 * @return true if the user is an associate, otherwise false
	 * @throws IOException
	 *             because of the use of connection pools that requires some files
	 */
	public boolean isAssociate(String token) throws IOException {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			Claims claims = null;
			boolean verified = false;
			String tokenUsername = null;
			TfUser tfUser = null;
			TfRole tfRole = null;

			if (token == null) {
				return false;
			}

			try {
				claims = getClaimsFromToken(token);
				if (claims != null) {
					tokenUsername = claims.getSubject();
				}
				if (tokenUsername != null) {
					tfUser = userDao.getUser(tokenUsername);
				}
				if (tfUser != null) {
					tfRole = tfUser.getTfRole();
				}
				if (tfUser != null && tfRole != null) {
					// makes sure the token is fresh and usernames are equal
					// and user role is an associate
					verified = (tfUser.getTfUserUsername().equals(tokenUsername) && !isTokenExpired(token)
							&& tfRole.getTfRoleName().equals("Associate"));
				}
			} catch (SignatureException se) {
				logger.error(se);
			}

			session.flush();
			tx.commit();
			return verified;

		} catch (Exception e) {
			session.flush();
			tx.rollback();
		} finally {
			session.close();
		}
		return false;
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
}
