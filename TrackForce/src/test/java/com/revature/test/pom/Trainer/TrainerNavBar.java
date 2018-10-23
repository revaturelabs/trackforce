package com.revature.test.pom.Trainer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

//POM for trainer navbar
//Implemented 10/23/2018 - BSS

public class TrainerNavBar {
	
	//button to expand collapsed nav bar
	public WebElement getNavDrop(WebDriver driver) {
		return driver.findElement(By.id("collapsedNav"));
	}
	
	//home button
	public WebElement getHome(WebDriver driver) {
		return driver.findElement(By.id("home"));
	}
	
	//batch list button
	public WebElement getBatchList(WebDriver driver) {
		return driver.findElement(By.id("batches"));
	}
	
	//associate list button
	public WebElement getAssociateList(WebDriver driver) {
		return driver.findElement(By.id("associates"));
	}
	
	//button to cause logout option to drop down
	public WebElement getLogoutDrop(WebDriver driver) {
		return driver.findElement(By.id("navbarDropdown"));
	}
	
	//logout button
	public WebElement getLogoutBn(WebDriver driver) {
		return driver.findElement(By.id("logout"));
	}

}
