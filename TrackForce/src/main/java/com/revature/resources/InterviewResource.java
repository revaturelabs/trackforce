package com.revature.resources;
import static com.revature.utils.LogUtil.logger;

import java.io.IOException;
import java.util.Set;
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

import com.revature.model.BatchInfo;
import com.revature.model.InterviewInfo;
import com.revature.services.AssociateService;
import com.revature.services.InterviewService;
import com.revature.services.JWTService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;


import io.swagger.annotations.Api;

@Path("/")
@Api(value = "Interviews")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InterviewResource {
	
	private AssociateService service = new AssociateService();
	private JWTService jService = new JWTService();
	private static InterviewService is = new InterviewService();
	
	//TODO: maybe get rid of this code
//	@GET
//	public Response getAllInterviews(@QueryParam("start") Long startDate,
//			@QueryParam("end") Long endDate) throws HibernateException, IOException {
//		//TODO handle exception
//		Set<InterviewInfo> interviews = is.getAllInterviews();
//		Status status = interviews == null || interviews.isEmpty() ? Status.NO_CONTENT : Status.OK;
//		System.out.println("inside get all interviews");
//		return Response.status(status).entity(interviews).build();
//	}
	
	@GET
	@Path("/{interviewid}")
	public Response getAssociateInterviews(@PathParam("associateid") Integer associateid,@HeaderParam("Authorization") String token) {
		System.out.println(token);
		Claims claims = null;
		System.out.println("Before the try block");
		try {
			System.out.println("In the try block");
			if (token == null) {
				throw new UnsupportedJwtException("token null");
			}
			claims = jService.getClaimsFromToken(token);
			System.out.println("Print claims " + claims);
		
		} catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException
				| IllegalArgumentException | NullPointerException e) {
			System.out.println("in the catch block");
			e.printStackTrace();
			return Response.status(403).build();
		}
		
		if (claims.getId().equals("1")) {
			Set<InterviewInfo> associateinfo = service.getInterviewsByAssociate(associateid);
			return Response.ok(associateinfo).build();
		} else {
			return Response.status(403).build();
		}
	}

	
	@POST
	public Response createInterview(@PathParam("associateid") int associateid) {
	
	//is.addInterviewByAssociate(associateid, ifc);
	return Response.status(201).build();
	}
	
	
	@GET 
	@Path("/{interviewid}")
	public Response getClientInterview(@PathParam("interviewid") int interviewid) {
		return Response.status(200).build();
	}
	
	@Path("/{interviewid}/job-description")
	@PUT Response updateJobDescription(@PathParam("associateid") int associateid, @PathParam("interviewid") int interviewid) {
		return Response.status(200).build();
	}
	
	@Path("/{interviewid}/dateSaleIssue")
	@PUT Response updateDateSalesIssue(@PathParam("associateid") int associateid, @PathParam("interviewid") int interviewid) {
		return Response.status(200).build(); 
	}
	
	@Path("/{interviewis}/flagAlert")
	@PUT Response updateFlageAlert(@PathParam("associateid") int associateid, @PathParam("interviewid") int interviewid) {
		return Response.status(200).build();
	}
	
	@Path("/{interviewis}/flagReason")
	@PUT Response updateFlageReason(@PathParam("associateid") int associateid, @PathParam("interviewid") int interviewid) {
		return Response.status(200).build();
	}
	
	@Path("/{interviewis}/dateAssociateIssue")
	@PUT Response updateDateAssociateIssue(@PathParam("associateid") int associateid, @PathParam("interviewid") int interviewid) {
		return Response.status(200).build();
	}
}
