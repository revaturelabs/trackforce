package com.revature.resources;

import static com.revature.utils.LogUtil.logger;

import java.util.Collection;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.revature.entity.TfInterview;
import com.revature.services.AssociateService;
import com.revature.services.BatchService;
import com.revature.services.ClientService;
import com.revature.services.CurriculumService;
import com.revature.services.InterviewService;
import com.revature.services.JWTService;
import com.revature.services.TrainerService;
import com.revature.services.UserService;

import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * <p> </p>
 * @version.date v06.2018.06.13
 */
@Path("interviews")
@Api(value = "AllInterviews")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AllInterviewResource {
	
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
	
	/**
	 * 
	 * @author Ian Buitrago, Adam L. 
	 * <p> </p>
	 * @version.date v06.2018.06.13
	 * 
	 * @param token
	 * @param sort
	 * @return
	 */
	@GET
	@ApiOperation(value = "returns all interviews", notes = "Gets a list of all interviews that can be sorted in ascending or descending order based on date.")
	public Response getAllInterviews(@HeaderParam("Authorization") String token, @QueryParam("sort") String sort) {
		
		logger.info("getAllInterviews()...");

		Status status = null;
		Claims payload = JWTService.processToken(token);
		List<TfInterview> interviews = interviewService.getAllInterviews();


		
		if (payload == null) {
			status = Status.UNAUTHORIZED;		// invalid token
		}else if (payload.getId().equals("5")) {
			status = Status.FORBIDDEN;
		} else {
			interviews = sort != null ? iservice.getAllInterviews(sort) : iservice.getAllInterviews();
			status = interviews == null || interviews.isEmpty() ? Status.NO_CONTENT : Status.OK;
			logger.info("	interviews.size() = " + interviews.size());
		}
		return Response.status(status).entity(interviews).build();
	}
}
