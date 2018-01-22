package com.revature.services;

import static org.junit.Assert.assertNotNull;

import javax.ws.rs.core.Response;

import org.testng.annotations.Test;

import com.revature.dao.DatabaseDAOImpl;

import java.io.IOException;

public class DatabaseServicesTest {

	DatabaseServices dServ = new DatabaseServices();
	
    @Test(enabled = false)
    public void deleteDB() throws IOException {
        Response result = dServ.deleteDB();
        assertNotNull(result);
    }

    @Test(enabled = false)
    public void populateDB() throws IOException {
        Response result = dServ.populateDB();
        assertNotNull(result);
    }
    
    @Test(enabled = false)
    public void populateDBSF() throws IOException {
        Response result = dServ.populateDBSF();
        assertNotNull(result);
    }
}