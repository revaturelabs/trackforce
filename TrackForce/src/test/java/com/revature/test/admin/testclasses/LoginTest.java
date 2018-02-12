package com.revature.test.admin.testclasses;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotEquals;
import static org.testng.Assert.fail;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.revature.test.admin.pom.Login;

public class LoginTest extends AdminSuite {

	@BeforeTest
	public void beforeTest() {
		System.out.println("Testing if login was successful so we are on Home page now");
	}

	@Test(priority = 1)
	public void IndexPage() {
		try {
			assertNotEquals(Login.getTitle(wd), "NGTrackForce");
		} catch (Throwable e) {
			fail("Failed to Login. 'NGTrackForce' header still on page'");
			e.printStackTrace();
		}
	}
	
	@AfterTest
	public void afterTest() {
		System.out.println("============ Login Tests finished ===============");
	}

}