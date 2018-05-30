package com.revature.resources;

import static com.revature.utils.LogUtil.logger;

import java.io.IOException;
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

import org.hibernate.HibernateException;

import com.revature.model.ClientInfo;
import com.revature.model.InterviewInfo;
import com.revature.services.ClientService;
import com.revature.services.JWTService;

import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("clients")
@Api(value = "clients")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class ClientResource {
    private ClientService service;

    public ClientResource() {
        this.service = new ClientService();
    }

    /**
     * Returns a map of all of the clients as a response object.
     *
     * @return A map of TfClients as a Response object
     * @throws IOException
     * @throws HibernateException
     */
    @GET
    @ApiOperation(value = "Returns all clients", notes = "Returns a map of all clients.")
    public Response getAllClients(@HeaderParam("Authorization") String token) throws IOException 
    {
    	Status status = null;
    	Set<ClientInfo> clients = null;
		Claims payload = JWTService.processToken(token);

		if (payload == null || !payload.getId().equals("1")) 
		{
			status = Status.UNAUTHORIZED;
		} 
		
		else 
		{
			clients = service.getClients();
		}		
    	
        return Response.status(status).entity(clients).build();
    }

    /**
     * Returns a StatusInfo object representing a client's associates and their
     * statuses.
     *
     * @param clientid The id of the client in the TfClient table
     * @return A StatusInfo object for a specified client
     * @throws IOException
     * @throws HibernateException
     */
    @Path("{clientid}")
    @GET
    @ApiOperation(value = "Returns a client", notes = "Returns a specific client based on client id.")
    public Response getClientInfo(@PathParam("clientid") int clientid, @HeaderParam("Authorization") String token) throws IOException 
    {
    	Status status = null;
    	ClientInfo client = null;
		Claims payload = JWTService.processToken(token);

		if (payload == null || !payload.getId().equals("1")) 
		{
			status = Status.UNAUTHORIZED;
		} 
		
		else 
		{
			 client = service.getClientByID(clientid);
		}		

		return Response.status(status).entity(client).build();
    }
}

