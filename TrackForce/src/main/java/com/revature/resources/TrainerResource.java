package com.revature.resources;

import static com.revature.utils.LogUtil.logger;

import java.util.List;

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
 * @version.date v6.18.06.13
 *
 */
@Path("/trainers")
@Api(value = "trainers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TrainerResource {

	// You're probably thinking, why would you ever do this? Why not just just make the methods all static in the service class?
	// This is to allow for Mokito tests, which have problems with static methods
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
	public Response getBatchFromTrainer(@ApiParam("Trainer id") @PathParam("id")int id,
			@HeaderParam("Authorization") String token) {
		logger.info("getting batch from trainers...");
		TfTrainer trainer = trainerService.getTrainer(id);
		LogUtil.logger.info(trainer);
		List<TfBatch> batches = trainer.getPrimary();
		Claims payload = JWTService.processToken(token);
		Status status = null;
		if (payload == null || payload.getId().equals("5")) {
			status = Status.UNAUTHORIZED;
		}
		else {
			status = batches == null || batches.isEmpty() ? Status.NO_CONTENT : Status.OK;
		}

		return Response.status(status).entity(batches).build();
	}
	
	@Path("/{id}/cotrainerbatch")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Displays the batches that the trainer is cotrainer on", notes = "")
	public Response getBatchFromCotrainer(@ApiParam("trainer id") @PathParam("id")int id,
			@HeaderParam("Authorization") String token) {
		logger.info("getting batch from trainers...");
		TfTrainer trainer = trainerService.getTrainer(id);
		LogUtil.logger.info(trainer);
		List<TfBatch> batches = trainer.getCoTrainer();
		Claims payload = JWTService.processToken(token);
		Status status = null;
		if (payload == null || payload.getId().equals("5")) {
			status = Status.UNAUTHORIZED;
		}
		else {
			status = batches == null || batches.isEmpty() ? Status.NO_CONTENT : Status.OK;
		}

		return Response.status(status).entity(batches).build();
	}

	// NOTE: this used to be get a trainer by *trainer* id, so if your tests are broken that's why.
	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "get a trainer by its user id")
	public Response getTrainer(@PathParam("id")int id, @HeaderParam("Authorization")String token) {
		TfTrainer trainer = trainerService.getTrainerByUserId(id);
		Claims payload = JWTService.processToken(token);
		Status status = null;
		if (payload == null || payload.getId().equals("5")) {
			status = Status.UNAUTHORIZED;
		}
		else {
			status = trainer == null ? Status.NO_CONTENT : Status.OK;
		}

		return Response.status(status).entity(trainer).build();
	}
	
}