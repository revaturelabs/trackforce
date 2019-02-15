package com.revature.resources;

import static com.revature.utils.LogUtil.logger;

import java.io.IOException;

import javax.persistence.NoResultException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.JSONObject;

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
import com.revature.utils.HibernateUtil;
import com.revature.utils.LogUtil;
import com.revature.utils.PasswordStorage;
import com.revature.utils.PasswordStorage.CannotPerformOperationException;
import com.revature.utils.PasswordStorage.InvalidHashException;

import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * </p>
 * 
 * @version v6.18.06.13
 *
 */
@Path("/users")
@Api(value = "users")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

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
	/*
	 * This method can create new Associates as well as new Trainers, so the newAssociate and newTrainer methods
	 * are unused and can likely be deleted. They may need their calls updated on the Angular side, however
	 */
	@Path("/newUser")
	@POST
	@Consumes("application/json")
	@ApiOperation(value = "Creates new user", notes = "")
	public Response createUser(TfUserAndCreatorRoleContainer container, @HeaderParam("Authorization") String token) {
		Claims payload = jwtServiceProcessToken(token);

		if (payload == null) {
			logger.error("The payload was null. Unathorized access.");
			return Response.status(Status.UNAUTHORIZED).entity(JWTService.invalidTokenBody(token)).build();
		}
		TfUser newUser = container.getUser();
		int creatorRole = Integer.parseInt((String) payload.get("roleID"));
		StringBuilder logMessage = new StringBuilder("Call Method createUser(). User: " + newUser);

		// any user created by an admin is approved
		if (creatorRole == 1)
			newUser.setIsApproved(1);
		else if (creatorRole > 4) {
			logger.error("Associate Role detected. Forbidden Access.");
			return Response.status(Status.FORBIDDEN).build();
		}
		// get the role being passed in
		boolean works = false;

		int role = newUser.getRole();

		TfRole tfrole = null;

		switch (role) {
		case 1:
			if (getUserService().getUser(newUser.getUsername()) == null) {
				tfrole = new TfRole(1, "Admin");
				newUser.setTfRole(tfrole);
				logMessage.append(logMessage + " \n Admin user is being created.");
				logMessage.append("\n	The user with hashed password is " + newUser);
				works = getUserService().insertUser(newUser);
			} else {
				works = false;
			}
			break;
		case 2:
			if (getUserService().getUser(newUser.getUsername()) == null) {
				tfrole = new TfRole(2, "Trainer");
				newUser.setTfRole(tfrole);
				TfTrainer newTrainer = new TfTrainer();
				newTrainer.setTfUser(newUser);
				newTrainer.setFirstName(TEMP);
				newTrainer.setLastName(TEMP);
				logMessage.append(logMessage + "\n	creating new trainer..." + newTrainer);
				logMessage.append("The trainer with hashed password is " + newTrainer);
				works = getTrainerService().createTrainer(newTrainer);
			} else {
				works = false;
			}
			break;
		case 3:
			if (getUserService().getUser(newUser.getUsername()) == null) {
				tfrole = new TfRole(3, "Sales-Delivery");
				newUser.setTfRole(tfrole);
				logMessage.append(logMessage + " \n SalesForce user is being created.");
				logMessage.append("\n	The user with hashed password is " + newUser);
				works = getUserService().insertUser(newUser);
			} else {
				works = false;
			}
			break;
		case 4:
			if (getUserService().getUser(newUser.getUsername()) == null) {
				tfrole = new TfRole(4, "Staging");
				newUser.setTfRole(tfrole);
				logMessage.append(logMessage + " \n Staging Manager user is being created.");
				logMessage.append("\n	The user with hashed password is " + newUser);
				works = getUserService().insertUser(newUser);
			} else {
				works = false;
			}
			break;
		case 5:
			if (getUserService().getUser(newUser.getUsername()) == null) {
				tfrole = new TfRole(5, ASSC);
				newUser.setTfRole(tfrole);
				TfAssociate newAssociate = new TfAssociate();
				newAssociate.setUser(newUser);
				newAssociate.setFirstName(TEMP);
				newAssociate.setLastName(TEMP);
				logMessage.append("\n	creating new associate..." + newAssociate);
				logMessage.append("\n	The associate with hashed password is " + newAssociate);
				works = getAssociateService().createAssociate(newAssociate);
			} else {
				works = false;
			}
			break;
		default:
			logger.warn(logMessage + "\n	Warning: Role is zero");
			break;
		}

		if (works) {
			logger.info(logMessage);
			return Response.status(Status.CREATED).build();
		} else {
			logger.info(logMessage);
			logger.error("Invalid Username.");
			return Response.status(Status.EXPECTATION_FAILED).build();
		}
	}

	@Path("/checkUsername")
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public Response checkUsername(String username) {
		logger.info("Call Method checkUsername().");
		final String varName = "result";
		JsonObject json = new JsonObject();
		String message;
		Boolean found = getUserService().getUser(username) == null;
		json.addProperty(varName, found.toString());
		message = json.toString();
		logger.info("Send back if username found. Found: " + found);
		return Response.ok(message, MediaType.TEXT_PLAIN).build();
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
//	The methods "new Associate" and "new trainer" are not used since "new user" can create a trainer and associate
	@Path("/newAssociate")
	@POST
	@Consumes("application/json")
	@ApiOperation(value = "Creates new Associate", notes = "Takes username, password, fname and lname to create new associate and user")
	public Response createNewAssociate(TfAssociate newAssociate) {
		logger.info("Method Call from Login Page with Register. createNewAssociate().");
		LogUtil.logger.info(newAssociate);
		if (newAssociate.getUser().getRole() == 5) {
			boolean works = false;

			TfRole tfrole = null;
			tfrole = new TfRole(5, ASSC);
			newAssociate.getUser().setTfRole(tfrole);
			logger.info(newAssociate.getUser().getTfRole());
			logger.info("creating new associate..." + newAssociate);
			works = getAssociateService().createAssociate(newAssociate);

			if (works) {
				logger.info("Valid Associate Created.");
				return Response.status(Status.CREATED).build();
			}
			logger.error("Associate could not be created. Invalid parameters.");
			return Response.status(Status.EXPECTATION_FAILED).build();
		} else {
			logger.error("Invalid Role-type. Associate Role expected.");
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
		logger.info("Method Call from Login Page with Register. createTrainer().");
		LogUtil.logger.info(newTrainer);
		if (newTrainer.getTfUser().getRole() == 2) {
			boolean works = false;

			TfRole tfrole = null;
			tfrole = new TfRole(2, "Trainer");
			newTrainer.getTfUser().setIsApproved(0);
			newTrainer.getTfUser().setTfRole(tfrole);
			logger.info(newTrainer.getTfUser().getTfRole());
			logger.info("creating new trainer..." + newTrainer);
			works = getTrainerService().createTrainer(newTrainer);

			if (works) {
				logger.info("Valid Trainer Created.");
				return Response.status(Status.CREATED).build();
			}
			logger.error("Trainer could not be created. Invalid Parameters.");
			return Response.status(Status.EXPECTATION_FAILED).build();
		} else {
			logger.error("Invalid Role-type. Trainer Role expected.");
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
		String logMessage = "submitCredentials()...\n	login: " + loginUser;
		TfUser user;
		try {
			user = getUserService().submitCredentials(loginUser);
			logger.info(logMessage + "\n	user: " + user);
		} catch (NoResultException | NullPointerException ex) {
			logger.error(ex.getMessage());
			return Response.status(Status.UNAUTHORIZED).build();
		}
		if (user != null) {
			logger.info("User object detected. Valid parameters.");
			return Response.status(Status.OK).entity(user).build();
		} else {
			logger.error("No user object found. Invalid parameters.");
			return Response.status(Status.UNAUTHORIZED).entity(null).build();
		}
	}

	/**
	 * @author 1806_Austin_M Uses JWTService to validate a web token. Used from
	 *         login component to check if session data is still up to date
	 * @param token
	 * @return
	 */
	@Path("/check")
	@GET
	@ApiOperation(value = "check method", notes = "The method checks whether a JWT is valid. returns 200 if valid, 401 if invalid.")
	public Response checkCredentials(@HeaderParam("Authorization") String token) {
		logger.info("checkCredentials()...");

		Claims payload = jwtServiceProcessToken(token);

		if (payload == null) {
			logger.error("The payload was null. Unathorized access.");
			return Response.status(Status.UNAUTHORIZED).entity(JWTService.invalidTokenBody(token)).build();
		} else {
			logger.info("Payload contains Information. JWT valid.");
			return Response.status(Status.OK).build();
		}

	}

	@Path("/init")
	@GET
	@ApiOperation(value = "Init method", notes = "Initializes the Hibernate Session Factory.")
	public Response sessionInitialization() {
		logger.info("Initizilizing SessionFactory");
		// HibernateUtil.runHibernate((Session session, Object ... args) ->
		// session.createNativeQuery("SELECT * FROM dual"));
		HibernateUtil.getSessionFactory();
		return Response.status(Status.OK).build();
	}

	@Path("/getUserRole")
	@GET
	@Produces("application/json")
	@ApiOperation(value = "Get Role value method", notes = "parses the JWT to check if its valid and returns the value if valid")
	public Response returnRole(@HeaderParam("Authorization") String token) {
		Claims payload = jwtServiceProcessToken(token);

		if (payload == null) {
			logger.error("The payload was null. Unathorized access.");
			return Response.status(Status.UNAUTHORIZED).entity(JWTService.invalidTokenBody(token)).build();
		}
		logger.info("JWT Valid. Returning User Role.");
		return Response.status(Status.OK).entity(payload.get("roleID")).build();
	}

	@Path("/updatepassword")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Update user password", notes = "Compare if old password is correct and then update user with new password")
	public Response updateUserPassword(@HeaderParam("Authorization") String token, String requestBody) {
		int userId = getJSONObject(requestBody).getInt("userId");
		String oldpassword = getJSONObject(requestBody).getString("oldPassword");
		String updatepassword = getJSONObject(requestBody).getString("newPassword");
		Status status = null;
		Claims payload = jwtServiceProcessToken(token);
		TfUser userUpdatePass = getUserService().getUser(userId);
		logger.info("Method Call to update User[" + userId + "]'s password.");
		try {
			if (payload == null) {
				logger.error("The payload was null. Unathorized access.");
				return Response.status(Status.UNAUTHORIZED).entity(JWTService.invalidTokenBody(token)).build();
			}
			// if oldpassword is sent as standard string
			else if (getPasswordStorageVerifyPassword(oldpassword, userUpdatePass.getPassword())) {
				logger.info("Oldpass[String] verified against User's password in database.");
				status = getUserService().updateUserPassword(userUpdatePass, updatepassword) ? Status.OK
						: Status.INTERNAL_SERVER_ERROR;
			}
			// if oldpassword is sent as the hashed password from the database
			else if (userUpdatePass.getPassword().equals(oldpassword)) {
				logger.info("Oldpass[Hashed] verified against User's password in database.");
				status = getUserService().updateUserPassword(userUpdatePass, updatepassword) ? Status.OK
						: Status.INTERNAL_SERVER_ERROR;
			} else {
				logger.error("Oldpassword incorrect against User's password in database.");
				return Response.status(Status.UNAUTHORIZED).entity(null).build();
			}
		} catch (CannotPerformOperationException e) {
			logger.error("Could not perform VerifyPassword.");
			status = Status.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		} catch (InvalidHashException e) {
			logger.error("User's password in database had an invalid hashset.");
			status = Status.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
		return Response.status(status).build();
	}

	@Path("/updateusername")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Update User's username", notes = "Compare if old password is correct and then update user with new password")
	public Response updateUserUsername(@HeaderParam("Authorization") String token, String requestBody) {
		int userId = getJSONObject(requestBody).getInt("userId");
		String oldpassword = getJSONObject(requestBody).getString("password");
		String newUsername = getJSONObject(requestBody).getString("newUsername");
		Status status = null;
		Claims payload = jwtServiceProcessToken(token);
		TfUser userUpdateName = getUserService().getUser(userId);
		logger.info("Method Call to update User[" + userId + "]'s username.");
		try {
			// Validate newUsername does not already exist in database.
			if (getUserService().getUser(newUsername) != null) {
				return Response.status(Status.BAD_REQUEST).build();
			}
			if (payload == null) {
				logger.error("The payload was null. Unathorized access.");
				return Response.status(Status.UNAUTHORIZED).entity(JWTService.invalidTokenBody(token)).build();
			}
			// if oldpassword is sent as standard string
			else if (getPasswordStorageVerifyPassword(oldpassword, userUpdateName.getPassword())) {
				logger.info("Oldpass[String] verified against User's password in database.");
				status = getUserService().updateUsername(userUpdateName, newUsername) ? Status.OK
						: Status.INTERNAL_SERVER_ERROR;
			}
			// if oldpassword is sent as the hashed password from the database
			else if (userUpdateName.getPassword().equals(oldpassword)) {
				logger.info("Oldpass[Hashed] verified against User's password in database.");
				status = getUserService().updateUsername(userUpdateName, newUsername) ? Status.OK
						: Status.INTERNAL_SERVER_ERROR;
			} else {
				logger.error("Oldpassword incorrect against User's password in database.");
				return Response.status(Status.UNAUTHORIZED).entity(null).build();
			}
		} catch (CannotPerformOperationException e) {
			logger.error("Could not perform VerifyPassword.");
			status = Status.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		} catch (InvalidHashException e) {
			logger.error("User's password in database had an invalid hashset.");
			status = Status.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
		return Response.status(status).build();
	}

	// Using these methods to allow for mocking of these classes during unit
	// testing.
	public AssociateService getAssociateService() {
		return new AssociateService();
	}

	public TrainerService getTrainerService() {
		return new TrainerService();
	}

	public UserService getUserService() {
		return new UserService();
	}

	public JWTService getJWTService() {
		return new JWTService();
	}

	public Claims jwtServiceProcessToken(String token) {
		return getJWTService().processToken(token);
	}

	public JSONObject getJSONObject(String str) {
		return new JSONObject(str);
	}

	public Boolean getPasswordStorageVerifyPassword(String str1, String str2)
			throws CannotPerformOperationException, InvalidHashException {
		return PasswordStorage.verifyPassword(str1, str2);
	}
}
