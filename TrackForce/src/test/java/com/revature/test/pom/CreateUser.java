package com.revature.test.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CreateUser {
	
	public static WebElement getUsername(WebDriver driver) {
		return driver.findElement(By.id("username"));
	}
	
	public static WebElement getPassword(WebDriver driver) {
		return driver.findElement(By.id("password"));
	}
	
	public static WebElement getConfirmPassword(WebDriver driver) {
		return driver.findElement(By.id("password2"));
	}
	
	public static WebElement getTrainerRoleRadio(WebDriver driver) {
		return driver.findElement(By.cssSelector("input[type='radio'][value='2']"));
	}
	
	public static WebElement getSalesRoleRadio(WebDriver driver) {
		return driver.findElement(By.cssSelector("input[type='radio'][value='3']"));
	}
	
	public static WebElement getStagingRadio(WebDriver driver) {
		return driver.findElement(By.cssSelector("input[type='radio'][value='4']"));
	}
	
	public static WebElement getAssociateRadio(WebDriver driver) {
		return driver.findElement(By.cssSelector("input[type='radio'][value='5']"));
	}
	
	public static WebElement getSubmitButton(WebDriver driver) {
		return driver.findElement(By.cssSelector("button[type='submit']"));
	}

}
