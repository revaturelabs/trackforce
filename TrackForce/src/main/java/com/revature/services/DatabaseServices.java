package com.revature.services;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.revature.dao.DatabaseDAOImpl;

@Path("database") // http://localhost:8080/
public class DatabaseServices {

	@GET
	@Path("populateDB")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response populateDB() {
		System.out.println("In Services");

		String string;
		DatabaseDAOImpl dbCalls = new DatabaseDAOImpl();
		string= dbCalls.populate();
		
		return Response.ok(string).build();

	}

	@GET
	@Path("deleteFromDB")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response deleteDB() {
		System.out.println("In Services");
		
		String string;
		DatabaseDAOImpl dbCalls = new DatabaseDAOImpl();
		string= dbCalls.deleteAll();
		
		return Response.ok(string).build();
	}

	@GET
	@Path("populateDBSF")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response populateDBSF() {
		System.out.println("In Services");

		String string;
		DatabaseDAOImpl dbCalls = new DatabaseDAOImpl();
		string= dbCalls.populateSF();
		
		return Response.ok(string).build();

	}
}
