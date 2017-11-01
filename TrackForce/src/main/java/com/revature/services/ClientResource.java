package com.revature.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.revature.model.Client;

@Path("/clients")
public class ClientResource {

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Client getAllClients() {
		Client clients = new Client("All Clients", 100, 95, 75, 107, 145, 23, 65, 72, 15, 34);
		return clients;
	}

	@GET
	@Path("{clientid}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Client getAClient(@PathParam("clientid") int clientid) {
		Client aClient = new Client("A Single Client", 5, 17, 34, 12, 6, 44, 23, 25, 16, 12);
		return aClient;
	}

}
