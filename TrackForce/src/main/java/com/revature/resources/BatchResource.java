package com.revature.resources;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.revature.model.BatchInfo;
import com.revature.services.BatchesService;

/**
 * Class that provides RESTful services for the batch listing and batch details page.
 */
@Path("batches")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BatchResource {

	private BatchesService service;

	public BatchResource() {
		this.service = new BatchesService();
	}

	/**
	 * Gets all batches, optionally filtered by start and end date query parameters
	 * For example, sending a GET request to /batches?start={date1}&end={date2} will return
	 * all batches with end dates between date 1 and date2
	 * @return - Response with 200 status and a List<BatchInfo> in the response body
	 */
	@GET
	public Response getAllBatches(
			@QueryParam("start") Long startDate,
			@QueryParam("end") Long endDate) throws IOException {
		List<BatchInfo> batches = service.getBatches(startDate, endDate);
		return Response.ok(batches).build();
	}

	/**
	 * Gets a batch by its id
	 * @return - Response with 200 status and the BatchInfo object in the response body
	 */
	@GET
	@Path("{id}")
	public Response getBatchById(@PathParam("id") int id) {
		BatchInfo batch = service.getBatchById(id);
		return Response.ok(batch).build();
	}
}