package com.revature.resources;

import static com.revature.utils.LogUtil.logger;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.revature.entity.TfBatch;
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
 * @version.date v06.2018.06.13
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
	// - Adam 06.2018.06.13
	AssociateService associateService = new AssociateService();
	BatchService batchService = new BatchService();
	ClientService clientService = new ClientService();
	CurriculumService curriculumService = new CurriculumService();
	InterviewService interviewService = new InterviewService();
	TrainerService trainerService = new TrainerService();
	UserService userService = new UserService();
	
//	@Path("/batch")
//	@POST
//	@Consumes("application/json")
//	@ApiOperation(value = "Displays the batch from the trainer", notes = "")
//	public Response getBatchFromTrainer(TfTrainer trainer) {
//		logger.info("getting batch from trainers...");
//		LogUtil.logger.info(trainer);
//		List<TfBatch> batches = trainer.getPrimary();
//		return Response.created(URI.create("/testingURIcreate")).build();
//	}
//	
//	
//	@Path("/cotrainerbatch")
//	@POST
//	@Consumes("application/json")
//	@ApiOperation(value = "Displays the batches that the trainer is cotrainer on", notes = "")
//	public Response getBatchFromCotrainer(TfTrainer trainer) {
//		logger.info("getting batch from trainers...");
//		LogUtil.logger.info(trainer);
//		List<TfBatch> batches = trainer.getCoTrainer();
//		return Response.created(URI.create("/testingURIcreate")).build();
//	}
	
	
	
}
