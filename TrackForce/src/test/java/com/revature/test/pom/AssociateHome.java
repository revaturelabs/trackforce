package com.revature.test.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AssociateHome {
	private static WebElement element;
	
	public static WebElement navBarDropDown(WebDriver wd) {
		element = wd.findElement(By.id("navbarDropdown"));
		return element;
		
	}
	
	public static WebElement logout(WebDriver wd) {
		element = wd.findElement(By.id("logout"));
		return element;
		
	}

}
