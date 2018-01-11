package com.revature.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.revature.dao.ClientDaoImpl;
import com.revature.dao.HomeDaoImpl;
import com.revature.entity.TfAssociate;
import com.revature.model.StatusInfo;
import com.revature.security.Jwt;
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
	@Jwt
	@Path("info")
	@Produces({ MediaType.APPLICATION_JSON })
	public StatusInfo getMappedAndUnmappedInfo() {
		init();
		return StatusInfoUtil.getAllAssociatesStatusInfo();
	}

	/**
	 * Returns a Response object from StatusInfoUtil with a List of Map objects as
	 * an entity. The format of the Map objects are as follows: <br>
	 * name: (name of client) <br>
	 * count: (count of desired status)
	 * 
	 * @param statusid
	 *            Status id of the status/stage of associates that the requester
	 *            wants information for.
	 * @return a Response object with a List of Map objects as an entity.
	 */
	@GET
	@Path("client/{statusid}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getClientsByStatus(@PathParam("statusid") int statusid) {
		init();
		return Response.ok(StatusInfoUtil.getClientsBasedOnStatusID(statusid)).build();
	}

	/**
	 * Returns a Response object from StatusInfoUtil with a List of Map objects as
	 * an entity. The format of the Map objects are as follows: <br>
	 * name: (name of curriculum) <br>
	 * count: (count of desired status)
	 * 
	 * @param statusid
	 *            Status id of the status/stage of associates that the requester
	 *            wants information for.
	 * @return a Response object with a List of Map objects as an entity.
	 */
	@GET
	@Path("skillset/{statusid}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getCurriculumsByStatus(@PathParam("statusid") int statusid) {
		init();
		return Response.ok(StatusInfoUtil.getCurriculumsBasedOnStatusID(statusid)).build();
	}

	/**
	 * Initializes objects needed for functionality from the StatusInfoUtil when
	 * maps in StatusInfoUtil are empty.
	 */
	private void init() {
		if (StatusInfoUtil.mapsAreEmpty()) {
			initForce();
		}
	}

	/**
	 * Forces initialization of objects needed for functionality from the
	 * StatusInfoUtil.
	 */
	@PUT
	@Path("init")
	public void initForce() {
		HomeDaoImpl.clearAssociates();
		clientDaoImpl.clearClients();
		StatusInfoUtil.clearMaps();
		List<TfAssociate> tfAssociates = homeDaoImpl.getAllTfAssociates();
		StatusInfoUtil.updateStatusInfoFromAssociates(tfAssociates);
	}
}
