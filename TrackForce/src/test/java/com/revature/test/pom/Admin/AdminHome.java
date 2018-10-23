package com.revature.test.pom.Admin;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

//IMPORTANT: A lot of code for this POM already exists in the general POM Package.
public class AdminHome {

	private static List<WebElement> element;
	
	public static List<WebElement> getCharts(WebDriver driver) {	
		element = driver.findElements(By.id("pie"));
		return element;		
	}
}