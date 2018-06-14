package com.revature.resources;

import static com.revature.utils.LogUtil.logger;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.dao.AssociateDaoHibernate;
import com.revature.entity.TfAssociate;
import com.revature.entity.TfUser;
import com.revature.model.AssociateInfo;
import com.revature.model.ClientMappedJSON;
import com.revature.request.model.AssociateFromClient;
import com.revature.request.model.CreateAssociateModel;
import com.revature.services.AssociateService;
import com.revature.services.BatchService;
import com.revature.services.ClientService;
import com.revature.services.CurriculumService;
import com.revature.services.InterviewService;
import com.revature.services.JWTService;
import com.revature.services.TrainerService;
import com.revature.services.UserService;

import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


/**
 * <p> </p>
 * @version.date v06.2018.06.13
 *
 */
@Path("/associates")
@Api(value = "associates")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AssociateResource {
	private AssociateService service = new AssociateService();
	private UserService userService;

	public AssociateResource() {
		this.userService = new UserService();
	}

	
	// You're probably thinking, why would you ever do this? Why not just just make the methods all static in the service class?
	// This is to allow for Mokito tests, which have problems with static methods
	// This is here for a reason! 
	// - Adam 06.2018.06.13
	AssociateService associateService = new AssociateService();
	BatchService batchService = new BatchService();
	ClientService clientService = new ClientService();
	CurriculumService curriculumService = new CurriculumService();
	InterviewService interviewService = new InterviewService();
	TrainerService trainerService = new TrainerService();
	UserService userService = new UserService();
	
	/**
	 * <p>Gets a list of all the associates, optionally filtered by a batch id. If an
	 * associate has no marketing status or curriculum, replaces them with blanks.
	 * If associate has no client, replaces it with "None".</p>
	 * @version.date v06.2018.06.13
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
			status = Status.UNAUTHORIZED;
		}
		else {
			associates = service.getAllAssociates();
			status = associates == null || associates.isEmpty() ? Status.NO_CONTENT : Status.OK;
		}

		return Response.status(status).entity(associates).build();
	}

	
	/**
	 * 
	 * @author Adam L. 
	 * <p> </p>
	 * @version.date v06.2018.06.13
	 * 
	 * @param associateid
	 * @param token
	 * @return
	 */
	@GET
	@ApiOperation(value = "Return an associate", notes = "Returns information about a specific associate.", response = AssociateInfo.class)
	@Path("/{associateid}")
	public Response getAssociate(@ApiParam(value = "An associate id.") @PathParam("associateid") int associateid,
			@HeaderParam("Authorization") String token) {
		logger.info("getAssociate()...");
		Status status = null;
		Claims payload = JWTService.processToken(token);
		TfAssociate associateinfo = associateService.getAssociate(associateid);

		if (payload == null || false) {
			status = Status.UNAUTHORIZED;
		}

		else {
			associateinfo = service.getAssociate(associateid);
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
	 * @version.date v06.2018.06.13
	 * 
	 * @param token
	 * @param marketingStatusId
	 * @param clientId
	 * @param ids - list of ids to update
	 * @return response 200 status if successful
	 */
	@PUT
	@ApiOperation(value = "Batch update associates", notes = "Updates the maretking status and/or the client of one or more associates")
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
			toBeUpdated = associateService.getAssociate(associateId);
//			toBeUpdated.setTfMarketingStatus(tfMarketingStatus);
//			toBeUpdated.setTfClient(tfClient);
			associates.add(toBeUpdated);
		}

		if (payload == null || !payload.getId().equals("1")) {
			status = Status.UNAUTHORIZED;
		}

		else {
			// marketing status & client id are given as query parameters, ids sent in body
			associateService.updateAssociates(associates);
		}

		return Response.ok().build();
	}


	/**
	 * 
	 * @author Adam L. 
	 * <p>Update the marketing status or client of an associate</p>
	 * @version.date v06.2018.06.13
	 * 
	 * @param id 
	 * @param associate
	 * @param token
	 * @return
	 */
	@PUT
	@ApiOperation(value = "updates associate values", notes = "The method updates the marketing status or client of a given associate by their id.")
	@Path("/{associateId}")
	public Response updateAssociate(@PathParam("associateId") Integer id, AssociateFromClient afc,
			@HeaderParam("Authorization") String token) {
		logger.info("updateAssociate()...");
		Status status = null;
		Claims payload = JWTService.processToken(token);

		if (payload == null || payload.getId().equals("5")) {
			status = Status.UNAUTHORIZED;
		}

		else {
			service.updateAssociate(afc);
			status = Status.OK;
		}

		return Response.status(status).build();
	}

	/*** OPTION 2 ***/
	//TODO MOVE OUT HIBERNATE CODE
	@PUT
	@ApiOperation(value = "updates associate values", notes = "The method updates start date of the client.")
	@Path("/{associateId}/{startDate}")
	public Response updateAssociate(@PathParam("associateId") Integer id, @PathParam("startDate") String startDate,
			@HeaderParam("Authorization") String token) {
		logger.info("updateAssociate2()...");
		Status status = null;
		Claims payload = JWTService.processToken(token);

		if (payload == null || payload.getId().equals("5")) {
			status = Status.UNAUTHORIZED;
		}

		else {
			associateService.updateAssociate(associate);
			status = Status.OK;
		}

		return Response.status(status).build();
	}
}
