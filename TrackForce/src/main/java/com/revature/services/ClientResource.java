package com.revature.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.revature.dao.ClientDao;
import com.revature.dao.ClientDaoImpl;
import com.revature.dao.HomeDao;
import com.revature.dao.HomeDaoImpl;
import com.revature.entity.TfClient;
import com.revature.model.StatusInfo;
import com.revature.utils.StatusInfoUtil;

@Path("/clients")
public class ClientResource {

	private ClientDao clientDaoImpl = new ClientDaoImpl();
	private HomeDao homeDaoImpl = new HomeDaoImpl();

	/**
	 * Returns a map of all of the clients from the TfClient table as a response
	 * object.
	 * 
	 * @return A map of TfClients as a Response object
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getAllClients() {
		List<TfClient> clients = clientDaoImpl.getAllTfClients();
		List<Map<String, Object>> entity = new ArrayList<>();
		for (TfClient client : clients) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", client.getTfClientId());
			map.put("name", client.getTfClientName());
			entity.add(map);
		}
		return Response.ok(entity).build();
	}

	/**
	 * Returns a StatusInfo object representing all clients' associates and their
	 * statuses.
	 * 
	 * @return A StatusInfo object for all clients
	 */
	@GET
	@Path("info")
	@Produces({ MediaType.APPLICATION_JSON })
	public StatusInfo getAllClientInfo() {
		init();
		return StatusInfoUtil.getAllAssociatesStatusInfo();
	}

	/**
	 * Returns a StatusInfo object representing a client's associates and their
	 * statuses.
	 * 
	 * @param clientid
	 *            The id of the client in the TfClient table
	 * @return A StatusInfo object for a specified client
	 */
	@GET
	@Path("{clientid}")
	@Produces({ MediaType.APPLICATION_JSON })
	public StatusInfo getClientInfo(@PathParam("clientid") int clientid) {
		init();
		if (clientid < 1)
			return new StatusInfo();
		else {
			return StatusInfoUtil.getClientStatusInfo(clientid);	
		}
	}

	static boolean initialized = false;

	private void init() {
		if (!initialized) {
			initialized = true;
			StatusInfoUtil.clearMaps();
			StatusInfoUtil.updateStatusInfoFromAssociates(homeDaoImpl.getAllTfAssociates());
		}
	}

	@PUT
	@Path("init")
	public void initForce() {
		initialized = true;
		StatusInfoUtil.clearMaps();
		StatusInfoUtil.updateStatusInfoFromAssociates(homeDaoImpl.getAllTfAssociates());
	}
}
