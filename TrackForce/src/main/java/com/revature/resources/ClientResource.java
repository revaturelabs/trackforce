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
	Response badToken = null;
	Response forbidden = null;
	Response authorized = null;
	
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
	@Path("/getAll/")
	@ApiOperation(value = "Returns all clients", notes = "Returns a map of all clients.")
	public Response getAllClients(@HeaderParam("Authorization") String token) {
		logger.info("Method call to getAllClients()...");
		List<TfClient> clients = clientService.getAllTfClients();
		Response badToken = Response.status(401).entity(JWTService.invalidTokenBody(token)).build();
		Response forbidden = Response.status(403).entity(clients).build();
		Response authorized = Response.status(clients == null || clients.isEmpty() ? Status.NO_CONTENT : Status.OK).entity(clients).build();

		return authorizeAdminUser(badToken, forbidden, authorized, token);
		
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
	public Response getMappedClients(@HeaderParam("Authorization") String token) {
		List<TfClient> clients = clientService.getMappedClients();
		Response badToken = Response.status(401).entity(JWTService.invalidTokenBody(token)).build();
		Response forbidden = Response.status(403).entity(clients).build();
		Response authorized = Response.status(200).entity(clients).build();

		return authorizeAdminUser(badToken, forbidden, authorized, token);
	}

	@GET
	@Path("/50/")
	public Response getFirstFiftyClients(@HeaderParam("Authorization") String token) {
		List<TfClient> clients = clientService.getFirstFiftyClients();
		Response badToken = Response.status(401).entity(JWTService.invalidTokenBody(token)).build();
		Response forbidden = Response.status(403).entity(clients).build();
		Response authorized = Response.status(200).entity(clients).build();

		return authorizeAdminUser(badToken, forbidden, authorized, token);
	
	}
	
	public Response authorizeAdminUser(Response badToken, Response forbidden, Response authorized, String token) {
		Claims payload = JWTService.processToken(token);
		if (payload == null) {
			return badToken;
		} else {

			if (JWTService.validateToken(token) == false) {
				return forbidden;
			} else {
				int role = 0;
				role = Integer.parseInt((String) payload.get("roleID"));
				if (role == 1) {
					return authorized;
				} else {
					return forbidden;
				}
			}
		}
}}