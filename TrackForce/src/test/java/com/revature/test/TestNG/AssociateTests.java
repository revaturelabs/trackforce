package com.revature.test.TestNG;

import org.testng.annotations.Test;

import com.revature.test.pom.Login;
import com.revature.test.pom.NavBar;


import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;



public class AssociateTests {
  static WebDriver wd;
  String username = "cyril";
  String password = "cyril";
  static WebDriverWait wait;
  
  @BeforeSuite
  void startup() {
	  /*
	   * @ Jacob Golidng
	   * Uncomment this line if you are working on a Mac OS to run this with test ng
	   */
	  //File chrome = new File("src/main/resources/chromeDriveer4Mac/chromedriver");
	  // Uncomment this line you are working with Windows to run testng
	  File chrome = new File("src/main/resources/chromedriver.exe");
	  System.setProperty("webdriver.chrome.driver", chrome.getAbsolutePath());
	  wd = new ChromeDriver();
	  wait = new WebDriverWait(wd,15);
  }
	
  @Test(priority = 0)
  public void LoadWebpage() {
	  wd.get(System.getenv("url") + "#/login");
	  new WebDriverWait(wd, 15).until(ExpectedConditions.urlContains("/login"));
  }
  
  @Test (priority = 1)
  public void LoginAssociate() {
	 wait.until(ExpectedConditions.elementToBeClickable(Login.getUsername(wd)));
	 Login.getUsername(wd).sendKeys(username);
	 wait.until(ExpectedConditions.elementToBeClickable(Login.getPassword(wd)));
	 Login.getPassword(wd).sendKeys(password);
	 wait.until(ExpectedConditions.elementToBeClickable(Login.getSignInButton(wd)));
	 Login.getSignInButton(wd).click();
	 wait.until(ExpectedConditions.urlContains(System.getenv("url")+"/associate-view"));
  }
  
  @Test (priority = 2)
  public void LogOut() {
	  
	  wait.until(ExpectedConditions.elementToBeClickable(NavBar.getWelcomeDropdown(wd)));
	  NavBar.getWelcomeDropdown(wd).click();
	  wait.until(ExpectedConditions.elementToBeClickable(NavBar.getLogout(wd)));
	  NavBar.getLogout(wd).click();
	  wait.until(ExpectedConditions.urlContains(System.getenv("url")+"/login"));
	  
  }
  @Test (priority = 3)
  public void invalidLogin() {
	  wait.until(ExpectedConditions.elementToBeClickable(Login.getUsername(wd)));
		 Login.getUsername(wd).sendKeys("hat");
		 wait.until(ExpectedConditions.elementToBeClickable(Login.getPassword(wd)));
		 Login.getPassword(wd).sendKeys("bat");
		 wait.until(ExpectedConditions.elementToBeClickable(Login.getSignInButton(wd)));
		 Login.getSignInButton(wd).click();
		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"pwd-container\"]/div/section/form/div/div[1]")));
  }
  
  @AfterSuite
	void quit() {
	  wd.quit();
  }

}
