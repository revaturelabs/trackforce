package com.revature.test.TestNG;

import org.testng.annotations.Test;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

public class AssociateTests {
  static WebDriver wd;
  String username = "AssociateTest";
  String password = "AssociateTest";
  
  @BeforeSuite
  void startup() {
	  File chrome = new File("src/main/resources/chromeDriveer4Mac/chromedriver");
	  System.setProperty("webdriver.chrome.driver", chrome.getAbsolutePath());
	  wd = new ChromeDriver();
  }
	
  @Test()
  public void LoadWebpage() {
	  wd.get("http://localhost:4200/#/login");
	  new WebDriverWait(wd, 15).until(ExpectedConditions.urlContains("http://localhost:4200/#/login"));
  }
  
  @AfterSuite
	void quit() {
		wd.quit();
  }

}
