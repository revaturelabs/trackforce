package com.revature.test.junit.entity;

import static org.junit.Assert.assertFalse;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import java.util.HashSet;

import org.junit.Test;

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
	public void test1() {
		tfrole.setTfRoleId(7);
		assertTrue(tfrole.getTfRoleId() == 7);
		assertFalse(tfrole.getTfRoleId() == 8);
	}

	@Test
	public void test2() {
		tfrole.setTfRoleName("Penguins");
		assertTrue(tfrole.getTfRoleName().equals("Penguins"));
		assertFalse(tfrole.getTfRoleName().equals("penguins"));
	}

	@Test
	public void test3() {
		tfrole.setTfUsers(new HashSet<TfUser>());
		assertTrue(tfrole.getTfUsers() instanceof HashSet);
	}

	@Test
	public void test4() {
		assertTrue(role1.equals(role2));
		assertFalse(role1.equals(new TfRole()));
	}

	@Test
	public void test5() {
		assertEquals(role1.hashCode(), role2.hashCode());
		assertNotEquals(role1.hashCode(), new TfRole().hashCode());
	}
}
