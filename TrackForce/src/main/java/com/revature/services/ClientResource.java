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

	@GET
	@Path("info")
	@Produces({ MediaType.APPLICATION_JSON })
	public ClientInfo getAllClientInfo() {
		ClientInfo clients = clientDaoImpl.getAllClientInfo();
		return clients;
	}

	@GET
	@Path("{clientid}")
	@Produces({ MediaType.APPLICATION_JSON })
	public ClientInfo getClientInfo(@PathParam("clientid") int clientid) {
		ClientInfo aClient = clientDaoImpl.getClientInfo(clientid);
		return aClient;
	}

}
