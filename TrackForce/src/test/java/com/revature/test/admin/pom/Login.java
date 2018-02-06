package com.revature.test.admin.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.revature.test.utils.WaitToLoad;
import com.revature.utils.DriverUtil;

public class Login {
	public static WebDriver wd = DriverUtil.getChromeDriver();
	static WebElement e = null;

	public static WebElement user(WebDriver d) {
		e = WaitToLoad.findDynamicElement(wd, By.name("username"), 10);
		return e;
	}

	public static WebElement password(WebDriver d) {
		e = WaitToLoad.findDynamicElement(wd, By.name("password"), 10);
		return e;
	}

	public static WebElement signin(WebDriver d) {
		e = WaitToLoad.findDynamicElement(wd, By.xpath("//*[@id=\"pwd-container\"]/div[2]/section/form/button"), 10);
		return e;
	}
}
