package com.revature.services;

import javax.ws.rs.Path;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;


@Path("database") //http://localhost:8080/project3/track
public class PopulateDeleteServices {

	@GET
	@Path ("/Populate")
	@Produces(MediaType.TEXT_HTML)
	public String populate(){
		PopulateDeleteDaoJDBC.populate();
		return "<h3>successfully populated database</h3>";
	}
	
	@GET
	@Path ("/DeleteAll")
	@Produces(MediaType.TEXT_HTML)	
	public String delete(){
		PopulateDeleteDaoJDBC.deleteAll();
		return "<h3>successfully cleared database</h3>";
		
	}

	
}
