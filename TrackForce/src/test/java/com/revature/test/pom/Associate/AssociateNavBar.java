package com.revature.test.pom.Associate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

//POM for associate nav bar
//Implemented 10/23/2018 - BSS

public class AssociateNavBar {
	
		//button to expand collapsed nav bar
		public WebElement getNavDrop(WebDriver driver) {
			return driver.findElement(By.id("collapsedNav"));
		}
		
		//home button
		public WebElement getHome(WebDriver driver) {
			return driver.findElement(By.id("home"));
		}
		
		//my interviews button
		public WebElement getInterviews(WebDriver driver) {
			return driver.findElement(By.id("interviews"));
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
