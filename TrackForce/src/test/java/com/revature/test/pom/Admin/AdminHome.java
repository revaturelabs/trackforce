package com.revature.test.pom.Admin;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdminHome {

	public static List<WebElement> getCharts(WebDriver driver) {
		return driver.findElements(By.id("pie"));
	}

}