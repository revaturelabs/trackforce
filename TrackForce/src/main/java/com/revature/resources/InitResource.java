package com.revature.resources;
import com.revature.services.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/** @author Adam L.
 * <p>Exists for the sole purpose of establishing a connection with the database upon webpage load.</p>
 * @version v06.18.06.18 */
@Path("init")
@Api(value = "init")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InitResource
{
	// You're probably thinking, why would you ever do this? Why not just just make the methods all static in the service class?
	// This is to allow for Mockito tests, which have problems with static methods
	// - Adam 06.18.06.13
	private UserService userService = new UserService();
		
	@GET
	@ApiOperation(value = "Initializes connection", notes = "Used to quickly establish a connection with the database.")
	public Response connectionInit() {
		userService.getUser("TestAssociate");
		return Response.status(Status.OK).build();
	}
}