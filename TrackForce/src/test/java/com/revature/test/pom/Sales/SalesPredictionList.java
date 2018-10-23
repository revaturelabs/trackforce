package com.revature.test.pom.Sales;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SalesPredictionList {
	
	public  WebElement getJTATextBox(WebDriver driver) {
		return driver.findElement(By.id("JTA-input"));
	}
	
	public  WebElement getJavaTextBox(WebDriver driver) {
		return driver.findElement(By.id("Java-input"));
	}
	
	public  WebElement getDotNetTextBox(WebDriver driver) {
		return driver.findElement(By.id(".Net-input"));
	}
	
	public  WebElement getPEGATextBox(WebDriver driver) {
		return driver.findElement(By.id("PEGA-input"));
	}
	
	public  WebElement getDynamicCRMTextBox(WebDriver driver) {
		return driver.findElement(By.id("DynamicCRM-input"));
	}
	
	public  WebElement getSalesforceTextBox(WebDriver driver) {
		return driver.findElement(By.id("Salesforce-input"));
	}
	
	public  WebElement getMicroservicesTextBox(WebDriver driver) {
		return driver.findElement(By.id("Microservices-input"));
	}
	
	public  WebElement getSEEDTextBox(WebDriver driver) {
		return driver.findElement(By.id("SEED-input"));
	}
	
	public  WebElement getOracleFusionTextBox(WebDriver driver) {
		return driver.findElement(By.id("Oracle Fusion-input"));
	}

	public  WebElement getStartDateTextBox (WebDriver driver) {
		return driver.findElement(By.id("startDate"));
	}
	
	public  WebElement getEndDateTextBox (WebDriver driver) {
		return driver.findElement(By.id("endDate"));
	}

}
