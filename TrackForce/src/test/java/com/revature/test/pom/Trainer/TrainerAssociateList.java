package com.revature.test.pom.Trainer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TrainerAssociateList {
	public WebElement getTextFilterBox(WebDriver driver) {
		return driver.findElement(By.name("filterByText"));
	}
	public WebElement getStatusDropdown(WebDriver driver) {
		return driver.findElement(By.name("filterByStatus"));
	}
	public WebElement getStatusByName(WebDriver driver, String name) {
		return getStatusDropdown(driver).findElement(By.linkText(name));
	}
	public WebElement getStatusById(WebDriver driver, int id) {
		return getStatusDropdown(driver).findElement(By.cssSelector("option:nth-child(" + id + ")"));
	}
	public WebElement getClientDropdown(WebDriver driver) {
		return driver.findElement(By.name("filterByClient"));
	}
	public WebElement getClientByName(WebDriver driver, String name) {
		return getClientDropdown(driver).findElement(By.linkText(name));
	}
	public WebElement getClientById(WebDriver driver, int id) {
		return getClientDropdown(driver).findElement(By.cssSelector("option:nth-child(" + id + ")"));
	}
	public WebElement getClearFiltersButton(WebDriver driver) {
		return driver.findElement(By.linkText("Clear Filters"));
	}
	public WebElement getFilterButton(WebDriver driver) {
		return driver.findElement(By.linkText("Filter"));
	}
}

