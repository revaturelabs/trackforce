package com.revature.services;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.revature.dao.ClientDaoImpl;
import com.revature.dao.HomeDaoImpl;
import com.revature.model.StatusInfo;
import com.revature.utils.StatusInfoUtil;

@Path("/")
public class HomeResource {

	private HomeDaoImpl homeDaoImpl = new HomeDaoImpl();
	private ClientDaoImpl clientDaoImpl = new ClientDaoImpl();

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

	@GET
	@Path("client/{statusid}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getClientsByStatus(@PathParam("statusid") int statusid) {
		init();
		return Response.ok(StatusInfoUtil.getClientsBasedOnStatusID(statusid)).build();
	}

	@GET
	@Path("skillset/{statusid}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getCurriculumsByStatus(@PathParam("statusid") int statusid) {
		init();
		return Response.ok(StatusInfoUtil.getCurriculumsBasedOnStatusID(statusid)).build();
	}

	static boolean initialized = false;

	private void init() {
		if (!initialized) {
			initialized = true;
			homeDaoImpl.clearAssociates();
			clientDaoImpl.clearClients();
			StatusInfoUtil.clearMaps();
			StatusInfoUtil.updateStatusInfoFromAssociates(homeDaoImpl.getAllTfAssociates());
		}
	}

	@PUT
	@Path("init")
	public void initForce() {
		initialized = true;
		homeDaoImpl.clearAssociates();
		clientDaoImpl.clearClients();
		StatusInfoUtil.clearMaps();
		StatusInfoUtil.updateStatusInfoFromAssociates(homeDaoImpl.getAllTfAssociates());
	}
}
