package com.revature.services;

import java.net.URI;
import java.net.URISyntaxException;

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
	@Produces({ MediaType.TEXT_HTML })
	public Response submitCredentials(@FormParam("username") String username, @FormParam("password") String password) {
		TfUser tfUser = userDaoImpl.getUser(username);
		String hashedPassword = tfUser.getTfHashpassword();
		try {
			if (PasswordStorage.verifyPassword(password, hashedPassword)) {
				URI homeLocation = new URI("html/home.html");
				return Response.temporaryRedirect(homeLocation).build();
			}
		} catch (URISyntaxException | CannotPerformOperationException | InvalidHashException e) {
			e.printStackTrace();
		}
		return Response.noContent().build();
	}

}
