package com.revature.test.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.revature.utils.DriverUtil;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class ServiceHooks {
	public static WebDriver driver;
	
	@Before("@HomeTagTest1")
	public void initializeHomeTabTests(){
		System.out.println("Initializing HomeTab Tests");
		driver = WebDriverUtil.getChromeDriver();
		driver.get(TestConfig.getBaseURL());
		LoginUtil.loginAsAdmin(driver);
	}
	@After("@HomeTagTest6")
	public void closeHomeTagTests(){
		driver.quit();
	}
	
	@Before("@ClientListStart")
	public void initializeClientListTests(){
		System.out.println("Initializing HomeTab Tests");
		driver = WebDriverUtil.getChromeDriver();
		driver.get(TestConfig.getBaseURL());
		LoginUtil.loginAsAdmin(driver);
	}
	
	@After("@ClientListEnd")
	public void closeClientListTests(){
		driver.quit();
	}
	
	@Before("@AssociateListStart")
	public void initializeAssociateTabTests(){
		System.out.println("Initializing HomeTab Tests");
		driver = WebDriverUtil.getChromeDriver();
		driver.get(TestConfig.getBaseURL());
		LoginUtil.loginAsAdmin(driver);
	}
	
	@After("@AssociateListEnd")
	public void closeAssociateListTests(){
		driver.quit();
	}
	
	@Before("@CreateNewAdmin")
	public void initializeCreateUserTabTests(){
		System.out.println("Initializing CreateUser Tests");
		driver = WebDriverUtil.getChromeDriver();
		driver.get(TestConfig.getBaseURL());
		LoginUtil.loginAsAdmin(driver);
	}
	@After("@CreateNewVP")
	public void closeCreateUserTagTests(){
		driver.quit();
	}
}
