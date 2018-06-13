package com.revature.resources;

import static com.revature.utils.LogUtil.logger;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.revature.entity.TfAssociate;
import com.revature.entity.TfUser;
import com.revature.services.AssociateService;
import com.revature.services.UserService;
import com.revature.utils.LogUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("users")
@Api(value = "users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

	@POST
	@Consumes("application/json")
	@ApiOperation(value = "Creates new Associate", notes = "Takes username, password, fname and lname to create new associate and user")
	public Response createNewAssociate(TfAssociate newAssociate) {
		logger.info("createNewAssociate()...");
		LogUtil.logger.info(newAssociate);
		AssociateService.createAssociate(newAssociate);
		return Response.created(URI.create("/testingURIcreate")).build();
	}

	/**
	 * Gets the user by the user's username
	 * 
	 * @param username
	 *            Username used to get the user
	 * @return Returns a TfUser json
	 */
	@GET
	@ApiOperation(value = "Gets user", notes = "Gets a specific user by their username.")
	@Path("/{username}")
	public Response getUser(@PathParam("username") String username) {
		logger.info("getUser()...");
		TfUser user = UserService.getUser(username);
		return Response.ok(user).build();
	}

	/**
	 * Method takes login information from front-end and verifies the information.
	 * If info is valid, a status code of 200 is returned, otherwise invalid token
	 *
	 * @param login
	 *            - contains login information
	 * @return a Response object with authentication data, such as username, JWT
	 *         token, and roleId
	 * @throws IOException
	 */

	@POST
	@ApiOperation(value = "login method", notes = "The method takes login inforation and verifies whether or not it is valid. returns 200 if valid, 400 if invalid.")
	@Path("login")
	public Response submitCredentials(TfUser loginuser) {
		logger.info("submitCredentials()...");
		logger.info("	login: " + loginuser);
		TfUser user = UserService.getUser(loginuser.getTfUserUsername());
		logger.info("	user: " + user);

		if (user != null) {
			return Response.status(200).entity(user).build();
		} else {
			return Response.status(Status.UNAUTHORIZED).build();
		}
	}
}
