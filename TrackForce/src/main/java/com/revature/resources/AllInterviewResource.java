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

import com.revature.model.InterviewInfo;
import com.revature.services.InterviewService;
import com.revature.services.JWTService;

import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("interviews")
@Api(value = "AllInterviews")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AllInterviewResource {
	private static final InterviewService iservice = new InterviewService();

	/**
	 * @author Ian Buitrago
	 * @param sort
	 *            optional, enter `?sort=desc` for most recent interviews first,
	 *            "asc" for opposite
	 * @return
	 */
	@GET
	@ApiOperation(value = "returns all interviews", notes = "Gets a list of all interviews that can be sorted in ascending or descending order based on date.")
	public Response getAllInterviews(@HeaderParam("Authorization") String token, @QueryParam("sort") String sort) {
		logger.info("getAllInterviews()...");
		Status status = null;
		Claims payload = JWTService.processToken(token);
		Collection<InterviewInfo> interviews = null;

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
