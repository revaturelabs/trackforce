package com.revature.test.admin.testclasses;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.fail;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.revature.test.admin.cukes.HomeTabCukes;

public class HomeTest extends AdminSuite {

	@BeforeTest
	public void beforeTest() {
		System.out.println("Running HomePage Tests");
		try {

		} catch (Throwable e) {
			fail("Home Page not found or could not be clicked");
			e.printStackTrace();
		}

	}

	@Test(priority = 1)
	// Clicks Home Tab
	public void ClickBatchesTab() {
		try {

			assertTrue(HomeTabCukes.i_am_on_the_Home_Page(wd));

		} catch (Throwable e) {
			fail("Error: Failed to click on the Home Page");
			e.printStackTrace();
		}
		assertTrue(true);
	}

	/*
	 * @Test(priority = 2) // Clicks Home Tab public void ClickPieChart() { try {
	 * Thread.sleep(2000); // Click on the Pie Chart HomeTab.pieChart(wd).click();
	 * 
	 * Thread.sleep(2000); } catch (Throwable e) { fail("Can't click on Pie Chart");
	 * e.printStackTrace(); }
	 * 
	 * }
	 */

	@Test(priority = 2)
	public void clickPhone() {
		try {

			assertTrue(HomeTabCukes.i_click_on_the_telephone_link(wd));

		} catch (Throwable e) {
			fail("Error: Failed to click on the telephone link");
			e.printStackTrace();
		}
	}

	@Test(priority = 3)
	public void clickEmail() {
		try {

			assertTrue(HomeTabCukes.i_click_on_the_email_link(wd));

		} catch (Throwable e) {
			fail("Error: Failed to click on the email link");
			e.printStackTrace();
		}
	}

	@Test(priority = 4)
	public void clickWebSite() {
		try {

			assertTrue(HomeTabCukes.i_click_on_the_website_link(wd));

		} catch (Throwable e) {
			fail("Error: Failed to click on the web site link");
			e.printStackTrace();
		}
	}

	@Test(priority = 5, enabled = false)
	public void clickPopulate() {
		try {

			assertTrue(HomeTabCukes.i_click_on_the_populate_database_button(wd));

		} catch (Throwable e) {
			fail("Error: Failed to click on the populate database button");
			e.printStackTrace();
		}
	}

	@Test(priority = 6, enabled = false)
	public void clickStatic() {
		try {

			assertTrue(HomeTabCukes.i_click_on_the_populate_static_salesforce_button(wd));

		} catch (Throwable e) {
			fail("Error: Failed to click on the populate static salesforce button");
			e.printStackTrace();
		}
	}

	@Test(priority = 7, enabled = false)
	public void clickEmpty() {
		try {

			assertTrue(HomeTabCukes.i_click_on_the_empty_database_button(wd));

		} catch (Throwable e) {
			fail("Error: Failed to click on the empty database button");
			e.printStackTrace();
		}
	}
}
