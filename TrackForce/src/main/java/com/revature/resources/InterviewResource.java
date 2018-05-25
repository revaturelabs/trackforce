package com.revature.resources;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.revature.services.AssociateService;
import com.revature.services.InterviewService;
import com.revature.services.JWTService;

@Path("/")
public class InterviewResource {
	
	private AssociateService service = new AssociateService();
	private JWTService jService = new JWTService();
	private static InterviewService is = new InterviewService();
	
	@POST
	public Response createInterview(@PathParam("associateid") int associateid) {
	
	//is.addInterviewByAssociate(associateid, ifc);
	return Response.status(201).build();
	}
	
	@Path("/{interviewid}")
	@GET Response getClientInterview(@PathParam("interviewid") int interviewid) {
		return Response.status(200).build();
	}
}
