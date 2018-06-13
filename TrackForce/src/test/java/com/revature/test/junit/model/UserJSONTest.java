package com.revature.test.junit.model;

import static org.junit.Assert.*;


import org.junit.Test;

import com.revature.model.UserJSON;


public class UserJSONTest {

	UserJSON user = new UserJSON();
	
	@Test
	public void testGetUsername() {
		user.setUsername("Alvin");
		assertTrue(user.getUsername().equals("Alvin"));
		assertFalse(user.getUsername().equals("Simon"));
	}
	
	@Test
	public void testSetUsername() {
		user.setUsername("Theodore");
		assertTrue(user.getUsername().equals("Theodore"));
		assertFalse(user.getUsername().equals("Franky"));
	}
	
	@Test
	public void testGetTfRoleId() {
		user.setTfRoleId(19);
		assertTrue(user.getTfRoleId() == 19);
		assertFalse(user.getTfRoleId() == 20);
	}
	
	@Test
	public void testGetToken() {
		user.setToken("token");
		assertTrue(user.getToken().equals("token"));
		assertFalse(user.getToken().equals("taken"));
	}
	
	@Test
	public void testGetUserId() {
		assertTrue(user.getUserId() == 0);
		assertFalse(user.getUserId() == 3);
	}
	
	@Test
	public void testSetUserId() {
		user.setUserId(33);
		assertTrue(user.getUserId() == 33);
		assertFalse(user.getUserId() == 9);
	}
	
	@Test
	public void testGetAssociateId() {
		assertTrue(user.getAssociateId() == 0);
		assertFalse(user.getAssociateId() == 99);
	}
	
	@Test
	public void testSetAssociateId() {
		user.setAssociateId(55);
		assertTrue(user.getAssociateId() == 55);
		assertFalse(user.getAssociateId() == 11);
	}
	
	

}
