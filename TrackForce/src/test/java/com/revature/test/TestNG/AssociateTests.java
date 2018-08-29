package com.revature.test.TestNG;

import org.testng.annotations.Test;
import org.testng.annotations.Test;

import com.revature.pom.LoginPage;

import static org.testng.Assert.expectThrows;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;


public class AssociateTests {
  static WebDriver wd;
  String username = "cyril";
  String password = "cyril";
  static WebDriverWait wait;
  
  @BeforeSuite
  void startup() {
	  File chrome = new File("src/main/resources/chromeDriveer4Mac/chromedriver");
	  System.setProperty("webdriver.chrome.driver", chrome.getAbsolutePath());
	  wd = new ChromeDriver();
	  wait = new WebDriverWait(wd,15);
  }
	
  @Test(priority = 0)
  public void LoadWebpage() {
	  wd.get("http://localhost:4200/#/login");
	  new WebDriverWait(wd, 15).until(ExpectedConditions.urlContains("http://localhost:4200/#/login"));
  }
  
  @Test (priority = 1)
  public void LoginAssociate() {
	 wait.until(ExpectedConditions.elementToBeClickable(LoginPage.username(wd)));
	 LoginPage.username(wd).sendKeys(username);
	 wait.until(ExpectedConditions.elementToBeClickable(LoginPage.password(wd)));
	 LoginPage.password(wd).sendKeys(password);
	 wait.until(ExpectedConditions.elementToBeClickable(LoginPage.signIn(wd)));
	 LoginPage.signIn(wd).click();
	 wait.until(ExpectedConditions.urlContains("http://localhost:4200/#/associate-view"));
	 
  }
  
  @AfterSuite
	void quit() {
	  wd.quit();
  }

}
