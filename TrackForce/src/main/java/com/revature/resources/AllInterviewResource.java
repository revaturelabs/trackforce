package com.revature.resources;

import static com.revature.utils.LogUtil.logger;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.hibernate.HibernateException;

import com.revature.model.InterviewInfo;
import com.revature.services.InterviewService;

@Path("/interviews")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AllInterviewResource {
	
	private InterviewService iservice = new InterviewService();
	
	@GET
	public Response getAllInterviews(@QueryParam("sort_by") String sortBy) throws HibernateException, IOException {
		//TODO handle exception
		Set<InterviewInfo> interviews = iservice.getAllInterviews();
		Status status = interviews == null || interviews.isEmpty() ? Status.NO_CONTENT : Status.OK;
		logger.info("	sortBy = " + sortBy);
		return Response.status(status).entity(interviews).build();
	}
}
