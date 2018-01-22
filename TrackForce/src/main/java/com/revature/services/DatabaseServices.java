package com.revature.services;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.revature.utils.DBLoaderUtil;


// For all intensive purposes, this service mocks Salesforce albeit extreme
// Salesforce would actually insert smaller data sets to be added to the cache
// DatabaseService just wipes the entire DB or populates it
@Path("database") // http://localhost:8080/
public class DatabaseServices {

    private PersistentServiceDelegator psd;

    public DatabaseServices() {
        psd = new PersistentServiceDelegator();
    }

    /**
     * injectable dependencies for easier testing
     * @param psd
     */
    public DatabaseServices(PersistentServiceDelegator psd) {
        this.psd = psd;
    }

    @GET
	@Path("populateDB")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response populateDB() throws IOException {
		DBLoaderUtil.populateDB();
		update();
		return Response.ok().build();
	}

	@DELETE
	@Path("deleteFromDB")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response deleteDB() throws IOException {
		DBLoaderUtil.truncateDB();
		update();
		return Response.ok().build();
	}

	@GET
	@Path("populateDBSF")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response populateDBSF() throws IOException {
		DBLoaderUtil.populateDBSF();
		update();
		return Response.ok().build();	
	}
	
	public void update() throws IOException {
		psd.updateAssociates();
		psd.updateBatches();
		psd.updateClients();
		psd.updateCurriculums();
		psd.updateMarketingStatus();
	}
}
