package com.revature.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	
	private static WebElement element;
	
	public static WebElement username(WebDriver wd) {
		element = wd.findElement(By.id("username"));
		return element;
	}
	
	public static WebElement password(WebDriver wd) {
		element = wd.findElement(By.id("password"));
		return element;
	}
	
	public static WebElement signIn(WebDriver wd) {	
		element = wd.findElement(By.xpath("//button[@type='submit']"));
		return element;		
	}
	
	public static WebElement invalidLogIn(WebDriver wd) {
		element = wd.findElement(By.xpath("//*[@id=\"pwd-container\"]/div/section/form/div/div[1]"));
		return element;
	}
	
}
