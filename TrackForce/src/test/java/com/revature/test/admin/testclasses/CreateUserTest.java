package com.revature.test.admin.testclasses;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.fail;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.revature.test.admin.pom.CreateUserTab;

public class CreateUserTest extends AdminSuite {

	@BeforeTest
	public void beforeTest() {
		System.out.println("Running Create User Tab Tests");
		try {

		} catch (Throwable e) {
			fail("Currently not in Locations Tab; Create User Tab not found or could not be clicked");
			e.printStackTrace();
		}
	}

	@Test(priority = 1)
	// Clicks Create user Tab and looks for the "Create New User" element
	public void GoToCreateUserTab() {
		try {
			assertTrue(CreateUserTab.clickCreateUserTab(wd));
			assertTrue(CreateUserTab.onCreateUserTab(wd));
		} catch (Throwable e) {
			fail("Error: Failed to switch to Create User Tab");
			e.printStackTrace();
		}
	}
	
	@Test(priority = 2)
	public void DoSomething() {
		try {
		} catch (Throwable e) {
			e.printStackTrace();
		}
		try {
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	

	@AfterTest
	public void afterTest() {
		System.out.println("============ Create User Tests finished ===============");
	}

}