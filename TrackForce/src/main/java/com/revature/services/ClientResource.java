package com.revature.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.revature.entity.TfClient;
import com.revature.model.ClientInfo;

@Path("/clients")
public class ClientResource {

	// should be list of client object/entity from DB
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getAllClients() {
		List<TfClient> clients = new ArrayList<>();
		clients.add(new TfClient(new BigDecimal(1), "Client 1"));
		clients.add(new TfClient(new BigDecimal(2), "Client 2"));
		clients.add(new TfClient(new BigDecimal(3), "Client 3"));

		return Response.ok(clients).build();
	}

	@GET
	@Path("info")
	@Produces({ MediaType.APPLICATION_JSON })
	public ClientInfo getAllClientInfo() {
		ClientInfo clients = new ClientInfo("All Clients", 100, 95, 75, 107, 145, 23, 65, 72, 15, 34);
		return clients;
	}

	@GET
	@Path("{clientid}")
	@Produces({ MediaType.APPLICATION_JSON })
	public ClientInfo getClientInfo(@PathParam("clientid") int clientid) {
		ClientInfo aClient = new ClientInfo("A Single Client", 5, 17, 34, 12, 6, 44, 23, 25, 16, 12);
		return aClient;
	}

}
