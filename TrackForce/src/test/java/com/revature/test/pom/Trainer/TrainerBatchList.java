package com.revature.test.pom.Trainer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TrainerBatchList {
	public WebElement getStartDateInput(WebDriver driver) {
		return driver.findElement(By.id("startDate"));
	}
	public WebElement getEndDateInput(WebDriver driver) {
		return driver.findElement(By.id("endDate"));
	}
}
