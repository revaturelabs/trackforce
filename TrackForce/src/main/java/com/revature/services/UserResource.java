package com.revature.services;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.revature.dao.UserDaoImpl;
import com.revature.entity.TfRole;
import com.revature.entity.TfUser;
import com.revature.model.LoginJSON;
import com.revature.model.UserJSON;
import com.revature.utils.LogUtil;
import com.revature.utils.PasswordStorage;
import com.revature.utils.PasswordStorage.CannotPerformOperationException;
import com.revature.utils.PasswordStorage.InvalidHashException;

@Path("/user")
public class UserResource {

	UserDaoImpl userDaoImpl = new UserDaoImpl();
	private String loginURL = "/TrackForce/html/login.html";
	private String homeURL = "/TrackForce/html/index.html";

	/**
	 * Method takes login information from front-end and verifies the 
	 * information. If info is valid, a status code of 200 is returned,
	 * otherwise 400 for a bad request 
	 * 
	 * @param username
	 *            Entered user name from a form.
	 * @param password
	 *            Entered password from a form.
	 * 
	 * @return a Response object with authentication data,
	 * such as username, JWT token, and roleId
	 */
	@POST
	@Path("submit")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response submitCredentials(LoginJSON login) {
		String username = login.getUsername();
		String password = login.getPassword();
		UserJSON userjson = null;
		JWTService jwt = null;
		
		try {
			//Attempts to get the user from the database based on username
			TfUser tfUser = userDaoImpl.getUser(username);
			if (tfUser != null) {
				String hashedPassword = tfUser.getTfUserHashpassword();
				//If the user object is empty, the user is invalid
				if (tfUser.equals(new TfUser())) {
					return Response.status(400).build();
				}
				else if (PasswordStorage.verifyPassword(password, hashedPassword)) {
					TfRole tfRole = tfUser.getTfRole();
					userjson = new UserJSON();
					
					if(tfRole != null) {
						BigDecimal tfRoleId = tfRole.getTfRoleId();
						String tfUserName = tfUser.getTfUserUsername();
						//If the user have a valid role and username, a 200 can be returned
						if(tfRoleId != null && tfUserName != null) {
							//Sets the role id and username to the userjson object, which is set back to angular
							userjson.setTfRoleId(tfRoleId);
							userjson.setUsername(tfUserName);
							//Uses JWT service to create token
							jwt = new JWTService();
							userjson.setToken(jwt.createToken(tfUserName));
							
							return Response.status(200).entity(userjson).build();
						}
					}
				} 
			}
		}
		catch(CannotPerformOperationException | InvalidHashException e) {
			LogUtil.logger.error(e);
		}
		//Default return is 400 for a bad request
		return Response.status(400).build();
	}

	/**
	 * Invalidates the client's session and sends a redirect response if successful.
	 * 
	 * @param request
	 * @return Response to redirect to login page if successfully invalidates the
	 *         session.
	 */
	@POST
	@Path("logout")
	@Produces({ MediaType.TEXT_HTML })
	public Response logout(@Context HttpServletRequest request) {
		if (request != null) {
			HttpSession session = request.getSession();
			if (session != null) {
				session.invalidate();
				try {
					URI loginLocation = new URI(loginURL);
					return Response.temporaryRedirect(loginLocation).build();
				} catch (URISyntaxException e) {
					LogUtil.logger.error(e);
				}
			}
		}
		return Response.status(Response.Status.BAD_REQUEST).build();
	}

	@GET
	@Path("name")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response getUserName(@Context HttpServletRequest request) {
		final HttpSession session = request.getSession();
		if (session != null) {
			String userName = (String) session.getAttribute("user");
			if (userName != null)
				return Response.ok(userName).build();
		}
		return Response.ok("").build();
	}
}
