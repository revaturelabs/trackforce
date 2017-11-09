package com.revature.services;

import java.util.ArrayList;
import java.util.List;

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
import com.revature.entity.TfAssociate;
import com.revature.entity.TfClient;
import com.revature.model.StatusInfo;
import com.revature.utils.StatusInfoUtil;

@Path("/")
public class HomeResource {

	private HomeDao homeDaoImpl = new HomeDaoImpl();
	private ClientDao clientDaoImpl = new ClientDaoImpl();

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
		init();
		return StatusInfoUtil.getAllAssociatesStatusInfo();
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
	public Response getAssociatesByStatus(@PathParam("statusid") int statusid) {
		StatusInfoUtil.getSpecificClientStatusInfoAsList();

		return Response.ok(new Object()).build();
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
