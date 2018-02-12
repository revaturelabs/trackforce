package com.revature.resources;

import java.io.IOException;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.hibernate.HibernateException;

import com.revature.model.AssociateInfo;
import com.revature.services.AssociateService;

@Path("associates")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AssociateResource {
	
	private AssociateService service;

    public AssociateResource() {
        this.service = new AssociateService();
    }
    
  //Refactored methods to match proper RESTful WS procedures
    
    /**
	 * Gets a list of all the associates, optionally filtered by a batch id. If an associate has no marketing status or
	 * curriculum, replaces them with blanks. If associate has no client, replaces
	 * it with "None".
	 * 
	 * @return - A Response object with a list of TfAssociate objects.
	 * @throws IOException
	 * @throws HibernateException
	 */
	@GET
	public Response getAllAssociates() {
		Set<AssociateInfo> associatesList = null;
		associatesList = service.getAllAssociates();
		service.getAllAssociates();
		if (associatesList == null || associatesList.isEmpty()) {
			// returns 404 if no associates found
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(associatesList).build();
	}
	
	/**
	 * Update the marketing status or client of associates
	 * 
	 * @param ids - to be updated
	 * @param marketingStatusIdStr - updating to
	 * @param clientIdStr - updating to
	 * @return Response - 200 status if successful
	 * @throws IOException
	 */
	@PUT
	public Response updateAssociates(
			Integer marketingStatusIdStr,
			Integer clientIdStr,
			Integer[] ids) {
		// marketing status & client id are given as query parameters, ids sent in body
		service.updateAssociates(ids, marketingStatusIdStr, clientIdStr);
		return Response.ok().build();
	}

	/**
	 * Returns information about a specific associate.
	 * 
	 * @param associateid - The ID of the associate to get information about
	 * @return - An AssociateInfo object that contains the associate's information.
	 * @throws IOException
	 */
	@GET
	@Path("{associateid}")
	public Response getAssociate(@PathParam("associateid") Integer associateid) {
		AssociateInfo associateinfo = service.getAssociate(associateid);
		return Response.ok(associateinfo).build();
	}
	
//	/**
//	 * Update the marketing status or client of an associate
//	 * 
//	 * @param id - The ID of the associate to change
//	 * @param marketingStatusId - What to change the associate's marketing status to
//	 * @param clientId - What client to change the associate to
//	 * @return
//	 * @throws NumberFormatException 
//	 * @throws IOException
//	 */
//	@PUT
//	@Path("{associateId}")
//	public Response updateAssociate(
//			@PathParam("associateId") Integer[] ids,
//            @QueryParam("marketingStatusId") Integer marketingStatusId,
//            @QueryParam("clientId") Integer clientId) {
//		
//		service.updateAssociates(ids, marketingStatusId, clientId);
//		return Response.ok().build();
//	}
}