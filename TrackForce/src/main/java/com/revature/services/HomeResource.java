package com.revature.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.revature.model.ClientInfo;

@Path("/")
public class HomeResource {
	
	@GET
	@Path("info")
	@Produces({ MediaType.APPLICATION_JSON })
	public ClientInfo getMappedAndUnmappedInfo() {
		return new ClientInfo("My test",2,4,24,6,5,15,61,14,13,4);
	}

}
