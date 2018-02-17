package com.revature.resources;
import java.io.IOException;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.hibernate.HibernateException;

import com.revature.model.ClientInfo;
import com.revature.services.BatchTechsService;

//import com.revature.services.BatchTechsService;

@Path("batchtechs")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BatchTechsResource {


    private BatchTechsService service;

    public BatchTechsResource() {
        this.service = new BatchTechsService();
    }
	
    @GET
	public Response getAllBatchTechs() throws HibernateException, IOException{
		return Response.ok(service.getAllBatchTechs()).build();
	}
    
    @Path("/{techid}")
    @GET
    public Response getBatchTechInfo(@PathParam("techid") int techid) throws IOException {
    	return Response.ok(service.getAssociateCountByTechId(techid)).build();
    }
    
    @Path("/{date1}/{date2}/{techname}")
    @GET
    public Response getBatchTechInfoName(
    		@PathParam("date1") Date date1,
    		@PathParam("date2") Date date2,
    		@PathParam("techname") String techname)throws IOException {
    	return Response.ok(service.getAvailableAssociatesByTech(date1, date2, techname)).build();
    }

}