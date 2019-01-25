package com.revature.test.TestNG;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.revature.services.*;
import com.revature.test.pom.*;
import com.revature.utils.EnvManager;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/*
 *  Author: Mohamed Yousef
 */

public class AdminTests {

	static WebDriver wd;
	static WebDriverWait wait;
	String username = "TestAdmin";
	String password = "TestAdmin";
	public final String url = "http://34.227.178.103:8090/NGTrackForce/";
	// stores the values of batches names on the batches page
	List<String> batchNames = new ArrayList<String>();
	
	/* 
	 *  stores the values of dates in the batch list page, first element is the start date of first batch & second 
	 *  element is the end date of the first batch and so on... because start date and end dates dont have unique identifiers
	 *  
	 */
	
	List<String> batchDates = new ArrayList<String>();
	List<String> batchStartDates = new ArrayList<String>();
	List<String> batchEndDates = new ArrayList<String>();
	
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
		wd.get(url);//EnvManager.NGTrackForce_URL+"login/");
		wait = new WebDriverWait(wd,10);
		wait.until(ExpectedConditions.urlContains(url));//EnvManager.NGTrackForce_URL+"login/"));
		
	}
	
	@Test(priority = 1, dependsOnMethods = "launchApplication")
	void adminCanLogin() {
		wait.until(ExpectedConditions.elementToBeClickable(Login.getUsername(wd)));
		Login.getUsername(wd).sendKeys(username);
		Login.getPassword(wd).sendKeys(password);
		wait.until(ExpectedConditions.elementToBeClickable(Login.getSignInButton(wd)));
		Login.getSignInButton(wd).click();
		wait.until(ExpectedConditions.urlContains("/app-home"));
	}
	
	@Test(priority = 2, dependsOnMethods = "adminCanLogin")
	void chartsLoadWithin10Seconds() {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("pie")));
		wait.until(ExpectedConditions.visibilityOfAllElements(Home.getGraphs(wd)));
	}	
	
	@Test(priority = 3, dependsOnMethods = "chartsLoadWithin10Seconds")
	void thereAreFourChartsOnAdminHome() {
		Assert.assertEquals(Home.getGraphs(wd).size() , 4 );
	}	
	
	@Test(priority = 4, dependsOnMethods = "chartsLoadWithin10Seconds")
	void navbarClientListVisibile() {
		wait.until(ExpectedConditions.visibilityOf(NavBar.getClientList(wd)));
	}	
	
	@Test(priority = 4, dependsOnMethods = "chartsLoadWithin10Seconds")
	void navbarHomeVisibile() {
		wait.until(ExpectedConditions.visibilityOf(NavBar.getHome(wd)));
	}	
	
	@Test(priority = 4, dependsOnMethods = "chartsLoadWithin10Seconds")
	void navbarBatchListVisibile() {
		wait.until(ExpectedConditions.visibilityOf(NavBar.getBatchList(wd)));
	}	
	
	@Test(priority = 4, dependsOnMethods = "chartsLoadWithin10Seconds")
	void navbarAssociateListVisibile() {
		wait.until(ExpectedConditions.visibilityOf(NavBar.getAssociateList(wd)));
	}	
	
	@Test(priority = 4, dependsOnMethods = "chartsLoadWithin10Seconds")
	void navbarPredictionsVisibile() {
		wait.until(ExpectedConditions.visibilityOf(NavBar.getPredictionList(wd)));
	}		
	
	@Test(priority = 4, dependsOnMethods = "chartsLoadWithin10Seconds")
	void navbarCreateUserVisibile() {
		wait.until(ExpectedConditions.visibilityOf(NavBar.getCreateUser(wd)));
	}		
	
	@Test(priority = 4, dependsOnMethods = "chartsLoadWithin10Seconds")
	void navbarSalesForceVisibile() {
		wait.until(ExpectedConditions.visibilityOf(NavBar.getSalesForce(wd)));
	}	
	
	@Test(priority = 4, dependsOnMethods = "chartsLoadWithin10Seconds")
	void navbarWelcomeAdminByName() {
		new WebDriverWait(wd, 10).until(ExpectedConditions.visibilityOf(NavBar.getWelcomeDropdown(wd)));
		Assert.assertTrue(NavBar.getWelcomeDropdown(wd).getText().contains(username));
	}	
	
	@Test(priority = 5, dependsOnMethods = "chartsLoadWithin10Seconds")
	void clientListPageLoads() {
		wait.until(ExpectedConditions.elementToBeClickable(NavBar.getClientList(wd)));
		NavBar.getClientList(wd).click();
		wait.until(ExpectedConditions.urlContains("/client-listing"));
	}	
	
	/*
	 *  There's a non-repeatable bug where sometimes the clientList is loaded twice. This test tests if the values
	 *  in the client list are unique.
	 *  
	 */
	
	@Test(priority = 6, dependsOnMethods = "clientListPageLoads")
	void clientListIsOnlyLoadedOnce() {
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@id=\"clients-list\"]")));
		
		for (WebElement x : ClientList.getAllClients(wd)) {
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
		wait.until(ExpectedConditions.elementToBeClickable(ClientList.getFilter(wd)));
		
		ClientList.getFilter(wd).sendKeys(searchInput);
		List<String> clientsSearch = new ArrayList<String>();
		for (WebElement x : ClientList.getAllClients(wd)) {
			clientsSearch.add(x.getText());
		}

		Assert.assertEquals(ClientList.getAllClients(wd).get(0).getText() , searchInput );
	}
	
	@Test(priority = 8)
	void adminBatchListNavbaFunctional() {
		wait.until(ExpectedConditions.elementToBeClickable(NavBar.getBatchList(wd)));
		NavBar.getBatchList(wd).click();
		wait.until(ExpectedConditions.urlContains("/batch-listing"));
	}
	
	@Test(priority = 9, dependsOnMethods = "adminBatchListNavbaFunctional")
	void allBatchesLoad() {
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//td[@class='name-column']")));
		
		for(WebElement x : BatchList.getBatchListElements(wd)) {
			batchNames.add(BatchList.getBatchName(wd, x).getText());
		}
		
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//td[@class='date-column']")));
		
		for(WebElement x : BatchList.getBatchListElements(wd)) {
			batchStartDates.add(BatchList.getBatchStartDate(wd, x).getText());
			batchEndDates.add(BatchList.getBatchEndDate(wd, x).getText());
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
		
		wait.until(ExpectedConditions.elementToBeClickable(BatchList.getStartDateInput(wd)));
		BatchList.getStartDateInput(wd).click();
		BatchList.getStartDateInput(wd).sendKeys(Keys.ARROW_LEFT);
		BatchList.getStartDateInput(wd).sendKeys(Keys.ARROW_LEFT);
		BatchList.getStartDateInput(wd).sendKeys(batchStartDates.get(0));
		
		wait.until(ExpectedConditions.elementToBeClickable(BatchList.getEndDateInput(wd)));
		BatchList.getEndDateInput(wd).click();
		BatchList.getEndDateInput(wd).sendKeys(Keys.ARROW_LEFT);
		BatchList.getEndDateInput(wd).sendKeys(Keys.ARROW_LEFT);
		BatchList.getEndDateInput(wd).sendKeys(batchEndDates.get(0));
		
//		wait.until(ExpectedConditions.elementToBeClickable(BatchList.submitFilter(wd)));
//		AdminBatchList.submitFilter(wd).click();
		
		List<String> filterNames = new ArrayList<String>();
		for(WebElement x : BatchList.getBatchListElements(wd)) {
			filterNames.add(BatchList.getBatchName(wd, x).getText());
		}
		
		if(filterNames.size() == 0 ) {
			filterNames.add(0, "");
		}
		
		Assert.assertEquals(filterNames.get(0), batchNames.get(0));
		
	}

	@Test(priority = 11)
	void associatePageLoads() {
		wait.until(ExpectedConditions.elementToBeClickable(NavBar.getAssociateList(wd)));
		NavBar.getAssociateList(wd).click();
		wait.until(ExpectedConditions.urlContains("/associate-listing"));
	}
	
	@Test(priority = 12, dependsOnMethods = "associatePageLoads")
	void adminCanUpdateAssociateNoErrors() {
		// cant find button except when being ran on debug mode?
		wait.until(ExpectedConditions.elementToBeClickable(AssociateList.getTopAssociateRow(wd)));
		AssociateList.getTopAssociateRow(wd).click();
		
		wait.until(ExpectedConditions.visibilityOf(AssociateList.getUpdateAssociatesButton(wd)));
		wait.until(ExpectedConditions.elementToBeClickable(AssociateList.getUpdateAssociatesButton(wd)));
		AssociateList.getUpdateAssociatesButton(wd).click();
		
		Select dropdown = new Select(AssociateList.getVerificationModalSelect(wd));
		dropdown.selectByIndex(1);
		
		Select dropdown1 = new Select(AssociateList.getStatusModalSelect(wd));
		dropdown1.selectByIndex(2);
		
		Select dropdown2 = new Select(AssociateList.getClientModalSelect(wd));
		dropdown2.selectByIndex(1);

		wait.until(ExpectedConditions.elementToBeClickable(AssociateList.getAscModalSubmit(wd)));
		AssociateList.getAscModalSubmit(wd).click();
		
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='alert alert-danger ng-star-inserted']")));
		} catch (Exception e) {			
			// this is just here to not fail the test if the element was not loaded
		}
		
		wait.until(ExpectedConditions.visibilityOf(AssociateList.getTopAssociateRow(wd)));
		wait.until(ExpectedConditions.elementToBeClickable(AssociateList.getTopAssociateRow(wd)));
		Assert.assertFalse(wd.getPageSource().contains("There was an error updating the Associate(s)"));
	}
	
	@Test(priority = 13)
	void createUserPageLoads() {

		wait.until(ExpectedConditions.elementToBeClickable(NavBar.getCreateUser(wd)));
		NavBar.getCreateUser(wd).click();
		wait.until(ExpectedConditions.urlContains("/create-user"));
	}
	
	@Test(priority = 14, dependsOnMethods = "createUserPageLoads")
	void adminCanCreateValidAssociate() {
		// will not pass currently because users cannot be created
		// test is not idempotent
		wait.until(ExpectedConditions.elementToBeClickable(CreateUser.getUsername(wd)));
		CreateUser.getUsername(wd).sendKeys(randomStringValid());
		
		CreateUser.getPassword(wd).sendKeys("#Mwm12345@");
		CreateUser.getConfirmPassword(wd).sendKeys("#Mwm12345@");
		
		wait.until(ExpectedConditions.elementToBeClickable(CreateUser.getAssociateRadio(wd)));
		Actions action = new Actions(wd);
		action.moveToElement(CreateUser.getAssociateRadio(wd)).perform();
		action.click().perform();
		
		wait.until(ExpectedConditions.elementToBeClickable(CreateUser.getSubmitButton(wd)));
		CreateUser.getSubmitButton(wd).click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class='alert alert-success ng-star-inserted']")));
		String message = wd.findElement(By.cssSelector("div[class='alert alert-success ng-star-inserted']")).getText();
		Assert.assertEquals(message, "User created successfully");

	}
	
	@Test(priority = 14, dependsOnMethods = "adminCanCreateValidAssociate")
	// will skip because of the above test
	void adminCannotCreateInvalidAssociate() {
		wait.until(ExpectedConditions.elementToBeClickable(CreateUser.getUsername(wd)));
		CreateUser.getUsername(wd).clear();
		CreateUser.getUsername(wd).sendKeys(randomStringInvalid());
		
		CreateUser.getPassword(wd).clear();
		CreateUser.getPassword(wd).sendKeys("123");
		CreateUser.getConfirmPassword(wd).clear();
		CreateUser.getConfirmPassword(wd).sendKeys("123");
		
		wait.until(ExpectedConditions.elementToBeClickable(CreateUser.getAssociateRadio(wd)));
		Actions action = new Actions(wd);
		action.moveToElement(CreateUser.getAssociateRadio(wd)).perform();
		action.click().perform();
		
		wait.until(ExpectedConditions.elementToBeClickable(CreateUser.getSubmitButton(wd)));
		CreateUser.getSubmitButton(wd).click();
		new WebDriverWait(wd,6).until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#NewUserForm > div.alert.alert-danger")));
	}
	
	
	@AfterSuite
	void quit() {
		wd.quit();
	}

}
