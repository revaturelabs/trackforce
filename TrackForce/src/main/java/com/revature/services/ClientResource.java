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
import com.revature.dao.HomeDao;
import com.revature.dao.HomeDaoImpl;
import com.revature.entity.TfAssociate;
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
		init();
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
			return new StatusInfo("");
		else
			return StatusInfoUtil.getClientStatusInfo(clientid);
	}

	private void init() {
		List<TfClient> clients = clientDaoImpl.getAllTfClients();
		StatusInfoUtil.clearMaps();
		for (TfClient client : clients) {
			StatusInfoUtil.putClientStatusInfo(client.getTfClientId().intValue(),
					new StatusInfo(client.getTfClientName()));
		}
		updateStatusInfoFromAssociates(homeDaoImpl.getAllTfAssociates());
	}

	// temporary
	/**
	 * Updates the status count for status info from all associates and the status
	 * counts for specific clients based on the status of specific associates'
	 * statuses.
	 * 
	 * @param associates
	 *            List of all associates
	 */
	private void updateStatusInfoFromAssociates(List<TfAssociate> associates) {
		StatusInfo allAssociatesStatusInfo = new StatusInfo("All clients");
		for (TfAssociate associate : associates) {
			StatusInfoUtil.updateStatusCount(allAssociatesStatusInfo, associate);
			TfClient tfClient = associate.getTfClient();
			if (tfClient != null) {
				int clientID = tfClient.getTfClientId().intValue();
				StatusInfo clientStatusInfo = StatusInfoUtil.getClientStatusInfo(clientID);
				StatusInfoUtil.updateStatusCount(clientStatusInfo, associate);
				StatusInfoUtil.putClientStatusInfo(clientID, clientStatusInfo);
			}
		}
		StatusInfoUtil.setAllAssociatesStatusInfo(allAssociatesStatusInfo);
	}

	@GET
	@Path("test")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getAStatusInfo() {
		List<TfClient> clients = clientDaoImpl.getAllTfClients();
		List<TfAssociate> associates = homeDaoImpl.getAllTfAssociates();

		Map<Integer, String> clientMap = new HashMap<>();
		Map<Integer, Object> associateMap = new HashMap<>();

		for (TfClient client : clients) {
			clientMap.put(client.getTfClientId().intValue(), client.getTfClientName());
		}
		for (TfAssociate associate : associates) {
			TfClient tfClient = associate.getTfClient();
			System.out.println(tfClient);
			if(tfClient != null)
				associateMap.put(associate.getTfAssociateId().intValue(), tfClient.getTfClientName());
		}

		return Response.ok(associateMap).build();
	}
}
