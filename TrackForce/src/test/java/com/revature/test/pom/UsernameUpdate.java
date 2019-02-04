package com.revature.test.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/*
 * This class is written to retrieve all of the page's fields and elements from the 
 *  username-update 
 * page. The driver should already be on that URL before using any of these methods, 
 * otherwise an error will occur. 
 * @Michael Tinning, Batch 1811
 */
public class UsernameUpdate {
	public static WebElement getNewUsername(WebDriver driver) {
		return driver.findElement(By.name("newUsername"));
	}
	public static WebElement getPassword(WebDriver driver) {
		return driver.findElement(By.name("password"));
	}
	public static WebElement getUpdateButton(WebDriver driver) {
		return driver.findElement(By.id("submitButton"));
	}
}
