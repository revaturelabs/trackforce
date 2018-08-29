package com.revature.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NavbarElements {
	
	private static WebElement element;
	
	public static WebElement batchList(WebDriver wd) {
		element = wd.findElement(By.cssSelector("//a[@ng-reflect-router-link='/batch-listing']"));
		return element;
	}
	
}
