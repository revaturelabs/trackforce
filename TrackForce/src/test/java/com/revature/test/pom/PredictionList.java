package com.revature.test.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//Written 10/23/18 - Josh
public class PredictionList {
	
	public static WebElement getJTATextBox(WebDriver driver) {
		return driver.findElement(By.id("JTA-input"));
	}
	
	public static WebElement getJavaTextBox(WebDriver driver) {
		return driver.findElement(By.id("Java-input"));
	}
	
	public static WebElement getDotNetTextBox(WebDriver driver) {
		return driver.findElement(By.id(".Net-input"));
	}
	
	public static WebElement getPEGATextBox(WebDriver driver) {
		return driver.findElement(By.id("PEGA-input"));
	}
	
	public static WebElement getDynamicCRMTextBox(WebDriver driver) {
		return driver.findElement(By.id("DynamicCRM-input"));
	}
	
	public static WebElement getSalesforceTextBox(WebDriver driver) {
		return driver.findElement(By.id("Salesforce-input"));
	}
	
	public static WebElement getMicroservicesTextBox(WebDriver driver) {
		return driver.findElement(By.id("Microservices-input"));
	}
	
	public static WebElement getSEEDTextBox(WebDriver driver) {
		return driver.findElement(By.id("SEED-input"));
	}
	
	public static WebElement getOracleFusionTextBox(WebDriver driver) {
		return driver.findElement(By.id("Oracle Fusion-input"));
	}

	public static WebElement getStartDateTextBox (WebDriver driver) {
		return driver.findElement(By.id("startDate"));
	}
	
	public static WebElement getEndDateTextBox (WebDriver driver) {
		return driver.findElement(By.id("endDate"));
	}
}
