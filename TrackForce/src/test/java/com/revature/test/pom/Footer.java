package com.revature.test.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Footer {

	public static WebElement getRevatureLogo(WebDriver driver) {
		return driver.findElement(By.cssSelector("image[class='img-responsive footer-img']"));
	}
	
	public static WebElement getPhoneNumber(WebDriver driver) {
		return driver.findElement(By.cssSelector("a[href='tel:703 995 4500']"));
	}
	
	public static WebElement getFaxNumber(WebDriver driver) {
		return driver.findElement(By.cssSelector("a[href='tel:(703) 507-8181']"));
	}
	
	public static WebElement getEmail(WebDriver driver) {
		return driver.findElement(By.cssSelector("a[href='info@revature.com']"));
	}
}
