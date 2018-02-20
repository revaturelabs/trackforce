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

import com.revature.dao.PredictionDaoImpl;
import com.revature.services.PredictionService;

//import com.revature.services.BatchTechsService;

@Path("prediction")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PredictionResource {


    private PredictionService service;

    public PredictionResource() {
        this.service = new PredictionService();
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
    
    @Path("/{time1}/{time2}")
    @GET
    public Response getBatchTechInfoName(
    		@PathParam("time1") long time1,
    		@PathParam("time2") long time2
    		)throws IOException {
    	Date afterThis = new Date(time1);
    	Date beforeThis = new Date(time2);
    	return Response.ok(new PredictionDaoImpl().getTotalAssociatesByTechBetweenDates(afterThis, beforeThis)).build();
    }
}

