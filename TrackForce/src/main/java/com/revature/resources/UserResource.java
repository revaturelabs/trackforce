package com.revature.resources;

import java.io.IOException;
import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.revature.entity.TfUser;
import com.revature.model.LoginJSON;
import com.revature.model.UserJSON;
import com.revature.request.model.CreateUserModel;
import com.revature.request.model.SuccessOrFailMessage;
import com.revature.services.UserService;

import io.swagger.annotations.Api;

@Path("users")
@Api(value = "users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private UserService service;

    public UserResource() {
        this.service = new UserService();
    }
    
    /**
     * Gets every user for TrackForce
     * @return Returns a json of all the users
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getAllUsers(){
    	//This will produce application/json
    	//Not sure if this will actually be needed
    	return Response.status(501)
    			.entity("This has not yet been implemented. There maybe future implementations")
    			.build();
    }
    
    /**
     * Endpoint used to create a new user in the database with a specified role, username and
     * password
     *
     * @param newUser
     * @return SuccessOrFailMessage
     */
    @POST
    public Response createNewUser(CreateUserModel newUser){
    	SuccessOrFailMessage msg = service.createNewUser(newUser);
    	if (msg.getStatus()) {
    		int userId = msg.getNewId();
    		URI location = URI.create("/user/"+userId);
    		return Response.created(location).build();
    	} else {
    		return Response.serverError().build();
    	}
    }
    
    /**
     * Gets the user by the user's username
     * @param username Username used to get the user
     * @return Returns a TfUser json
     */
    @GET
    @Path("/{username}")
    public Response getUser(@PathParam("username") String username) {
    	TfUser user = service.getUser(username);
    	return Response.ok(user).build();
    }

    /**
     * Method takes login information from front-end and verifies the information.
     * If info is valid, a status code of 200 is returned, otherwise 400 for a bad
     * request
     *
     * @param login - contains login information
     * @return a Response object with authentication data, such as username, JWT
     * token, and roleId
     * @throws IOException
     */
    @POST
    @Path("login")
    public Response submitCredentials(LoginJSON login) throws IOException {
    	UserJSON userjson = null;
    	userjson = service.submitCredentials(login);
    	if (userjson != null) {
    		return Response.status(200).entity(userjson).build();
    	}
    	else {
    		// Default return is 400 for a bad request
    	    return Response.status(400).build();
    	}
    }

    @GET
    @Path("/test")
    @Produces(MediaType.TEXT_PLAIN)
    public String testM() {
        return "This is a test";
    }
}
