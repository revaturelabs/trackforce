package com.revature.resources;

import static com.revature.utils.LogUtil.logger;

import java.io.IOException;
import java.util.HashMap;
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

import org.apache.log4j.Logger;

import com.revature.entity.TfClient;
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
	private final static Logger logger = Logger.getLogger(ClientResource.class);

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
	
	/*
	 * Each of the methods below builds three different responses determined by the method
	 * authorizeAdminUser whether or not the 1) token is bad, 2) if the token is either expired 
	 * or the username does not match the token username or 3) if the token is good, is valid, 
	 * and the user role is equal to 1, designating an administrator which is the only role 
	 * which should be permitted to view the client list. 
	 *
	 */
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
		Response forbidden = Response.status(403).build();
		Response authorized = Response.status(clients == null || clients.isEmpty() ? Status.NO_CONTENT : Status.OK).entity(clients).build();

		logger.info("Status 401: Null token. Status 403: Forbidden Access. Status 200: Good to Go");
		return authorizeUserToken(badToken, forbidden, authorized, token);
	}

	@GET
	@Path("/associates/get/{client_id}")
	public Response getMappedAssociatesByClientId(@PathParam("client_id") Long client_id, @HeaderParam("Authorization") String token) {
		Long[] response = new Long[4];
		logger.info("Method call to getMappedAssociatesByClientId");
		// Requesting data for all clients is indicated by -1 for client id
		if(client_id == -1) {
			String[] countMapKeys = {"Mapped Training", "Mapped Reserved", "Mapped Selected", "Mapped Confirmed"};
			HashMap<String,Integer> countMap = associateService.getStatusCountsMap();
			
			for (Integer i = 0; i < response.length; i++) {
				response[i] = Long.parseLong(countMap.get(countMapKeys[i]).toString());
			}
		// Otherwise return data for a single specific client
		} else {
			for (Integer i = 0; i < response.length; i++) {
				response[i] = associateService.getMappedAssociateCountByClientId(client_id, i + 1);
			}
		}

		Response badToken = Response.status(401).entity(JWTService.invalidTokenBody(token)).build();
		Response forbidden = Response.status(403).build();
		Response authorized = Response.status(200).entity(response).build();

		logger.info("Status 401: Null token. Status 403: Forbidden Access. Status 200: Good to Go");
		return authorizeUserToken(badToken, forbidden, authorized, token);
	}

	@GET
	@Path("/mapped/get/")
	public Response getMappedClients(@HeaderParam("Authorization") String token) {
		logger.info("Method call to getMappedClients");
		List<TfClient> clients = clientService.getMappedClients();
		Response badToken = Response.status(401).entity(JWTService.invalidTokenBody(token)).build();
		Response forbidden = Response.status(403).build();
		Response authorized = Response.status(200).entity(clients).build();

		logger.info("Status 401: Null token. Status 403: Forbidden Access. Status 200: Good to Go");
		return authorizeUserToken(badToken, forbidden, authorized, token);
	}

	@GET
	@Path("/50/")
	public Response getFirstFiftyClients(@HeaderParam("Authorization") String token) {
		logger.info("Method call to getFirstFiftyClients");
		List<TfClient> clients = clientService.getFirstFiftyClients();
		Response badToken = Response.status(401).entity(JWTService.invalidTokenBody(token)).build();
		Response forbidden = Response.status(403).build();
		Response authorized = Response.status(200).entity(clients).build();

		logger.info("Status 401: Null token. Status 403: Forbidden Access. Status 200: Good to Go");
		return authorizeUserToken(badToken, forbidden, authorized, token);
	}
	
	/**
	 * 
	 *Created to abstract out the authorization portion for each of the above methods in the 
	 *ClientResource class. Allows each to return a response unique to its purpose. 
	 *@author Ashley R
	 *Written 11 Nov 2018
	 */
	public Response authorizeUserToken(Response badToken, Response forbidden, Response authorized, String token) {
		//Processes the token and returns the payload
		Claims payload = JWTService.processToken(token);
		logger.info("Processing the user's JWT.");
		//Checks to see if payload is null, and if so assigns it as being an invalid token body 
		if (payload == null) {
			logger.error("The payload was null. Unathorized access.");
			return badToken;
		} else {
			//Ensures the token is unexpired and the username on the token matches that
			//of the logged in user. If it is not valid, assigns it a status of forbidden
			if (new JWTService().validateToken(token) == false) {
				logger.info("Checking to see if the JWT is valid.");
				return forbidden;
			} else {
				//The roleID is checked from the decrypted token. Any user should 
				// be able to view the client list as associates need the list
				// for scheduling interviews
				int role = 0;
				role = Integer.parseInt((String) payload.get("roleID"));
				logger.info("Returning user roleID.");
				if (role > 0 && role <= 5) {
					logger.info("User authorized to view client list.");
					return authorized;
				} else {
					logger.error("User had insufficent privileges.");
					return forbidden;
				}
			}
		}
}}