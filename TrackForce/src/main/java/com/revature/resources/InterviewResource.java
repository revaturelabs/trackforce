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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

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
import io.restassured.http.ContentType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * @author Mitchell H's PC, Adam L. 
 * <p> </p>
 * @version.date v06.2018.06.13
 *
 */
@Path("/")
@Api(value = "Interviews")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InterviewResource {

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
	 * @author Adam L. 
	 * <p> </p>
	 * @version.date v06.2018.06.13
	 * 
	 * @param associateid
	 * @param token
	 * @param interview
	 * @return
	 */
	@Path("/{associateid}/verify")
	@POST
	@ApiOperation(value = "Creates interview", notes = "Creates an interview for a specific associate based on associate id. Returns 201 if successful, 403 if not.")
	public Response createInterview(@PathParam("associateid") int associateid,
			@HeaderParam("Authorization") String token, TfInterview interview) {
		logger.info("createInterview()...");
		Status status = null;
		Claims payload = JWTService.processToken(token);

		if (payload == null || !(payload.getId().equals("1") || payload.getId().equals("5"))) {
			status = Status.UNAUTHORIZED;
		} else {
			interviewService.createInterview(interview);
			status = Status.CREATED;
		}

		return Response.status(status).build();
	}

	/**
	 * @author Adam L. 
	 * <p> </p>
	 * @version.date v06.2018.06.13
	 * 
	 * @param token
	 * @param associateId
	 * @return
	 * @throws HibernateException
	 * @throws IOException
	 */
	@Path("/associate/{associateid}/interviews")
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
	 * <p> </p>
	 * @version.date v06.2018.06.13
	 * 
	 * @param interviewid
	 * @param interviewId
	 * @param token
	 * @return
	 * @throws IOException
	 */
	@GET
	@ApiOperation(value = "Returns an interview", notes = "Returns a specific interview by id.")
	@Path("/{interviewid}")
	public Response getAssociateInterview(@PathParam("interviewid") Integer interviewid,
			@PathParam("iid") Integer interviewId, @HeaderParam("Authorization") String token)
			throws IOException {
		logger.info("getAssociateInterview()...");
		Status status = null;
		Claims payload = JWTService.processToken(token);
		TfInterview interview = interviewService.getInterviewById(interviewId);

		if (payload == null) { // invalid token
			status = Status.UNAUTHORIZED;
		} else if (!(payload.getId().equals("1") || payload.getId().equals("5"))) { // wrong roleid
			status = Status.FORBIDDEN;
		} else {
			logger.info("inside get associate interview else");
			status = interview == null ? Status.NO_CONTENT : Status.OK;
		}

		return Response.status(status).entity(interview).build();
	}


	/**
	 * @author Adam L. 
	 * <p> </p>
	 * @version.date v06.2018.06.13
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
	public Response updateInterview(@PathParam("associateid") int associateid,
			@PathParam("interviewid") int interviewId, @HeaderParam("Authorization") String token,
			TfInterview interview) {
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