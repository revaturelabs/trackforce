package com.revature.resources;

import static com.revature.utils.LogUtil.logger;

import java.util.List;

import javax.persistence.NoResultException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.revature.entity.TfBatch;
import com.revature.entity.TfTrainer;
import com.revature.services.AssociateService;
import com.revature.services.BatchService;
import com.revature.services.ClientService;
import com.revature.services.CurriculumService;
import com.revature.services.InterviewService;
import com.revature.services.JWTService;
import com.revature.services.TrainerService;
import com.revature.services.UserService;
import com.revature.utils.LogUtil;

import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <p> </p>
 * @version v6.18.06.13
 *
 */
@Path("/trainers")
@Api(value = "trainers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TrainerResource {

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

	@Path("/{id}/batch")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Displays the batch from the trainer", notes = "")
	public Response getBatchFromTrainer(@ApiParam("Trainer id") @PathParam("id") int id,
			@HeaderParam("Authorization") String token) {
		String logMessage = "getting batch from trainer: ";
		TfTrainer trainer;
		List<TfBatch> batches;
		Claims payload = JWTService.processToken(token);
		Status status = null;
		if (payload == null || ((String) payload.get("roleID")).equals("5")) {
			logger.info(logMessage + "unauthorized; payload == null");
			return Response.status(Status.UNAUTHORIZED).entity(JWTService.invalidTokenBody(token)).build();
		} else {
			try {
				trainer = trainerService.getTrainer(id);
				logger.info(logMessage + trainer);
				batches = trainer.getPrimary();
			} catch (NoResultException nre) {
				logger.error("No results found. Trainer had no batches.");
				return Response.status(Status.NO_CONTENT).build();
			}
			status = batches == null || batches.isEmpty() ? Status.NO_CONTENT : Status.OK;
		}
		logger.info("Returning all batch data for this Trainer: " + id);
		return Response.status(status).entity(batches).build();
	}

	@Path("/{id}/cotrainerbatch")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Displays the batches that the trainer is cotrainer on", notes = "")
	public Response getBatchFromCotrainer(@ApiParam("trainer id") @PathParam("id") int id,
			@HeaderParam("Authorization") String token) {
		String logMessage = "getting batch from co-trainer: ";
		TfTrainer trainer;
		List<TfBatch> batches;
		Claims payload = JWTService.processToken(token);
		Status status = null;
		if (payload == null || ((String) payload.get("roleID")).equals("5")) {
			logger.info(logMessage + "unauthorized; payload == null");
			return Response.status(Status.UNAUTHORIZED).entity(JWTService.invalidTokenBody(token)).build();
		} else {
			try {
				trainer = trainerService.getTrainer(id);
				logger.info(logMessage + trainer);
				batches = trainer.getCoTrainer();
			} catch (NoResultException nre) {
				logger.error("No results found. CoTrainer had no batches.");
				return Response.status(Status.NO_CONTENT).build();
			}
			status = batches == null || batches.isEmpty() ? Status.NO_CONTENT : Status.OK;
		}

		logger.info("Returning all Batches associated with CoTrainer: " + id);
		return Response.status(status).entity(batches).build();
	}

	// NOTE: this used to be get a trainer by *trainer* id, so if your tests are broken that's why.
	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "get a trainer by its user id")
	public Response getTrainer(@PathParam("id")int id, @HeaderParam("Authorization")String token) {
		TfTrainer trainer;
		Claims payload = JWTService.processToken(token);
		Status status = null;
		if (payload == null || ((String) payload.get("roleID")).equals("5")) {
			logger.error("Either Payload was null or User had insufficent privileges.");
			return Response.status(Status.UNAUTHORIZED).entity(JWTService.invalidTokenBody(token)).build();
		} else {
			try {
				trainer = trainerService.getTrainerByUserId(id);
			} catch (NoResultException nre) {
				logger.error("No results found. This User '"+id+"' is not a trainer.");
				return Response.status(Status.NO_CONTENT).build();
			}
			status = trainer == null ? Status.NO_CONTENT : Status.OK;
		}

		logger.info("Returning Trainer via UserId: " + id);
		return Response.status(status).entity(trainer).build();
	}
	
	@Path("/allTrainers")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "get all trainers")
	public Response getAllTrainers(@HeaderParam("Authorization") String token) {
		String logMessage = "getAllClients()...";
		Status status = null;
		List<TfTrainer> trainers = trainerService.getAllTrainers();
		Claims payload = JWTService.processToken(token);

		if (payload == null) {
			logger.info(logMessage + "\n unauthorized; payload == null");
			return Response.status(Status.UNAUTHORIZED).entity(JWTService.invalidTokenBody(token)).build(); // invalid token
		} else if (!(((String) payload.get("roleID")).equals("1") || ((String) payload.get("roleID")).equals("5"))) {
			logger.info(logMessage + "\n Insufficient Privileges. Either not Admin or is an Associate.");
			return Response.status(Status.FORBIDDEN).build();
		} else {
			logger.info(logMessage);
			status = trainers == null || trainers.isEmpty() ? Status.NO_CONTENT : Status.OK;
		}
		
		logger.info("Returning all Trainers.");
		return Response.status(status).entity(trainers).build();
	}

	/**
	 *
	 * @author Curtis H.
	 *  <p>Updates a trainer</p>
	 * @version v6.18.06.13
	 *
	 * @param id
	 * @param trainer
	 * @param token
	 * @return
	 */
	@PUT
	@ApiOperation(value = "updates trainer values", notes = "The method updates a trainer based on their id.")
	@Path("/update/{trainerId}")
	public Response updateTrainer(@PathParam("trainerId") Integer id, TfTrainer trainer,
	                                @HeaderParam("Authorization") String token) {
		logger.info("updateTrainer()...");
		Claims payload = JWTService.processToken(token);
		if (trainer == null) {
			logger.error("This Trainer '"+id+"' does not exist.");
			return Response.status(Status.NO_CONTENT).build();
		}
		else if (payload == null || ((String) payload.get("roleID")).equals("5")) {
			logger.error("Either Payload was null or User had insufficent privileges.");
			return Response.status(Status.UNAUTHORIZED).entity(JWTService.invalidTokenBody(token)).build();
		}
		else {
			System.out.println(payload.get(trainer));
			trainerService.updateTrainer(trainer);
			logger.info("Updated Trainer: " + id);
			return Response.status(Status.ACCEPTED).build();
		}
	}
	
}