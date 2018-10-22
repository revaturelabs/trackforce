package com.revature.test.pom;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdminClientList {
	
	public static List<WebElement> elementList = new ArrayList<WebElement>();
	public static WebElement element;
	public static List<WebElement> clientList(WebDriver wd) {
		elementList = wd.findElements(By.xpath("//*[@id=\"clients-list\"]"));
		return elementList;
	}
	
	public static WebElement filter(WebDriver wd) {
		element = wd.findElement(By.id("clientSearch"));
		return element;
	}

}
