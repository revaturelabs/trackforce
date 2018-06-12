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
		tfuser.setTfAssociate(new TfAssociate());
		assertTrue(tfuser.getTfAssociate() instanceof TfAssociate);
	}
	@Test
	public void test2() {
		tfuser.setTfRole(new TfRole());
		assertTrue(tfuser.getTfRole() instanceof TfRole);
	}
	@Test
	public void test3() {
		tfuser.setTfUserAssociate(new TfAssociate());
		assertTrue(tfuser.getTfUserAssociate() instanceof TfAssociate);
	}
	@Test
	public void test4() {
		tfuser.setTfUserHashpassword("Penguins");
		assertTrue(tfuser.getTfUserHashpassword().equals("Penguins"));
		assertFalse(tfuser.getTfUserHashpassword().equals("penguins"));
	}
	@Test
	public void test5() {
		tfuser.setTfUserId(7);
		assertTrue(tfuser.getTfUserId() == 7);
		assertFalse(tfuser.getTfUserId() == 6);
	}
	@Test
	public void test6() {
		tfuser.setTfUserUsername("Penguins");
		assertTrue(tfuser.getTfUserUsername().equals("Penguins"));
		assertFalse(tfuser.getTfUserUsername().equals("penguins"));
	}
}
