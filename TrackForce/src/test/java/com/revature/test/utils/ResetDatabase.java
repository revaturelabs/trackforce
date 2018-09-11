package com.revature.test.utils;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;
//import com.revature.utils.DbResetUtil;

/**
 *Must be run before ALL of the back-end tests to get consistent
 *data from the database. Basically all of the tests are to be run on
 *the development database so when we test the services, they will be
 *modifying the database as it won't affect production. That being said
 *running the tests multiple times will cause a lot to fail as a result,
 *so we need to run the reset database script each time to get the same results
 */
public class ResetDatabase {

//	@Test
//	public void resetDB() { 
//		DbResetUtil dru = new DbResetUtil();
//		assertTrue(dru.resetDatabase());
//	}

}
