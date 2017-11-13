package com.revature.services;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.revature.utils.PasswordStorage;
import com.revature.utils.PasswordStorage.CannotPerformOperationException;
import com.revature.utils.PasswordStorage.InvalidHashException;

@Path("/user")
public class UserResource {

	// UserDaoImpl userDaoImpl = new UserDaoImpl();

	/**
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	@POST
	@Path("submit")
	@Produces({ MediaType.TEXT_HTML })
	public Response submitCredentials(@FormParam("username") String username, @FormParam("password") String password) {
		System.out.println("made it");
		
		// String hashedPassword = userDaoImpl.getHashedPassword(username);
		String hashedPassword = "";

		try {
			//if (PasswordStorage.verifyPassword(password, hashedPassword)) {
				URI homeLocation = new URI("html/login.html");
				return Response.temporaryRedirect(homeLocation).build();
			//}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		return Response.noContent().build();
	}

}
