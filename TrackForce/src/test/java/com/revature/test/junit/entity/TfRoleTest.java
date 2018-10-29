package com.revature.test.junit.entity;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import java.util.HashSet;

import org.testng.annotations.Test;

import com.revature.entity.TfRole;
import com.revature.entity.TfUser;

/**
 * Tests to test basic getter and setter functionality for TfRole
 * 
 * @author Jesse
 * @Since 6.18.06.11
 */
public class TfRoleTest {

	TfRole role1 = new TfRole(1, "tfRoleName");
	TfRole role2 = new TfRole(1, "tfRoleName");
	TfRole tfrole = new TfRole();

	@Test
	public void testRoleIDGetSet() {
		tfrole.setTfRoleId(7);
		assertTrue(tfrole.getTfRoleId() == 7);
		assertFalse(tfrole.getTfRoleId() == 8);
	}

	@Test
	public void testRoleNameGetSet() {
		tfrole.setTfRoleName("Penguins");
		assertTrue(tfrole.getTfRoleName().equals("Penguins"));
		assertFalse(tfrole.getTfRoleName().equals("penguins"));
	}

	@Test
	public void testRoleUsersGetSet() {
		tfrole.setTfUsers(new HashSet<TfUser>());
		assertTrue(tfrole.getTfUsers() instanceof HashSet);
	}

	@Test
	public void testRoleEquivalence() {
		assertTrue(role1.equals(role2));
		assertFalse(role1.equals(new TfRole()));
	}

	@Test
	public void testRoleHashCode() {
		assertEquals(role1.hashCode(), role2.hashCode());
		assertNotEquals(role1.hashCode(), new TfRole().hashCode());
	}
	
	@Test
	public void testRoleToString() {
		assertEquals(role1.toString(), role2.toString());
		assertNotEquals(role1.toString(), tfrole.toString());
	}
}
