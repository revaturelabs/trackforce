package com.revature.test.TestNG;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.revature.pom.*;
import com.revature.services.*;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

public class AdminTests {
	/*
	 *  author: Mohamed Yousef
	 */
	static WebDriver wd;
	String username = "TestAdmin";
	String password = "TestAdmin";

	
	// Holds the client list before its cleaned 
	List<String> clients = new ArrayList<String>();
	
	// Holds a unique set of clients and formated nicely
	List<String> unique = new ArrayList<String>();
	
	@Test(priority = 0)
	void launchApplication() {
		File chrome = new File("src/main/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", chrome.getAbsolutePath());
		wd = new ChromeDriver();
		wd.get("http://localhost:4200/NGTrackForce/");
		new WebDriverWait(wd, 15).until(ExpectedConditions.urlContains("http://localhost:4200/#/login"));
	}
	
	@Test(priority = 1, dependsOnMethods = "launchApplication")
	void adminCanLogin() {
		new WebDriverWait(wd, 15).until(ExpectedConditions.elementToBeClickable(LoginPage.username(wd)));
		LoginPage.username(wd).sendKeys(username);
		LoginPage.password(wd).sendKeys(password);
		new WebDriverWait(wd, 15).until(ExpectedConditions.elementToBeClickable(LoginPage.signIn(wd)));
		LoginPage.signIn(wd).click();
		new WebDriverWait(wd, 15).until(ExpectedConditions.urlContains("#/app-home"));
	}
	
	@Test(priority = 2, dependsOnMethods = "adminCanLogin")
	void chartsLoadWithin10Seconds() {
		new WebDriverWait(wd, 10).until(ExpectedConditions.presenceOfElementLocated(By.id("pie")));
		new WebDriverWait(wd, 10).until(ExpectedConditions.visibilityOfAllElements(AdminHome.charts(wd)));
	}	
	
	@Test(priority = 3, dependsOnMethods = "chartsLoadWithin10Seconds")
	void thereAreFourChartsOnAdminHome() {
		Assert.assertEquals(AdminHome.charts(wd).size() , 4 );
	}	
	
	@Test(priority = 4, dependsOnMethods = "chartsLoadWithin10Seconds")
	void navbarClientListVisibile() {
		new WebDriverWait(wd, 10).until(ExpectedConditions.visibilityOf(AdminNavbarElements.clientList(wd)));
	}	
	
	@Test(priority = 4, dependsOnMethods = "chartsLoadWithin10Seconds")
	void navbarHomeVisibile() {
		new WebDriverWait(wd, 10).until(ExpectedConditions.visibilityOf(AdminNavbarElements.home(wd)));
	}	
	
	@Test(priority = 4, dependsOnMethods = "chartsLoadWithin10Seconds")
	void navbarBatchListVisibile() {
		new WebDriverWait(wd, 10).until(ExpectedConditions.visibilityOf(AdminNavbarElements.batchList(wd)));
	}	
	
	@Test(priority = 4, dependsOnMethods = "chartsLoadWithin10Seconds")
	void navbarAssociateListVisibile() {
		new WebDriverWait(wd, 10).until(ExpectedConditions.visibilityOf(AdminNavbarElements.associateList(wd)));
	}	
	
	@Test(priority = 4, dependsOnMethods = "chartsLoadWithin10Seconds")
	void navbarPredictionsVisibile() {
		new WebDriverWait(wd, 10).until(ExpectedConditions.visibilityOf(AdminNavbarElements.predictions(wd)));
	}		
	
	@Test(priority = 4, dependsOnMethods = "chartsLoadWithin10Seconds")
	void navbarCreateUserVisibile() {
		new WebDriverWait(wd, 10).until(ExpectedConditions.visibilityOf(AdminNavbarElements.createUser(wd)));
	}		
	
	@Test(priority = 4, dependsOnMethods = "chartsLoadWithin10Seconds")
	void navbarSalesForceVisibile() {
		new WebDriverWait(wd, 10).until(ExpectedConditions.visibilityOf(AdminNavbarElements.salesForce(wd)));
	}	
	
	@Test(priority = 4, dependsOnMethods = "chartsLoadWithin10Seconds")
	void navbarWelcomeAdminByName() {
		new WebDriverWait(wd, 10).until(ExpectedConditions.visibilityOf(AdminNavbarElements.welcomeText(wd)));
		Assert.assertTrue(AdminNavbarElements.welcomeText(wd).getText().contains(username));
	}	
	
	@Test(priority = 5, dependsOnMethods = "chartsLoadWithin10Seconds")
	void clientListPageLoads() {
		new WebDriverWait(wd, 10).until(ExpectedConditions.elementToBeClickable(AdminNavbarElements.clientList(wd)));
		AdminNavbarElements.clientList(wd).click();
		new WebDriverWait(wd, 15).until(ExpectedConditions.urlContains("#/client-listing"));
	}	
	
	/*
	 *  There's a non-repeatable bug where sometimes the clientList is loaded twice. This test tests if the values
	 *  in the client list are unique.
	 *  
	 */
	
	@Test(priority = 6, dependsOnMethods = "clientListPageLoads")
	void clientListIsOnlyLoadedOnce() {
		new WebDriverWait(wd, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@id=\"clients-list\"]")));
		
		
		
		for (WebElement x : AdminClientList.clientList(wd)) {
			clients.add(x.getText());
		}
		
		// cleans the client list
		String[] arr = clients.get(0).split("\n");
		
		for(String x : arr) {
			if(unique.contains(x)) {
				Assert.assertTrue(false);
			}
			unique.add(x);
		}
		
	}
	
	@Test(priority = 6, dependsOnMethods = "clientListIsOnlyLoadedOnce")
	void adminCanFilterClients() {
		new WebDriverWait(wd, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@id=\"clients-list\"]")));

		String searchInput = unique.get(0);
		new WebDriverWait(wd, 15).until(ExpectedConditions.elementToBeClickable(AdminClientList.filter(wd)));
		
		AdminClientList.filter(wd).sendKeys(searchInput);
		List<String> clientsSearch = new ArrayList<String>();
		for (WebElement x : AdminClientList.clientList(wd)) {
			clientsSearch.add(x.getText());
		}

		Assert.assertEquals(AdminClientList.clientList(wd).get(0).getText() , searchInput );
	}
	
//	@Test(priority = 7, dependsOnMethods = "clientListPageLoads")
//	void clientListIsOnlyLoadedOnce() {
//		List<String> clients = new ArrayList<String>();
//		for (WebElement x : AdminClientList.clientList(wd)) {
//			if (clients.contains(x.getText())) {
//				Assert.assertTrue(false);
//			}
//			clients.add(x.getText());
//		}
//	}	
	
	@AfterSuite
	void quit() {
		wd.quit();
	}

}
