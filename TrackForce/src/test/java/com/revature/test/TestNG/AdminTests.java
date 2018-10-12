package com.revature.test.TestNG;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.revature.pom.*;
import com.revature.services.*;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

/*
 *  Author: Mohamed Yousef
 */

public class AdminTests {

	static WebDriver wd;
	static WebDriverWait wait;
	String username = "TestAdmin";
	String password = "TestAdmin";
	
	// stores the values of batches names on the batches page
	List<String> batchNames = new ArrayList<String>();
	
	/* 
	 *  stores the values of dates in the batch list page, first element is the start date of first batch & second 
	 *  element is the end date of the first batch and so on... because start date and end dates dont have unique identifiers
	 *  
	 */
	
	List<String> batchDates = new ArrayList<String>();
	
	// Holds the client list before its cleaned 
	List<String> clients = new ArrayList<String>();
	
	// Holds a unique set of clients and formated nicely
	List<String> unique = new ArrayList<String>();
	
	// Method from stackOverFlow to generate a random string (no invalid characters) -- used to generate a valid username
	public String randomStringValid() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 10) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;

	}
	
	// Method from stackOverFlow to generate a random string (with invalid characters) -- used to generate an invalid username
	public String randomStringInvalid() {
		String SALTCHARS = ";*#$)(!~,:'\"}{][mWaZ";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 10) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;

	}
	
	@Test(priority = 0)
	void launchApplication() {
		File chrome = new File("src/main/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", chrome.getAbsolutePath());
		wd = new ChromeDriver();
		wd.get(System.getenv("url")+"NGTrackForce/");
		wait = new WebDriverWait(wd,10);
		wait.until(ExpectedConditions.urlContains(System.getenv("url")+"#/login"));
		
	}
	
	@Test(priority = 1, dependsOnMethods = "launchApplication")
	void adminCanLogin() {
		wait.until(ExpectedConditions.elementToBeClickable(LoginPage.username(wd)));
		LoginPage.username(wd).sendKeys(username);
		LoginPage.password(wd).sendKeys(password);
		wait.until(ExpectedConditions.elementToBeClickable(LoginPage.signIn(wd)));
		LoginPage.signIn(wd).click();
		wait.until(ExpectedConditions.urlContains("#/app-home"));
	}
	
	@Test(priority = 2, dependsOnMethods = "adminCanLogin")
	void chartsLoadWithin10Seconds() {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("pie")));
		wait.until(ExpectedConditions.visibilityOfAllElements(AdminHome.charts(wd)));
	}	
	
	@Test(priority = 3, dependsOnMethods = "chartsLoadWithin10Seconds")
	void thereAreFourChartsOnAdminHome() {
		Assert.assertEquals(AdminHome.charts(wd).size() , 4 );
	}	
	
	@Test(priority = 4, dependsOnMethods = "chartsLoadWithin10Seconds")
	void navbarClientListVisibile() {
		wait.until(ExpectedConditions.visibilityOf(AdminNavbarElements.clientList(wd)));
	}	
	
	@Test(priority = 4, dependsOnMethods = "chartsLoadWithin10Seconds")
	void navbarHomeVisibile() {
		wait.until(ExpectedConditions.visibilityOf(AdminNavbarElements.home(wd)));
	}	
	
	@Test(priority = 4, dependsOnMethods = "chartsLoadWithin10Seconds")
	void navbarBatchListVisibile() {
		wait.until(ExpectedConditions.visibilityOf(AdminNavbarElements.batchList(wd)));
	}	
	
	@Test(priority = 4, dependsOnMethods = "chartsLoadWithin10Seconds")
	void navbarAssociateListVisibile() {
		wait.until(ExpectedConditions.visibilityOf(AdminNavbarElements.associateList(wd)));
	}	
	
	@Test(priority = 4, dependsOnMethods = "chartsLoadWithin10Seconds")
	void navbarPredictionsVisibile() {
		wait.until(ExpectedConditions.visibilityOf(AdminNavbarElements.predictions(wd)));
	}		
	
	@Test(priority = 4, dependsOnMethods = "chartsLoadWithin10Seconds")
	void navbarCreateUserVisibile() {
		wait.until(ExpectedConditions.visibilityOf(AdminNavbarElements.createUser(wd)));
	}		
	
	@Test(priority = 4, dependsOnMethods = "chartsLoadWithin10Seconds")
	void navbarSalesForceVisibile() {
		wait.until(ExpectedConditions.visibilityOf(AdminNavbarElements.salesForce(wd)));
	}	
	
	@Test(priority = 4, dependsOnMethods = "chartsLoadWithin10Seconds")
	void navbarWelcomeAdminByName() {
		new WebDriverWait(wd, 10).until(ExpectedConditions.visibilityOf(AdminNavbarElements.welcomeText(wd)));
		Assert.assertTrue(AdminNavbarElements.welcomeText(wd).getText().contains(username));
	}	
	
	@Test(priority = 5, dependsOnMethods = "chartsLoadWithin10Seconds")
	void clientListPageLoads() {
		wait.until(ExpectedConditions.elementToBeClickable(AdminNavbarElements.clientList(wd)));
		AdminNavbarElements.clientList(wd).click();
		wait.until(ExpectedConditions.urlContains("#/client-listing"));
	}	
	
	/*
	 *  There's a non-repeatable bug where sometimes the clientList is loaded twice. This test tests if the values
	 *  in the client list are unique.
	 *  
	 */
	
	@Test(priority = 6, dependsOnMethods = "clientListPageLoads")
	void clientListIsOnlyLoadedOnce() {
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@id=\"clients-list\"]")));
		
		
		
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
	
	@Test(priority = 7, dependsOnMethods = "clientListIsOnlyLoadedOnce")
	void adminCanFilterClients() {
		String searchInput = unique.get(0);
		wait.until(ExpectedConditions.elementToBeClickable(AdminClientList.filter(wd)));
		
		AdminClientList.filter(wd).sendKeys(searchInput);
		List<String> clientsSearch = new ArrayList<String>();
		for (WebElement x : AdminClientList.clientList(wd)) {
			clientsSearch.add(x.getText());
		}

		Assert.assertEquals(AdminClientList.clientList(wd).get(0).getText() , searchInput );
	}
	
	@Test(priority = 8)
	void adminBatchListNavbaFunctional() {
		wait.until(ExpectedConditions.elementToBeClickable(AdminNavbarElements.batchList(wd)));
		AdminNavbarElements.batchList(wd).click();
		wait.until(ExpectedConditions.urlContains("#/batch-listing"));
	}
	
	@Test(priority = 9, dependsOnMethods = "adminBatchListNavbaFunctional")
	void allBatchesLoad() {
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//td[@class='name-column']")));
		
		for(WebElement x : AdminBatchList.batchesNames(wd)) {
			batchNames.add(x.getText());
		}
		
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//td[@class='date-column']")));
		
		for(WebElement x : AdminBatchList.batchesDates(wd)) {
			batchDates.add(x.getText());
		}
	}
	
	/*
	 * There's a defect where the filter functionality on the batch list tab for the admin user does not work at edge cases.
	 * For example if a batch starts 12/1/2017 and ends 11/2/2018 and you try to filter by inputing start date as 
	 * 12/1/2018 and end date 11/2/2018 it wont show any results, you have to input one day before start date and one
	 * day after end date for it to work. 
	 * 
	 * This test grabs the start date and end date of the first batch and checks if it shows up after filtering.
	 */
	
	@Test(priority = 10, dependsOnMethods = "allBatchesLoad")
	void batchFilteringWorksAtEdgeCases() {
		
		wait.until(ExpectedConditions.elementToBeClickable(AdminBatchList.startDateInput(wd)));
		AdminBatchList.startDateInput(wd).clear();
		AdminBatchList.startDateInput(wd).sendKeys(batchDates.get(0));
		
		wait.until(ExpectedConditions.elementToBeClickable(AdminBatchList.endDateInput(wd)));
		AdminBatchList.endDateInput(wd).clear();
		AdminBatchList.endDateInput(wd).sendKeys(batchDates.get(1));
		
		wait.until(ExpectedConditions.elementToBeClickable(AdminBatchList.submitFilter(wd)));
		AdminBatchList.submitFilter(wd).click();
		
		List<String> filterNames = new ArrayList<String>();
		for(WebElement x : AdminBatchList.batchesNames(wd)) {
			filterNames.add(x.getText());
		}
		
		if(filterNames.size() == 0 ) {
			filterNames.add(0, "");
		}
		
		Assert.assertEquals(filterNames.get(0), batchNames.get(0));
		
	}

	@Test(priority = 11)
	void associatePageLoads() {
		wait.until(ExpectedConditions.elementToBeClickable(AdminNavbarElements.associateList(wd)));
		AdminNavbarElements.associateList(wd).click();
		wait.until(ExpectedConditions.urlContains("#/associate-listing"));
	}
	
	@Test(priority = 12, dependsOnMethods = "associatePageLoads")
	void adminCanUpdateAssociateNoErrors() {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"1\"]")));
		wait.until(ExpectedConditions.elementToBeClickable(AdminAssociateList.associate1(wd)));
		AdminAssociateList.associate1(wd).click();
		
		Select dropdown = new Select(AdminAssociateList.verificationMenu(wd));
		dropdown.selectByIndex(1);
		
		Select dropdown1 = new Select(AdminAssociateList.statusMenu(wd));
		dropdown1.selectByIndex(1);
		
		Select dropdown2 = new Select(AdminAssociateList.clientMenu(wd));
		dropdown2.selectByIndex(1);

		wait.until(ExpectedConditions.elementToBeClickable(AdminAssociateList.updateAssociateBTN(wd)));
		AdminAssociateList.updateAssociateBTN(wd).click();
		
		try {
		new WebDriverWait(wd,7).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='alert alert-danger ng-star-inserted']")));
		} catch (Exception e) {			
			// this is just here to not fail the test if the element was not loaded
		}
		Assert.assertFalse(wd.getPageSource().contains("There was an error updating the Associate(s)"));
	}
	
	@Test(priority = 13)
	void createUserPageLoads() {
		wait.until(ExpectedConditions.elementToBeClickable(AdminNavbarElements.createUser(wd)));
		AdminNavbarElements.createUser(wd).click();
		wait.until(ExpectedConditions.urlContains("#/create-user"));
	}
	
	@Test(priority = 14, dependsOnMethods = "createUserPageLoads")
	void adminCanCreateValidAssociate() {
		wait.until(ExpectedConditions.elementToBeClickable(AdminCreateUser.usernameInput(wd)));
		AdminCreateUser.usernameInput(wd).sendKeys(randomStringValid());
		
		AdminCreateUser.passwordInput(wd).sendKeys("#Mwm12345@");
		AdminCreateUser.confirmPasswordInput(wd).sendKeys("#Mwm12345@");
		
		wait.until(ExpectedConditions.elementToBeClickable(AdminCreateUser.selectAssociate(wd)));
		Actions action = new Actions(wd);
		action.moveToElement(AdminCreateUser.selectAssociate(wd)).perform();
		action.click().perform();
		
		wait.until(ExpectedConditions.elementToBeClickable(AdminCreateUser.submitAssociateBTN(wd)));
		AdminCreateUser.submitAssociateBTN(wd).click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#NewUserForm > div.alert.alert-success")));
		String message = wd.findElement(By.cssSelector("#NewUserForm > div.alert.alert-success")).getText();
		
		Assert.assertEquals(message, "User created successfully");

	}
	
	@Test(priority = 14, dependsOnMethods = "adminCanCreateValidAssociate")
	void adminCannotCreateInvalidAssociate() {
		wait.until(ExpectedConditions.elementToBeClickable(AdminCreateUser.usernameInput(wd)));
		AdminCreateUser.usernameInput(wd).clear();
		AdminCreateUser.usernameInput(wd).sendKeys(randomStringInvalid());
		
		AdminCreateUser.passwordInput(wd).clear();
		AdminCreateUser.passwordInput(wd).sendKeys("123");
		AdminCreateUser.confirmPasswordInput(wd).clear();
		AdminCreateUser.confirmPasswordInput(wd).sendKeys("123");
		
		wait.until(ExpectedConditions.elementToBeClickable(AdminCreateUser.selectAssociate(wd)));
		Actions action = new Actions(wd);
		action.moveToElement(AdminCreateUser.selectAssociate(wd)).perform();
		action.click().perform();
		
		wait.until(ExpectedConditions.elementToBeClickable(AdminCreateUser.submitAssociateBTN(wd)));
		AdminCreateUser.submitAssociateBTN(wd).click();
		new WebDriverWait(wd,6).until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#NewUserForm > div.alert.alert-danger")));
	}
	
	
	@AfterSuite
	void quit() {
		wd.quit();
	}

}
