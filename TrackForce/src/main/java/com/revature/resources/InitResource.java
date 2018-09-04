package com.revature.resources;

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

import com.revature.services.AssociateService;
import com.revature.services.BatchService;
import com.revature.services.ClientService;
import com.revature.services.CurriculumService;
import com.revature.services.InterviewService;
import com.revature.services.JWTService;
import com.revature.services.TrainerService;
import com.revature.services.UserService;

import io.jsonwebtoken.Claims;
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

	// You're probably thinking, why would you ever do this? Why not just just make the methods all static in the service class?
	// This is to allow for Mockito tests, which have problems with static methods
	// This is here for a reason! 
	// - Adam 06.18.06.13
	AssociateService associateService = new AssociateService();
	BatchService batchService = new BatchService();
	ClientService clientService = new ClientService();
	CurriculumService curriculumService = new CurriculumService();
	InterviewService interviewService = new InterviewService();
	TrainerService trainerService = new TrainerService();
	UserService userService = new UserService();
		
	@GET
	@ApiOperation(value = "Initializes connection", notes = "Used to quickly establish a connection with the database.")
	public Response connectionInit() {
		userService.getUser("TestAssociate");
		return Response.status(Status.OK).build();
	}
	
	@POST
	@ApiOperation(value = "Reinitialize Database", notes = "Truncates the entire database and inserts the original data set")
	@Path("/reinitdb")
	public Response reinitDB(@HeaderParam("Authorization") String token) {
		Claims payload = JWTService.processToken(token);
		if(payload.getId() != "1") {
			return Response.status(Status.UNAUTHORIZED).build();
		}
		
		return Response.status(Status.OK).build();
	}
	
	
}
