package com.revature.test.pom.Staging;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//Written 10/23/18 - Josh
public class StagingHome {

	private static WebDriverWait wait;
	
	/*//Checks for "Home" label at top of page - Josh
	public static void waitForHomePageOpen(WebDriver driver) {
		try {
			wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body > app-component > div > app-home > div > div:nth-child(1) > div > h3")));
		} catch (TimeoutException e) {
			e.printStackTrace();
			driver.quit();
		}
		
	} 
	
	//If you click on any part of any pie graph (Only unmapped is functional.), 
	//then another graph will appear further dividing the section you clicked on into 
	//what they were trained for. - Josh
	public static void waitForSkillset(WebDriver driver) {
		try {
			wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("skillset")));
		} catch (TimeoutException e) {
			e.printStackTrace();
			driver.quit();
		}
	}*/
	
	public static List<WebElement> getGraphs(WebDriver driver) {
		//waitForHomePageOpen(driver);
		return driver.findElements(By.id("pie"));
	}
	
	public static WebElement getSkillset(WebDriver driver) {
		//waitForSkillset(driver);
		return driver.findElement(By.id("skillset"));
	}
}
