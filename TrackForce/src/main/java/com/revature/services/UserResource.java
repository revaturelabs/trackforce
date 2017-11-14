package com.revature.services;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
	public Response submitCredentials(@FormParam("username") String username, @FormParam("password") String password) {
		TfUser tfUser = userDaoImpl.getUser(username);
		String hashedPassword = tfUser.getTfUserHashpassword();
		try {
			if (PasswordStorage.verifyPassword(password, hashedPassword)) {
				System.out.println("Password verified");
				//URI homeLocation = new URI("html/index.html");
				URI homeLocation = new URI("../../../../TrackForce/html/index.html");
				System.out.println("URI: " + homeLocation);
				System.out.println("username: " + username + ", password: " + password);
				System.out.println("User role ID: " + tfUser.getTfRole().getTfRoleName());
				return Response.temporaryRedirect(homeLocation).build();
			}
		} catch (URISyntaxException | CannotPerformOperationException | InvalidHashException e) {
			e.printStackTrace();
		}
		return Response.noContent().build();
	}

}
