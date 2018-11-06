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
import org.testng.annotations.BeforeSuite;



public class AssociateTests {
  
  @BeforeSuite
  void startup() {
	  /*
	   * @ Jacob Golidng
	   * Uncomment this line if you are working on a Mac OS to run this with test ng
	   */
	  //File chrome = new File("src/main/resources/chromeDriveer4Mac/chromedriver");
	  // Uncomment this line you are working with Windows to run testng
	 
	  
	  ServiceHooks.driver = WebDriverUtil.getChromeDriver();
	  ServiceHooks.driver.get(EnvManager.NGTrackForce_URL);
	  ServiceHooks.wait = new WebDriverWait(ServiceHooks.driver,5);
  }
	
  @Test(priority = 0)
  public void LoadWebpage() {
	 ServiceHooks.driver.get(EnvManager.NGTrackForce_URL + "/#/login");
	 ServiceHooks.wait.until(ExpectedConditions.urlContains("/login"));
  }
  
  @Test (priority = 1)
  public void LoginAssociate() {
	 LoginUtil.loginAsAssociate(ServiceHooks.driver);
	 Login.getSignInButton(ServiceHooks.driver).click();
	 ServiceHooks.wait.until(ExpectedConditions.urlContains(EnvManager.NGTrackForce_URL+"/associate-view"));
  }
  
  @Test (priority = 2)
  public void LogOut() {
	  
	  ServiceHooks.wait.until(ExpectedConditions.elementToBeClickable(NavBar.getWelcomeDropdown(ServiceHooks.driver)));
	  NavBar.getWelcomeDropdown(ServiceHooks.driver).click();
	  ServiceHooks.wait.until(ExpectedConditions.elementToBeClickable(NavBar.getLogout(ServiceHooks.driver)));
	  NavBar.getLogout(ServiceHooks.driver).click();
	  ServiceHooks.wait.until(ExpectedConditions.urlContains(EnvManager.NGTrackForce_URL+"/login"));
	  
  }
  @Test (priority = 3)
  public void invalidLogin() {
	  ServiceHooks.wait.until(ExpectedConditions.elementToBeClickable(Login.getUsername(ServiceHooks.driver)));
		 Login.getUsername(ServiceHooks.driver).sendKeys("hat");
		 ServiceHooks.wait.until(ExpectedConditions.elementToBeClickable(Login.getPassword(ServiceHooks.driver)));
		 Login.getPassword(ServiceHooks.driver).sendKeys("bat");
		 ServiceHooks.wait.until(ExpectedConditions.elementToBeClickable(Login.getSignInButton(ServiceHooks.driver)));
		 Login.getSignInButton(ServiceHooks.driver).click();
		 ServiceHooks.wait.until(ExpectedConditions.visibilityOf(Login.getErrorPopup(ServiceHooks.driver)));
  }
  
  @AfterSuite
	void quit() {
	  ServiceHooks.driver.quit();
  }

}
