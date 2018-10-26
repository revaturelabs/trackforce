package com.revature.test.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

//POM for Trackforce trainer homepage
//Implemented 10/23/2018 - BSS

public class TrainerHome {
	
	//ID Label from table header
	public WebElement getIdLabel(WebDriver driver) {
		return driver.findElement(By.cssSelector("body > app-component > div > app-trainer-view > div > div > div.col-lg-5.col-md-12.mcolumn.left > table > thead > tr > th:nth-child(2)"));
	}
	
	//Trainer firstname label from first row of table
	public WebElement getFNLabel(WebDriver driver) {
		return driver.findElement(By.cssSelector("body > app-component > div > app-trainer-view > div > div > div.col-lg-5.col-md-12.mcolumn.left > table > tbody > tr:nth-child(1) > td:nth-child(2)"));
	}
	
	//Trainer lastname label from second row of table
	public WebElement getLNLabel(WebDriver driver) {
		return driver.findElement(By.cssSelector("body > app-component > div > app-trainer-view > div > div > div.col-lg-5.col-md-12.mcolumn.left > table > tbody > tr:nth-child(2) > td:nth-child(2)"));
	}
	
	//Trainer firstname input
	public WebElement getFNInput(WebDriver driver) {
		return driver.findElement(By.cssSelector("body > app-component > div > app-trainer-view > div > div > div.col-lg-7.col-md-12.mcolumn.right > div > form > fieldset > input:nth-child(2)"));
	}
	
	//Trainer lastname input
	public WebElement getLNInput(WebDriver driver) {
		return driver.findElement(By.cssSelector("body > app-component > div > app-trainer-view > div > div > div.col-lg-7.col-md-12.mcolumn.right > div > form > fieldset > input:nth-child(4)"));
	}
	
	//Submit button
	public WebElement getSubmitBn(WebDriver driver) {
		return driver.findElement(By.id("submitButton"));
	}
	
	//Reset button
	public WebElement getResetBn(WebDriver driver) {
		return driver.findElement(By.id("resetButton"));
	}

}
