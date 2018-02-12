package com.revature.resources;

import java.io.IOException;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.HibernateException;

import com.revature.model.ClientInfo;
import com.revature.services.ClientService;

@Path("clients")
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
    @Consumes({MediaType.APPLICATION_JSON})
    public Response getAllClients(@QueryParam("id") int id, @QueryParam("name") String name ) throws IOException {
    	Set<ClientInfo> clients;
    	if(id > 0) clients = service.getClientByID(id);
    	else {
    		if( !name.isEmpty() )clients = service.getClientByName(name);
    		else clients = service.getClients();
    	}
        return Response.ok(clients).build();
    }

//    /**
//     * Returns a StatusInfo object representing a client's associates and their
//     * statuses.
//     *
//     * @param clientid The id of the client in the TfClient table
//     * @return A StatusInfo object for a specified client
//     * @throws IOException
//     * @throws HibernateException
//     */
//    @Path("{clientid}")
//    @GET
//    public Response getClientInfo(@PathParam("clientid") int clientid) throws IOException {
//    	StatusInfo si = service.getClientInfo(clientid);
//        return Response.ok(si).build();
//    }
}

