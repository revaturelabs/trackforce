package com.revature.test.admin.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.revature.test.utils.WaitToLoad;
import com.revature.utils.DriverUtil;

public class Login {
	public static WebDriver wd = null;
	static WebElement e = null;

	public static WebElement user(WebDriver d) {
		e = d.findElement(By.id("username"));
		return e;
	}

	public static WebElement password(WebDriver d) {
		e = d.findElement(By.id("password"));
		return e;
	}

	public static WebElement signin(WebDriver d) {
		e = d.findElement(By.xpath("//button[@type='submit']"));
		return e;
	}
}
