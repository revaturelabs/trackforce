package com.revature.test.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

//Written 10/23/18 - Josh
//Assumes the correct page is already currently accessed by the driver param
//This page refers to the login page, the home screen of the application
public class Home {

	private static WebDriverWait wait;
	
	//Used in AdminTests.java 
	public static List<WebElement> getGraphs(WebDriver driver) {
		return driver.findElements(By.id("pie"));
	}
	
	public static WebElement getPhone(WebDriver driver) {
		return driver.findElement(By.id("phoneNumber"));
	}
	
	public static WebElement getFax(WebDriver driver) {
		return driver.findElement(By.id("faxNumber"));
	}
	
	public static WebElement getEmail(WebDriver driver) {
		return driver.findElement(By.linkText("info@revature.com"));
	}
	
	public static WebElement getWebsite(WebDriver driver) {
		return driver.findElement(By.className("footer-img"));
	}
}
