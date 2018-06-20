package com.revature.resources;

import static com.revature.utils.LogUtil.logger;

import java.io.IOException;
import java.net.URI;

import javax.persistence.NoResultException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.revature.entity.TfAssociate;
import com.revature.entity.TfRole;
import com.revature.entity.TfTrainer;
import com.revature.entity.TfUser;
import com.revature.services.*;
import com.revature.utils.LogUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * <p> </p>
 * @version v6.18.06.13
 *
 */
@Path("/users")
@Api(value = "users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

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
	MarketingStatusService marketingStatusService = new MarketingStatusService();


	/**
	 * @author Adam L.
	 * <p> </p>
	 * @version v6.18.06.13
	 *
	 * @param newUser
	 * @return
	 */
	@Path("/newUser")
	@POST
	@Consumes("application/json")
	@ApiOperation(value = "Creates new user", notes = "")
	public Response createUser(TfUser newUser) {
		logger.info("creating new user..." + newUser);
		
		// any user created by an admin is approved
		newUser.setIsApproved(1);

		// get the role being passed in 
		int role = newUser.getRole();
		TfRole tfrole = new TfRole();
		
		if(role != 0) {
			switch(role) {
			case 1:
				tfrole = new TfRole(1, "Admin");
				newUser.setTfRole(tfrole);
				userService.insertUser(newUser);
				break;
			case 2:
				tfrole = new TfRole(2, "Trainer");
				newUser.setTfRole(tfrole);
				TfTrainer newTrainer = new TfTrainer();
				newTrainer.setTfUser(newUser);
				newTrainer.setFirstName("placehold");
				newTrainer.setLastName("placeholder");
				logger.info("creating new trainer..." + newTrainer);
				trainerService.createTrainer(newTrainer);
				break;
			case 3:
				tfrole = new TfRole(3, "Sales-Delivery");
				newUser.setTfRole(tfrole);
				userService.insertUser(newUser);
				break;
			case 4:
				tfrole = new TfRole(4, "Staging");
				newUser.setTfRole(tfrole);
				userService.insertUser(newUser);
				break;
			case 5:
				tfrole = new TfRole(5, "Associate");
				newUser.setTfRole(tfrole);
				TfAssociate newAssociate = new TfAssociate();
				newAssociate.setUser(newUser);
				newAssociate.setFirstName("placehold");
				newAssociate.setLastName("placeholder");
				logger.info("creating new associate..." + newAssociate);
				associateService.createAssociate(newAssociate);
				break;
			}
		}
		if(userService.insertUser(newUser)) {
			return Response.status(Status.CREATED).build();
		}
		return Response.status(Status.EXPECTATION_FAILED).build();
	}

	/**
	 * @author Adam L.
	 * <p> </p>
	 * @version v6.18.06.13
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
		if (newAssociate.getUser().getRole() == 5) {
			if (associateService.createAssociate(newAssociate)) {
				return Response.status(Status.CREATED).build();
			}
			return Response.status(Status.EXPECTATION_FAILED).build();
		}
		else {
			return Response.status(Status.FORBIDDEN).build();
		}
	}

	/**
	 * @author Adam L.
	 * <p> </p>
	 * @version v6.18.06.13
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
		if (newTrainer.getTfUser().getRole() == 2) {
			if (trainerService.createTrainer(newTrainer)) {
				return Response.status(Status.CREATED).build();
			}
			return Response.status(Status.EXPECTATION_FAILED).build();
		}
		else {
			return Response.status(Status.FORBIDDEN).build();
		}
	}

	/**
	 * @author Adam L.
	 * <p> </p>
	 * @version v6.18.06.13
	 *
	 * @param loginUser
	 * @return
	 * @throws IOException
	 */
	@Path("/login")
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	@ApiOperation(value = "login method", notes = "The method takes login inforation and verifies whether or not it is valid. returns 200 if valid, 403 if invalid.")
	public Response submitCredentials(TfUser loginUser) throws IOException {
		logger.info("submitCredentials()...");
		logger.info("	login: " + loginUser);
		TfUser user;
		try {
			user = userService.submitCredentials(loginUser);
			logger.info("	user: " + user);
		} catch (NoResultException nre) {
			nre.printStackTrace();
			return Response.status(Status.FORBIDDEN).build();
		}
		if (user != null) {
			logger.info("sending 200 response..");
			return Response.status(Status.OK).entity(user).build();
		} else {
			logger.info("sending unauthorized response..");
			return Response.status(Status.UNAUTHORIZED).build();
		}
	}
}
