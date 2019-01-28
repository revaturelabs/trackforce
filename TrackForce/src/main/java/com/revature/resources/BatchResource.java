package com.revature.resources;

import com.revature.daoimpl.BatchDaoImpl;
import com.revature.entity.TfAssociate;
import com.revature.entity.TfBatch;
import com.revature.services.*;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.revature.utils.LogUtil.logger;

/**
 * <p>Class that provides RESTful services for the batch listing and batch details
 * page.</p>
 * @version v6.18.06.13
 */
@Path("/batches")
@Api(value = "batches")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BatchResource {

	// You're probably thinking, why would you ever do this? Why not just just make the methods all static in the service class?
	// This is to allow for Mockito tests, which have problems with static methods
	// This is here for a reason! 
	// - Adam 06.18.06.13
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
	 * @version v6.18.06.13
	 * 
	 * @param startDate
	 * @param endDate
	 * @param token
	 * @return Response with 200 status and a List<BatchInfo> in the response body
	 */
	@GET
	@ApiOperation(value = "Returns all Batches", notes = "Returns a list of all batches optionally filtered by start and end dates.")
	public Response getAllBatches(@QueryParam("start") Long startDate, @QueryParam("end") Long endDate,
			@HeaderParam("Authorization") String token) {
		
		StringBuilder logMessage = new StringBuilder();
		logMessage.append("getallBatches()...");
		List<TfBatch> batches = batchService.getAllBatches();
		
		Claims payload = JWTService.processToken(token);
		if (payload == null) {
			return Response.status(Status.UNAUTHORIZED).entity(JWTService.invalidTokenBody(token)).build();
		}

		Status status = null;
		int role = Integer.parseInt((String)payload.get("roleID"));

		Set<Integer> authorizedRoles = new HashSet<>(Arrays.asList(new Integer[] { 1, 2, 3, 4 }));

		if (authorizedRoles.contains(role)) {
			if (startDate != null && endDate != null) {
				logMessage.append("	start = " + new Timestamp(startDate));
				logMessage.append("	end = " + new Timestamp(endDate));
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
			logger.info("User has insufficient Privilieges. Forbidden Access");
			status = Status.FORBIDDEN;
		}
		logMessage.append("	batches size: " + (batches == null ? null : batches.size()));
		logger.info(logMessage);

		return Response.status(status).entity(batches).build();
	}

	/**
	 * @author Adam L. 
	 * <p> </p>
	 * @version v6.18.06.13
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
		logger.info("associates batch set -> "+associates.isEmpty());
		
		Claims payload = JWTService.processToken(token);
		if (payload == null) {
			logger.error("The payload was null. Unathorized access.");
			return Response.status(Status.UNAUTHORIZED).entity(JWTService.invalidTokenBody(token)).build();
		}
		Status status = null;
		int role = Integer.parseInt((String) payload.get("roleID"));

		Set<Integer> authorizedRoles = new HashSet<>(Arrays.asList(new Integer[] { 1, 2, 3, 4}));


		if (authorizedRoles.contains(role)) {
			logger.info("in authorized roles");
			// results and status set in here
			status = associates == null || associates.isEmpty() ? Status.NO_CONTENT : Status.OK;
		} else {
			logger.error("User has insufficient Privileges. Forbidden Access");
			status = Status.FORBIDDEN;
		}
		logger.info("Return status: "+status);
		return Response.status(status).entity(associates).build();
	}

	/**
	 * 1806_Chris_P:
	 * Returns a list of batches related to a selected ciriculum/technology on the Predictions page.
	 * this is used for the Batch Details page.
	 */
	@GET
	@ApiOperation(value = "Returns batches", notes = "Returns list of batches that matches the date range and course.")
	@Path("/details")
	public Response getBatchDetails(@QueryParam("start") Long startDate, @QueryParam("end") Long endDate,
							@QueryParam("courseName") String courseName, @HeaderParam("Authorization") String token) {
		logger.info("getBatchDetails()...");
		Claims payload = JWTService.processToken(token);
		if (payload == null) {
			logger.error("The payload was null. Unathorized access.");
			return Response.status(Status.UNAUTHORIZED).entity(JWTService.invalidTokenBody(token)).build();
		}
		Status status = null;
		status = Status.OK;
		int role = Integer.parseInt((String)payload.get("roleID"));
		
		JSONObject batchDetails = new JSONObject();
		JSONArray batchesJ = new JSONArray();

		BatchDaoImpl bd = new BatchDaoImpl();
		List<TfBatch> batches = bd.getBatchesForPredictions(courseName, new Timestamp(startDate), new Timestamp(endDate));
		
		for (TfBatch batch : batches) {
			int unmappedCount = getUnmappedCount(batch.getAssociates());
			
			JSONObject b = new JSONObject();
			b.put("batchName", batch.getBatchName());
			b.put("startDate", (Long)batch.getStartDate().getTime());
			b.put("endDate", (Long)batch.getEndDate().getTime());
			b.put("associateCount", unmappedCount);
			batchesJ.put(b);
		}
		batchDetails.put("courseBatches", batchesJ);
		
		return Response.status(status).entity(batchDetails.toString()).build();
	}
	
	/**
	 * 1806_Austin_M 
	 * Iterate through set of associates and increment count based on associate status.
	 * 
	 * @param associates
	 * @return count of associates with 'unmapped' status
	 */
	public Integer getUnmappedCount(Set<TfAssociate> associates) {
		int n = 0;
		logger.info("Method call to getUnammpedCount");
		for(TfAssociate a : associates) {
			if(a.getMarketingStatus().getId() > 5)
				n++;
		}
		
		logger.info("Return all unmapped Associates.");
		return n;
	}

	/**
	 * 1806_Chris_P
	 * Super similar to the previous method, except that this one only returns the aggregate count of all associates in
	 * a particular curriculum selected from the Predictions Page.
	 */
	@GET
	@ApiOperation(value = "Returns associates for batch", notes = "Returns list of associates for a specific batch based on batch id.")
	@Path("/countby")
	public Response getBatchCounts(@QueryParam("start") Long startDate, @QueryParam("end") Long endDate,
							@QueryParam("courseName") String courseName, @HeaderParam("Authorization") String token) {
		logger.info("getBatchAssociateCounts...");

		Claims payload = JWTService.processToken(token);
		if (payload == null) {
			logger.error("The payload was null. Unathorized access.");
			return Response.status(Status.UNAUTHORIZED).entity(JWTService.invalidTokenBody(token)).build();
		}
		Status status = null;
		status = Status.OK;
		int role = Integer.parseInt((String)payload.get("roleID"));
		
		Set<Integer> authorizedRoles = new HashSet<>(Arrays.asList(new Integer[] { 1, 2, 3, 4}));
		if (authorizedRoles.contains(role)) {
			status = Status.OK;
		} else {
			logger.info("User has insufficient Privilieges. Forbidden Access");
			status = Status.FORBIDDEN;
		}
		
		JSONObject associateCount = new JSONObject();
		BatchDaoImpl bd = new BatchDaoImpl();
		
		Object count = bd.getBatchCountsForPredictions(courseName, new Timestamp(startDate), new Timestamp(endDate));
		System.out.println("===================== count is: " + count);
		
		Long lCount = Long.valueOf(count.toString());
		associateCount.put("associateCount", lCount);
		logger.info("Return Batch Counts for associates.");
		return Response.status(status).entity(associateCount.toString()).build();
	}
	
	//1806_Andrew_H gets all batches within a certain date range, used in batch-details
	@GET
	@ApiOperation(value = "Returns associates for batch", notes = "Returns list.")
	@Path("/withindates")
	public Response getBatchesWithinDates(@QueryParam("start") Long startDate, @QueryParam("end") Long endDate,
						@HeaderParam("Authorization") String token) {
		logger.info("getBatchesWithinDates()...");
		Claims payload = JWTService.processToken(token);
		if (payload == null) {
			logger.error("The payload was null. Unathorized access.");
			return Response.status(Status.UNAUTHORIZED).entity(JWTService.invalidTokenBody(token)).build();
		}
		Status status = null;
		status = Status.OK;	
		int role = Integer.parseInt((String)payload.get("roleID"));

		Set<Integer> authorizedRoles = new HashSet<>(Arrays.asList(new Integer[] { 1, 2, 3, 4}));
		if (authorizedRoles.contains(role)) {
			status = Status.OK;
		} else {
			logger.info("User has insufficient Privilieges. Forbidden Access");
			status = Status.FORBIDDEN;
		}
		
		
		System.out.println(new Timestamp(endDate).toString());
		BatchDaoImpl bd = new BatchDaoImpl();
		List<TfBatch> batches = bd.getBatchesWithinDates(new Timestamp(startDate), new Timestamp(endDate));
		

		return Response.status(status).entity(batches).build();

	}
	
	/**
	 * 1806_Kevin_C
	 * Gets a batch with the given id
	 */
	@GET
	@ApiOperation(value = "Returns batch information given a batch id", notes = "Returns batch information based on batch id.")
	@Path("/batch/{id}")
	public Response getBatchInfo(@PathParam("id") Integer id, @HeaderParam("Authorization") String token) {
		logger.info("getBatchInformation...");

		Claims payload = JWTService.processToken(token);
		if (payload == null) {
			logger.error("The payload was null. Unathorized access.");
			return Response.status(Status.UNAUTHORIZED).build();
		}
		Status status = null;
		status = Status.OK;
		int role = Integer.parseInt((String)payload.get("roleID"));

		Set<Integer> authorizedRoles = new HashSet<>(Arrays.asList(new Integer[] { 1, 2, 3, 4}));
		if (authorizedRoles.contains(role)) {
			status = Status.OK;
		} else {
			status = Status.FORBIDDEN;
		}
		
		BatchDaoImpl bd = new BatchDaoImpl();
		TfBatch batch = batchService.getBatchById(id);
		
		
		return Response.status(status).entity(batch).build();
	}
}
