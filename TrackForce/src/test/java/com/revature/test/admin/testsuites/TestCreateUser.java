package com.revature.test.admin.testsuites;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.fail;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.revature.test.admin.pom.CreateUserTab;

public class TestCreateUser extends AdminSuite {

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
	//Clicks Create user Tab
	public void OnCreateUserTab() {
		try {
			assertTrue(CreateUserTab.clickCreateUserTab(wd));
		} catch (Throwable e) {
			System.out.println("Error clicking Create User Tab");
			e.printStackTrace();
		}
	}

	@AfterTest
	public void afterTest() {
		System.out.println("============ Create User Tests finished ===============");
	}

}