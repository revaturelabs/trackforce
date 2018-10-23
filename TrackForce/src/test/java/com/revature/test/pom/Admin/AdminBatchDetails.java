package com.revature.test.pom.Admin;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdminBatchDetails {
	
	private static List<WebElement> elementList;
	
	public static WebElement getBarGraph(WebDriver driver) {	
		WebElement element = driver.findElement(By.id("bar"));
		return element;		
	}
	
	public static List<WebElement> getAssociateList(WebDriver driver) {
		elementList = driver.findElements(By.tagName("tr"));
		return elementList;
	}

}
