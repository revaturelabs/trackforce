package com.revature.resources;

import static com.revature.utils.LogUtil.logger;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.revature.model.BatchInfo;
import com.revature.services.BatchesService;

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
	
	private BatchesService service;

	public BatchResource() {
		this.service = new BatchesService();
	}

	/**
	 * Gets all batches, optionally filtered by start and end date query parameters
	 * For example, sending a GET request to /batches?start={date1}&end={date2} will
	 * return all batches with end dates between date 1 and date2
	 * 
	 * @return - Response with 200 status and a List<BatchInfo> in the response body
	 */
	@GET
	@ApiOperation(value = "Returns all Batches", notes = "Returns a list of a list of all batches optionally filtered by start and end dates.")
	public Response getAllBatches() {
		Set<BatchInfo> batches = service.getAllBatches();
		Status status = batches == null || batches.isEmpty() ? Status.NO_CONTENT : Status.OK;

		logger.info("getallBatches()");
		logger.info("	batches size: " + (batches == null ? null : batches.size()));
		return Response.status(status).entity(batches).build();
	}

	/**
	 * examples:
	 * 
	 * @param startDate
	 * @param endDate
	 * @param curriculum
	 * @return
	 */
	public Response getAllBatches(@DefaultValue("1510549200000") @QueryParam("start") Long startDate,
			@DefaultValue("1527480000000") @QueryParam("end") Long endDate
	) {

		logger.info("getAllBatches(): " + "");
		List<BatchInfo> result = service.getBatches(startDate, endDate);

		Status status = result == null || result.isEmpty() ? Status.NO_CONTENT : Status.OK;

		return Response.status(status).entity(result).build();
	}

	/**
	 * @author Ian Buitrago
	 * @param curriculum
	 *            name
	 * @return set of batches matching curriculum
	 */
	@GET
	@ApiOperation(value = "returns batches by curriculum", notes = "Returns a list of batches filtered by curriculum name.")
	@Path("curriculum/{curriculum}")
	public Response getBatchesByCurri(@PathParam("curriculum") String curriculum,
			@HeaderParam("Authorization") String token, @QueryParam("start") Long startDate,
			@QueryParam("end") Long endDate) {
		Status status = null;
		Claims payload = JWTService.processToken(token);
		Collection<BatchInfo> results = new HashSet<>();

		if (payload == null || payload.getId().equals("5")) {
			status = Status.UNAUTHORIZED;
		}

		else {
			logger.info("getBatchesByCurriculum(): " + curriculum);

			if (startDate != null && endDate != null) {
				logger.info("	start = " + new Timestamp(startDate));
				logger.info("	end = " + new Timestamp(endDate));
				Collection<BatchInfo> batches = service.getBatches(startDate, endDate);

				for (BatchInfo b : batches) {
					if (b.getCurriculumName() != null && b.getCurriculumName().equalsIgnoreCase(curriculum))
						results.add(b);
				}
			}

			else {
				results = service.getBatchesByCurri(curriculum);
			}

			status = results == null || results.isEmpty() ? Status.NO_CONTENT : Status.OK;
			logger.info("	batch size: " + (results == null ? null : results.size()));
		}

		return Response.status(status).entity(results).build();
	}

	/**
	 * Gets a batch by its id
	 * 
	 * @return - Response with 200 status and the BatchInfo object in the response
	 *         body
	 */
	@GET
	@Path("/{id}")
	@ApiOperation(value = "Returns a batch", notes = "Returns a specific batch by id.")
	public Response getBatchById(@PathParam("id") Integer id) {
		BatchInfo batch = service.getBatchById(id);
		return Response.ok(batch).build();
	}

	@GET
	@ApiOperation(value = "Returns associates for batch", notes = "Returns list of associates for a specific batch based on batch id.")
	@Path("{id}/associates")
	public Response getAssociatesForBatch(@PathParam("id") Integer id) {
		return Response.ok(service.getAssociatesForBranch(id)).build();
	}

	// dummy test method: returns ["Yuvi1804", 25],["wills batch", 14] every time
	@GET
	@Path("/adam")
	public Response getSomeBatches() {
		Bar b = new Bar();
		b.batchName = "Wills batch";
		b.size = 14;
		Set<Bar> sb = new HashSet<>();
		sb.add(b);
		sb.add(new Bar());
		logger.info("getSomeBatches(): " + sb.size());
		return Response.status(Status.OK).entity(sb).build();
	}
}

// dummy
class Bar {
	public String batchName = "Yuvi1804";
	public int size = 25;
}