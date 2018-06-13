package com.revature.test.junit.model;

import static org.junit.Assert.*;
import org.junit.Test;


import com.revature.model.LoginJSON;

/***
 * jUnit Tests for LoginJSON class in com.revature.model
 * @author David Kim
 *  @since 6.18..06.11
 */
public class LoginJSONTest {

	LoginJSON json = new LoginJSON("Cheryl", "password");
	
	@Test
	public void testGetUsername() {
		json.setUsername("Cherry");
		assertTrue(json.getUsername().equals("Cherry"));
		assertFalse(json.getUsername().equals("password"));
	}
	
	@Test
	public void testGetPassword() {
		json.setPassword("new password");
		assertTrue(json.getPassword().equals("new password"));
		assertFalse(json.getPassword().equals("password"));
	}
}
