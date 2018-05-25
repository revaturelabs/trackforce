package com.revature.resources;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.dao.AssociateDaoHibernate;
import com.revature.model.AssociateInfo;
import com.revature.model.InterviewInfo;
import com.revature.request.model.AssociateFromClient;
import com.revature.model.ClientMappedJSON;
import com.revature.request.model.InterviewFromClient;
import com.revature.services.AssociateService;
import com.revature.services.InterviewService;
import com.revature.utils.HibernateUtil;
import com.revature.utils.LogUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@Path("associates")
@Api(value = "associates")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AssociateResource {
	
	private AssociateService service;

    public AssociateResource() {
        this.service = new AssociateService();
    }
    
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
	 @ApiOperation(value = "Return all associates",
	    notes = "Gets a set of all the associates, optionally filtered by a batch id. If an associate has no marketing status or\r\n" + 
	    		" curriculum, replaces them with blanks. If associate has no client, replaces\r\n" + 
	    		" it with \"None\".",
	    response = AssociateInfo.class,
	    responseContainer = "Set")
	public Response getAllAssociates() {
		Set<AssociateInfo>associatesList = service.getAllAssociates();
		if (associatesList == null || associatesList.isEmpty()) return Response.status(Status.NOT_FOUND).build();// returns 404 if no associates found
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
	 @ApiOperation(value = "Batch update associates",
	    notes = "Updates the maretking status and/or the client of one or more associates")
	public Response updateAssociates(
			@DefaultValue("0") @ApiParam(value = "marketing status id") @QueryParam("marketingStatusId") Integer marketingStatusId,
			@DefaultValue("0") @ApiParam(value = "client id") @QueryParam("clientId") Integer clientId,
			List<Integer> ids) {
		// marketing status & client id are given as query parameters, ids sent in body
		service.updateAssociates(ids, marketingStatusId, clientId);
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
	 @ApiOperation(value = "Return an associate",
	    notes = "Returns information about a specific associate.",
	    response = AssociateInfo.class)
	@Path("{associateid}")
	public Response getAssociate(@ApiParam(value = "An associate id.") @PathParam("associateid") Integer associateid) {
		AssociateInfo associateinfo = service.getAssociate(associateid);
		return Response.ok(associateinfo).build();
	}
	
	@GET
	 @ApiOperation(value = "Return an associate",
	    notes = "Returns information about a specific associate.")
	@Path("mapped/{statusId}")
	public Response getMappedInfo(@PathParam("statusId") int statusId) {
		Map<Integer, ClientMappedJSON> mappedStats = service.getMappedInfo(statusId);
		if(mappedStats.isEmpty()) return Response.status(500).build();
		return Response.ok(mappedStats).build();
	}
	
	@GET
	@Path("unmapped/{statusId}")
	public Response getUnmappedInfo(@PathParam("statusId") int statusId) {
		return Response.ok(service.getUnmappedInfo(statusId)).build();
	}
	
	/**
	 * Update the marketing status or client of an associate
	 * 
	 * @param id - The ID of the associate to change
	 * @param marketingStatusId - What to change the associate's marketing status to
	 * @param clientId - What client to change the associate to
	 * @return
	 * @throws NumberFormatException 
	 * @throws IOException
	 */
	
	/**** OPTION 1 ****/
	
	@PUT
	@Path("{associateId}")
	public Response updateAssociate(
			@PathParam("associateId") Integer id,
			AssociateFromClient afc) {
		service.updateAssociate(afc);
		return Response.ok().build();
	}

	/*** OPTION 2 ***/	
	@PUT
	@Path("{associateId}/{startDate}")
	public Response updateAssociate(
			@PathParam("associateId") Integer id,
			@PathParam("startDate") String startDate) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		 Transaction tx = session.beginTransaction();
		// StringBuilder sb = new StringBuilder();
	        try {
	        	StoredProcedureQuery spq = session.createStoredProcedureCall("admin.UPDATEASSOCIATECLIENTSTARTDATE"); 
	        	spq.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
	        	spq.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
	        	spq.setParameter(1, id);
	        	spq.setParameter(2, startDate);
	        	spq.execute();
	        } catch (Exception e) {
	        	LogUtil.logger.error(e);
	            session.flush();
	            tx.rollback();
	        } finally {
	        	new AssociateDaoHibernate().cacheAllAssociates();
	            session.close();
	        }
	        return Response.ok().build();
	}

	
	/**** OPTION 1+2****/
/*	@PUT
	@Path("{associateId}")
	public Response updateAssociate(
			@PathParam("associateId") Integer id,
			@DefaultValue("0") @QueryParam("marketingStatusId") Integer marketingStatusId,
			@DefaultValue("0") @QueryParam("clientId") Integer clientId,
			String startDate) {
		List<Integer> list = new ArrayList<>();
		list.add(id);
		service.updateAssociates(list, marketingStatusId, clientId);
		
		//This code separately updates the client start date using a stored procedure
		Session session = HibernateUtil.getSessionFactory().openSession();
		 Transaction tx = session.beginTransaction();
		// StringBuilder sb = new StringBuilder();
	        try {
	        	StoredProcedureQuery spq = session.createStoredProcedureCall("admin.UPDATEASSOCIATECLIENTSTARTDATE"); 
	        	spq.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
	        	spq.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
	        	spq.setParameter(1, id);
	        	spq.setParameter(2, startDate);
	        	spq.execute();
	        } catch (Exception e) {
	            e.printStackTrace();
	            session.flush();
	            tx.rollback();
	        } finally {
	        	new AssociateDaoHibernate().cacheAllAssociates(); //refreshes the associates cache
	            session.close();
	        }
		
		return Response.ok().build();
	}
*/	
	@GET
	@Path("{associateid}/interviews")
	public Response getAssociateInterviews(@PathParam("associateid") Integer associateid) {
		Set<InterviewInfo> associateinfo = service.getInterviewsByAssociate(associateid);
		return Response.ok(associateinfo).build();
	}
	
	@POST
	@Path("{associateid}/interviews")
	public Response addAssociateInterview(
			@PathParam("associateid") Integer associateid,
			InterviewFromClient ifc) {
		InterviewService is = new InterviewService();
		is.addInterviewByAssociate(associateid, ifc);
		return Response.ok().build();
	}

}