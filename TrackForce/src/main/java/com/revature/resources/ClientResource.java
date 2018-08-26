package com.revature.resources;
import com.revature.entity.TfClient;
import com.revature.services.AssociateService;
import com.revature.services.ClientService;
import com.revature.services.JWTService;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.List;
import static com.revature.utils.LogUtil.logger;

/** @version v6.18.06.13 */
@Path("/clients")
@Api(value = "clients")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class ClientResource
{
	// You're probably thinking, why would you ever do this? Why not just just make the methods all static in the service class?
	// This is to allow for Mockito tests, which have problems with static methods
	// - Adam 06.18.06.13
	private AssociateService associateService = new AssociateService();
	private ClientService clientService = new ClientService();

	/** @author Adam L.
	 * <p> Returns a map of all of the clients as a response object. </p>
	 * @version v6.18.06.13 */
	@GET
	@ApiOperation(value = "Returns all clients", notes = "Returns a map of all clients.")
	public Response getAllClients(@HeaderParam("Authorization") String token) {
		logger.info("getAllClients()...");
		Status status;
		List<TfClient> clients = clientService.getAllTfClients();
		Claims payload = JWTService.processToken(token);

		if (payload == null) // invalid token
			return Response.status(Status.UNAUTHORIZED).entity(JWTService.invalidTokenBody(token)).build();
		else
			status = clients == null || clients.isEmpty() ? Status.NO_CONTENT : Status.OK;
		return Response.status(status).entity(clients).build();
	}

	@GET
	@Path("/associates/get/{client_id}")
	public Response getMappedAssociatesByClientId(@PathParam("client_id") Long client_id) {
		Long[] response = new Long[4];
		for (int i = 0; i < response.length; i++)
			response[i] = associateService.getMappedAssociateCountByClientId(client_id, i + 1);
		return Response.status(200).entity(response).build();
	}
	
	@GET
	@Path("/mapped/get/")
	public Response getMappedClients()
	{ return Response.status(200).entity(clientService.getMappedClients()).build(); }
	
	@GET
	@Path("/50/")
	public Response getFirstFiftyClients()
	{ return Response.status(200).entity(clientService.getFirstFiftyClients()).build(); }
}