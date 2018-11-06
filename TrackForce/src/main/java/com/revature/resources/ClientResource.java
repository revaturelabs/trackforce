package com.revature.resources;

import static com.revature.utils.LogUtil.logger;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.revature.entity.TfClient;
import com.revature.services.AssociateService;
import com.revature.services.BatchService;
import com.revature.services.ClientService;
import com.revature.services.CurriculumService;
import com.revature.services.InterviewService;
import com.revature.services.JWTService;
import com.revature.services.TrainerService;
import com.revature.services.UserService;
import com.revature.utils.UserAuthentication;

import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * </p>
 * 
 * @version v6.18.06.13
 */
@Path("/clients")
@Api(value = "clients")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class ClientResource {

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

	/**
	 * 
	 * @author Adam L.
	 *         <p>
	 *         Returns a map of all of the clients as a response object.
	 *         </p>
	 * @version v6.18.06.13
	 * 
	 * @param token
	 * @return
	 * @throws IOException
	 */
	@GET
	@Path("/") //added by Ashley R.
	@ApiOperation(value = "Returns all clients", notes = "Returns a map of all clients.")
	public Response getAllClients(@HeaderParam("Authorization") String token) {
		logger.info("getAllClients()...");
		Status status = null;
		List<TfClient> clients = clientService.getAllTfClients();
		Claims payload = JWTService.processToken(token);
		
//		if (UserAuthentication.Authorized(token, new int[] {1})) {
			if (payload == null || UserAuthentication.Authorized(token, new int[] {1})) {
				System.out.println("Authentication Failed.");
				return Response.status(Status.UNAUTHORIZED).entity(JWTService.invalidTokenBody(token)).build();
			}
			// invalid token
			else {
				JWTService.validateToken(token);
				status = clients == null || clients.isEmpty() ? Status.NO_CONTENT : Status.OK;
			}
		
		//}
		
		return Response.status(status).entity(clients).build();
	}

	@GET
	@Path("/associates/get/{client_id}")
	public Response getMappedAssociatesByClientId(@PathParam("client_id") Long client_id) {
		Long[] response = new Long[4];
		for (Integer i = 0; i < response.length; i++) {
			response[i] = associateService.getMappedAssociateCountByClientId(client_id, i + 1);
		}
		return Response.status(200).entity(response).build();
	}
	
	@GET
	@Path("/mapped/get/")
	public Response getMappedClients() {
		return Response.status(200).entity(clientService.getMappedClients()).build();
	}
	
	@GET
	@Path("/50/")
	public Response getFirstFiftyClients(@HeaderParam("Authorization") String token) {
		//create a list to store the 50 clients that get returned.
		List<TfClient> clients = clientService.getFirstFiftyClients();
		//create a variable to store the status code
		Status status = null;
		//decode the token to check the roleId provided
		Claims payload = JWTService.processToken(token);
		
		if (payload == null) {
			return Response.status(405).entity(null).build();
		}
		return Response.status(status).entity(clients).build();
	}
	
	
	
}