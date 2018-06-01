package com.revature.resources;



import static com.revature.utils.LogUtil.logger;



import java.io.IOException;

import java.util.Collection;

import java.util.Map;

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

import com.revature.utils.LogUtil;



import io.jsonwebtoken.Claims;

import io.jsonwebtoken.ExpiredJwtException;

import io.jsonwebtoken.MalformedJwtException;

import io.jsonwebtoken.SignatureException;

import io.jsonwebtoken.UnsupportedJwtException;

import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;



/**

 * Sub Resource

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

	

	

	@POST

	@ApiOperation(value = "Creates interview", notes = "Creates an interview for a specific associate based on associate id. Returns 201 if successful, 403 if not.")

	public Response createInterview(@PathParam("associateid") int associateid,

			@HeaderParam("Authorization") String token, InterviewFromClient ifc) {

		logger.info(ifc);

		Status status = null;

		logger.info(token);

		Claims payload = JWTService.processToken(token);



		if (payload == null || !(payload.getId().equals("1") || payload.getId().equals("5"))) {

			logger.info("inside unautherorized");

			status = Status.UNAUTHORIZED;

		} else {

			logger.info("inside createInterview endpoint");

			is.addInterviewByAssociate(associateid, ifc);

			// does service actually work?

			status = Status.CREATED;

		}



		return Response.status(status).build();

	}

	

	public InterviewResource() {

		this.is = new InterviewService();

	}



	@GET

	@ApiOperation(value = "Returns all interviews for an associate", notes ="Returns a list of all interviews.")

	public Response getAllInterviews(@HeaderParam("Authorization") String token, @PathParam("associateid") Integer associateid)

			throws HibernateException, IOException 

	{

		// TODO handle exception

		Status status = null;

		Collection<InterviewInfo> interviews = null;

		Claims payload = JWTService.processToken(token);



		if (payload == null || !payload.getId().equals("1") || !payload.getId().equals("5")) {

			status = Status.UNAUTHORIZED;

		}

		else 

		{

			interviews = is.getInterviewsByAssociate(associateid);

			logger.info(interviews);

			status = interviews == null || interviews.isEmpty() ? Status.NO_CONTENT : Status.OK;

			logger.info("inside get all interviews");

		}



		return Response.status(status).entity(interviews).build();

		

	}



	@GET

	@ApiOperation(value = "Returns an interview", notes = "Returns a specific interview by id.")

	@Path("/{interviewid}")

	public Response getAssociateInterview(@PathParam("associateid") Integer associateid,

			@PathParam("interviewid") Integer interviewid,

			@HeaderParam("Authorization") String token) throws IOException {

		Status status = null;

		Collection<InterviewInfo> interview = null;

		Claims payload = JWTService.processToken(token);



		if (payload.getId().equals("1")) {

			Set<InterviewInfo> interviews = is.getInterviewsByAssociateAndInterviewid(associateid, interviewid);

			return Response.ok(interviews).build();

		} else {

			return Response.status(403).build();

		}

	}

	

	

//	@PUT

//	@Path("/{interviewid}/twentyfourFlag")

//	public Response twentyfourFlag(TfInterview flagInterview, @HeaderParam("Authorization") String token,

//			@PathParam("associateid") Integer associateid,

//			@PathParam("interviewid") Integer interviewid) {

//		Status status = null;

//		Claims payload = JWTService.processToken(token);

//

//		if (payload == null || !payload.getId().equals("1") || !payload.getId().equals("5")) {

//			status = Status.UNAUTHORIZED;

//		} else {

//			InterviewDaoHibernate id = new InterviewDaoHibernate();

//			id.updateInterview(flagInterview);

//			// does service actually work?

//			status = Status.ACCEPTED;

//		}

//		return Response.status(status).build();

//	}

//	

//	@PUT

//	@Path("/{interviewid}/interview-went")

//	public Response interviewWent(TfInterview interviewWent, @HeaderParam("Authorization") String token,

//			@PathParam("associateid") Integer associateid,

//			@PathParam("interviewid") Integer interviewid) {

//		Status status = null;

//		Claims payload = JWTService.processToken(token);

//

//		if (payload == null || !payload.getId().equals("1") || !payload.getId().equals("5")) {

//			status = Status.UNAUTHORIZED;

//		} else {

//			InterviewDaoHibernate id = new InterviewDaoHibernate();

//			id.updateInterview(interviewWent);

//			// does service actually work?

//			status = Status.ACCEPTED;

//		}

//		return Response.status(status).build();

//	}

//	

//	@PUT

//	@Path("/{interviewid}/client-feedback")

//	public Response clientFeedback(TfInterview clientFeedback, @HeaderParam("Authorization") String token,

//			@PathParam("associateid") Integer associateid,

//			@PathParam("interviewid") Integer interviewid) {

//		Status status = null;

//		Claims payload = JWTService.processToken(token);

//

//		if (payload == null || !payload.getId().equals("1") || !payload.getId().equals("3")) {

//			status = Status.UNAUTHORIZED;

//		} else {

//			InterviewDaoHibernate id = new InterviewDaoHibernate();

//			id.updateInterview(clientFeedback);

//			// does service actually work?

//			status = Status.ACCEPTED;

//		}

//		return Response.status(status).build();

//	}

//	@GET

//	@Path("/{interviewid}/client-feedback")

//	public Response getFeedback(TfInterview feedback,

//			@HeaderParam("Authorization") String token,

//			@PathParam("associateid") Integer associateid,

//			@PathParam("interviewid") Integer interviewid) {

//		Status status = null;

//		Claims payload = JWTService.processToken(token);

//

//		if (payload == null || !payload.getId().equals("1") || !payload.getId().equals("3")) {

//			status = Status.UNAUTHORIZED;

//		} else if (feedback.getTfIsClientFeedbackVisible() == 1) {

//			InterviewDaoHibernate id = new InterviewDaoHibernate();

//			id.getInterviewById(feedback.getTfInterviewId());

//			// does service actually work?

//			status = Status.ACCEPTED;

//		}

//		return Response.status(status).build();

//	}

	@Path("/{interviewid}")

	@ApiOperation(value = "updates interview", notes = " Updates interview")

	@PUT

	public Response updateInterview(@PathParam("associateid") int associateid,

			@PathParam("interviewid") int interviewid, @HeaderParam("Authorization") String token,



			TfInterview changeInterview) 

	{

		logger.info("hits update interview method");



		Status status = null;

		Claims payload = JWTService.processToken(token);

		logger.info(payload.getId());





		if (payload == null || !(payload.getId().equals("1") || payload.getId().equals("5")))

		{

			status = Status.UNAUTHORIZED;

		} 

		

		else 

		{

		logger.info("jersey part is done");

			//is.updateInterview(changeInterview);

			status = Status.ACCEPTED;

		}

		logger.info("end update interview without hitting endpoint");

		return Response.status(status).build();



	}

}