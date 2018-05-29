package com.revature.resources;

import static com.revature.utils.LogUtil.logger;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.revature.model.InterviewInfo;
import com.revature.services.InterviewService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("interviews")
@Api(value = "AllInterviews")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AllInterviewResource {
	private static final InterviewService iservice = new InterviewService();

	/**
	 * 
	 * @param sort
	 *            optional, enter `?sort=desc` for most recent interviews first,
	 *            "asc" for opposite
	 * @return
	 */
	@GET
	@ApiOperation(value = "returns all interviews", notes = "Gets a list of all interviews that can be sorted in ascending or descending order based on date.")
	public Response getAllInterviews(@QueryParam("sort") String sort) {
		Collection<InterviewInfo> interviews = sort != null ? iservice.getAllInterviews(sort)
				: iservice.getAllInterviews();
		Status status = interviews == null || interviews.isEmpty() ? Status.NO_CONTENT : Status.OK;

		logger.info("	interviews.size() = " + interviews.size());
		return Response.status(status).entity(interviews).build();
	}
}
