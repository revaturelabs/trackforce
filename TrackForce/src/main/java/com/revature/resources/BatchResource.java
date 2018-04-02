package com.revature.resources;


import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.revature.model.BatchInfo;
import com.revature.services.BatchesService;

import io.swagger.annotations.Api;

/**
 * Class that provides RESTful services for the batch listing and batch details page.
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
	 * For example, sending a GET request to /batches?start={date1}&end={date2} will return
	 * all batches with end dates between date 1 and date2
	 * @return - Response with 200 status and a List<BatchInfo> in the response body
	 */
	@GET
	public Response getAllBatches(
			@DefaultValue("1510549200000") @QueryParam("start") Long startDate,
			@DefaultValue("1527480000000") @QueryParam("end") Long endDate) {
		return Response.ok(service.getBatches(startDate, endDate)).build();
	}

	public Response getAllBatches() {
		return Response.ok(service.getAllBatches()).build();
	}
	/**
	 * Gets a batch by its id
	 * @return - Response with 200 status and the BatchInfo object in the response body
	 */
	@GET
	@Path("{id}")
	public Response getBatchById(@PathParam("id") Integer id) {
		BatchInfo batch = service.getBatchById(id);
		return Response.ok(batch).build();
	}
	
	@GET
	@Path("{id}/associates")
	public Response getAssociatesForBatch(@PathParam("id")Integer id) {
		return Response.ok(service.getAssociatesForBranch(id)).build();
	}
}