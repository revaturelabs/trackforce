package com.revature.test.admin.testclasses;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.fail;


import org.openqa.selenium.Alert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.revature.test.admin.cukes.CreateUserCukes;

public class CreateUserTest extends AdminSuite {

	static int testNumber = 1;
	
	// The base username of String "user" may need to change each run of the tests
	// 		to avoid duplicate account names
	// Uses String "user" + int "ID" to create a username; int "ID"
	// 		increments per user
	private static String user = "TestUser";
	private static String pass = "1234";
	private static String passMismatch = "12345";
	
	private static int ID = 1;

	@BeforeTest
	// Clicks Create user Tab and checks the URL
	public void beforeTest() {
		System.out.println("Running Create User Tab Tests");
		try {
			////assertTrue(CreateUserCukes.clickCreateUserTab(wd));
			////assertTrue(CreateUserCukes.loadedCreateUserTab(wd));

		} catch (Throwable e) {
			fail("Error: Failed to navigate to Create User tab");
			e.printStackTrace();
		}
	}

	@Test(priority = 1)
	// Fills in username, password, selects radio button, and submits
	public void CreateAdmin() {
		try {
			////assertTrue(CreateUserCukes.inputUsername(wd, user + ID));
			ID++;
			////assertTrue(CreateUserCukes.inputPassword(wd, pass));
			////assertTrue(CreateUserCukes.inputPasswordConfirm(wd, pass));
			////assertTrue(CreateUserCukes.clickAdminRadio(wd));
			////assertTrue(CreateUserCukes.submitForm(wd));
			//assertFalse(CreateUserCukes.cancelAlert(wd));
		} catch (Throwable e) {
			//fail("Error: Failed to create new Administrater");
			e.printStackTrace();
		}
	}
	
	@Test(priority = 2)
	// Fills in username, password, selects radio button, and submits
	public void CreateManager() {
		try {
			////assertTrue(CreateUserCukes.inputUsername(wd, user + ID));
			ID++;
			////assertTrue(CreateUserCukes.inputPassword(wd, pass));
			//assertTrue(CreateUserCukes.inputPasswordConfirm(wd, pass));
			//assertTrue(CreateUserCukes.clickManagerRadio(wd));
			//assertTrue(CreateUserCukes.submitForm(wd));
			//assertFalse(CreateUserCukes.cancelAlert(wd));
		} catch (Throwable e) {
			//fail("Error: Failed to create new Manager");
			e.printStackTrace();
		}
	}
	
	@Test(priority = 3)
	// Fills in username, password, selects radio button, and submits
	public void CreateVP() {
		try {
			//assertTrue(CreateUserCukes.inputUsername(wd, user + ID));
			ID++;
			//assertTrue(CreateUserCukes.inputPassword(wd, pass));
			//assertTrue(CreateUserCukes.inputPasswordConfirm(wd, pass));
			//assertTrue(CreateUserCukes.clickVPRadio(wd));
			//assertTrue(CreateUserCukes.submitForm(wd));
			//assertFalse(CreateUserCukes.cancelAlert(wd));
		} catch (Throwable e) {
			//fail("Error: Failed to create new Vice President");
			e.printStackTrace();
		}
	}

	@Test(priority = 4)
	// Fills in username, password, selects radio button, and submits
	public void CreateAssociate() {
		try {
			//assertTrue(CreateUserCukes.inputUsername(wd, user + ID));
			ID++;
			//assertTrue(CreateUserCukes.inputPassword(wd, pass));
			//assertTrue(CreateUserCukes.inputPasswordConfirm(wd, pass));
			//assertTrue(CreateUserCukes.clickAssociateRadio(wd));
			//assertTrue(CreateUserCukes.submitForm(wd));
			//assertFalse(CreateUserCukes.cancelAlert(wd));
		} catch (Throwable e) {
			//fail("Error: Failed to create new Associate");
			e.printStackTrace();
		}
	}

	@Test(priority = 5)
	// Fills in duplicate username, password, selects radio button, and submits
	// 		Tests to see if business logic detects and complains about duplicate username
	public void CreateAssociateDuplicateUsername() {
		try {
			//assertTrue(CreateUserCukes.inputUsername(wd, user + ID));
			ID++;
			//assertTrue(CreateUserCukes.inputPassword(wd, pass));
			//assertTrue(CreateUserCukes.inputPasswordConfirm(wd, pass));
			//assertTrue(CreateUserCukes.clickAssociateRadio(wd));
			//assertTrue(CreateUserCukes.submitForm(wd));
			//assertTrue(CreateUserCukes.cancelAlert(wd)); //alert should poppup and cancel it
		} catch (Throwable e) {
			//fail("Error: Failed to create new Associate");
			e.printStackTrace();
		}
	}
	
	@Test(priority = 6, enabled = false)
	// Fills in username, not matching passwords, selects radio button, and submits
	// 		Tests to see if business logic detects and complains about mismatching passwords
	public void CreateAssociatePasswordMismatch() {
		try {
			//assertTrue(CreateUserCukes.inputUsername(wd, user + ID));
			ID++;
			//assertTrue(CreateUserCukes.inputPassword(wd, pass));
			//assertTrue(CreateUserCukes.inputPasswordConfirm(wd, passMismatch));
			//assertTrue(CreateUserCukes.clickAssociateRadio(wd));
			//assertTrue(CreateUserCukes.submitForm(wd));
			//assertTrue(CreateUserCukes.cancelAlert(wd)); //alert should poppup and cancel it
		} catch (Throwable e) {
			//fail("Error: Failed to create new Associate");
			e.printStackTrace();
		}
	}
	
	@AfterTest
	public void afterTest() {
		System.out.println("============ Create User Tests finished ===============");
	}

}