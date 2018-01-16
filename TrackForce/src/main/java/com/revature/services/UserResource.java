package com.revature.services;

import java.io.IOException;
import java.math.BigDecimal;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.dao.UserDaoImpl;
import com.revature.entity.TfRole;
import com.revature.entity.TfUser;
import com.revature.model.LoginJSON;
import com.revature.model.UserJSON;
import com.revature.utils.HibernateUtil;
import com.revature.utils.LogUtil;
import com.revature.utils.PasswordStorage;

@Path("/user")
public class UserResource {

	UserDaoImpl userDaoImpl = new UserDaoImpl();

	/**
	 * Method takes login information from front-end and verifies the information.
	 * If info is valid, a status code of 200 is returned, otherwise 400 for a bad
	 * request
	 * 
	 * @param username
	 *            Entered user name from a form.
	 * @param password
	 *            Entered password from a form.
	 * 
	 * @return a Response object with authentication data, such as username, JWT
	 *         token, and roleId
	 * @throws IOException
	 */
	@POST
	@Path("submit")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response submitCredentials(LoginJSON login) throws IOException {
		String username = login.getUsername();
		String password = login.getPassword();
		UserJSON userjson = null;
		JWTService jwt = null;
		Session session = HibernateUtil.getSession().openSession();
		Transaction tx = session.beginTransaction();
		try {
			// Attempts to get the user from the database based on username
			TfUser tfUser = userDaoImpl.getUser(username);
			if (tfUser != null) {
				String hashedPassword = tfUser.getTfUserHashpassword();
				// If the user object is empty, the user is invalid
				if (tfUser.equals(new TfUser())) {
					return Response.status(400).build();
				} else if (PasswordStorage.verifyPassword(password, hashedPassword)) {
					TfRole tfRole = tfUser.getTfRole();
					userjson = new UserJSON();

					if (tfRole != null) {
						BigDecimal tfRoleId = tfRole.getTfRoleId();
						String tfUserName = tfUser.getTfUserUsername();
						// If the user have a valid role and username, a 200 can be returned
						if (tfRoleId != null && tfUserName != null) {
							// Sets the role id and username to the userjson object, which is set back to
							// angular
							userjson.setTfRoleId(tfRoleId);
							userjson.setUsername(tfUserName);
							// Uses JWT service to create token
							jwt = new JWTService();
							userjson.setToken(jwt.createToken(tfUserName));
							session.flush();
							tx.commit();
							return Response.status(200).entity(userjson).build();
						}
					}
				}
			}
		} catch (Exception e) {
			session.flush();
			tx.rollback();
			e.printStackTrace();
			LogUtil.logger.error(e);
			throw new IOException("Could not get associate", e);
		} finally {
			session.close();
		}
		// Default return is 400 for a bad request
		return Response.status(400).build();
	}
}
