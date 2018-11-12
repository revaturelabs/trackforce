package com.revature.test.dao;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.revature.daoimpl.UserDaoImpl;
import com.revature.entity.TfUser;

/** Test class for testing UserDAOImpl
 * 
 * Danger of false negatives in the case of database changes.
 * 
 * Directly refers to existent entries in the database. Be warned that any
 * change in the database may very well cause tests to fail despite the DAO 
 * working just fine.
 */
public class UserDAOTest {
	
	@Test
	public void canGetUserByUsername() {
		Assert.assertEquals(1, new UserDaoImpl().getUser("TestAdmin").getId());
	}

	@Test
	public void getAllUsersIsNotEmpty() {
		Assert.assertNotEquals(0, new UserDaoImpl().getAllUsers().size());
	}

	/**
	 * getAllUsersWorks tests for an
	 */
	@Test
	public void getAllUsersWorks() {
		List<TfUser> list = new ArrayList<TfUser>();
		list = new UserDaoImpl().getAllUsers();
		Assert.assertNotNull(list);
		Assert.assertNotEquals(list.size(), 0);
	}

	@Test(expectedExceptions = NullPointerException.class)
	public void insertingDuplicateUsersThrowsException() {
		TfUser user = new UserDaoImpl().getUser("TestAdmin");
		new UserDaoImpl().insertUser(user);
	}

	@Test
	public void getRoleUserDaoWorks() {
		Assert.assertEquals(new UserDaoImpl().getRole(1).getTfRoleName(), "Admin");
	}

	@Test
	public void updateUserDaoWorks() {
		TfUser user = new UserDaoImpl().getUser("dvtestuser2");
		Assert.assertEquals(new UserDaoImpl().updateUser(user), true);
	}
}
