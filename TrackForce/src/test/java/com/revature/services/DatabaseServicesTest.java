package com.revature.services;

import static org.junit.Assert.assertNotNull;

import javax.ws.rs.core.Response;

import org.testng.annotations.Test;

import com.revature.dao.DatabaseDAOImpl;

public class DatabaseServicesTest {

	@Test
	public void deleteDB() {
		System.out.println("In Services");

		String string;
		DatabaseDAOImpl dbCalls = new DatabaseDAOImpl();
		string = dbCalls.deleteAll();
		assertNotNull(Response.ok(string).build());
	}

	@Test
	public void populateDB() {
		System.out.println("In Services");
		String string;
		DatabaseDAOImpl dbCalls = new DatabaseDAOImpl();
		string = dbCalls.populate();
		assertNotNull(Response.ok(string).build());
	}

}
