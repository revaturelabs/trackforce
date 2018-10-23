package com.revature.test.pom.Trainer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TrainerHome {
	
	public WebElement getIdLabel(WebDriver driver) {
		return driver.findElement(By.cssSelector("body > app-component > div > app-trainer-view > div > div > div.col-lg-5.col-md-12.mcolumn.left > table > thead > tr > th:nth-child(2)"));
	}
	
	public WebElement getFNLabel(WebDriver driver) {
		return driver.findElement(By.cssSelector("body > app-component > div > app-trainer-view > div > div > div.col-lg-5.col-md-12.mcolumn.left > table > tbody > tr:nth-child(1) > td:nth-child(2)"));
	}
	
	public WebElement getLNLabel(WebDriver driver) {
		return driver.findElement(By.cssSelector("body > app-component > div > app-trainer-view > div > div > div.col-lg-5.col-md-12.mcolumn.left > table > tbody > tr:nth-child(2) > td:nth-child(2)"));
	}
	
	public WebElement getFNInput(WebDriver driver) {
		return driver.findElement(By.cssSelector("body > app-component > div > app-trainer-view > div > div > div.col-lg-7.col-md-12.mcolumn.right > div > form > fieldset > input:nth-child(2)"));
	}
	
	public WebElement getLNInput(WebDriver driver) {
		return driver.findElement(By.cssSelector("body > app-component > div > app-trainer-view > div > div > div.col-lg-7.col-md-12.mcolumn.right > div > form > fieldset > input:nth-child(4)"));
	}
	
	public WebElement getSubmitBn(WebDriver driver) {
		return driver.findElement(By.id("submitButton"));
	}
	
	public WebElement getResetBn(WebDriver driver) {
		return driver.findElement(By.id("resetButton"));
	}

}
