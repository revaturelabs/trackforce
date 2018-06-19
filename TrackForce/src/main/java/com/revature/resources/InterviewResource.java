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

import org.apache.maven.shared.invoker.SystemOutHandler;
import org.hibernate.HibernateException;

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
	 *         <p>
	 *         </p>
	 * @version v6.18.06.13
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
			return Response.status(Status.UNAUTHORIZED).build(); // invalid token
		} else if (payload.getId().equals("5")) {
			return Response.status(Status.FORBIDDEN).build();
		} else {
			status = interviews == null || interviews.isEmpty() ? Status.NO_CONTENT : Status.OK;
			logger.info("	interviews.size() = " + interviews.size());
		}
		return Response.status(status).entity(interviews).build();
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
	@Path("/{associateid}")
	@POST
	@ApiOperation(value = "Creates interview", notes = "Creates an interview for a specific associate based on associate id. Returns 201 if successful, 403 if not.")
	public Response createInterview(@PathParam("associateid") int associateid,
			@HeaderParam("Authorization") String token, TfInterview interview) {
		logger.info("createInterview()...");
		Status status = null;
		Claims payload = JWTService.processToken(token);

		if (payload == null || !(payload.getId().equals("1") || !(payload.getId().equals("5")))) {
			status = Status.UNAUTHORIZED;
		} else {
			interviewService.createInterview(interview);
			status = Status.CREATED;
		}

		return Response.status(status).build();
	}

	/**
	 * @author Adam L.
	 *         <p>
	 *         </p>
	 * @version v6.18.06.13
	 * 
	 * @param token
	 * @param associateId
	 * @return
	 * @throws HibernateException
	 * @throws IOException
	 */
	@Path("/{associateid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Returns all interviews for an associate", notes = "Returns a list of all interviews.")
	public Response getAllInterviews(@HeaderParam("Authorization") String token,
			@PathParam("associateid") Integer associateId) throws HibernateException, IOException {
		logger.info("getAllInterviews()...");
		Status status = null;
		List<TfInterview> interviews = interviewService.getInterviewsByAssociate(associateId);
		Claims payload = JWTService.processToken(token);

		if (payload == null) { // invalid token

			status = Status.UNAUTHORIZED;
		} else if (!(payload.getId().equals("1") || payload.getId().equals("5"))) { // wrong roleid
			status = Status.FORBIDDEN;
		} else {
			logger.info(interviews);
			status = interviews == null || interviews.isEmpty() ? Status.NO_CONTENT : Status.OK;
		}

		return Response.status(status).entity(interviews).build();

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
	@Path("/{interviewid}")
	@ApiOperation(value = "updates interview", notes = " Updates interview")
	@PUT
	public Response updateInterview(@PathParam("interviewid") int interviewId,
			@HeaderParam("Authorization") String token, TfInterview interview) {
		logger.info("updateInterview()...");
		Status status = null;
		Claims payload = JWTService.processToken(token);

		if (payload == null) { // invalid token
			status = Status.UNAUTHORIZED;
		} else if (!(payload.getId().equals("1") || payload.getId().equals("5"))) { // wrong roleid
			status = Status.FORBIDDEN;
		} else {
			interviewService.updateInterview(interview);
			status = Status.ACCEPTED;
		}

		return Response.status(status).build();
	}
}