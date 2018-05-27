package com.revature.resources;

import java.io.IOException;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.hibernate.HibernateException;

import com.revature.model.InterviewInfo;
import com.revature.services.InterviewService;

import io.swagger.annotations.Api;

@Path("interviews")
@Api(value = "AllInterviews")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AllInterviewResource {

	private InterviewService iservice = new InterviewService();

	@GET
	public Response getAllInterviews() {
		Set<InterviewInfo> interviews = null;
		try {
			interviews = iservice.getAllInterviews();
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
		}
		Status status = interviews == null || interviews.isEmpty() ? Status.NO_CONTENT : Status.OK;
		return Response.status(status).entity(interviews).build();

	}
}
