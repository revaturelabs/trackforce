package com.revature.services;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
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
		try {
			URI loginLocation = new URI(loginURL);
			TfUser tfUser = userDaoImpl.getUser(username);
			if (tfUser != null) {
				String hashedPassword = tfUser.getTfUserHashpassword();
				if (tfUser.equals(new TfUser()))
					return Response.seeOther(loginLocation).build();
				else if (PasswordStorage.verifyPassword(password, hashedPassword)) {
					final HttpSession session = request.getSession();
					if (session != null) {
						TfRole tfRole = tfUser.getTfRole();
						if (tfRole != null) {
							BigDecimal tfRoleId = tfRole.getTfRoleId();
							if (tfRoleId != null)
								session.setAttribute("roleid", tfRoleId);
						}
						String tfUserName = tfUser.getTfUserUsername();
						if (tfUserName != null)
							session.setAttribute("user", tfUserName);
					}
					URI homeLocation = new URI(homeURL);
					return Response.seeOther(homeLocation).build();
				} else
					return Response.seeOther(loginLocation).build();
			}
		} catch (URISyntaxException | CannotPerformOperationException | InvalidHashException e) {
			LogUtil.logger.error(e);
		}
		return Response.noContent().build();
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
