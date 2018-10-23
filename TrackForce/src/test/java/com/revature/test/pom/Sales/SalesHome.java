package com.revature.test.pom.Sales;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SalesHome {
	
	private static List<WebElement> elementList;
	
	public static List<WebElement> getAllPieCharts(WebDriver driver) {	
		return driver.findElements(By.id("pie"));
	}
	
	public static WebElement getUndeployedPieChart(WebDriver driver) {
		return getAllPieCharts(driver).get(0);
	}
	
	public static WebElement getDeployedPieChart(WebDriver driver) {
		return getAllPieCharts(driver).get(1);
	}
	
	public static WebElement getMappedPieChart(WebDriver driver) {
		return getAllPieCharts(driver).get(2);
	}
	
	public static WebElement getUnmappedPieChart(WebDriver driver) {
		return getAllPieCharts(driver).get(3);
	}
	
	public static WebElement getClosingX(WebDriver driver) {
		WebElement element = driver.findElement(By.cssSelector("body > app-component > div > app-home > div > div:nth-child(1) > div > div > button > span"));
		return element;
	}

}
