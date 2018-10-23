package com.revature.test.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//Written 10/23/18  - Josh
public class ClientList {
	
	public static WebElement getClientSearchBar(WebDriver driver) {
		return driver.findElement(By.id("clientSearch"));
	}
	
	public static List<WebElement> getAllClients(WebDriver driver){
		return driver.findElements(By.className("client-name"));
	}
	
	public static WebElement getFilter(WebDriver driver) {
		return driver.findElement(By.id("clientSearch"));
	}
}
