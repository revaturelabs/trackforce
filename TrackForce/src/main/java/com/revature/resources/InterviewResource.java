package com.revature.resources;

import static com.revature.utils.LogUtil.logger;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.hibernate.HibernateException;

import com.revature.entity.TfAssociate;
import com.revature.entity.TfInterview;
import com.revature.entity.TfTrainer;
import com.revature.entity.TfUser;
import com.revature.services.AssociateService;
import com.revature.services.BatchService;
import com.revature.services.ClientService;
import com.revature.services.CurriculumService;
import com.revature.services.InterviewService;
import com.revature.services.JWTService;
import com.revature.services.TrainerService;
import com.revature.services.UserService;
import com.revature.utils.UserAuthentication;

import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author Mitchell H's PC, Adam L.
 *         <p>
 *         </p>
 * @version v6.18.06.13
 *
 */
@Path("/interviews")
@Api(value = "Interviews")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InterviewResource {

	// You're probably thinking, why would you ever do this? Why not just just make
	// the methods all static in the service class?
	// This is to allow for Mockito tests, which have problems with static methods
	// This is here for a reason!
	// - Adam 06.18.06.13
	static AssociateService associateService = new AssociateService();
	static BatchService batchService = new BatchService();
	static ClientService clientService = new ClientService();
	static CurriculumService curriculumService = new CurriculumService();
	static InterviewService interviewService = new InterviewService();
	static TrainerService trainerService = new TrainerService();
	static UserService userService = new UserService();

	/**
	 * 
	 * @author Ian Buitrago, Adam L.
	 *         <p>
	 *         </p>
	 * @version v6.18.06.13
	 * 
	 *          Roles that can get all interviews: Admin, Sales, Staging
	 * @param token
	 * @param sort
	 * @return
	 */
	@GET
	@ApiOperation(value = "returns all interviews", notes = "Gets a list of all interviews that can be sorted in ascending or descending order based on date.")
	public Response getAllInterviews(@HeaderParam("Authorization") String token, @QueryParam("sort") String sort) {

		StringBuilder logMessage = new StringBuilder("getAllInterviews()...");
		Status status = null;

		if (UserAuthentication.Authorized(token, new int[] { 1, 3, 4 })) {
			List<TfInterview> interviews = interviewService.getAllInterviews();
			status = (interviews == null || interviews.isEmpty()) ? Status.NO_CONTENT : Status.OK;
			logMessage.append("\n	interviews.size() = " + interviews.size());
			logger.info(logMessage);
			return Response.status(status).entity(interviews).build();
		} else {
			logger.info(logMessage);
			return Response.status(Status.UNAUTHORIZED).entity(JWTService.invalidTokenBody(token)).build();
		}

	}

	/**
	 * @author Adam L.
	 *         <p>
	 *         </p>
	 * @version v6.18.06.13
	 * 
	 * @param associateid
	 * @param token
	 * @param interview
	 * @return
	 */
	@Path("/create/{associateid}")
	@POST
	@ApiOperation(value = "Creates interview", notes = "Creates an interview for a specific associate based on associate id. Returns 201 if successful, 401 if not.")
	public Response createInterview(@PathParam("associateid") int associateid, @HeaderParam("Authorization") String token, TfInterview interview) {
		logger.info("createInterview()...");
		Status status = null;
		
		if (canAccessInterview(token, associateid)) {
			interview.setAssociate(associateService.getAssociate(associateid));
			interview.setJobDescription("No current description.");
			interviewService.createInterview(interview);
			status = Status.CREATED;
		} else {
			status = Status.UNAUTHORIZED;
		}

		return Response.status(status).build();
	}

	/**
	 * @author Adam L.
	 *         <p>
	 *         </p>
	 * @version v6.18.06.13
	 * 
	 *          Role restrictions: Associate can get only their interviews Trainer
	 *          can get all of their batches associates interviews Other roles have
	 *          unlimited access to this resource
	 * 
	 * @param token
	 * @param associateId
	 * @return
	 * @throws HibernateException
	 * @throws IOException
	 */
	@Path("/associate/{associateid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Returns all interviews for an associate", notes = "Returns a list of all interviews.")
	public Response getAllInterviews(@HeaderParam("Authorization") String token, @PathParam("associateid") Integer associateId) throws HibernateException, IOException {
		logger.info("getAllInterviews()...");

		if (canAccessInterview(token, associateId)) {
			List<TfInterview> interviewList = interviewService.getInterviewsByAssociate(associateId);
			logger.info("list has this data ->"+interviewList);
			return Response.status(Status.OK).entity(interviewList).build();
		} else {
			return Response.status(Status.UNAUTHORIZED).entity(JWTService.invalidTokenBody(token)).build();
		}
	}

	/**
	 * An associate can only get their own interviews A trainer can only get their
	 * associates interviews Other roles can get all interviews
	 * 
	 * @param token       All requests require a valid login token
	 * @param interviewId The primary key for the interview to fetch
	 * @return A JSON string representing a TfInterview object, or null if either
	 *         one does not exist matching the ID provided or access is not allowed
	 */
	@Path("/getInterviewById/{interviewId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Returns an interview by id", notes = "Returns an interview.")
	public Response getInterviewById(@HeaderParam("Authorization") String token, @PathParam("interviewId") Integer interviewId) throws HibernateException, IOException {
		logger.info("getInterviewById()...");

		TfInterview interview = interviewService.getInterviewById(interviewId);

		if (interview != null && canAccessInterview(token, interview.getAssociate().getId())) {
			return Response.status(Status.OK).entity(interview).build();
		} else {
			return Response.status(Status.UNAUTHORIZED).entity(JWTService.invalidTokenBody(token)).build();
		}
	}

	/**
	 * @author Adam L.
	 *         <p>
	 *         </p>
	 * @version v6.18.06.13
	 * 
	 * @param associateid
	 * @param interviewId
	 * @param token
	 * @param interview
	 * @return
	 */
	@Path("/update/{interviewid}")
	@ApiOperation(value = "updates interview", notes = " Updates interview")
	@PUT
	public Response updateInterview(@PathParam("interviewid") int interviewId, @HeaderParam("Authorization") String token, TfInterview interview) {
		logger.info("updateInterview()...");

		if (interview == null)
			return Response.status(Status.BAD_REQUEST).build();

		/*
		 * FIXME - Not sure why the interview ID was passed in as a path param with the interview
		 * object, the interview object should have the interview ID already. There is no setter for
		 * interviewId so the one present in the object passed in will be used,
		 * regardless of the one in the path parameter
		 */
		
		if (canAccessInterview(token, interview.getAssociate().getId())) {
			interviewService.updateInterview(interview);
			return Response.status(Status.ACCEPTED).build();
		} else {
			return Response.status(Status.UNAUTHORIZED).entity(JWTService.invalidTokenBody(token)).build();
		}
	}

	/**
	 * Given an associate Id and a login token, determine if we have access to the
	 * interviews for this associate
	 * 
	 * @param token
	 * @param associateId
	 * @return
	 */
	private boolean canAccessInterview(String token, int associateId) {
		Claims payload = JWTService.processToken(token);
		if (payload == null)
			return false;

		int role = 0;
		try {
			role = Integer.parseInt((String)payload.get("roleID"));
		} catch (NumberFormatException nfe) {
			return false;
		}

		// These roles have unlimited access to interviews
		if (role == 1 || role == 3 || role == 4)
			return true;

		TfUser user = userService.getUser(payload.getSubject());
		TfAssociate assoc = associateService.getAssociateByUserId(user.getId());

		if (role == 5) {
			return (assoc.getId() == associateId);
		} else if (role == 2) {
			TfTrainer trainer = trainerService.getTrainerByUserId(user.getId());
			return (trainer.getPrimary().contains(assoc.getBatch()));
		} else {
			return false; // Somehow a user ended up with a role not in [1,5]
		}
	}
}