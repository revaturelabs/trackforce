package com.revature.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import static com.revature.utils.LogUtil.logger;

import com.revature.services.AssociateService;
import com.revature.services.BatchService;
import com.revature.services.ClientService;
import com.revature.services.CurriculumService;
import com.revature.services.InterviewService;
import com.revature.services.TrainerService;
import com.revature.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author Adam L. 
 * <p>Exists for the sole purpose of establishing a connection with the database upon webpage load.</p>
 * @version.date v06.18.06.18
 *
 */
@Path("init")
@Api(value = "init")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InitResource {

	UserService userService = new UserService();
		
	@GET
	@ApiOperation(value = "Initializes connection", notes = "Used to quickly establish a connection with the database.")
	public Response connectionInit() {
		logger.info("connectionInit Started. TestAssociate used to establish connection to Database.");
		userService.getUser("TestAssociate");
		return Response.status(Status.OK).build();
	}
	
	
}
