package com.revature.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.revature.dao.DatabaseDAOImpl;

@Path("database") // http://localhost:8080/
public class DatabaseServices {

	@GET
	@Path("populateDB")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void populateDB() {
		DatabaseDAOImpl dbCalls = new DatabaseDAOImpl();
		dbCalls.populate();
	}

	@GET
	@Path("deleteFromDB")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void deleteDB() {
		DatabaseDAOImpl dbCalls = new DatabaseDAOImpl();
		dbCalls.deleteAll();

	}

}
