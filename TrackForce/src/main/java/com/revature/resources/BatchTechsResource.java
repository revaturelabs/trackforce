package com.revature.resources;
import java.io.IOException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.hibernate.HibernateException;
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
}


