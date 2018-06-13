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
import com.revature.services.TrainerService;
import com.revature.services.UserService;

import com.revature.utils.LogUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/users")
@Api(value = "users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

	@Path("/newUser")
	@POST
	@Consumes("application/json")
	@ApiOperation(value = "Creates new user", notes = "")
	public Response createUser(TfUser newUser) {
//		, @HeaderParam("Authorization") String token
		
//		if(newUser.getTfRole().getTfRoleId() == 1 || newUser.getTfRole().getTfRoleId() == 3 || newUser.getTfRole().getTfRoleId() == 4) {
//			Status status = null;
//			Claims payload = JWTService.processToken(token);
//			
//			if (payload == null || payload.getId().equals("5")) {
//				status = Status.UNAUTHORIZED;
//			}
//			else {
//				status = associates == null || associates.isEmpty() ? Status.NO_CONTENT : Status.OK;
//			}
//			
//		} else {
			logger.info("creating new user...");
			LogUtil.logger.info(newUser);
			UserService.insertUser(newUser);
			return Response.created(URI.create("/testingURIcreate")).build();
//		}
	}
	
	@Path("/newAssociate")
	@POST
	@Consumes("application/json")
	@ApiOperation(value = "Creates new Associate", notes = "Takes username, password, fname and lname to create new user")
	public Response createNewAssociate(CreateAssociateModel newAssociate) {
		logger.info("createNewAssociate()...");
		LogUtil.logger.info(newAssociate);
		// SuccessOrFailMessage msg = service.createNewAssociate(newAssociate);
		// if (msg.getStatus()) {
		// int userId = msg.getNewId();
		// URI location = URI.create("/user/"+userId);
		// return Response.created(location).build();
		// } else {
		// return Response.serverError().build();
		// }
		service.createNewAssociate(newAssociate);
		return Response.created(URI.create("/testingURIcreate")).build();
	}
	
	@Path("/newTrainer")
	@POST
	@Consumes("application/json")
	@ApiOperation(value = "Creates new trainer", notes = "")
	public Response createTrainer(TfTrainer newTrainer) {
		logger.info("creating new user...");
		LogUtil.logger.info(newTrainer);
		TrainerService.createTrainer(newTrainer);
		return Response.created(URI.create("/testingURIcreate")).build();
	}

	@Path("/login")
	@POST
	@Consumes("application/json")
	@ApiOperation(value = "login method", notes = "The method takes login inforation and verifies whether or not it is valid. returns 200 if valid, 400 if invalid.")
	public Response submitCredentials(TfUser loginuser) {
		logger.info("submitCredentials()...");
//		logger.info("	login: " + login);
		UserJSON userjson = null;
		userjson = service.submitCredentials(login);
		logger.info("	user: " + userjson);

		if (userjson != null) {
			return Response.status(200).entity(userjson).build();
		} else {
			return Response.status(Status.UNAUTHORIZED).build();
		}
	}

	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String testM() {
		return "This is a test";
	}
}
