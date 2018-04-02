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

import io.swagger.annotations.Api;

@Path("skillset")
@Api(value = "skillset")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CurriculumResource {

    private CurriculumService service;

    public CurriculumResource() {
        this.service = new CurriculumService();
    }
	
    @GET
	public Response getAllCurriculums() throws HibernateException, IOException{
		return Response.ok(service.getCurriculums()).build();
	}
}
