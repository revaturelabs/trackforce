package com.revature.resources;

import static com.revature.utils.HibernateUtil.updateDetached;
import static com.revature.utils.LogUtil.logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PATCH;
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
 * <p> </p>
 * @version v6.18.06.13
 *
 */
@Path("/associates")
@Api(value = "associates")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AssociateResource {

	
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
	MarketingStatusService marketingStatusService = new MarketingStatusService();
	
	/**
	 * <p>Gets a list of all the associates, optionally filtered by a batch id. If an
	 * associate has no marketing status or curriculum, replaces them with blanks.
	 * If associate has no client, replaces it with "None".</p>
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
			return Response.status(Status.UNAUTHORIZED).build();
		}

		
		else {
			if(payload.getId().equals("2")) {
				List<TfAssociate> assoc=new ArrayList<TfAssociate>();
				for(TfAssociate a:associates) {
					if(a.getBatch()!=null) {
						if(payload.getSubject().equals(a.getBatch().getTrainer().getTfUser().getUsername())) {
							assoc.add(a);
						}
						List<TfTrainer> cotrainers=a.getBatch().getCoTrainer();
						for(TfTrainer t:cotrainers) {
							if(t.getTfUser().getUsername().equals(payload.getSubject())) {
								assoc.add(a);
							}
						}

					}
				}
				associates=assoc;
			}
			status = associates == null || associates.isEmpty() ? Status.NO_CONTENT : Status.OK;
		}

		return Response.status(status).entity(associates).build();
	}

	
	/**
	 *
	 * @author Curtis H.
	 * Given a user id, returns an associate.
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
			return Response.status(Status.UNAUTHORIZED).build();
		}
		else {
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
	 * ------------- NEEDS WORK -------------
	 * 
	 * @author Adam L. 
	 * <p>Update the marketing status or client of associates</p>
	 * @version v6.18.06.13
	 * 
	 * @param token
	 * @param marketingStatusId
	 * @param clientId
	 * @param ids - list of ids to update
	 * @return response 200 status if successful
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Batch update associates", notes = "Updates the marketing status and/or the client of one or more associates")
	public Response updateAssociates(@HeaderParam("Authorization") String token,
			@DefaultValue("0") @ApiParam(value = "marketingStatusId") @QueryParam("marketingStatusId") Integer marketingStatusId,
			@DefaultValue("0") @ApiParam(value = "clientId") @QueryParam("clientId") Integer clientId,
			@DefaultValue("-1") @ApiParam(value="verification") @QueryParam("verification") Integer isApproved,
			List<Integer> ids) {
		logger.info("updateAssociates()...");
		logger.info(ids);
		Status status = null;
		Claims payload = JWTService.processToken(token);
		
		List<TfAssociate> associates = new LinkedList<>();
		TfAssociate toBeUpdated = null;
		for(int associateId : ids) {
			toBeUpdated = associateService.getAssociate(associateId);
			if(marketingStatusId!=0) {
				toBeUpdated.setMarketingStatus(marketingStatusService.getMarketingStatusById(marketingStatusId));
			}
			if(clientId!=0) {
				toBeUpdated.setClient(clientService.getClient(clientId));
			}
			if(isApproved>=0) {
				toBeUpdated.getUser().setIsApproved(isApproved);
			}
			associates.add(toBeUpdated);
		}


		if (payload == null || !payload.getId().equals("1")) {
			return Response.status(Status.UNAUTHORIZED).build();
		}
		else {
			
			// marketing status & client id are given as query parameters, ids sent in body
			AssociateService as=new AssociateService();
			return as.updateAssociates(associates) ? Response.ok().build() : Response.serverError().build();
		}
	}


	/**
	 * 
	 * @author Adam L. 
	 * <p>Update the marketing status or client of an associate</p>
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

		if (payload == null || payload.getId().equals("5")) {
			return Response.status(Status.UNAUTHORIZED).build();
		}
		else if (payload.getId().equals("5")) {
			status = associateService.updateAssociatePartial(associate) ? Status.OK : Status.INTERNAL_SERVER_ERROR;
		}
		else {
			status = associateService.updateAssociate(associate) ? Status.OK : Status.INTERNAL_SERVER_ERROR;
		}
		return Response.status(status).build();
	}

	@GET
	@ApiOperation(value = "Gets how many associates are mapped to each client", notes="Gets how many associates are mapped to each client")
	@Path("mapped/{statusId}")
	public Response getMappedInfo(@PathParam("statusId") int statusId) {
		logger.info("getMappedInfo()...");
		return Response.ok(associateService.getMappedInfo(statusId)).build();
	}


	@GET
	@ApiOperation(value = "Gets how many associates are mapped to each client", notes="Gets how many associates are mapped to each client")
	@Path("undeployed/{mappedOrUnmapped}")
	public Response getUndeployed(@PathParam("mappedOrUnmapped") String which) {
		logger.info("getUndeployed()...");
		return Response.ok(associateService.getUndeployed(which)).build();
	}
	
	@PUT
	@ApiOperation(value="Approves an associate", notes="Approves an associate")
	@Path("{assocId}/approve")
	public Response approveAssociate(@PathParam("assocId") int associateId) {
		return associateService.approveAssociate(associateId) ? Response.ok(true).build() : Response.serverError().entity(false).build();
	}

}
