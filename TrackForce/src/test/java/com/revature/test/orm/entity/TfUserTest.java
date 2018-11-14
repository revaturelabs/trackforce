package com.revature.test.orm.entity;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import com.revature.entity.TfRole;
import com.revature.entity.TfUser;

/**
 * Tests to test basic getter and setter functionality for TfUser
 * 
 * @author Jesse
 * @Since 6.18.06.11
 */
public class TfUserTest {
	TfRole role = new TfRole();

	//Can't use any other constructor besides default since TfUser class is marked
	//@Entity, which requires the class to not have any custom constructors
	TfUser tfuser1 = new TfUser();
	TfUser tfuser2 = new TfUser();

	TfUser tfuser = new TfUser();

	@Test
	public void testUserApprovedGetSet() {
		tfuser.setIsApproved(1);
		assertTrue(tfuser.getIsApproved() == 1);
		assertFalse(tfuser.getIsApproved() != 1);
	}

	@Test
	public void testUserTfRoleGetSet() {
		tfuser.setTfRole(new TfRole());
		assertTrue(tfuser.getTfRole() instanceof TfRole);
	}

	@Test
	public void testUserPasswordGetSet() {
		tfuser.setPassword("Penguins");
		assertTrue(tfuser.getPassword().equals("Penguins"));
		assertFalse(tfuser.getPassword().equals("penguins"));
	}

	@Test
	public void testUserIDGetSet() {
		tfuser.setId(7);
		assertTrue(tfuser.getId() == 7);
		assertFalse(tfuser.getId() == 6);
	}

	@Test
	public void testUserUsernameGetSet() {
		tfuser.setUsername("Penguins");
		assertTrue(tfuser.getUsername().equals("Penguins"));
		assertFalse(tfuser.getUsername().equals("penguins"));
	}

	@Test
	public void testUserTokenGetSet() {
		tfuser.setToken("Token");
		assertTrue(tfuser.getToken().equals("Token"));
		assertFalse(tfuser.getToken().equals("token"));
	}

	@Test
	public void testUserIntRoleGetSet() {
		tfuser.setRole(1);
		assertTrue(tfuser.getRole() == 1);
		assertFalse(tfuser.getRole() != 1);
	}
	

	@BeforeGroups("NotGetSet")
	public void setUpUsers() {
		tfuser1.setId(1);
		tfuser1.setIsApproved(0);
		tfuser1.setPassword("pass");
		tfuser1.setRole(1);
		tfuser1.setTfRole(role);
		tfuser1.setToken("token");
		tfuser1.setUsername("name");
		tfuser2.setId(1);
		tfuser2.setIsApproved(0);
		tfuser2.setPassword("pass");
		tfuser2.setRole(1);
		tfuser2.setTfRole(role);
		tfuser2.setToken("token");
		tfuser2.setUsername("name");
	}

	@Test(groups="NotGetSet")
	public void test8() {
		assertTrue(tfuser1.equals(tfuser2));
		System.out.println(tfuser1.toString());
		assertFalse(tfuser1.equals(new TfUser()));
	}

	@Test(groups="NotGetSet")
	public void test9() {
		assertEquals(tfuser1.hashCode(), tfuser2.hashCode());
		assertNotEquals(tfuser1.hashCode(), new TfUser().hashCode());
	}
	
	@Test(groups="NotGetSet")
	public void testUserToString() {
		assertEquals(tfuser1.toString(), tfuser2.toString());
		assertNotEquals(tfuser1.toString(), tfuser.toString());
	}
}
