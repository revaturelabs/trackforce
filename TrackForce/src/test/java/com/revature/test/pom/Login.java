package com.revature.test.pom;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * All the locations of the elements are specified in the tests.properties file
 * in src/test/resources. Any additional elements should be specified in there and 
 * referenced with prop.getProperty("element")
 * @author Jesse (reviewer)
 * @since 6.18.06.07
 */
public class Login {

	public static WebElement getTitle(WebDriver driver) {
		return driver.findElement(By.tagName("title"));
	}

	public static WebElement getUsername(WebDriver driver) {
		return driver.findElement(By.id("username"));
	}
	
	public static WebElement getPassword(WebDriver driver) {
		return driver.findElement(By.id("password"));
	}

	public static WebElement getSignInButton(WebDriver driver) {
		return driver.findElement(By.cssSelector("button[type='submit']"));
	}
	
	public static WebElement getRegisterButton(WebDriver driver) {
		return driver.findElement(By.cssSelector("button[type='submit'][innerText='Register']"));
	}
	
	public static WebElement getConfirmPassword(WebDriver driver) {
		return driver.findElement(By.id("cpassword"));
	}
	
	public static WebElement getFirstName(WebDriver driver) {
		return driver.findElement(By.id("fname"));
	}
	
	public static WebElement getLastName(WebDriver driver) {
		return driver.findElement(By.id("lname"));
	}
	
	public static WebElement getSelectRole(WebDriver driver) {
		return driver.findElement(By.id("chosenRole"));
	}
	
	public static WebElement getAssociateRole(WebDriver driver) {
		return getSelectRole(driver).findElement(By.cssSelector("option[value='1: 5']"));
	}
	
	public static WebElement getTrainerRole(WebDriver driver) {
		return getSelectRole(driver).findElement(By.cssSelector("option[value='2: 2']"));
	}
	
	public static WebElement getRegisterNewUserButton(WebDriver driver) {
		return driver.findElement(By.cssSelector("button[type='submit'][innerText='Register New User']"));

	}



}
