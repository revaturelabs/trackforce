package com.revature.test.pom.Sales;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SalesAssociateList {
	
	public static WebElement getFilterByText(WebDriver driver) {
		return driver.findElement(By.cssSelector("input[type='text'][name='filterByText']"));
	}
	
	public static WebElement getStatusSelect(WebDriver driver) {
		return driver.findElement(By.cssSelector("select[name='filterByStatus']"));
	}
	
	public static WebElement getStatusOption(WebDriver driver, String value) {
		return getStatusSelect(driver).findElement(By.cssSelector("option[value='" + value + "']"));
	}
	
	public static WebElement getClientSelect(WebDriver driver) {
		return driver.findElement(By.cssSelector("select[name='filterByClient']"));
	}
	
	public static WebElement getClientOptionByName(WebDriver driver, String clientName) {
		return getClientSelect(driver).findElement(By.cssSelector("option[innerText='" + clientName + "']"));
	}
	
	public static WebElement getClientOptionByValue(WebDriver driver, int clientValue) {
		return getClientSelect(driver).findElement(By.cssSelector("option[value='" + clientValue + "']"));
	}
	
	public static WebElement getClearFiltersButton(WebDriver driver) {
		return driver.findElement(By.cssSelector("button[type='button'][innerText='Clear Filters']"));
	}
	
	public static WebElement getFilterButton(WebDriver driver) {
		return driver.findElement(By.cssSelector("button[type='button'][innerText='Filter']"));
	}
	
	public static List<WebElement> getAssociateRows(WebDriver driver) {
		return driver.findElements(By.cssSelector("tr[class='ng-star-inserted']"));
	}
	
	public static WebElement getAssociateRow(WebDriver driver, int id) {
		String detailsHref = "/NGTrackForce/form-comp/"+id;
		for (WebElement row: getAssociateRows(driver)) {
			if (getAssociateDetails(driver, row).getAttribute("href").equals(detailsHref)) {
				return row;
			}
		}
		return null;
	}
	
	public static List<WebElement> getRowColumns(WebDriver driver, WebElement row) {
		return row.findElements(By.tagName("td"));
	}
	
	public static WebElement getFirstName(WebDriver driver, WebElement row) {
		return getRowColumns(driver, row).get(0);
	}
	
	public static WebElement getLastName(WebDriver driver, WebElement row) {
		return getRowColumns(driver, row).get(1);
	}
	
	public static WebElement getMarketingStatus(WebDriver driver, WebElement row) {
		return getRowColumns(driver, row).get(2);
	}
	
	public static WebElement getClientName(WebDriver driver, WebElement row) {
		return getRowColumns(driver, row).get(3);
	}
	
	public static WebElement getBatchName(WebDriver driver, WebElement row) {
		return getRowColumns(driver, row).get(4);
	}
	
	public static WebElement getVerificationStatus(WebDriver driver, WebElement row) {
		return getRowColumns(driver, row).get(5);
	}
	
	public static WebElement getAssociateDetails(WebDriver driver, WebElement row) {
		return getRowColumns(driver, row).get(6).findElement(By.tagName("a"));
	}
	
	public static WebElement getUpdateAssociatesButton(WebDriver driver) {
		return driver.findElement(By.cssSelector("button[type='button'][innerText='Update']"));
	}
	
}
