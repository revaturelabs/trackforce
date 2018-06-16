package com.revature.test.junit.entity;

import static org.junit.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.junit.Test;

import com.revature.entity.TfAssociate;
import com.revature.entity.TfRole;
import com.revature.entity.TfUser;

/**
 * Tests to test basic getter and setter functionality for TfUser
 * @author Jesse
 * @Since 6.18.06.11
 */
public class TfUserTest {

	TfUser tfuser = new TfUser();

	@Test
	public void test1() {
		tfuser.setIsApproved(1);
		assertTrue(tfuser.getIsApproved() == 1);
		assertFalse(tfuser.getIsApproved() != 1);
	}
	@Test
	public void test2() {
		tfuser.setTfRole(new TfRole());
		assertTrue(tfuser.getTfRole() instanceof TfRole);
	}
	@Test
	public void test4() {
		tfuser.setPassword("Penguins");
		assertTrue(tfuser.getPassword().equals("Penguins"));
		assertFalse(tfuser.getPassword().equals("penguins"));
	}
	@Test
	public void test5() {
		tfuser.setId(7);
		assertTrue(tfuser.getId() == 7);
		assertFalse(tfuser.getId() == 6);
	}
	@Test
	public void test6() {
		tfuser.setUsername("Penguins");
		assertTrue(tfuser.getUsername().equals("Penguins"));
		assertFalse(tfuser.getUsername().equals("penguins"));
	}
	@Test
	public void test7() {
		tfuser.setToken("Token");
		assertTrue(tfuser.getToken().equals("Token"));
		assertFalse(tfuser.getToken().equals("token"));
	}
	@Test
	public void rest() {
		tfuser.setRole(1);
		assertTrue(tfuser.getRole() == 1);
		assertFalse(tfuser.getRole() != 1);
	}
}
