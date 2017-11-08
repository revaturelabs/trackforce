package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.revature.dao.ClientDao;
import com.revature.dao.ClientDaoImpl;
import com.revature.dao.HomeDao;
import com.revature.dao.HomeDaoImpl;
import com.revature.entity.TfAssociate;
import com.revature.entity.TfClient;
import com.revature.model.StatusInfo;
import com.revature.utils.StatusInfoUtil;

@Path("/")
public class HomeResource {

	private HomeDao homeDaoImpl = new HomeDaoImpl();
	private ClientDao clientDaoImpl = new ClientDaoImpl();
	private List<TfAssociate> associates;
	private List<TfClient> clients;

	/**
	 * Returns a StatusInfo object showing mapped and unmapped info for all of the
	 * associates.
	 * 
	 * @return a StatusInfo object for all of the associates.
	 */
	@GET
	@Path("info")
	@Produces({ MediaType.APPLICATION_JSON })
	public StatusInfo getMappedAndUnmappedInfo() {
		associates = this.getAllTfAssociates();
		return StatusInfoUtil.getAllAssociatesStatusInfo();
	}

	@PUT
	@Path("init")
	@Produces({ MediaType.APPLICATION_JSON })
	public void init() {
		associates = this.getAllTfAssociates();
		clients = clientDaoImpl.getAllTfClients();
		StatusInfoUtil.clearMaps();
		for(TfClient client : clients) {
			StatusInfoUtil.putClientStatusInfo(client.getTfClientId().intValue(), new StatusInfo(client.getTfClientName()));
		}
		updateStatusInfoFromAssociates(associates);
	}

	/**
	 * 
	 * @param statusid
	 *            the ID of the desired associate marketing status
	 * @return a list of TfAssociate objects filtered by marketing status ID.
	 */
	@GET
	@Path("{statusid}")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<TfAssociate> getAssociatesByStatus(@PathParam("statusid") int statusid) {
		associates = homeDaoImpl.getAllTfAssociates();
		return associatesListByStatus(associates, statusid);
	}

	/**
	 * Updates the status count for status info from all associates and the status
	 * counts for specific clients based on the status of specific associates'
	 * statuses.
	 * 
	 * @param associates
	 * List of all associates
	 */
	private void updateStatusInfoFromAssociates(List<TfAssociate> associates) {
		StatusInfo allAssociatesStatusInfo = new StatusInfo("All clients");
		for (TfAssociate associate : associates) {
			StatusInfoUtil.updateStatusCount(allAssociatesStatusInfo, associate);
			if (associate.getTfClient().getTfClientId() != null) {
				int clientID = associate.getTfClient().getTfClientId().intValue();
				StatusInfo clientStatusInfo = StatusInfoUtil.getClientStatusInfo(clientID);
				StatusInfoUtil.updateStatusCount(clientStatusInfo, associate);
				StatusInfoUtil.putClientStatusInfo(clientID, clientStatusInfo);
			}
		}
		StatusInfoUtil.setAllAssociatesStatusInfo(allAssociatesStatusInfo);
	}

	/**
	 * This method takes a list of TfAssociates and a desired marketing status ID,
	 * and filters the list to give back a list of only TfAssociates who are listed
	 * under that ID.
	 * 
	 * @param allAssociates
	 *            the list of all TfAssociates from the database
	 * @param id
	 *            the marketing status ID we want to filter by
	 * @return a list of TfAssociates filtered by the marketing status id
	 */
	private List<TfAssociate> associatesListByStatus(List<TfAssociate> allAssociates, int id) {
		List<TfAssociate> assoc = new ArrayList<>();
		for (TfAssociate associate : allAssociates) {
			if (associate.getTfMarketingStatus().getTfMarketingStatusId().intValue() == id) {
				assoc.add(associate);
			}
		}
		return assoc;
	}
	
	/**
	 * Returns the instance variable associates after ensuring that
	 * it has the TfAssociate objects from the database.
	 * 
	 * @return
	 * list of TfAssociate
	 */
	private List<TfAssociate> getAllTfAssociates(){
		if (associates == null || associates.isEmpty()) {
			associates = homeDaoImpl.getAllTfAssociates();
		}
		return associates;
	}
}
