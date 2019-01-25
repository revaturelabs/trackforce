package com.revature.test.TestNG;

import org.testng.annotations.Test;

import com.revature.test.pom.Login;
import com.revature.test.pom.NavBar;
import com.revature.test.utils.LoginUtil;
import com.revature.test.utils.ServiceHooks;
import com.revature.test.utils.WebDriverUtil;
import com.revature.utils.EnvManager;
import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
	 * making sure that the appropriate tabs are visible on the associates 
	 * homepage
	 */
	@Test(dependsOnMethods= {"LoginAssociate"})
	public void elementsOnPage() {
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.linkText("My Interviews")));
	}
	
	@Test(priority = 1)
	public void LogOut() {
		wait.until(ExpectedConditions.elementToBeClickable(NavBar.getWelcomeDropdown(wd)));
		NavBar.getWelcomeDropdown(wd).click();
		wait.until(ExpectedConditions.elementToBeClickable(NavBar.getLogout(wd)));
		NavBar.getLogout(wd).click();
		wait.until(ExpectedConditions.urlContains(url));//EnvManager.NGTrackForce_URL + "login/"));
	}

	@AfterSuite
	void quit() {
		wd.quit();
	}

}
