package com.revature.resources;

import static com.revature.utils.LogUtil.logger;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.hibernate.HibernateException;

import com.revature.entity.TfAssociate;
import com.revature.services.AssociateService;
import com.revature.services.JWTService;

import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Path("/associates")
@Api(value = "associates")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AssociateResource {

	/**
	 * Gets a list of all the associates, optionally filtered by a batch id. If an
	 * associate has no marketing status or curriculum, replaces them with blanks.
	 * If associate has no client, replaces it with "None".
	 *
	 * The different user types and their ID's are Admin: 1 Trainer: 2
	 * Sales/Delivery 3 Staging Manager 4 Associate 5
	 * 
	 * @return A Response object with a list of TfAssociate objects.
	 * @throws IOException
	 * @throws HibernateException
	 */

	@GET
	@ApiOperation(value = "Return all associates", notes = "Gets a set of all the associates, optionally filtered by a batch id. If an associate has no marketing status or\r\n"
			+ " curriculum, replaces them with blanks. If associate has no client, replaces\r\n"
			+ " it with \"None\".", response = TfAssociate.class, responseContainer = "Set")
	public Response getAllAssociates(@HeaderParam("Authorization") String token) {
		logger.info("getAllAssociates()...");
		Status status = null;
		List<TfAssociate> associates = AssociateService.getAllAssociates();
		Claims payload = JWTService.processToken(token);

		if (payload == null || payload.getId().equals("5")) {
			status = Status.UNAUTHORIZED;
		}

		else {
			status = associates == null || associates.isEmpty() ? Status.NO_CONTENT : Status.OK;
		}

		return Response.status(status).entity(associates).build();
	}

	@GET
	@ApiOperation(value = "Return an associate", notes = "Returns information about a specific associate.", response = TfAssociate.class)
	@Path("/{associateid}")
	public Response getAssociate(@ApiParam(value = "An associate id.") @PathParam("associateid") int associateid,
			@HeaderParam("Authorization") String token) {
		logger.info("getAssociate()...");
		Status status = null;
		Claims payload = JWTService.processToken(token);
		TfAssociate associateinfo = AssociateService.getAssociate(associateid);

		if (payload == null || false) {
			status = Status.UNAUTHORIZED;
		}
		else {
			status = associateinfo == null ? Status.NO_CONTENT : Status.OK;
		}

		return Response.status(status).entity(associateinfo).build();
	}

	/**
	 * Update the marketing status or client of associates
	 * 
	 * @param ids
	 *            list of ids to update
	 * @param marketingStatusId
	 *            updating to
	 * @return clientId updating to
	 * @return response 200 status if successful
	 * @throws IOException
	 */
	@PUT
	@ApiOperation(value = "Batch update associates", notes = "Updates the marketing status and/or the client of one or more associates")
	public Response updateAssociates(@HeaderParam("Authorization") String token,
			@DefaultValue("0") @ApiParam(value = "marketing status id") @QueryParam("marketingStatusId") Integer marketingStatusId,
			@DefaultValue("0") @ApiParam(value = "client id") @QueryParam("clientId") Integer clientId,
			List<Integer> ids) {
		logger.info("updateAssociates()...");
		Status status = null;
		Claims payload = JWTService.processToken(token);
		
		List<TfAssociate> associates = null;
		TfAssociate toBeUpdated = null;
		for(int associateId : ids) {
			toBeUpdated = AssociateService.getAssociate(associateId);
//			toBeUpdated.setTfMarketingStatus(tfMarketingStatus);
//			toBeUpdated.setTfClient(tfClient);
			associates.add(toBeUpdated);
		}

		if (payload == null || !payload.getId().equals("1")) {
			status = Status.UNAUTHORIZED;
		}

		else {
			// marketing status & client id are given as query parameters, ids sent in body
			AssociateService.updateAssociates(associates);
		}

		return Response.ok().build();
	}

//	@PUT
//	@ApiOperation(value = "Update associate marketing status", notes = "The method sets the marketing status of the associate by id.")
//	@Path("/{associateId}/marketing")
//	public Response updateAssociateMarketingStatus(@HeaderParam("Authorization") String token,
//			@PathParam("associateId") Integer id) {
//
//		logger.info("updateAssociateMarketingStatus()...");
//
//		Status status = null;
//		Claims payload = JWTService.processToken(token);
//
//		if (payload == null || payload.getId().equals("5")) {
//			status = Status.UNAUTHORIZED;
//		}
//		else {
//			// service.updateAssociateMarketingStatus(id);
//			logger.info("hits update endpoint working");
//			status = Status.OK;
//		}
//		return Response.status(status).build();
//	}

//	@POST
//	@Consumes("application/json")
//	@ApiOperation(value = "Creates new Associate", notes = "Takes username, password, fname and lname to create new user")
//	public Response createNewAssociate(CreateAssociateModel newAssociate) {
//		logger.info("createNewAssociate()...");
//		LogUtil.logger.info(newAssociate);
//		// SuccessOrFailMessage msg = service.createNewAssociate(newAssociate);
//		// if (msg.getStatus()) {
//		// int userId = msg.getNewId();
//		// URI location = URI.create("/user/"+userId);
//		// return Response.created(location).build();
//		// } else {
//		// return Response.serverError().build();
//		// }
//		userService.createNewAssociate(newAssociate);
//		return Response.created(URI.create("/testingURIcreate")).build();
//	}

//	@GET
//	@ApiOperation(value = "Return an associate", notes = "Returns information about a specific associate.")
//	@Path("/mapped/{statusId}")
//	public Response getMappedInfo(@PathParam("statusId") int statusId, @HeaderParam("Authorization") String token) {
//		logger.info("getMappedInfo()...");
//		Status status = null;
//		Claims payload = JWTService.processToken(token);
//		Map<Integer, ClientMappedJSON> mappedStats = null;
//
//		if (payload == null || !payload.getId().equals("1")) {
//			status = Status.UNAUTHORIZED;
//			return Response.status(status).build();
//		}
//
//		else {
//			mappedStats = service.getMappedInfo(statusId);
//			if (mappedStats.isEmpty())
//				return Response.status(204).build();
//		}
//
//		return Response.ok(mappedStats).build();
//	}

//	@GET
//	@Path("unmapped/{statusId}")
//	public Response getUnmappedInfo(@PathParam("statusId") int statusId) {
//		logger.info("getUnmappedInfo()...");
//		return Response.ok(service.getUnmappedInfo(statusId)).build();
//	}

	/**
	 * Update the marketing status or client of an associate
	 * 
	 * @param id
	 *            The ID of the associate to change
	 * @param marketingStatusId
	 *            What to change the associate's marketing status to
	 * @param clientId
	 *            What client to change the associate to
	 * @return
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	/**** OPTION 1 ****/
	@PUT
	@ApiOperation(value = "updates associate values", notes = "The method updates the marketing status or client of a given associate by their id.")
	@Path("/{associateId}")
	public Response updateAssociate(@PathParam("associateId") Integer id, TfAssociate associate,
			@HeaderParam("Authorization") String token) {
		logger.info("updateAssociate()...");
		Status status = null;
		Claims payload = JWTService.processToken(token);

		if (payload == null || payload.getId().equals("5")) {
			status = Status.UNAUTHORIZED;
		}
		else {
			AssociateService.updateAssociate(associate);
			status = Status.OK;
		}

		return Response.status(status).build();
	}

	/**
	 * Updates the associate status to Approved
	 * 
	 * @param id
	 *            The ID of the associate to Approve
	 * @return response 200 status if successful
	 * 
	 */
//	@PUT
//	@ApiOperation(value = "updates associate verification", notes = "The method sets the verfication status to Approved of a given associate by their id.")
//	@Path("/{associateId}/verify")
//	public Response updateAssociateVerification(@PathParam("associateId") Integer id,
//			@HeaderParam("Authorization") String token) {
//		logger.info("updateAssociateVerification()...");
//
//		Status status = null;
//		Claims payload = JWTService.processToken(token);
//
//		if (payload == null || !(payload.getId().equals("1") || payload.getId().equals("2"))) {
//			status = Status.UNAUTHORIZED;
//		} else {
//			service.updateAssociateVerification(id);
//			status = Status.NO_CONTENT;
//		}
//		return Response.status(status).build();
//	}


//	@ApiOperation(value = "-pulls our subresource interviews", notes = "pulls our subresource interviews")
//	@Path("/{associateid}/interviews")
//	public InterviewResource addAssociateInterview() {
//		return new InterviewResource();
//	}
	

//	@PUT
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Path("/{associateid}/info")
//	public Response updateUserInfo(@PathParam("associateid") Integer id, CreateAssociateModel associate){
//		if(service.updateAssociateInfo(id, associate)) {
//			return Response.status(200).build();
//		}
//		else return Response.status(400).build();
//	}
}
