package com.revature.resources;

import static com.revature.utils.LogUtil.logger;

import java.io.IOException;
import java.net.URI;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.revature.entity.TfUser;
import com.revature.model.InterviewInfo;
import com.revature.model.LoginJSON;
import com.revature.model.UserJSON;
import com.revature.request.model.CreateUserModel;
import com.revature.request.model.SuccessOrFailMessage;
import com.revature.services.JWTService;
import com.revature.services.UserService;

import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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
    @ApiOperation(value ="Creates new User", notes ="Creates a new user in the database with a specified role, username, and password.")
    public Response createNewUser(CreateUserModel newUser, @HeaderParam("Authorization") String token)
    {
    	Status status = null;
		Claims payload = JWTService.processToken(token);

		if (payload == null || !payload.getId().equals("1")) 
		{
			status = Status.UNAUTHORIZED;
			return Response.status(status).build();
		} 
		
		else 
		{
			SuccessOrFailMessage msg = service.createNewUser(newUser);
	    	if (msg.getStatus()) 
	    	{
	    		int userId = msg.getNewId();
	    		URI location = URI.create("/user/"+userId);
	    		return Response.created(location).build();
	    	} 
	    	
	    	else 
	    	{
	    		return Response.serverError().build();
	    	}
		}

    }
    
    /**
     * Gets the user by the user's username
     * @param username Username used to get the user
     * @return Returns a TfUser json
     */
    @GET
    @ApiOperation(value = "Gets user", notes ="Gets a specific user by their username.")
    @Path("/{username}")
    public Response getUser(@PathParam("username") String username,@HeaderParam("Authorization") String token) 
    {
    	Status status = null;
		TfUser user = null;
		Claims payload = JWTService.processToken(token);

		if (payload == null || !payload.getId().equals("1")) 
		{
			status = Status.UNAUTHORIZED;
		} 
		
		else 
		{
			user = service.getUser(username);
		}
    	
    	return Response.status(status).entity(user).build();
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
    @ApiOperation(value = "login method", notes ="The method takes login inforation and verifies whether or not it is valid. returns 200 if valid, 400 if invalid.")
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
