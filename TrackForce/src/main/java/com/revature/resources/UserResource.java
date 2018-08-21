package com.revature.resources;

import static com.revature.utils.LogUtil.logger;

import java.io.IOException;

import javax.persistence.NoResultException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.gson.JsonObject;
import com.revature.entity.TfAssociate;
import com.revature.entity.TfRole;
import com.revature.entity.TfTrainer;
import com.revature.entity.TfUser;
import com.revature.entity.TfUserAndCreatorRoleContainer;
import com.revature.services.AssociateService;
import com.revature.services.BatchService;
import com.revature.services.ClientService;
import com.revature.services.CurriculumService;
import com.revature.services.InterviewService;
import com.revature.services.JWTService;
import com.revature.services.MarketingStatusService;
import com.revature.services.TrainerService;
import com.revature.services.UserService;
import com.revature.utils.LogUtil;

import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.mortbay.util.ajax.JSON;

/**
 * <p>
 * </p>
 * 
 * @version v6.18.06.13
 *
 */
@Path("/users")
@Api(value = "users")
@Consumes({MediaType.APPLICATION_JSON})
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

	// You're probably thinking, why would you ever do this? Why not just just make
	// the methods all static in the service class?
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

	private static final String TEMP = "placeholder"; 
	private static final String ASSC = "Associate";
	
	/**
	 * @author Adam L.
	 *         <p>
	 *         </p>
	 * @version v6.18.06.13
	 *
	 * @param newUser
	 * @return
	 */
	@Path("/newUser")
	@POST
	@Consumes("application/json")
	@ApiOperation(value = "Creates new user", notes = "")
	public Response createUser(TfUserAndCreatorRoleContainer container) {
		TfUser newUser = container.getUser();
		int creatorRole = container.getCreatorRole();
		logger.info("creating new user..." + newUser);
		
		// any user created by an admin is approved
		if(creatorRole == 1)
			newUser.setIsApproved(1);
		else if (creatorRole > 4)
			return Response.status(Status.EXPECTATION_FAILED).build();

		// get the role being passed in
		boolean works = true;
    
		int role = newUser.getRole();

		TfRole tfrole = null;

		switch (role) {
		case 1:
			if (userService.getUser(newUser.getUsername()) == null){
				tfrole = new TfRole(1, "Admin");
				newUser.setTfRole(tfrole);
				works = userService.insertUser(newUser);
			}
			else {
				works = false;
			}
			break;
		case 2:
			if (userService.getUser(newUser.getUsername()) == null) { 
				tfrole = new TfRole(2, "Trainer");
				newUser.setTfRole(tfrole);
				TfTrainer newTrainer = new TfTrainer();
				newTrainer.setTfUser(newUser);
				newTrainer.setFirstName(TEMP);
				newTrainer.setLastName(TEMP);
				logger.info("creating new trainer..." + newTrainer);
				works = trainerService.createTrainer(newTrainer);
			}
			else {
				works = false;
			}
			break;
		case 3:
			if (userService.getUser(newUser.getUsername()) == null) {
				tfrole = new TfRole(3, "Sales-Delivery");
				newUser.setTfRole(tfrole);
				works = userService.insertUser(newUser);
			}
			else {
				works = false;
			}
			break;
		case 4:
			if (userService.getUser(newUser.getUsername()) == null) {
				tfrole = new TfRole(4, "Staging");
				newUser.setTfRole(tfrole);
				works = userService.insertUser(newUser);
			}
			else {
				works = false;
			}
			break;
		case 5:
			if (userService.getUser(newUser.getUsername()) == null) {
				tfrole = new TfRole(5, ASSC);
				newUser.setTfRole(tfrole);
				TfAssociate newAssociate = new TfAssociate();
				newAssociate.setUser(newUser);
				newAssociate.setFirstName(TEMP);
				newAssociate.setLastName(TEMP);
				logger.info("creating new associate..." + newAssociate);
				works = associateService.createAssociate(newAssociate);
			}
			else {
				works = false;
			}
			break;
		default:
			logger.warn("Role is zero");
			break;
		}

		if (works) {
			return Response.status(Status.CREATED).build();
		} else {
			return Response.status(Status.EXPECTATION_FAILED).build();
		}
	}
	
	@Path("/checkUsername")
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public Response checkUsername(String username) {
		final String varName = "result";
		JsonObject json = new JsonObject();
		
		String message;
		if(userService.getUser(username) == null) {
			json.addProperty(varName, "true");
			message = json.toString();
			return Response.ok(message,MediaType.TEXT_PLAIN).build();
		}
		else {
			json.addProperty(varName, "false");
			message = json.toString();
			return Response.ok(message,MediaType.TEXT_PLAIN).build();
		}
	}
	/**
	 * @author Adam L.
	 *         <p>
	 *         </p>
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
			boolean works = false;

			TfRole tfrole = null;
			tfrole = new TfRole(5, ASSC);
			newAssociate.getUser().setTfRole(tfrole);
			logger.info(newAssociate.getUser().getTfRole());
			logger.info("creating new associate..." + newAssociate);
			works = associateService.createAssociate(newAssociate);

			if (works) {
				return Response.status(Status.CREATED).build();
			}
			return Response.status(Status.EXPECTATION_FAILED).build();
		} else {
			return Response.status(Status.FORBIDDEN).build();
		}
	}

	/**
	 * @author Adam L.
	 *         <p>
	 *         </p>
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
			boolean works = false;

			TfRole tfrole = null;
			tfrole = new TfRole(5, ASSC);
			newTrainer.getTfUser().setIsApproved(0);
			newTrainer.getTfUser().setTfRole(tfrole);
			logger.info(newTrainer.getTfUser().getTfRole());
			logger.info("creating new trainer..." + newTrainer);
			works = trainerService.createTrainer(newTrainer);

			if (works) {
				return Response.status(Status.CREATED).build();
			}
			return Response.status(Status.EXPECTATION_FAILED).build();
		} else {
			return Response.status(Status.FORBIDDEN).build();
		}
	}

	/**
	 * @author Adam L.
	 *         <p>
	 *         </p>
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
	public Response submitCredentials(TfUser loginUser) {
		logger.info("submitCredentials()...");
		logger.info("	login: " + loginUser);
		TfUser user;
		try {
			user = userService.submitCredentials(loginUser);
			logger.info("	user: " + user);
		} catch (NoResultException nre) {
			logger.error(nre);
			return Response.status(Status.FORBIDDEN).build();
		}
		if (user != null) {
			logger.info("sending 200 response..");
			return Response.status(Status.OK).entity(user).build();
		} else {
			logger.info("sending unauthorized response..");
			return Response.status(Status.UNAUTHORIZED).entity(null).build();
		}
	}
	
	/**
	 * @author 1806_Austin_M
	 * Uses JWTService to validate a web token. Used from login component to check if session data is still up to date
	 * @param token
	 * @return
	 */
	@Path("/check")
	@GET
	@ApiOperation(value = "check method", notes = "The method checks whether a JWT is valid. returns 200 if valid, 401 if invalid.")
	public Response checkCredentials(@HeaderParam("Authorization") String token) {
		logger.info("checkCredentials()...");

		Claims payload = JWTService.processToken(token);

		if (payload == null) 
			return Response.status(Status.UNAUTHORIZED).entity(JWTService.invalidTokenBody(token)).build();
		else
			return Response.status(Status.OK).build();

	}
	
	
}
