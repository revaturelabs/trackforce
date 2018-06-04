package com.revature.resources;

import static com.revature.utils.LogUtil.logger;

import java.io.IOException;
import java.util.Date;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.revature.model.InterviewInfo;
import com.revature.services.JWTService;
import com.revature.services.PredictionService;

import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//import com.revature.services.BatchTechsService;

@Path("prediction")
@Api(value = "prediction")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PredictionResource {

	private PredictionService service;

	public PredictionResource() {
		this.service = new PredictionService();
	}

	@Path("/{time1}/{time2}")
	@GET
	@ApiOperation(value = "Gets batch prediction", notes = "Gets all availiable associates by a certain tech in a certain timeframe.")
	public Response getBatchTechInfoName(@PathParam("time1") long time1, @PathParam("time2") long time2,
			@HeaderParam("Authorization") String token) throws IOException {
		logger.info("getBatchTechInfoName()...");
		Status status = null;
		Claims payload = JWTService.processToken(token);

		if (payload == null || !payload.getId().equals("1")) {
			status = Status.UNAUTHORIZED;
			return Response.status(status).build();
		}

		else {
			Date afterThis = new Date(time1);
			Date beforeThis = new Date(time2);
			return Response.ok(service.getAvailableAssociatesByTech(afterThis, beforeThis)).build();
		}

	}
}
