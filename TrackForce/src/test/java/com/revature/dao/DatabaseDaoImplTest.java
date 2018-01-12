package com.revature.dao;

import org.testng.annotations.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class DatabaseDaoImplTest {

    DatabaseDAOImpl dbDao = new DatabaseDAOImpl();

    @Test(enabled = false)
    public void truncateTables() throws IOException {
    	String result = dbDao.deleteAll();
        assertEquals("Database Emptied Successfully", result);
        assertNotEquals("Database Empty Error", result);
    }

    @Test(enabled = false)
    public void populateTables() throws IOException {
        String result = dbDao.populate();
        assertEquals("Database Population Successful", result);
        assertNotEquals("Error: Data Exists", result);
    }

    @Test(enabled = false)
    public void truncateTablesForSF() throws IOException {
    	String result = dbDao.deleteAll();
        assertEquals("Database Emptied Successfully", result);
        assertNotEquals("Database Empty Error", result);
    }
    
    @Test(enabled = false)
    public void populateTablesSF() throws IOException {
        String result = dbDao.populateSF();
        assertEquals("SF - Database Population Successful", result);
        assertNotEquals("Error: Data Exists", result);
    }
    

}
