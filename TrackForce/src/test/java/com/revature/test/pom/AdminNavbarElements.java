package com.revature.test.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdminNavbarElements {
	
	private static WebElement element;
	
	public static WebElement home(WebDriver wd) {
		element = wd.findElement(By.id("home"));
		return element;
	}
	
	public static WebElement clientList(WebDriver wd) {
		element = wd.findElement(By.id("clients"));
		return element;
	}
	
	public static WebElement associateList(WebDriver wd) {
		element = wd.findElement(By.id("associates"));
		return element;
	}
	
	public static WebElement batchList(WebDriver wd) {
		element = wd.findElement(By.id("batches"));
		return element;
	}
	
	public static WebElement predictions(WebDriver wd) {
		element = wd.findElement(By.id("predictions"));
		return element;
	}
	
	public static WebElement createUser(WebDriver wd) {
		element = wd.findElement(By.id("create"));
		return element;
	}
	
	public static WebElement salesForce(WebDriver wd) {
		element = wd.findElement(By.id("salesforce"));
		return element;
	}
	
	public static WebElement logout(WebDriver wd) {
		element = wd.findElement(By.xpath("/html/body/app-component/app-navbar/nav/div/ul[1]/li[2]/button"));
		return element;
	}
	
	// old welcomeText test was using xpath, now using id
	public static WebElement welcomeText(WebDriver wd) {
		element = wd.findElement(By.id("navbarDropdown"));
		return element;
	}
	
	
}
