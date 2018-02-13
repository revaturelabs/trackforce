package com.revature.test.admin.testclasses;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.fail;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.revature.test.admin.cukes.CreateUserCukes;
import com.revature.test.admin.pom.BatchListTab;
import com.revature.test.admin.pom.HomeTab;

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
			Thread.sleep(2000);
			// Click on the Pie Chart
			HomeTab.clickHomeTab(wd).click();
			
			Thread.sleep(2000);
		} catch (Throwable e) {
			fail("Can't click on the Home Tab");
			e.printStackTrace();
		}
		assertTrue(true);
	}
	
	/*
	@Test(priority = 2)
	// Clicks Home Tab 
	public void ClickPieChart() {
		try {
			Thread.sleep(2000);
			// Click on the Pie Chart
			HomeTab.pieChart(wd).click();
			
			Thread.sleep(2000);
		} catch (Throwable e) {
			fail("Can't click on Pie Chart");
			e.printStackTrace();
		}
		
	}
	*/
	
	@Test(priority = 2)
	public void clickPhone() {
		try {
			Thread.sleep(2000);
			// Click on the Pie Chart
			HomeTab.phone(wd).click();
			
			Thread.sleep(2000);
		} catch (Throwable e) {
			fail("Can't click on the phone link");
			e.printStackTrace();
		}
	}
	
	@Test(priority = 3)
	public void clickEmail() {
		try {
			Thread.sleep(2000);
			// Click on the Pie Chart
			HomeTab.email(wd).click();
			
			Thread.sleep(2000);
		} catch (Throwable e) {
			fail("Can't click on the email link");
			e.printStackTrace();
		}
	}
	
	@Test(priority = 4)
	public void clickWebSite() {
		try {
			Thread.sleep(2000);
			// Click on the Pie Chart
			HomeTab.website(wd).click();
			
			Thread.sleep(7000);
		} catch (Throwable e) {
			fail("Can't click on the web site link");
			e.printStackTrace();
		}
	}
}
