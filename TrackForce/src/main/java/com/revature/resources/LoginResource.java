package com.revature.resources;

import static com.revature.utils.LogUtil.logger;

import java.io.IOException;
import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.revature.entity.TfAssociate;
import com.revature.entity.TfTrainer;
import com.revature.entity.TfUser;
import com.revature.services.AssociateService;
import com.revature.services.BatchService;
import com.revature.services.ClientService;
import com.revature.services.CurriculumService;
import com.revature.services.InterviewService;
import com.revature.services.TrainerService;
import com.revature.services.UserService;
import com.revature.utils.LogUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * <p> </p>
 * @version.date v6.18.06.13
 *
 */
@Path("/users")
@Api(value = "users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoginResource {

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


	/**
	 * @author Adam L.
	 * <p> </p>
	 * @version.date v6.18.06.13
	 *
	 * @param newUser
	 * @return
	 */
	@Path("/newUser")
	@POST
	@Consumes("application/json")
	@ApiOperation(value = "Creates new user", notes = "")
	public Response createUser(TfUser newUser) {
		logger.info("creating new user...");
		LogUtil.logger.info(newUser);
		userService.insertUser(newUser);
		return Response.status(Status.CREATED).build();
	}

	/**
	 * @author Adam L.
	 * <p> </p>
	 * @version.date v6.18.06.13
	 *
	 * @param newAssociate
	 * @return
	 */
	@Path("/newAssociate")
	@POST
	@Consumes("application/json")
	@ApiOperation(value = "Creates new Associate", notes = "Takes username, password, fname and lname to create new associate and user")
	public Response createNewAssociate(TfAssociate newAssociate) {
		logger.info("createNewAssociate()...");
		LogUtil.logger.info(newAssociate);
		associateService.createAssociate(newAssociate);
		return Response.status(Status.CREATED).build();
	}

	/**
	 * @author Adam L.
	 * <p> </p>
	 * @version.date v6.18.06.13
	 *
	 * @param newTrainer
	 * @return
	 */
	@Path("/newTrainer")
	@POST
	@Consumes("application/json")
	@ApiOperation(value = "Creates new trainer", notes = "")
	public Response createTrainer(TfTrainer newTrainer) {
		logger.info("creating new user...");
		LogUtil.logger.info(newTrainer);
		trainerService.createTrainer(newTrainer);
		return Response.status(Status.CREATED).build();
	}

	/**
	 * @author Adam L.
	 * <p> </p>
	 * @version.date v6.18.06.13
	 *
	 * @param loginUser
	 * @return
	 * @throws IOException
	 */
	@Path("/login")
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	@ApiOperation(value = "login method", notes = "The method takes login inforation and verifies whether or not it is valid. returns 200 if valid, 400 if invalid.")
	public Response submitCredentials(TfUser loginUser) throws IOException {
		logger.info("submitCredentials()...");
		logger.info("	login: " + loginUser);
		TfUser user = userService.submitCredentials(loginUser);
		logger.info("	user: " + user);
		if (user != null) {
			logger.info("sending 200 response..");
			return Response.status(Status.OK).entity(user).build();
		} else {
			logger.info("sending unauthorized response..");
			return Response.status(Status.UNAUTHORIZED).build();
		}

	}

}
