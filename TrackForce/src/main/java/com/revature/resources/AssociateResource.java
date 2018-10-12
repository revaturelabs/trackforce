package com.revature.resources;

import static com.revature.utils.LogUtil.logger;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.NoResultException;
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
import org.json.JSONObject;

import com.revature.daoimpl.BatchDaoImpl;
import com.revature.entity.TfAssociate;
import com.revature.entity.TfTrainer;
import com.revature.services.AssociateService;
import com.revature.services.BatchService;
import com.revature.services.ClientService;
import com.revature.services.CurriculumService;
import com.revature.services.InterviewService;
import com.revature.services.JWTService;
import com.revature.services.MarketingStatusService;
import com.revature.services.TrainerService;
import com.revature.services.UserService;

import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <p>
 * </p>
 * 
 * @version v6.18.06.13
 *
 */
@Path("/associates")
@Api(value = "associates")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AssociateResource {

	// You're probably thinking, why would you ever do this? Why not just just make
	// the methods all static in the service class?
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
	MarketingStatusService marketingStatusService = new MarketingStatusService();

	/**
	 * <p>
	 * Gets a list of all the associates, optionally filtered by a batch id. If an
	 * associate has no marketing status or curriculum, replaces them with blanks.
	 * If associate has no client, replaces it with "None".
	 * </p>
	 * 
	 * @version v6.18.06.13
	 * 
	 * @return A Response object with a list of TfAssociate objects.
	 * @throws IOException
	 * @throws HibernateException
	 */
	@Path("/allAssociates")
	@GET
	@ApiOperation(value = "Return all associates", notes = "Gets a set of all the associates,", response = TfAssociate.class, responseContainer = "Set")
	public Response getAllAssociates(@HeaderParam("Authorization") String token) {
		logger.info("getAllAssociates()...");
		Status status = null;
		List<TfAssociate> associates = associateService.getAllAssociates();
		Claims payload = JWTService.processToken(token);

		if (payload == null || payload.getId().equals("5")) {
			return Response.status(Status.UNAUTHORIZED).entity(JWTService.invalidTokenBody(token)).build();
		} else {
//			if (payload.getId().equals("2")) {
//				List<TfAssociate> assoc = new ArrayList<TfAssociate>();
//				for (TfAssociate a : associates) {
//					if (a.getBatch() != null) {
//						if (payload.getSubject().equals(a.getBatch().getTrainer().getTfUser().getUsername())) {
//							assoc.add(a);
//						}
//						List<TfTrainer> cotrainers = a.getBatch().getCoTrainer();
//						for (TfTrainer t : cotrainers) {
//							if (t.getTfUser().getUsername().equals(payload.getSubject())) {
//								assoc.add(a);
//							}
//						}
//
//					}
//				}
//				associates = assoc;
//			}
			status = associates == null || associates.isEmpty() ? Status.NO_CONTENT : Status.OK;
		}

		return Response.status(status).entity(associates).build();
	}
	
	@Path("/countAssociates")
	@GET
	@ApiOperation(value = "Return count of associates per category", notes = "Gets a count of the associates in each category,", 
				  response = TfAssociate.class, responseContainer = "Set")
	public Response getCountAssociates(@HeaderParam("Authorization") String token) {
		logger.info("getCountAssociates...");

		Claims payload = JWTService.processToken(token);
		if (payload == null) {
			return Response.status(Status.UNAUTHORIZED).entity(JWTService.invalidTokenBody(token)).build();
		}
		Status status = null;
		status = Status.OK;
		
		JSONObject associateCounts = new JSONObject();
		
		List<Integer> counts = new ArrayList<>();
		
		counts.add(Integer.parseInt(associateService.getCountUndeployedMapped().toString()));
		counts.add(Integer.parseInt(associateService.getCountUndeployedUnmapped().toString()));
		
		counts.add(Integer.parseInt(associateService.getCountDeployedMapped().toString()));
		counts.add(Integer.parseInt(associateService.getCountDeployedUnmapped().toString()));
		
		counts.add(Integer.parseInt(associateService.getCountUnmappedTraining().toString()));
		counts.add(Integer.parseInt(associateService.getCountUnmappedOpen().toString()));
		counts.add(Integer.parseInt(associateService.getCountUnmappedSelected().toString()));
		counts.add(Integer.parseInt(associateService.getCountUnmappedConfirmed().toString()));
		
		counts.add(Integer.parseInt(associateService.getCountMappedTraining().toString()));
		counts.add(Integer.parseInt(associateService.getCountMappedReserved().toString()));
		counts.add(Integer.parseInt(associateService.getCountMappedSelected().toString()));
		counts.add(Integer.parseInt(associateService.getCountMappedConfirmed().toString()));
	
		associateCounts.put("counts", counts);
		return Response.status(status).entity(associateCounts.toString()).build();
	}

	/**
	 *
	 * @author Curtis H. Given a user id, returns an associate.
	 * @version v6.18.06.13
	 *
	 * @param id
	 * @param token
	 * @return
	 */
	@GET
	@ApiOperation(value = "Return an associate", notes = "Returns information about a specific associate.", response = TfAssociate.class)
	@Path("/{id}")
	public Response getAssociateByUserId(@ApiParam(value = "An associate id.") @PathParam("id") int id,
			@HeaderParam("Authorization") String token) {
		logger.info("getAssociateByUserId()...");
		Status status = null;
		Claims payload = JWTService.processToken(token);
		TfAssociate associateinfo;

		if (payload == null || false) {
			return Response.status(Status.UNAUTHORIZED).entity(JWTService.invalidTokenBody(token)).build();
		} else {
			try {
				associateinfo = associateService.getAssociateByUserId(id);
			} catch (NoResultException nre) {
				logger.info("No associate found...");
				return Response.status(Status.NO_CONTENT).build();
			}
			status = associateinfo == null ? Status.NO_CONTENT : Status.OK;
		}

		return Response.status(status).entity(associateinfo).build();
	}
	
	/**	
	 *	
	 * @author 	
	 * Given a associate id, returns an associate.	
	 * @version v6.18.06.13	
	 *	
	 * @param id	
	 * @param token	
	 * @return	
	 */	
	@GET	
	@ApiOperation(value = "Return an associate", notes = "Returns information about a specific associate.", response = TfAssociate.class)	
	@Path("/associates/{id}")	
	public Response getAssociate(@ApiParam(value = "An associate id.") @PathParam("id") int id,	
	                             @HeaderParam("Authorization") String token) {	
		logger.info("getAssociate()...");	
		Status status = null;	
		Claims payload = JWTService.processToken(token);	
		TfAssociate associateinfo;	
 		if (payload == null || false) {	
			return Response.status(Status.UNAUTHORIZED).entity(JWTService.invalidTokenBody(token)).build();	
		}	
		else {	
			try {	
				associateinfo = associateService.getAssociate(id);	
			} catch (NoResultException nre) {	
				logger.info("No associate found...");	
				return Response.status(Status.NO_CONTENT).build();	
			}	
			status = associateinfo == null ? Status.NO_CONTENT : Status.OK;	
		}	
		System.out.println(status);	
		System.out.println(associateinfo);
		return Response.status(status).entity(associateinfo).build();
	}

	/**
	 * 
	 * ------------- NEEDS WORK -------------
	 * 
	 * @author Adam L.
	 *         <p>
	 * 		Update the marketing status or client of associates
	 *         </p>
	 * @version v6.18.06.13
	 * 
	 * @param token
	 * @param marketingStatusId
	 * @param clientId
	 * @param ids
	 *            - list of ids to update
	 * @return response 200 status if successful
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Batch update associates", notes = "Updates the marketing status and/or the client of one or more associates")
	public Response updateAssociates(@HeaderParam("Authorization") String token,
			@DefaultValue("-1") @ApiParam(value = "marketingStatusId") @QueryParam("marketingStatusId") Integer marketingStatusId,
			@DefaultValue("-1") @ApiParam(value = "clientId") @QueryParam("clientId") Integer clientId,
			@DefaultValue("-1") @ApiParam(value = "verification") @QueryParam("verification") Integer isApproved,
			List<Integer> ids) {
		logger.info("updateAssociates()...");
		logger.info(ids);
		Status status = null;
		Claims payload = JWTService.processToken(token);

		List<TfAssociate> associates = new LinkedList<>();
		TfAssociate toBeUpdated = null;
		for (int associateId : ids) {
			toBeUpdated = associateService.getAssociate(associateId);
			if (marketingStatusId >= 0) {
				toBeUpdated.setMarketingStatus(marketingStatusService.getMarketingStatusById(marketingStatusId));
			}
			if (clientId >= 0) {
				toBeUpdated.setClient(clientService.getClient(clientId));
			}
			if (isApproved >= 0) {
				toBeUpdated.getUser().setIsApproved(isApproved);
			}
			associates.add(toBeUpdated);
		}


		if (payload == null || payload.getId().equals("2") || payload.getId().equals("5")) {
			return Response.status(Status.UNAUTHORIZED).entity(JWTService.invalidTokenBody(token)).build();
		} else {

			// marketing status & client id are given as query parameters, ids sent in body
			AssociateService as = new AssociateService();
			return as.updateAssociates(associates) ? Response.ok().build() : Response.serverError().build();
		}
	}

	/**
	 * 
	 * @author Adam L.
	 *         <p>
	 * 		Update the marketing status or client of an associate
	 *         </p>
	 * @version v6.18.06.13
	 * 
	 * @param id
	 * @param associate
	 * @param token
	 * @return
	 */
	@PUT
	@ApiOperation(value = "updates associate values", notes = "The method updates the marketing status or client of a given associate by their id.")
	@Path("/{associateId}")
	public Response updateAssociate(@PathParam("associateId") Integer id, TfAssociate associate,
			@HeaderParam("Authorization") String token) {

		logger.info("updateAssociate()...");
		Status status = null;
		Claims payload = JWTService.processToken(token);
		System.out.println(id);

		if (payload == null) {
			return Response.status(Status.UNAUTHORIZED).entity(JWTService.invalidTokenBody(token)).build();
		} else if (payload.getId().equals("5")) {
			status = associateService.updateAssociatePartial(associate) ? Status.OK : Status.INTERNAL_SERVER_ERROR;
		} else {
			status = associateService.updateAssociate(associate) ? Status.OK : Status.INTERNAL_SERVER_ERROR;
		}
		return Response.status(status).build();
	}

	@GET
	@ApiOperation(value = "Gets how many associates are mapped to each client", notes = "Gets how many associates are mapped to each client")
	@Path("mapped/{statusId}")
	public Response getMappedInfo(@PathParam("statusId") int statusId) {
		logger.info("getMappedInfo()...");
		return Response.ok(associateService.getMappedInfo(statusId)).build();
	}

	@GET
	@ApiOperation(value = "Gets how many associates are mapped to each client", notes = "Gets how many associates are mapped to each client")
	@Path("undeployed/{mappedOrUnmapped}")
	public Response getUndeployed(@PathParam("mappedOrUnmapped") String which) {
		logger.info("getUndeployed()...");
		return Response.ok(associateService.getUndeployed(which)).build();
	}

	@PUT
	@ApiOperation(value = "Approves an associate", notes = "Approves an associate")
	@Path("{assocId}/approve")
	public Response approveAssociate(@PathParam("assocId") int associateId) {
		return associateService.approveAssociate(associateId) ? Response.ok(true).build()
				: Response.serverError().entity(false).build();
	}

	@GET
	@Path("/nass/")
	public Response getNAssociates() {
		return Response.status(200).entity(associateService.getNAssociates()).build();
	}
	
	
	/**
	 * Get a single "page" of associates. Previous iterations retrieved all of the associates at once;
	 * this was a major performance chokepoint. This method will return only the associates requested.
	 * This method can filter the results by their marketing status id, or client.
	 * @param startIndex The index of the first employee to return. Note that the order of the results is solely dependant
	 * on how they are returned from the DAO.
	 * @param numResults The number of records that should be returned, or all if the actual number is less than numResults
	 * @param mStatusId Filter the results by this marketing status id. The default, -1, indicates to not use this filter
	 * @param clientId Filter the results by this client id. The default, -1, indicates to not use this filter
	 * @param token The authentication token obtained when logging in. User role '5' (Associate) will be rejected
	 * @return A json encoded list of type TfAssociate
	 */
	@GET
	@Path("/page")
	public Response getAssociatePage(
			@DefaultValue("0") @QueryParam("startIndex") Integer startIndex,
			@DefaultValue("50") @QueryParam("numResults") Integer numResults,
			@DefaultValue("-1") @QueryParam("mStatusId") Integer mStatusId,
			@DefaultValue("-1") @QueryParam("clientId") Integer clientId,
			@DefaultValue("") @QueryParam("sortText") String sortText,
			@HeaderParam("Authorization") String token) 
	
	{		
		logger.info("getAssociatePage(" + startIndex + ", " + numResults + ", " + mStatusId + ", " + clientId + ", " + sortText +")");
		Status status = null;
		Claims payload = JWTService.processToken(token);

		//Check token
		if (payload == null || payload.getId().equals("5")) {
			return Response.status(Status.UNAUTHORIZED).entity(JWTService.invalidTokenBody(token)).build();
		} 
		
		List<TfAssociate> associates;
		try {
			 associates = associateService.getAssociatePage(startIndex, numResults, mStatusId, clientId, sortText);
		} catch (IllegalArgumentException iae) {
			return Response.status(Status.BAD_REQUEST).build();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}

		//If no results, return 204 and null ; otherwise 200 and the list 
		status = associates == null ? Status.NO_CONTENT : Status.OK;

		return Response.status(status).entity(associates).build();
	}
}