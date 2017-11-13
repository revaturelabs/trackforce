package com.revature.services;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.revature.utils.PasswordStorage;

@Path("/user")
public class UserResource {

	UserDaoImpl userDaoImpl = new UserDaoImpl();

	@POST
	@Path("submit")
	public Response submitCredentials(@FormParam("username") String username, @FormParam("password") String password) {
		String hashedPassword = userDaoImpl.getHashedPassword(username);
		if (PasswordStorage.verifyPassword(password, hashedPassword)) {
			try {
				URI homeLocation = new URI("home location");
				return Response.temporaryRedirect(homeLocation).build();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}
		return Response.noContent().build();
	}

}
