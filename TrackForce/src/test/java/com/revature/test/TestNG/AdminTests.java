package com.revature.test.TestNG;

import java.io.File;
import com.revature.pom.*;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

public class AdminTests {

	static WebDriver wd;
	String username = "TestAdmin";
	String password = "TestAdmin";
	
	@Test(priority = 0)
	void launchApplication() {
		File chrome = new File("src/main/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", chrome.getAbsolutePath());
		wd = new ChromeDriver();
		wd.get("http://34.227.178.103:8090/NGTrackForce/");
		new WebDriverWait(wd, 15).until(ExpectedConditions.urlContains("http://34.227.178.103:8090/NGTrackForce/"));

	}
	
	@Test(priority = 1, dependsOnMethods = "launchApplication")
	void adminCanLogin() {
		new WebDriverWait(wd, 15).until(ExpectedConditions.elementToBeClickable(LoginPage.username(wd)));
		LoginPage.username(wd).sendKeys(username);
		LoginPage.password(wd).sendKeys(password);
		new WebDriverWait(wd, 15).until(ExpectedConditions.elementToBeClickable(LoginPage.signIn(wd)));
		LoginPage.signIn(wd).click();
		new WebDriverWait(wd, 15).until(ExpectedConditions.urlContains("NGTrackForce/#/app-home"));
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
	
	@Test(priority = 3, dependsOnMethods = "chartsLoadWithin10Seconds")
	void random() {
		
		System.out.println(NavbarElements.batchList(wd).getText());
	}	
	
	@AfterSuite
	void quit() {
		wd.quit();
	}

}
