package com.revature.resources;

import static com.revature.utils.LogUtil.logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
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
import org.json.JSONObject;

import com.revature.entity.TfAssociate;
import com.revature.entity.TfUser;
import com.revature.services.AssociateService;
import com.revature.services.BatchService;
import com.revature.services.ClientService;
import com.revature.services.CurriculumService;
import com.revature.services.InterviewService;
import com.revature.services.JWTService;
import com.revature.services.MarketingStatusService;
import com.revature.services.TrainerService;
import com.revature.services.UserService;
import com.revature.utils.UserAuthentication;

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
		
		if (!UserAuthentication.Authorized(token, new int[] {1,2,3,4}))
			return Response.status(Status.UNAUTHORIZED).entity(JWTService.invalidTokenBody(token)).build();

		List<TfAssociate> associates = associateService.getAllAssociates();

		Status status = associates == null || associates.isEmpty() ? Status.NO_CONTENT : Status.OK;
		return Response.status(status).entity(associates).build();
	}
	
	@Path("/countAssociates")
	@GET
	@ApiOperation(value = "Return count of associates per category", notes = "Gets a count of the associates in each category,", 
				  response = TfAssociate.class, responseContainer = "Set")
	public Response getCountAssociates(@HeaderParam("Authorization") String token) {
		logger.info("getCountAssociates...");

		if (!UserAuthentication.Authorized(token, new int[] {1,2,3,4}))
			return Response.status(Status.UNAUTHORIZED).entity(JWTService.invalidTokenBody(token)).build();
		
		Status status = Status.OK;
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
	@ApiOperation(value = "Return an associate provided their user ID", notes = "Returns information about a specific associate.", response = TfAssociate.class)
	@Path("/{id}")
	public Response getAssociateByUserId(@ApiParam(value = "The USER ID of an associate.") @PathParam("id") int id,
			@HeaderParam("Authorization") String token) {
		logger.info("getAssociateByUserId()...");
		
		//This only checks if this is a valid token, not if authorized
		if (!UserAuthentication.Authorized(token, new int[] {1,2,3,4,5}))
			return Response.status(Status.UNAUTHORIZED).entity(JWTService.invalidTokenBody(token)).build();
		
		TfAssociate associateinfo = associateService.getAssociateByUserId(id);
		
		//Get the user account from the token
		Claims payload = JWTService.processToken(token);
		TfUser user = userService.getUser(payload.getSubject());
		
		if (associateinfo == null) { //No associate with that ID
			return Response.status(Status.NO_CONTENT).build();
		} else if (payload.getId().equals("5") && associateinfo.getUser().getId() != user.getId()) {
			//The user is logged in as a different associate
			return Response.status(Status.UNAUTHORIZED).entity(JWTService.invalidTokenBody(token)).build();
		}

		return Response.status(Status.OK).entity(associateinfo).build();
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

		//This only checks if this is a valid token, not if authorized
		if (!UserAuthentication.Authorized(token, new int[] {1,2,3,4,5}))
			return Response.status(Status.UNAUTHORIZED).entity(JWTService.invalidTokenBody(token)).build();

		TfAssociate associateinfo = associateService.getAssociate(id);

		//Get the user account from the token
		Claims payload = JWTService.processToken(token);
		TfUser user = userService.getUser(payload.getSubject());
		
		if (associateinfo == null) { //No associate with that ID
			return Response.status(Status.NO_CONTENT).build();
		} else if (payload.getId().equals("5") && associateinfo.getUser().getId() != user.getId()) {
			//The user is logged in as a different associate
			return Response.status(Status.UNAUTHORIZED).entity(JWTService.invalidTokenBody(token)).build();
		}

		return Response.status(Status.OK).entity(associateinfo).build();

	}

	/**
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
	 * @return response 200 status if successful, regardless of if the id's were present
	 * 500 on error
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Batch update associates", notes = "Updates the marketing status and/or the client of one or more associates")
	public Response updateAssociates(@HeaderParam("Authorization") String token,
			@DefaultValue("0") @ApiParam(value = "marketingStatusId") @QueryParam("marketingStatusId") Integer marketingStatusId,
			@DefaultValue("0") @ApiParam(value = "clientId") @QueryParam("clientId") Integer clientId,
			@DefaultValue("-1") @ApiParam(value = "verification") @QueryParam("verification") Integer isApproved,
			List<Integer> ids) {
		logger.info("updateAssociates()...");
		
		if (!UserAuthentication.Authorized(token, new int[] {1,3,4})) {
			//This user is not of the apropriate role
			return Response.status(Status.UNAUTHORIZED).entity(JWTService.invalidTokenBody(token)).build();
		} else if (ids == null) {
			//Request body can not be parsed into type List<Integer>
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		//Get all of the associates requested
		List<TfAssociate> associates = new LinkedList<>();
		for (int associateId : ids) { 
			associates.add(associateService.getAssociate(associateId));
		}
		
		//An associate ID that is not in the database was requested.
		//Atomicity says that none of the transaction should resolve
		if (associates.contains(null)) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		//Update the associates in the list
		for (TfAssociate associate : associates) {
			if (marketingStatusId != 0) {
				associate.setMarketingStatus(marketingStatusService.getMarketingStatusById(marketingStatusId));
			}
			if (clientId != 0) {
				associate.setClient(clientService.getClient(clientId));
			}
			if (isApproved >= 0) {
				associate.getUser().setIsApproved(isApproved);
			}
		}

		//FIXME - If one of the querry parameters are not present in the database, the DAO should
		//signal that an update failure occured, possibly through an exception. As of now, the
		//associates are updated with null. This is a low priority bug
		
		//200 or 500
		try {
			associateService.updateAssociates(associates);
			logger.info("Update successful on " + associates.size() + " associates, no exceptions");
			return Response.ok().build();
		} catch (Exception e) { 
			logger.error(e.getMessage());
			return Response.serverError().build();
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
	 * This parameter was never actually used in the origional implementation. I kept it in place to keep
	 * any tests working. The associate actually updated will be: the one that corresponds to the 
	 * logged in users token if the user is an associate, or the one that corresponds to the associate
	 * object passed in the body otherwise. -MD
	 * @param associate
	 * @param token
	 * @return
	 */
	
	/* FIXME!!! This updates the associate based on a TfAssociate object obtained in a seperate resource.
	That object is missing data such as password, and if this resource is called by a user other than an associate
	the missing data was set to null. Furthermore, an associate was able to forge a TfAssociate object and
	update another associate record. The fix I have implemented is a bad hack and should be reworked. Unfourtinately,
	we have run out of time -MD */
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "updates associate values", notes = "The method updates the marketing status or client of a given associate by their id.")
	@Path("/{associateId}")
	public Response updateAssociate(@PathParam("associateId") Integer id, TfAssociate associate,
			@HeaderParam("Authorization") String token) {

		logger.info("updateAssociate()...");
		
		//Check if the user has a valid token, role is not considered here
		if (!UserAuthentication.Authorized(token, new int[] {1,2,3,4,5}))
			return Response.status(Status.UNAUTHORIZED).entity(JWTService.invalidTokenBody(token)).build();
		
		//Get the id from the associate object provided. This could throw
		int reqId;
		try {
			reqId = associate.getId();
		} catch (NullPointerException npe) {
			logger.info("NullPointerException caught while getting associateId");
			return Response.status(Status.BAD_REQUEST).build();
		}

		Claims payload = JWTService.processToken(token);
		
		try {
			if (payload.getId().equals("5")) {
				/* An associate wants to update their name
				Get their associate object based on their token and make sure they are trying to update their own
				and not someone elses */
				TfUser user = userService.getUser(payload.getSubject());
				TfAssociate dbAssoc = associateService.getAssociateByUserId(user.getId());
				
				if (dbAssoc == null || dbAssoc.getId() != reqId)			
					return Response.status(Status.BAD_REQUEST).build();
				
				//All checks are good, update
				associateService.updateAssociatePartial(associate);
				return Response.ok().build();
				
			} else { 
				//As of now, any other role can make these changes to an associate
				TfAssociate assocRecordToUpdate = associateService.getAssociate(reqId);
				assocRecordToUpdate.setMarketingStatus(associate.getMarketingStatus());
				assocRecordToUpdate.setClient(associate.getClient());
				assocRecordToUpdate.setBatch(associate.getBatch());
				assocRecordToUpdate.getUser().setIsApproved(associate.getUser().getIsApproved());
				
				associateService.updateAssociate(assocRecordToUpdate);
				return Response.ok().build();
			}
		} catch (NullPointerException npe) {
			logger.info("NullPointerException caught while parsing associate data");
			return Response.status(Status.BAD_REQUEST).build();
		}
	}
	

	@GET
	@ApiOperation(value = "Gets how many associates are mapped to each client", notes = "Gets how many associates are mapped to each client")
	@Path("mapped/{statusId}")
	public Response getMappedInfo(@PathParam("statusId") int statusId, @HeaderParam("Authorization") String token) {
		
		logger.info("getMappedInfo()...");
		if (UserAuthentication.Authorized(token, new int[] {1,2,3,4}))
			return Response.ok(associateService.getMappedInfo(statusId)).build();
		else
			return Response.status(Status.UNAUTHORIZED).entity(JWTService.invalidTokenBody(token)).build();
	}

	@GET
	@ApiOperation(value = "Gets how many associates are mapped to each client", notes = "Gets how many associates are mapped to each client")
	@Path("undeployed/{mappedOrUnmapped}")
	public Response getUndeployed(@PathParam("mappedOrUnmapped") String which, @HeaderParam("Authorization") String token) {
		
		logger.info("getUndeployed()...");
		if (UserAuthentication.Authorized(token, new int[] {1,2,3,4}))
			return Response.ok(associateService.getUndeployed(which)).build();
		else
			return Response.status(Status.UNAUTHORIZED).entity(JWTService.invalidTokenBody(token)).build();
	}

	@PUT
	@ApiOperation(value = "Approves an associate", notes = "Approves an associate")
	@Path("{assocId}/approve")
	public Response approveAssociate(@PathParam("assocId") int associateId, @HeaderParam("Authorization") String token) {

		logger.info("approveAssociate() assocId == " + associateId);
		if (UserAuthentication.Authorized(token, new int[] {1,2,3,4})) 
			return Response.ok(associateService.approveAssociate(associateId)).build();
		else
			return Response.status(Status.UNAUTHORIZED).entity(JWTService.invalidTokenBody(token)).build();
	}

	@GET
	@Path("/nass/")
	//FIXME This should be removed once paging is implemented
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
			@HeaderParam("Authorization") String token) 
	
	{		
		logger.info("getAssociatePage(" + startIndex + ", " + numResults + ", " + mStatusId + ", " + clientId + ")");

		//Check token
		if (!UserAuthentication.Authorized(token, new int[] {1,2,3,4})) {
			return Response.status(Status.UNAUTHORIZED).entity(JWTService.invalidTokenBody(token)).build();
		} 
		
		List<TfAssociate> associates;
		try {
			 associates = associateService.getAssociatePage(startIndex, numResults, mStatusId, clientId);
		} catch (IllegalArgumentException iae) {
			System.out.println("Exception caught: " + iae.getClass().getName() + " ; " + iae.getMessage());
			return Response.status(Status.BAD_REQUEST).build();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}

		//If no results, return 204 and null ; otherwise 200 and the list 
		Status status = (associates == null) ? Status.NO_CONTENT : Status.OK;

		return Response.status(status).entity(associates).build();
	}
}