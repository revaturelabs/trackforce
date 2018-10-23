package com.revature.test.pom.Staging;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StagingBatchList {
	
	private static List<WebElement> elementList;
	

	public static List<WebElement> getBatchListElements(WebDriver driver) {
		elementList = driver.findElements(By.cssSelector("tr[class = 'max-width ng-star-inserted']"));
		return elementList;
		
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
		WebElement element = driver.findElement(By.id("startDate"));
		return element;
	}
	
	public static WebElement getEndDateInput(WebDriver driver) {
		WebElement element = driver.findElement(By.id("endDate"));
		return element;
	}
	
	public static WebElement getPieChart(WebDriver driver) {	
		WebElement element = driver.findElement(By.id("pie"));
		return element;		
	}

}
