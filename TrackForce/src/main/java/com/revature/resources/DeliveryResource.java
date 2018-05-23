package com.revature.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//import io.swagger.annotations.Api;

@Path("delivery")
//@Api(value = "associates")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DeliveryResource {
	
	@POST
	@Path("{associateid}/interviews")
	public Response 
}
