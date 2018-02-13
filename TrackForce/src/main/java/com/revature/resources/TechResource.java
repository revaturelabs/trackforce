package com.revature.resources;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.HibernateException;

import com.revature.services.CurriculumService;
import com.revature.services.TechService;

@Path("technologies")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TechResource {

    private TechService service;

    public TechResource() {
        this.service = new TechService();
    }
	
    @GET
	public Response getAllTechs() throws HibernateException, IOException{
		return Response.ok(service.getTechs()).build();
	}
}
