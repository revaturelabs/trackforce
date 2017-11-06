package com.revature.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.revature.dao.ClientDao;
import com.revature.dao.ClientDaoImpl;
import com.revature.entity.TfClient;
import com.revature.model.ClientInfo;

@Path("/clients")
public class ClientResource {
	
	private ClientDao clientDaoImpl = new ClientDaoImpl();

	/**
	 * Returns a map of all of the clients from the TfClient table
	 * as a response object.
	 * 
	 * @return
	 * a map of TfClients as a Response object
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getAllClients() {
		List<TfClient> clients = clientDaoImpl.getAllTfClients();
		List<Map<String,Object>> entity = new ArrayList<>();
		for(TfClient client : clients) {
			Map<String,Object> map = new HashMap<>();
			map.put("id", client.getTfClientId());
			map.put("name", client.getTfClientName());
			entity.add(map);
		}

		return Response.ok(entity).build();
	}

	/**
	 * Returns a ClientInfo object representing all clients'
	 * associates and their statuses.
	 * 
	 * @return
	 * a ClientInfo object for all clients
	 */
	@GET
	@Path("info")
	@Produces({ MediaType.APPLICATION_JSON })
	public ClientInfo getAllClientInfo() {
		return clientDaoImpl.getAllClientInfo();
	}

	/**
	 * Returns a ClientInfo object representing a client's
	 * associates and their statuses.
	 * 
	 * @param clientid
	 * The id of the client in the TfClient table
	 * @return
	 * a ClientInfo object for a specified client
	 */
	@GET
	@Path("{clientid}")
	@Produces({ MediaType.APPLICATION_JSON })
	public ClientInfo getClientInfo(@PathParam("clientid") int clientid) {
		if(clientid < 1)
			return new ClientInfo("");
		else
			return clientDaoImpl.getClientInfo(clientid);
	}
}
