package com.revature.test.pom.Staging;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//Written 10/23/18  - Josh
public class StagingClientList {

	private static WebDriverWait wait;
	
	/*//Checks for "Clients" label on left of page - Josh
	public static void waitForClientListOpen(WebDriver driver) {
		try {
			wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body > app-component > div > app-client-list > div > div > h3")));
		} catch (TimeoutException e) {
			e.printStackTrace();
			driver.quit();
		}
	}*/
	
	public static WebElement getClientSearchBar(WebDriver driver) {
		return driver.findElement(By.id("clientSearch"));
	}
	
	public static List<WebElement> getAllClients(WebDriver driver){
		return driver.findElements(By.className("client-name"));
	}
}
