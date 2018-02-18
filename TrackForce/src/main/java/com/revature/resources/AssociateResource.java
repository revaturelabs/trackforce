package com.revature.resources;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
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
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.dao.AssociateDaoHibernate;
import com.revature.model.AssociateInfo;
import com.revature.model.InterviewInfo;
<<<<<<< HEAD
import com.revature.model.ClientMappedJSON;
=======
import com.revature.request.model.AssociateFromClient;
>>>>>>> de503e542a9d25a1591fa082eafd6aced65c4aac
import com.revature.services.AssociateService;
import com.revature.utils.HibernateUtil;

@Path("associates")
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
	public Response updateAssociates(
			@DefaultValue("0") @QueryParam("marketingStatusId") Integer marketingStatusId,
			@DefaultValue("0") @QueryParam("clientId") Integer clientId,
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
	@Path("{associateid}")
	public Response getAssociate(@PathParam("associateid") Integer associateid) {
		AssociateInfo associateinfo = service.getAssociate(associateid);
		return Response.ok(associateinfo).build();
	}
	
	@GET
	@Path("mapped/{statusId}")
	public Response getMappedInfo(@PathParam("statusId") int statusId) {
		Map<Integer, ClientMappedJSON> mappedStats = service.getMappedInfo(statusId);
		if(mappedStats.isEmpty()) return Response.status(500).build();
		return Response.ok(mappedStats).build();
	}
	
	@GET
	@Path("unmapped/{statusId}")
	public Response getUnmappedInfo(@PathParam("statusId") int statusId) {
		return service.getUnmappedInfo(statusId);
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
/*	
	@PUT
	@Path("{associateId}")
	public Response updateAssociate(
			@PathParam("associateId") Integer id,
			String startDate) {
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
	        	new AssociateDaoHibernate().cacheAllAssociates();
	            session.close();
	        }
	        return Response.ok().build();
	}
*/	
	
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
		System.out.println(associateinfo);
		return Response.ok(associateinfo).build();
	}

}