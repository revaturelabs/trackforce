package com.revature.resources;

import static com.revature.utils.LogUtil.logger;

import java.io.IOException;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.hibernate.HibernateException;

import com.revature.model.InterviewInfo;
import com.revature.services.CurriculumService;
import com.revature.services.JWTService;

import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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
    @ApiOperation(value = "Returns all curriculums", notes = "Returns a list of all curriculums.")
	public Response getAllCurriculums(@HeaderParam("Authorization") String token) throws HibernateException, IOException
    {
    	Status status = null;
		Claims payload = JWTService.processToken(token);

		if (payload == null || !payload.getId().equals("1")) 
		{
			return Response.status(status).build();
		} 
		
		else 
		{
			return Response.ok(service.getCurriculums()).build();
		}
		
	}
}
