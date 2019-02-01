package com.revature.test.services;

import static org.junit.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.revature.entity.TfRole;
import com.revature.entity.TfUser;
import com.revature.services.UserService;
import com.revature.test.utils.Log;

public class UserServicesTest {

	private UserService service;
	private Properties prop;

	@Test(priority = 0)
	public void init() {
		service = new UserService();
		service.getAllUsers();
		prop = new Properties();
		try {
			FileInputStream propFile = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\database_entries.properties");
			prop.load(propFile);
			propFile.close();
		} catch (FileNotFoundException e) {
			Log.Log.error(e.getMessage());
		} catch (IOException e) {
			Log.Log.error(e.getMessage());
		}
	}

	@Test
	public void testUserGetAll() {
		List<TfUser> users = service.getAllUsers();

		Assert.assertNotNull(users);
		Assert.assertTrue(!users.isEmpty());
	}

	@Test
	public void testUserGetByUsername() {
		TfUser user = service.getUser(prop.getProperty("user_username"));
		Assert.assertEquals(user.getUsername(), prop.getProperty("user_username"));
	}

	@Test
	public void testGetNonExistentUserByUsername() {
		String badUsername = "     ";
		TfUser badUser = service.getUser(badUsername);
		Assert.assertNull(badUser);
	}

	@Test(enabled = false) // disabled until able to properly stub.
	public void testUserCreate() {
		TfUser newUser = new TfUser();
		String newUsername = "Sample user for testing";
		int id = 5000;
		String password = "fake password";
		newUser.setId(id);
		newUser.setIsApproved(0);
		newUser.setPassword(password);
		newUser.setRole(3);
		newUser.setUsername(newUsername);
		boolean result = service.insertUser(newUser);
		Assert.assertTrue(result);
		TfUser retrieveUser = service.getUser(newUsername);
		Assert.assertEquals(retrieveUser, newUser);
	}

	@Test
	public void testCreateNullUser() {
		TfUser nullUser = null;
		boolean result = service.insertUser(nullUser);
		Assert.assertFalse(result);
	}

	@Test
	public void testUserGetRole() {
		Integer roleId = Integer.parseInt(prop.getProperty("role_id"));
		String roleName = prop.getProperty("role_name");
		TfRole role = service.getRole(roleId);

		Assert.assertEquals(role.getTfRoleId(), roleId);
		Assert.assertEquals(role.getTfRoleName(), roleName);
	}

	@Test
	public void testGetNonExistentRole() {
		int badRoleId = 90;
		TfRole badRole = service.getRole(badRoleId);

		Assert.assertNull(badRole);
	}

	@Test
	public void testUserSubmitCredentials() {
		// get existing user
		// expects noPasswordResult to be null, but still has non-null fields?
		String username = prop.getProperty("user_username");
		TfUser user = service.getUser(username);
		TfUser user2 = new TfUser();
		user2.setUsername(username);
		user2.setPassword(username);
		TfUser verifyUser = service.submitCredentials(user2);

		Assert.assertEquals(verifyUser.getUsername(), user.getUsername());
		// Now test nonexistent / null users
		TfUser nullUser = service.submitCredentials(null);
		Assert.assertNull(nullUser);
		TfUser userNoPassword = new TfUser();
		userNoPassword.setUsername("User with no password");
		TfUser noPasswordResult = service.submitCredentials(userNoPassword);

		Assert.assertNull(noPasswordResult);
	}
}