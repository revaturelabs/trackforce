package com.revature.test.TestNG;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.revature.test.pom.AssociateHome;
import com.revature.test.pom.Login;
import com.revature.test.pom.NavBar;

public class AssociateTests {
	static WebDriver wd;
	static WebDriverWait wait;
	String username = "bobbert1234";
	String password = "Bobbert12!";
	public final String url = "http://34.227.178.103:8090/NGTrackForce/";


	@BeforeClass
	public void LoadWebpage() {
		File chrome = new File("src/main/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", chrome.getAbsolutePath());
		wd = new ChromeDriver();
		wd.get(url);// EnvManager.NGTrackForce_URL+"login/");
		wait = new WebDriverWait(wd, 10);
		wait.until(ExpectedConditions.urlContains(url));// EnvManager.NGTrackForce_URL+"login/"));
		
	}
	/**
	 * Login in as an associate 
	 */
	@Test(priority = 0)
	public void LoginAssociate() {
		wait.until(ExpectedConditions.elementToBeClickable(Login.getUsername(wd)));
		Login.getUsername(wd).sendKeys(username);
		Login.getPassword(wd).sendKeys(password);
		wait.until(ExpectedConditions.elementToBeClickable(Login.getSignInButton(wd)));
		Login.getSignInButton(wd).click();
		wait.until(ExpectedConditions.urlContains("/associate-view"));
		
	}

	/*
	 * Verifying that the tabs shows up on the associate's page when they log in 
	 */
	@Test(priority = 1)
	public void elementsOnPage() {
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.linkText("My Interviews")));
	}
	
	/*
	 * Update first and last name, click submit and check if the 
	 * the names are indeed updated in associate information
	 * 
	 * Test pass
	 */
	@Test(priority = 2)
	public void updateInformation() {
		String first = "FirstName", last = "LasName";
		
		WebElement fName = AssociateHome.newFirstName(wd);
		WebElement lName = AssociateHome.newLastName(wd);
		WebElement submit = AssociateHome.submitName(wd);
		fName.click();
		fName.clear();
		fName.sendKeys(first);
		
		lName.click();
		lName.clear();
		lName.sendKeys(last);
		
		submit.click();
		
		String updatedFirst = AssociateHome.updatedFirstName(wd).getText();
		String updatedLast = AssociateHome.updatedLastName(wd).getText();
		Assert.assertEquals(updatedFirst, first);
		Assert.assertEquals(updatedLast, last);
		
	}
	
	/*
	 * Associate adding an interview
	 */
	@Test(priority = 3)
	public void addInterview() {
		AssociateHome.interviewTab(wd).click();
		int beforeAddingInterview = AssociateHome.numberOfTR(wd).size();
		
		Select client = AssociateHome.chooseclient(wd);
		wd.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		client.selectByVisibleText("ADP");//Index(pickClient);
		
		Select type = AssociateHome.chooseType(wd);
		wd.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		wd.findElement(By.id("interviewType"));
		type.selectByVisibleText("Online");//Index(pickType);
		
		WebElement date = AssociateHome.inputDate(wd);
		String datetime = new SimpleDateFormat("MM/dd/yyyy HH:mm aaa").format(Calendar.getInstance().getTime());
		String datetime1 = "02/09/2019	12:45";
		date.sendKeys(datetime1);
		date.sendKeys(Keys.ARROW_UP);
		
		AssociateHome.checkBox(wd).click();
		wd.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		AssociateHome.addInterview(wd).click();
		
		
		wd.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		int afterAddingInterview = AssociateHome.numberOfTR(wd).size();
		
		Assert.assertEquals(afterAddingInterview, beforeAddingInterview+1);
	}
	
	/*
	 * Verifying that associate can logout from their session
	 */
	@Test(priority = 4)
	public void LogOut() {
		wait.until(ExpectedConditions.elementToBeClickable(NavBar.getWelcomeDropdown(wd)));
		NavBar.getWelcomeDropdown(wd).click();
		wait.until(ExpectedConditions.elementToBeClickable(NavBar.getLogout(wd)));
		NavBar.getLogout(wd).click();
		wait.until(ExpectedConditions.urlContains(url));
	}

		
	@AfterSuite
	void quit() {
		wd.quit();
	}

}
