package com.revature.resources;

import static com.revature.utils.LogUtil.logger;

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

import com.revature.entity.TfBatch;
import com.revature.services.BatchesService;
import com.revature.services.JWTService;

import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Class that provides RESTful services for the batch listing and batch details
 * page.
 */
@Path("batches")
@Api(value = "batches")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BatchResource {

	/**
	 * Gets all batches, optionally filtered by start and end date query parameters
	 * For example, sending a GET request to /batches?start={date1}&end={date2} will
	 * return all batches with end dates between date1 and date2
	 * 
	 * @author Ian Buitrago
	 * @return - Response with 200 status and a List<BatchInfo> in the response body
	 */
	@GET
	@ApiOperation(value = "Returns all Batches", notes = "Returns a list of a list of all batches optionally filtered by start and end dates.")
	public Response getAllBatches(@QueryParam("start") Long startDate, @QueryParam("end") Long endDate,
			@HeaderParam("Authorization") String token) {
		logger.info("getallBatches()...");
		List<TfBatch> batches = BatchesService.getAllBatches();
		
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
//				batches = service.getBatches(startDate, endDate);
			} else {
//				batches = service.getAllBatches();
			}
			status = batches == null || batches.isEmpty() ? Status.NO_CONTENT : Status.OK;
		} else {
			status = Status.FORBIDDEN;
		}
		logger.info("	batches size: " + (batches == null ? null : batches.size()));

		return Response.status(status).entity(batches).build();
	}
//
//	/**
//	 * @author Ian Buitrago
//	 * @param curriculum
//	 *            name
//	 * @return set of batches matching curriculum
//	 */
//	@GET
//	@Path("curriculum/{curriculum}")
//	@ApiOperation(value = "returns batches by curriculum", notes = "Returns a list of batches filtered by curriculum name.")
//	public Response getBatchesByCurri(@PathParam("curriculum") String curriculum,
//			@HeaderParam("Authorization") String token, @QueryParam("start") Long startDate,
//			@QueryParam("end") Long endDate) {
//		logger.info("getBatchesByCurri()...");
//		List<TfBatch> batches = BatchesService.getAllBatches();
//		
//		Claims payload = JWTService.processToken(token);
//		if (payload == null) {
//			return Response.status(Status.UNAUTHORIZED).build();
//		}
//		Status status = null;
//		int role = Integer.parseInt(payload.getId());
//		Set<Integer> authorizedRoles = new HashSet<>(Arrays.asList(new Integer[] { 1, 2, 3, 4, 5 }));
//
//		if (authorizedRoles.contains(role)) {
//			if (startDate != null && endDate != null) {
//				logger.info("	start = " + new Timestamp(startDate));
//				logger.info("	end = " + new Timestamp(endDate));
//				List<BatchInfo> batches = service.getBatches(startDate, endDate);
//
//				// TODO should be filtered in DAO layer
//				for (BatchInfo b : batches) {
//					if (b.getCurriculumName() != null && b.getCurriculumName().equalsIgnoreCase(curriculum))
//						results.add(b);
//				}
//
//			} else {
//				results = service.getBatchesByCurri(curriculum);
//			}
//			status = results == null || results.isEmpty() ? Status.NO_CONTENT : Status.OK;
//		} else {
//			status = Status.FORBIDDEN;
//		}
//		logger.info("	batch size: " + (results == null ? null : results.size()));
//
//		return Response.status(status).entity(results).build();
//	}

	/**
	 * Gets a batch by its id
	 * 
	 * @return - Response with 200 status and the BatchInfo object in the response
	 *         body
	 */
	@GET
	@Path("/{id}")
	@ApiOperation(value = "Returns a batch", notes = "Returns a specific batch by id.")
	public Response getBatchById(@PathParam("id") Integer id, @HeaderParam("Authorization") String token) {
		logger.info("getBatchById()...");
		TfBatch batch = BatchesService.getBatchById(id);
		
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
//
//	@GET
//	@ApiOperation(value = "Returns associates for batch", notes = "Returns list of associates for a specific batch based on batch id.")
//	@Path("{id}/associates")
//	public Response getBatchAssociates(@PathParam("id") Integer id, @HeaderParam("Authorization") String token) {
//		logger.info("getBatchAssociates()...");
//		List<TfAssociate> associates = 
//		
//		Claims payload = JWTService.processToken(token);
//		if (payload == null) {
//			return Response.status(Status.UNAUTHORIZED).build();
//		}
//		Status status = null;
//		int role = Integer.parseInt(payload.getId());
//		Set<Integer> authorizedRoles = new HashSet<>(Arrays.asList(new Integer[] { 1, 2, 3, 4, 5 }));
//
//		if (authorizedRoles.contains(role)) {
//			// results and status set in here
//			associates = service.getAssociatesForBranch(id);
//			status = associates == null || associates.isEmpty() ? Status.NO_CONTENT : Status.OK;
//		} else {
//			status = Status.FORBIDDEN;
//		}
//
//		return Response.status(status).entity(associates).build();
//	}
//
//	/**
//	 * @author Ian Buitrago
//	 */
//	// dummy test method: returns ["Yuvi1804", 25],["wills batch", 14] every time
//	@GET
//	@Path("/adam")
//	public Response getAdam() {
//		logger.info("getAdam()...");
//		Bar b = new Bar();
//		b.batchName = "Wills batch";
//		b.size = 14;
//		Set<Bar> sb = new HashSet<>();
//		sb.add(b);
//		sb.add(new Bar());
//		return Response.status(Status.OK).entity(sb).build();
//	}
}
