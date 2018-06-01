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
		if(driver==null) {
			System.out.println("Initializing HomeTab Tests");
			driver = WebDriverUtil.getChromeDriver();
			driver.get(TestConfig.getBaseURL());
			LoginUtil.loginAsAdmin(driver);
			}
	}
	@After("@HomeTagTest6")
	public void quitHomeTagTests(){
		driver.quit();
		driver=null;
	}
	
	@Before("@ClientListStart")
	public void initializeClientListTests(){
		if(driver==null) {
			System.out.println("Initializing HomeTab Tests");
			driver = WebDriverUtil.getChromeDriver();
			driver.get(TestConfig.getBaseURL());
			LoginUtil.loginAsAdmin(driver);
			}
	}
	
	@After("@ClientListEnd")
	public void quitClientListTests(){
		driver.quit();
		driver=null;
	}
	
	@Before("@AssociateListStart")
	public void initializeAssociateTabTests(){
		if(driver==null) {
			System.out.println("Initializing HomeTab Tests");
			driver = WebDriverUtil.getChromeDriver();
			driver.get(TestConfig.getBaseURL());
			LoginUtil.loginAsAdmin(driver);
			}
	}
	
	@After("@AssociateListEnd")
	public void quitAssociateListTests(){
		driver.quit();
		driver=null;
	}
	
	@Before("@CreateNewAdmin")
	public void initializeCreateUserTabTests(){
		if(driver==null) {
		System.out.println("Initializing HomeTab Tests");
		driver = WebDriverUtil.getChromeDriver();
		driver.get(TestConfig.getBaseURL());
		LoginUtil.loginAsAdmin(driver);
		}
	}
	@After("@CreateNewAssociate")
	public void quitCreateUserTagTests(){
		driver.quit();
		driver=null;
	}
}
