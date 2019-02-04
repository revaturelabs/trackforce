package com.revature.test.TestNG;

import org.testng.annotations.Test;

import com.revature.test.pom.AssociateHome;
import com.revature.test.pom.Login;
import com.revature.test.pom.NavBar;
import com.revature.test.utils.LoginUtil;
import com.revature.test.utils.ServiceHooks;
import com.revature.test.utils.WebDriverUtil;
import com.revature.utils.EnvManager;
import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public class AssociateTests {
	static WebDriver wd;
	static WebDriverWait wait;
	String username = "cyril";
	String password = "cyril";
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
	 * Updates the user information.
	 * first name and last name
	 */
	@Test(priority = 2)
	public void updateInformation() {
		WebElement fName = AssociateHome.newFirstName(wd);
		WebElement lName = AssociateHome.newLastName(wd);
		WebElement submit = AssociateHome.submitName(wd);
		fName.click();
		fName.clear();
		fName.sendKeys("FirstName");
		
		lName.click();
		lName.clear();
		lName.sendKeys("LastName");
		
		submit.click();
		
	}
	
	/*
	 * Verifying that associate can logout from their session
	 */
	@Test(priority = 3)
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
