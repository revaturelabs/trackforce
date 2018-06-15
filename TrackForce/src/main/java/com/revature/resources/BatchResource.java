package com.revature.resources;

import static com.revature.utils.LogUtil.logger;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.revature.entity.TfAssociate;
import com.revature.entity.TfBatch;
import com.revature.services.AssociateService;
import com.revature.services.BatchService;
import com.revature.services.ClientService;
import com.revature.services.CurriculumService;
import com.revature.services.InterviewService;
import com.revature.services.JWTService;
import com.revature.services.TrainerService;
import com.revature.services.UserService;

import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>Class that provides RESTful services for the batch listing and batch details
 * page.</p>
 * @version.date v06.2018.06.13
 */
@Path("batches")
@Api(value = "batches")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BatchResource {

	// You're probably thinking, why would you ever do this? Why not just just make the methods all static in the service class?
	// This is to allow for Mokito tests, which have problems with static methods
	// This is here for a reason! 
	// - Adam 06.2018.06.13
	AssociateService associateService = new AssociateService();
	BatchService batchService = new BatchService();
	ClientService clientService = new ClientService();
	CurriculumService curriculumService = new CurriculumService();
	InterviewService interviewService = new InterviewService();
	TrainerService trainerService = new TrainerService();
	UserService userService = new UserService();
	
	/**
	 * @author Ian Buitrago, Andy A., Adam L. 
	 * <p>Gets all batches, optionally filtered by start and end date query parameters
	 * For example, sending a GET request to /batches?start={date1}&end={date2} will
	 * return all batches with end dates between date1 and date2</p>
	 * @version.date v06.2018.06.13
	 * 
	 * @param startDate
	 * @param endDate
	 * @param token
	 * @return Response with 200 status and a List<BatchInfo> in the response body
	 */
	@GET
	@ApiOperation(value = "Returns all Batches", notes = "Returns a list of a list of all batches optionally filtered by start and end dates.")
	public Response getAllBatches(@QueryParam("start") Long startDate, @QueryParam("end") Long endDate,
			@HeaderParam("Authorization") String token) {
		logger.info("getallBatches()...");
		List<TfBatch> batches = batchService.getAllBatches();
		
		Claims payload = JWTService.processToken(token);
		if (payload == null) {
			return Response.status(Status.UNAUTHORIZED).build();
		}

		Status status = null;
		int role = Integer.parseInt(payload.getId());
		Set<Integer> authorizedRoles = new HashSet<>(Arrays.asList(new Integer[] { 1, 2, 3, 4, 5 }));

		if (authorizedRoles.contains(role)) {
			if (startDate != null && endDate != null) {
				logger.info("	start = " + new Timestamp(startDate));
				logger.info("	end = " + new Timestamp(endDate));
				int i = 0;
				Date start = new Date(startDate);
				Date end = new Date(endDate);
				// iterates through the list of batches and removes those that are before the start and after the end date
				while(i < batches.size()) {
					if(batches.get(i).getStartDate().before(start))
						batches.remove(i);
					else if(batches.get(i).getEndDate().after(end))
						batches.remove(i);
					//increments ONLY if no elements were removed
					else
						i++;
				}
			}
			status = batches == null || batches.isEmpty() ? Status.NO_CONTENT : Status.OK;
		} else {
			status = Status.FORBIDDEN;
		}
		logger.info("	batches size: " + (batches == null ? null : batches.size()));

		return Response.status(status).entity(batches).build();
	}

	
	/**
	 * @author Adam L. 
	 * <p>Gets a batch by its id</p>
	 * @version.date v06.2018.06.13
	 * 
	 * @param id
	 * @param token
	 * @return - Response with 200 status and the BatchInfo object in the response body
	 */
	@GET
	@Path("/{id}")
	@ApiOperation(value = "Returns a batch", notes = "Returns a specific batch by id.")
	public Response getBatchById(@PathParam("id") Integer id, @HeaderParam("Authorization") String token) {
		logger.info("getBatchById()...");
		TfBatch batch = batchService.getBatchById(id);
		
		Claims payload = JWTService.processToken(token);
		if (payload == null) {
			return Response.status(Status.UNAUTHORIZED).build();
		}
		int role = Integer.parseInt(payload.getId());
		Set<Integer> authorizedRoles = new HashSet<>(Arrays.asList(new Integer[] { 1, 2, 3, 4, 5 }));
		Status status = null;

		if (authorizedRoles.contains(role)) {
			status = batch == null ? Status.NO_CONTENT : Status.OK;
		} else {
			status = Status.FORBIDDEN;
		}
		return Response.status(status).entity(batch).build();
	}

	/**
	 * @author Adam L. 
	 * <p> </p>
	 * @version.date v06.2018.06.13
	 * 
	 * @param id
	 * @param token
	 * @return
	 */
	@GET
	@ApiOperation(value = "Returns associates for batch", notes = "Returns list of associates for a specific batch based on batch id.")
	@Path("{id}/associates")
	public Response getBatchAssociates(@PathParam("id") Integer id, @HeaderParam("Authorization") String token) {
		logger.info("getBatchAssociates()...");
		Set<TfAssociate> associates = batchService.getBatchById(id).getAssociates();
		
		Claims payload = JWTService.processToken(token);
		if (payload == null) {
			return Response.status(Status.UNAUTHORIZED).build();
		}
		Status status = null;
		int role = Integer.parseInt(payload.getId());
		Set<Integer> authorizedRoles = new HashSet<>(Arrays.asList(new Integer[] { 1, 2, 3, 4, 5 }));

		if (authorizedRoles.contains(role)) {
			// results and status set in here
			status = associates == null || associates.isEmpty() ? Status.NO_CONTENT : Status.OK;
		} else {
			status = Status.FORBIDDEN;
		}

		return Response.status(status).entity(associates).build();
	}

}
