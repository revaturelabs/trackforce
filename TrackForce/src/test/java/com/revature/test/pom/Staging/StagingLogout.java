package com.revature.test.pom.Staging;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StagingLogout {
	private static WebDriverWait wait;
	
	/*private static void waitForLogoutMenuOpen(WebDriver driver) {
		try {
			wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout")));
		} catch (TimeoutException e) {
			e.printStackTrace();
			driver.quit();
		}
	}*/
	
	public static WebElement getLogoutButton(WebDriver driver) {
		//waitForLogoutMenuOpen(driver);
		return driver.findElement(By.id("logout"));
	}
	
	
}
