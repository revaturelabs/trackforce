package com.revature.test.admin.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Login {
	public static WebDriver wd = null;

	public static WebElement getUsername(WebDriver d) {
		return d.findElement(By.id("username"));
	}

	public static WebElement getPassword(WebDriver d) {
		return d.findElement(By.id("password"));
	}	

	public static WebElement getSignin(WebDriver d) {
		return d.findElement(By.xpath("//button[@type='submit']"));
	}
	
	public static String getTitle(WebDriver d) {
		return d.findElement(By.xpath("//title")).getText();
	}
	
}
