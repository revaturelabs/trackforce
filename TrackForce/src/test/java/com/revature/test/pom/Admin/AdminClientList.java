package com.revature.test.pom.Admin;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdminClientList {

	public static List<WebElement> elementList = new ArrayList<WebElement>();
	public static WebElement element;

	public static List<WebElement> getClientList(WebDriver driver) {
		elementList = driver.findElements(By.id("clients-list"));
		return elementList;
	}

	public static WebElement getFilter(WebDriver driver) {
		element = driver.findElement(By.id("clientSearch"));
		return element;
	}

}