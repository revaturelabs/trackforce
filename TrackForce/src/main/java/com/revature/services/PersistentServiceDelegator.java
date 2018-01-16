package com.revature.services;

import java.io.IOException;
import java.util.Collection;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.revature.model.*;
import com.revature.utils.PersistentStorage;

// PersistentServiceDelegator has two purposes:
// 1: pull data from DB on notification from Salesforce of data change
// 2: Send responses back to the client 

// Salesforce is assumed to be responsible for invoking the proper controllers
// This is to optimize Trackforce and run with the data that is made available to us
@Path("data")
public class PersistentServiceDelegator {

	// Observers to check for change
	private static Delegate[] delegates = { new AssociateService(), new BatchesService(), new ClientResource(),
			new CurriculumService(), new MarketingStatusService() };

	@POST
	@Path("/update/associate")
	public Response updateAssociates() throws IOException {
		// evict the cache
		PersistentStorage.getStorage().evictAssociates();
		PersistentStorage.getStorage().evictBatches();
		PersistentStorage.getStorage().evictClients();
		
		// execute the update
		delegates[0].execute();

		// notify Salesforce of the response
		return Response.ok().build();
	}
	
	@POST
	@Path("/update/batch")
	public Response updateBatches() throws IOException {
		// evict the cache
		PersistentStorage.getStorage().evictBatches();
		
		// execute the update
		delegates[1].execute();

		// notify Salesforce of the response
		return Response.ok().build();
	}
	
	@POST
	@Path("/update/client")
	public Response updateClients() throws IOException {
		// evict the cache
		PersistentStorage.getStorage().evictClients();
		
		// execute the update
		delegates[2].execute();

		// notify Salesforce of the response
		return Response.ok().build();
	}
	
	@POST
	@Path("/update/skills")
	public Response updateCurriculums() throws IOException {
		PersistentStorage.getStorage().evictCurriculums();
		
		// execute the update
		delegates[3].execute();

		// notify Salesforce of the response
		return Response.ok().build();
	}
	
	@POST
	@Path("/update/marketing")
	public Response updateMarketingStatus() throws IOException {
		PersistentStorage.getStorage().evictMarketingStatuses();
		
		// execute the update
		delegates[4].execute();

		// notify Salesforce of the response
		return Response.ok().build();
	}
	
	@GET
	@Path("/get/associate/")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<AssociateInfo> getAssociates() throws IOException {
		// execute the update
		Collection<AssociateInfo> set = delegates[0].read();

		// notify client of the response
		return set;
	}
	
	@GET
	@Path("/get/batch/")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<BatchInfo> getBatches() throws IOException {
		// execute the update
		Collection<BatchInfo> set = delegates[1].read();

		// notify client of the response
		return set;
	}
	
	@GET
	@Path("/get/batch/date")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<BatchInfo> getBatchesSortedByDate() throws IOException {
		// execute the update
		Collection<BatchInfo> set = delegates[1].read("Date");

		// notify client of the response
		return set;
	}
	
	@GET
	@Path("/get/client/")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<ClientInfo> getClients() throws IOException {
		// execute the update
		Collection<ClientInfo> set = delegates[2].read();

		// notify client of the response
		return set;
	}
	
	@GET
	@Path("/get/summary")
	@Produces(MediaType.APPLICATION_JSON)
	public StatusInfo getTotals() throws IOException {
		// execute the update
		Collection<StatusInfo> set = delegates[2].read("summary");
		StatusInfo si = null;
		if(set != null && !set.isEmpty())
			si = set.iterator().next();
			
		// notify client of the response
		return si;
	}
	
	@GET	
	@Path("/get/skills/")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<CurriculumInfo> getCurriculums() throws IOException {
		// execute the update
		Collection<CurriculumInfo> set = delegates[3].read();

		// notify client of the response
		return set;
	}
	
	@GET
	@Path("/get/marketing/")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<MarketingStatusInfo> getMarketingStatuses() throws IOException {
		// execute the update
		Collection<MarketingStatusInfo> set = delegates[4].read();

		// notify client of the response
		return set;
	}
}
