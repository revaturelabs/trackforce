package com.revature.dao;

import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class DatabaseDaoImplTest {

    DatabaseDAOImpl dbDao = new DatabaseDAOImpl();

    @Test(enabled = false)
    public void truncateTables() {
    	String result = dbDao.deleteAll();
        assertEquals("Database Emptied Successfully", result);
        assertNotEquals("Database Empty Error", result);
    }

    @Test(enabled = false)
    public void populateTables() {
        String result = dbDao.populate();
        assertEquals("Database Population Successful", result);
        assertNotEquals("Error: Data Exists", result);
    }

    @Test(enabled = false)
    public void truncateTablesForSF() {
    	String result = dbDao.deleteAll();
        assertEquals("Database Emptied Successfully", result);
        assertNotEquals("Database Empty Error", result);
    }
    
    @Test(enabled = false)
    public void populateTablesSF() {
        String result = dbDao.populateSF();
        assertEquals("SF - Database Population Successful", result);
        assertNotEquals("Error: Data Exists", result);
    }
    

}
