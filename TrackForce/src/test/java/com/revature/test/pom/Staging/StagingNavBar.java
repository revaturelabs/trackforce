package com.revature.test.pom.Staging;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StagingNavBar {
	
	public static WebElement getRevatureLogo(WebDriver driver) {
		return driver.findElement(By.id("logo"));
	}
	
	public static WebElement getHome(WebDriver driver) {
		return driver.findElement(By.id("home"));
	}
	
	public static WebElement getClientList(WebDriver driver) {
		return driver.findElement(By.id("clients"));
	}
	
	public static WebElement getBatchList(WebDriver driver) {
		return driver.findElement(By.id("batches"));
	}
	
	public static WebElement getAssociateList(WebDriver driver) {
		return driver.findElement(By.id("associates"));
	}
	
	public static WebElement getPredictionList(WebDriver driver) {
		return driver.findElement(By.id("predictions"));
	}
	
	public static WebElement getCreateUser(WebDriver driver) {
		return driver.findElement(By.id("create"));
	}
	
	public static WebElement getWelcome(WebDriver driver) {
		return driver.findElement(By.id("navbarDropdown"));
	}
	
	public static WebElement getLogout(WebDriver driver) {
		return driver.findElement(By.id("logout"));
	}
	
	public static WebElement getMobileNavToggler(WebDriver driver) {
		return driver.findElement(By.cssSelector("span[class='navbar-toggler-icon']"));
	}
}
