package com.revature.test.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// Implicit wait util method
// Accepts webdriver as first param, the selector method, and the maximum time it will wait until it times out
public class WaitToLoad {
	public static WebElement findDynamicElement(WebDriver wd, By by, int timeOut) {
	    WebDriverWait wait = new WebDriverWait(wd, timeOut);
	    WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	    return element;
	}
}
