package com.revature.services;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.HibernateException;

import com.revature.dao.DatabaseDAOImpl;

@Path("database") // http://localhost:8080/
public class DatabaseServices {

    @GET
    @Path("populateDB")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response populateDB() throws HibernateException, IOException {
        String string;
        DatabaseDAOImpl dbCalls = new DatabaseDAOImpl();
        string = dbCalls.populate();

        return Response.ok(string).build();
    }

    @DELETE
    @Path("deleteFromDB")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response deleteDB() throws HibernateException, IOException {
        String string;
        DatabaseDAOImpl dbCalls = new DatabaseDAOImpl();
        string = dbCalls.deleteAll();

        return Response.ok(string).build();
    }

    @GET
    @Path("populateDBSF")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response populateDBSF() throws HibernateException, IOException {
        String string;
        DatabaseDAOImpl dbCalls = new DatabaseDAOImpl();
        string = dbCalls.populateSF();

        return Response.ok(string).build();
    }
}
