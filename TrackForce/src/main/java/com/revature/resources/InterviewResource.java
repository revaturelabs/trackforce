package com.revature.resources;
import com.revature.entity.TfInterview;
import com.revature.services.InterviewService;
import com.revature.services.JWTService;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hibernate.HibernateException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.List;
import static com.revature.utils.LogUtil.logger;

/** @author Mitchell H's PC, Adam L.
 * @version v6.18.06.13 */
@Path("/interviews")
@Api(value = "Interviews")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InterviewResource
{
	// You're probably thinking, why would you ever do this? Why not just just make the methods all static in the service class?
	// This is to allow for Mockito tests, which have problems with static methods
	// - Adam 06.18.06.13
	private InterviewService interviewService = new InterviewService();

	/** @author Ian Buitrago, Adam L.
	 * @version v6.18.06.13 */
	@GET
	@ApiOperation(value = "returns all interviews",
			notes = "Gets a list of all interviews that can be sorted in ascending or descending order based on date.")
	public Response getAllInterviews(@HeaderParam("Authorization") String token, @QueryParam("sort") String sort) {
		logger.info("getAllInterviews()...");
		Status status;
		Claims payload = JWTService.processToken(token);
		List<TfInterview> interviews = interviewService.getAllInterviews();

		if (payload == null) // invalid token
			return Response.status(Status.UNAUTHORIZED).entity(JWTService.invalidTokenBody(token)).build();
		else if (payload.getId().equals("5"))
			return Response.status(Status.FORBIDDEN).build();
		else {
			status = interviews == null || interviews.isEmpty() ? Status.NO_CONTENT : Status.OK;
			logger.info("	interviews.size() = " + interviews.size());
		}
		return Response.status(status).entity(interviews).build();
	}

	/** @author Adam L.
	 * @version v6.18.06.13 */
	@Path("/{associateid}")
	@POST
	@ApiOperation(value = "Creates interview",
			notes = "Creates an interview for a specific associate based on associate id. Returns 201 if successful, 403 if not.")
	public Response createInterview(@PathParam("associateid") int associateid,
			@HeaderParam("Authorization") String token, TfInterview interview) {
		logger.info("createInterview()...");
		Status status;
		Claims payload = JWTService.processToken(token);

		if (payload == null || !(payload.getId().equals("5")))
			status = Status.UNAUTHORIZED;
		else {
			interview.setJobDescription("No current description.");
			interviewService.createInterview(interview);
			status = Status.CREATED;
		}
		return Response.status(status).build();
	}

	/** @author Adam L.
	 * @version v6.18.06.13 */
	@Path("/{associateid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Returns all interviews for an associate", notes = "Returns a list of all interviews.")
	public Response getAllInterviews(@HeaderParam("Authorization") String token,
			@PathParam("associateid") Integer associateId) throws HibernateException {
		logger.info("getAllInterviews()...");
		Status status;
		List<TfInterview> interviews = interviewService.getInterviewsByAssociate(associateId);
		Claims payload = JWTService.processToken(token);
		if (payload == null) // invalid token
			status = Status.UNAUTHORIZED;
		else {
			logger.info(interviews);
			status = interviews == null || interviews.isEmpty() ? Status.NO_CONTENT : Status.OK;
		}
		return Response.status(status).entity(interviews).build();
	}
	
	/** @author RayR
	 * @version v6.18.06.13 */
	@Path("/getInterviewById/{interviewId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Returns an interview by id", notes = "Returns an interview.")
	public Response getInterviewById(@HeaderParam("Authorization") String token,
			@PathParam("interviewId") Integer interviewId) throws HibernateException {
		logger.info("getInterviewById()...");
		Status status;
		TfInterview interview = interviewService.getInterviewById(interviewId);
		Claims payload = JWTService.processToken(token);

		if (payload == null) // invalid token
			status = Status.UNAUTHORIZED;
		else if (!(payload.getId().equals("1") || payload.getId().equals("5") || payload.getId().equals("3")))
			status = Status.FORBIDDEN; // wrong roleid
		else {
			logger.info(interview);
			status = interview == null ? Status.NO_CONTENT : Status.OK;
		}
		return Response.status(status).entity(interview).build();
	}

	/** @author Adam L.
	 * @version v6.18.06.13 */
	@Path("/{interviewid}")
	@ApiOperation(value = "updates interview", notes = "Updates interview")
	@PUT
	public Response updateInterview(@PathParam("interviewid") int interviewId,
			@HeaderParam("Authorization") String token, TfInterview interview) {
		logger.info("updateInterview()...");
		Status status;
		Claims payload = JWTService.processToken(token);

		if (payload == null) // invalid token
			status = Status.UNAUTHORIZED;
		else if (!(payload.getId().equals("1") || payload.getId().equals("5") || payload.getId().equals("3")))
			status = Status.FORBIDDEN;// wrong roleid
		else {
			interviewService.updateInterview(interview);
			status = Status.ACCEPTED;
		}
		return Response.status(status).build();
	}
}