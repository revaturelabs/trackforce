package com.revature.resources;
import com.revature.entity.TfBatch;
import com.revature.entity.TfTrainer;
import com.revature.services.JWTService;
import com.revature.services.TrainerService;
import com.revature.utils.LogUtil;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javax.persistence.NoResultException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.List;
import static com.revature.utils.LogUtil.logger;

/** @version v6.18.06.13 */
@Path("/trainers")
@Api(value = "trainers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TrainerResource
{
	// You're probably thinking, why would you ever do this? Why not just just make the methods all static in the service class?
	// This is to allow for Mockito tests, which have problems with static methods
	// - Adam 06.18.06.13
	private TrainerService trainerService = new TrainerService();

	@Path("/{id}/batch")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Displays the batch from the trainer")
	public Response getBatchFromTrainer(@ApiParam("Trainer id") @PathParam("id") int id,
			@HeaderParam("Authorization") String token) {
		logger.info("getting batch from trainers...");
		TfTrainer trainer;
		List<TfBatch> batches;
		Claims payload = JWTService.processToken(token);
		Status status;
		if (payload == null || payload.getId().equals("5"))
			return Response.status(Status.UNAUTHORIZED).entity(JWTService.invalidTokenBody(token)).build();
		else {
			try {
				trainer = trainerService.getTrainer(id);
				LogUtil.logger.info(trainer);
				batches = trainer.getPrimary();
			} catch (NoResultException nre) { return Response.status(Status.NO_CONTENT).build(); }
			status = batches == null || batches.isEmpty() ? Status.NO_CONTENT : Status.OK;
		}
		return Response.status(status).entity(batches).build();
	}

	@Path("/{id}/cotrainerbatch")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Displays the batches that the trainer is cotrainer on")
	public Response getBatchFromCotrainer(@ApiParam("trainer id") @PathParam("id") int id,
			@HeaderParam("Authorization") String token) {
		logger.info("getting batch from trainers...");
		TfTrainer trainer;
		List<TfBatch> batches;
		Claims payload = JWTService.processToken(token);
		Status status;
		if (payload == null || payload.getId().equals("5"))
			return Response.status(Status.UNAUTHORIZED).entity(JWTService.invalidTokenBody(token)).build();
		else {
			try {
				trainer = trainerService.getTrainer(id);
				LogUtil.logger.info(trainer);
				batches = trainer.getCoTrainer();
			} catch (NoResultException nre) {  return Response.status(Status.NO_CONTENT).build(); }
			status = batches == null || batches.isEmpty() ? Status.NO_CONTENT : Status.OK;
		}
		return Response.status(status).entity(batches).build();
	}

	/** NOTE: this used to be get a trainer by *trainer* id, so if your tests are broken that's why. */
	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "get a trainer by its user id")
	public Response getTrainer(@PathParam("id")int id, @HeaderParam("Authorization")String token) {
		TfTrainer trainer;
		Claims payload = JWTService.processToken(token);
		Status status;
		if (payload == null || payload.getId().equals("5"))
			return Response.status(Status.UNAUTHORIZED).entity(JWTService.invalidTokenBody(token)).build();
		else {
			try { trainer = trainerService.getTrainerByUserId(id); }
			catch (NoResultException nre) {
				logger.debug("NoResultException!");
				return Response.status(Status.NO_CONTENT).build();
			}
			status = trainer == null ? Status.NO_CONTENT : Status.OK;
		}
		return Response.status(status).entity(trainer).build();
	}
	
	@Path("/allTrainers")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "get all trainers")
	public Response getAllTrainers(@HeaderParam("Authorization") String token) {
		logger.info("getAllClients()...");
		Status status;
		List<TfTrainer> trainers = trainerService.getAllTrainers();
		Claims payload = JWTService.processToken(token);

		if (payload == null) // invalid token
			return Response.status(Status.UNAUTHORIZED).entity(JWTService.invalidTokenBody(token)).build();
		else if (!(payload.getId().equals("1") || payload.getId().equals("5")))
			return Response.status(Status.FORBIDDEN).build();
		else
			status = trainers == null || trainers.isEmpty() ? Status.NO_CONTENT : Status.OK;
		return Response.status(status).entity(trainers).build();
	}

	/** @author Curtis H.
	 *  <p>Updates a trainer</p>
	 * @version v6.18.06.13 */
	@PUT
	@ApiOperation(value = "updates trainer values", notes = "The method updates a trainer based on their id.")
	@Path("/{trainerId}")
	public Response updateTrainer(@PathParam("trainerId") Integer id, TfTrainer trainer,
	                                @HeaderParam("Authorization") String token) {
		logger.info("updateTrainer()...");
		Claims payload = JWTService.processToken(token);
		if (trainer == null)
			return Response.status(Status.NO_CONTENT).build();
		else if (payload == null || payload.getId().equals("2") || payload.getId().equals("5"))
			return Response.status(Status.UNAUTHORIZED).entity(JWTService.invalidTokenBody(token)).build();
		else {
			trainerService.updateTrainer(trainer);
			return Response.status(Status.ACCEPTED).build();
		}
	}
}