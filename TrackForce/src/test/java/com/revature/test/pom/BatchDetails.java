package com.revature.test.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BatchDetails {
	
	
	public static WebElement getBarGraph(WebDriver driver) {	
		return driver.findElement(By.id("bar"));
	}
	
	public static List<WebElement> getAssociateList(WebDriver driver) {
		return driver.findElements(By.tagName("tr"));
	}

}
