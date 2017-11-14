package com.revature.services;

import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.revature.dao.UserDaoImpl;
import com.revature.entity.TfUser;
import com.revature.utils.PasswordStorage;
import com.revature.utils.PasswordStorage.CannotPerformOperationException;
import com.revature.utils.PasswordStorage.InvalidHashException;

@Path("/user")
public class UserResource {

	UserDaoImpl userDaoImpl = new UserDaoImpl();

	/**
	 * Returns a Response object with a redirect
	 * 
	 * @param username
	 *            Entered user name from a form.
	 * @param password
	 *            Entered password from a form.
	 * 
	 * @return
	 */
	@POST
	@Path("submit")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.TEXT_HTML })
	public Response submitCredentials(@FormParam("username") String username, @FormParam("password") String password,
			@Context HttpServletRequest request) {
		TfUser tfUser = userDaoImpl.getUser(username);
		if (tfUser != null) {
			String hashedPassword = tfUser.getTfUserHashpassword();
			try {
				if (tfUser.equals(new TfUser()))
					return Response.status(Response.Status.UNAUTHORIZED).build();
				else if (PasswordStorage.verifyPassword(password, hashedPassword)) {
					final HttpSession session = request.getSession();
					if (session != null) {
						session.setAttribute("roleid", tfUser.getTfRole().getTfRoleId());
						session.setAttribute("user", tfUser.getTfUserUsername());
					}
					System.out.println("Password verified");
					URI homeLocation = new URI("../../../../TrackForce/html/index.html");
					System.out.println("URI: " + homeLocation);
					System.out.println("username: " + username + ", password: " + password);
					System.out.println("User role name: " + tfUser.getTfRole().getTfRoleName());
					return Response.temporaryRedirect(homeLocation).build();
				} else
					return Response.status(Response.Status.UNAUTHORIZED).build();
			} catch (URISyntaxException | CannotPerformOperationException | InvalidHashException e) {
				e.printStackTrace();
			}
		}
		return Response.status(Response.Status.BAD_REQUEST).build();
	}

	/**
	 * Invalidates the client's session and sends a redirect response if successful.
	 * 
	 * @param request
	 * @return Response to redirect to login page if successfully invalidates the session.
	 */
	@POST
	@Path("logout")
	public Response logout(@Context HttpServletRequest request) {
		if (request != null) {
			HttpSession session = request.getSession();
			if (session != null) {
				session.invalidate();
				try {
					URI loginLocation = new URI("../../../../TrackForce/html/login.html");
					return Response.temporaryRedirect(loginLocation).build();
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}
			}
		}
		return Response.status(Response.Status.BAD_REQUEST).build();
	}
}
