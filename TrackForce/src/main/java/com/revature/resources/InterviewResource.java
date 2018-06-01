package com.revature.resources;

import static com.revature.utils.LogUtil.logger;

import java.io.IOException;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
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

import org.apache.commons.httpclient.HttpClient;
import org.hibernate.HibernateException;

import com.revature.dao.InterviewDaoHibernate;
import com.revature.entity.TfInterview;
import com.revature.model.InterviewInfo;
import com.revature.request.model.InterviewFromClient;
import com.revature.services.AssociateService;
import com.revature.services.InterviewService;
import com.revature.services.JWTService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author Mitchell H's PC
 * 
 *         The different types of users Admin: 1 Trainer: 2 Sales/Delivery 3
 *         Staging Manager 4 Associate 5
 */

@Path("/")
@Api(value = "Interviews")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InterviewResource {
	private AssociateService service = new AssociateService();
	private JWTService jService = new JWTService();
	private static InterviewService is = new InterviewService();

	@GET
	@ApiOperation(value = "Returns all interviews for an associate", notes = "Returns a list of all interviews.")
	public Response getAllInterviews(@HeaderParam("Authorization") String token, @QueryParam("start") Long startDate,
			@QueryParam("end") Long endDate) throws HibernateException, IOException {
		// TODO handle exception
		Status status = null;
		Set<InterviewInfo> interviews = null;
		Claims payload = JWTService.processToken(token);

		if (payload == null || !payload.getId().equals("1")) {
			status = Status.UNAUTHORIZED;
		}

		else {
			interviews = is.getAllInterviews();
			status = interviews == null || interviews.isEmpty() ? Status.NO_CONTENT : Status.OK;
			logger.info("inside get all interviews");
		}

		return Response.status(status).entity(interviews).build();
		
	}

	@GET
	@ApiOperation(value = "Returns an interview", notes = "Returns a specific interview by id.")
	@Path("/{interviewid}")
	public Response getAssociateInterviews(@PathParam("associateid") Integer associateid,
			@PathParam("interviewid") Integer interviewid, @HeaderParam("Authorization") String token) {
		logger.info(token);

		Claims claims = null;
		logger.info("Before the try block");
		try {
			logger.info("In the try block");
			if (token == null) {
				throw new UnsupportedJwtException("token null");
			}
			claims = jService.getClaimsFromToken(token);
			logger.info("Print claims " + claims);

		} catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException
				| IllegalArgumentException | NullPointerException e) {
			logger.info("in the catch block");
			e.printStackTrace();
			return Response.status(403).build();
		}

		if (claims.getId().equals("1")) {
			Set<InterviewInfo> associateinfo = service.getInterviewsByAssociateAndInterviewid(associateid, interviewid);
			return Response.ok(associateinfo).build();
		} else {
			return Response.status(403).build();
		}
	}

	// TODO: change the Form params to be whatever is being sent
	// TODO: create an InterviewFromClient object with the form param arguments
	@POST
	@ApiOperation(value = "Creates interview", notes = "Creates an interview for a specific associate based on associate id. Returns 201 if successful, 403 if not.")
	public Response createInterview(@PathParam("associateid") int associateid,
			@HeaderParam("Authorization") String token, InterviewFromClient ifc) {
		Status status = null;
		Claims payload = JWTService.processToken(token);

		if (payload == null || !payload.getId().equals("1")) {
			status = Status.UNAUTHORIZED;
		} else {
			is.addInterviewByAssociate(associateid, ifc);
			// does service actually work?
			status = Status.CREATED;
		}

		return Response.status(status).build();
	}

	@PUT
	@Path("/{interviewid}/twentyfourFlag")
	public Response twentyfourFlag(TfInterview flagInterview, @HeaderParam("Authorization") String token,
			@PathParam("associateid") Integer associateid, @PathParam("interviewid") Integer interviewid) {
		Status status = null;
		Claims payload = JWTService.processToken(token);

		if (payload == null || !payload.getId().equals("1") || !payload.getId().equals("5")) {
			status = Status.UNAUTHORIZED;
		} else {
			InterviewDaoHibernate id = new InterviewDaoHibernate();
			id.updateInterview(flagInterview);
			// does service actually work?
			status = Status.ACCEPTED;
		}
		return Response.status(status).build();
	}

	@PUT
	@Path("/{interviewid}/interview-went")
	public Response interviewWent(TfInterview interviewWent, @HeaderParam("Authorization") String token,
			@PathParam("associateid") Integer associateid, @PathParam("interviewid") Integer interviewid) {
		Status status = null;
		Claims payload = JWTService.processToken(token);

		if (payload == null || !payload.getId().equals("1") || !payload.getId().equals("5")) {
			status = Status.UNAUTHORIZED;
		} else {
			InterviewDaoHibernate id = new InterviewDaoHibernate();
			id.updateInterview(interviewWent);
			// does service actually work?
			status = Status.ACCEPTED;
		}
		return Response.status(status).build();
	}

	@PUT
	@Path("/{interviewid}/client-feedback")
	public Response clientFeedback(TfInterview clientFeedback, @HeaderParam("Authorization") String token,
			@PathParam("associateid") Integer associateid, @PathParam("interviewid") Integer interviewid) {
		Status status = null;
		Claims payload = JWTService.processToken(token);

		if (payload == null || !payload.getId().equals("1") || !payload.getId().equals("3")) {
			status = Status.UNAUTHORIZED;
		} else {
			InterviewDaoHibernate id = new InterviewDaoHibernate();
			id.updateInterview(clientFeedback);
			// does service actually work?
			status = Status.ACCEPTED;
		}
		return Response.status(status).build();
	}

	@GET
	@Path("/{interviewid}/client-feedback")
	public Response getFeedback(TfInterview feedback, @HeaderParam("Authorization") String token,
			@PathParam("associateid") Integer associateid, @PathParam("interviewid") Integer interviewid) {
		Status status = null;
		Claims payload = JWTService.processToken(token);

		if (payload == null || !payload.getId().equals("1") || !payload.getId().equals("3")) {
			status = Status.UNAUTHORIZED;
		} else if (feedback.getTfIsClientFeedbackVisible() == 1) {
			InterviewDaoHibernate id = new InterviewDaoHibernate();
			id.getInterviewById(feedback.getTfInterviewId());
			// does service actually work?
			status = Status.ACCEPTED;
		}
		return Response.status(status).build();
	}

	@Path("/{interviewid")
	@ApiOperation(value = "updates interview", notes = " Updates interview")
	@PUT
	public Response updateInterview(@PathParam("associateid") int associateid,
			@PathParam("interviewid") int interviewid, @HeaderParam("Authorization") String token,
			TfInterview changeInterview) {
		Status status = null;
		Claims payload = JWTService.processToken(token);

		if (payload == null || !payload.getId().equals("1") || !payload.getId().equals("5")) {
			status = Status.UNAUTHORIZED;
		}

		else {
			InterviewDaoHibernate hd = new InterviewDaoHibernate();
			// If parameter for TfInterview works,
			hd.updateInterview(changeInterview);
			status = Status.ACCEPTED;
		}

		return Response.status(204).build();
	}
}