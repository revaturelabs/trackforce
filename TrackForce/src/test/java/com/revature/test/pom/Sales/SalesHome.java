package com.revature.test.pom.Sales;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SalesHome {
	
	private static List<WebElement> elementList;
	
	public static List<WebElement> getPieCharts(WebDriver driver) {	
		elementList = driver.findElements(By.id("pie"));
		return elementList;		
	}
	
	public static WebElement getUndeployedPieChart(WebDriver driver) {
		elementList = driver.findElements(By.id("pie"));
		return elementList.get(0);
	}
	
	public static WebElement getDeployedPieChart(WebDriver driver) {
		elementList = driver.findElements(By.id("pie"));
		return elementList.get(1);
	}
	
	public static WebElement getMappedPieChart(WebDriver driver) {
		elementList = driver.findElements(By.id("pie"));
		return elementList.get(2);
	}
	
	public static WebElement getUnmappedPieChart(WebDriver driver) {
		elementList = driver.findElements(By.id("pie"));
		return elementList.get(3);
	}
	
	public static WebElement getClosingX(WebDriver driver) {
		WebElement element = driver.findElement(By.cssSelector("body > app-component > div > app-home > div > div:nth-child(1) > div > div > button > span"));
		return element;
	}

}
