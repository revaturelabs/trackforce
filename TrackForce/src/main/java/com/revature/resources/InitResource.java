package com.revature.resources;

import static com.revature.utils.LogUtil.logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
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
import com.revature.utils.DbResetUtil;
import com.revature.utils.UserAuthentication;

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
	
	
	/**
	 * This resource is used to reset the database to a known dataset. Only the administrator
	 * role will be authorized to call this resource. While the database is reinitialized, any
	 * call to the HibernateUtil.getSessionFactiory() method will get an exception. 
	 * @param token
	 * @return
	 */
	@GET
	@Path("/database")
	public Response resetDatabase(@HeaderParam("Authorization") String token) {
		logger.warn("resetDatabase()...");

		//Only Admin can access this resource
		if (UserAuthentication.Authorized(token, new int[] {1})) {
			try {
				DbResetUtil.resetDatabase();
			} catch (Exception ex) {
				ex.printStackTrace();
				logger.error(ex);
				return Response.status(Status.INTERNAL_SERVER_ERROR).build();
			}
		} else {
			return Response.status(Status.UNAUTHORIZED).entity(JWTService.invalidTokenBody(token)).build();
		}
		
		return Response.status(Status.OK).build();
	}
	
}
