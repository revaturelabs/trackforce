package com.revature.test.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BatchList {

	public static List<WebElement> getBatchListElements(WebDriver driver) {
		return driver.findElements(By.cssSelector("tr[class = 'max-width ng-star-inserted']"));
	}
	
	public static WebElement getBatchName(WebDriver driver,WebElement row) {
		return row.findElements(By.tagName("td")).get(0);
	}
	
	public static WebElement getBatchStartDate(WebDriver driver,WebElement row) {
		return row.findElements(By.tagName("td")).get(1);
	}
	
	public static WebElement getBatchEndDate(WebDriver driver,WebElement row) {
		return row.findElements(By.tagName("td")).get(2);
	}
	
	public static WebElement getStartDateInput(WebDriver driver) {
		return driver.findElement(By.id("startDate"));
	}
	
	public static WebElement getEndDateInput(WebDriver driver) {
		return driver.findElement(By.id("endDate"));
	}
	
	public static WebElement getPieChart(WebDriver driver) {	
		return driver.findElement(By.id("pie"));
	}
	
	
}
