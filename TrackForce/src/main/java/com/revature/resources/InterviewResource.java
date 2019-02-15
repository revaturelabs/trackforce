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
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.hibernate.HibernateException;

import com.revature.entity.TfAssociate;
import com.revature.entity.TfInterview;
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
	public AssociateService getAssociateService() { return new AssociateService(); }
	public InterviewService getInterviewService() { return new InterviewService(); }
	public UserService getUserService() { return new UserService(); }

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

		StringBuilder logMessage = new StringBuilder("Call Method getAllInterviews()");
		Status status = null;

		if (authorized(token, 1, 3, 4)) {
			List<TfInterview> interviews = getInterviewService().getAllInterviews();
			status = (interviews == null || interviews.isEmpty()) ? Status.NO_CONTENT : Status.OK;
			logMessage.append("\n	interviews.size() = " + ((interviews != null) ? interviews.size() : "null"));
			logger.info(logMessage);
			logger.info("Returning ALL Interviews. Admin/Sales/Staging roles only.");
			return responseStatus(status).entity(interviews).build();
		} else {
			logger.info(logMessage);
			logger.error("User had insufficent privileges. Unathorized access.");
			return responseStatus(Status.UNAUTHORIZED).entity(jwtInvalidToken(token)).build();
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
			interview.setAssociate(getAssociateService().getAssociate(associateid));
			interview.setJobDescription("No current description.");
			getInterviewService().createInterview(interview);
			status = Status.CREATED;
		} else {
			logger.error("User not Authenticated. Unathorized access.");
			status = Status.UNAUTHORIZED;
		}

		logger.info("Interview created for Associate: " + associateid);
		return responseStatus(status).build();
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
			List<TfInterview> interviewList = getInterviewService().getInterviewsByAssociate(associateId);
			logger.info("Associate: " + associateId + " list has this data ->"+interviewList);
			return responseStatus(Status.OK).entity(interviewList).build();
		} else {
			logger.error("User not Authenticated. Unathorized access.");
			return responseStatus(Status.UNAUTHORIZED).entity(jwtInvalidToken(token)).build();
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

		TfInterview interview = getInterviewService().getInterviewById(interviewId);

		if (interview != null && canAccessInterview(token, interview.getAssociate().getId())) {
			logger.info("Returning Interview: " + interviewId);
			return responseStatus(Status.OK).entity(interview).build();
		} else {
			logger.error("User not Authenticated. Unathorized access.");
			return responseStatus(Status.UNAUTHORIZED).entity(jwtInvalidToken(token)).build();
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

		if (interview == null) {
			logger.error("Interview object was null.");
			return responseStatus(Status.BAD_REQUEST).build();
		}
		/*
		 * FIXME - Not sure why the interview ID was passed in as a path param with the interview
		 * object, the interview object should have the interview ID already. There is no setter for
		 * interviewId so the one present in the object passed in will be used,
		 * regardless of the one in the path parameter
		 */
		
		if (canAccessInterview(token, interview.getAssociate().getId())) {
			getInterviewService().updateInterview(interview);
			logger.info("Updated Interview: " +interviewId);
			return responseStatus(Status.ACCEPTED).build();
		} else {
			logger.error("User not Authenticated. Unathorized access.");
			return responseStatus(Status.UNAUTHORIZED).entity(jwtInvalidToken(token)).build();
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
	public boolean canAccessInterview(String token, int associateId) {
		Claims payload = jwtProcessToken(token);
		if (payload == null) {
			logger.error("Payload was null.");
			return false;
		}
		int role = 0;
		try {
			role = parseRole((String)payload.get("roleID"));
		} catch (NumberFormatException nfe) {
			logger.error("RoleId was not an integer.");
			logger.error(nfe.getMessage());
			return false;
		}
		// These roles have unlimited access to interviews
        //v1811: role == 2 was temp added to bypass a nullpointerexception thrown
        // when the trainer does not have a batch assigned to them
        // When trainers can be properly assigned to batches or updated for certain batches,
        // then removal of role==2 can be done to allow the trainer only to view their batches' associates.
        //v1811: Currently throws a nullpointerexception if the trainer does not have a batch assigned to them.
        // Does not hit this point because the previous group check of role types returns true for trainer.
		// These roles have unlimited access to interviews
		if (role == 1 || role == 2 || role == 3 || role == 4)
			return true;

		TfUser user = getUserService().getUser(payload.getSubject());
		TfAssociate assoc = getAssociateService().getAssociateByUserId(user.getId());

		if (role == 5) {
			logger.info("Role was Associate. Obtaining associate of this id: " + associateId);
			return (assoc.getId() == associateId);
		} else {//if (role == 2) {
//			TfTrainer trainer = getTrainerService().getTrainerByUserId(user.getId());
//			logger.info("Role was Trainer. Getting all batch associates.");
//			return (trainer.getPrimary().contains(assoc.getBatch()));
//		} else {
			logger.error("RoleId was not in range of 1-5.");
			return false; // Somehow a user ended up with a role not in [1,5]
		}
	}
	
	/**
	 * Calls UserService.Authorized to determine if a user is authorized. 
	 * Takes varargs ints specifying which User IDs (1=admin, 5=associate) are allowed on the page.
	 * Allows for Mockito testing 
	 * @author Michael Tinning, Batch1811
	 * @param token
	 * @param ids
	 * @return true if user token is allowed on the page, else false
	 */
	public Boolean authorized(String token, int ... ids) {
		return UserAuthentication.Authorized(token, ids);
	}
	public ResponseBuilder responseStatus(Status status) {
		return Response.status(status);
	}
	public Claims jwtProcessToken(String token) {
		return JWTService.processToken(token);
	}
	public Integer parseRole(String role) {
		return Integer.parseInt(role);
	}
	public String jwtInvalidToken(String token) {
		return JWTService.invalidTokenBody(token);
	}
}