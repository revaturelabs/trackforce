package com.revature.services;

import static org.junit.Assert.assertNotNull;

import javax.ws.rs.core.Response;

import org.testng.annotations.Test;

import com.revature.dao.DatabaseDAOImpl;

public class DatabaseServicesTest {

	DatabaseServices dServ = new DatabaseServices();
	
    @Test(enabled = false)
    public void deleteDB() {
        Response result = dServ.deleteDB();
        assertNotNull(result);
    }

    @Test(enabled = false)
    public void populateDB() {
        Response result = dServ.populateDB();
        assertNotNull(result);
    }
    
    @Test(enabled = false)
    public void populateDBSF() {
        Response result = dServ.populateDBSF();
        assertNotNull(result);
    }
}