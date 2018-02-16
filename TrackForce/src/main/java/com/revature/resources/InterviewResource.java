package com.revature.resources;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.HibernateException;

import com.revature.services.InterviewService; //create a new service

@Path("interviews")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InterviewResource {
	
	private InterviewService interviewservice;  //InterService class not yet created

    public InterviewResource() {
        this.interviewservice = new InterviewService();
    }
	
    @GET
	public Response getAllInterviews() throws HibernateException, IOException{
		return Response.ok(interviewservice.getAllInterviews()).build();   //needs to call the function that brings up interviews given an associate id.
	}

}