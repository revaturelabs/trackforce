package com.revature.test.admin.testclasses;

import static org.junit.Assert.assertNotEquals;
import static org.testng.Assert.fail;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.revature.test.admin.pom.Login;

/**
 * This class contains one test which checks that the page has updated after logging in.
 * This test needs to be run at a very specific time in order to be successful.
 * @since 6.18.06.07
 */
public class LoginTest extends AdminSuite {

	@BeforeTest
	public void beforeTest() {
		System.out.println("============ Initializing Login Tests ===============");
		System.out.println("");
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