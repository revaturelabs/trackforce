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
		element = wd.findElement(By.cssSelector("#pwd-container > div > section > form > div > div:nth-child(4) > button:nth-child(1)"));
		return element;		
	}
	
	public static WebElement register(WebDriver wd) {
		element = wd.findElement(By.cssSelector("#pwd-container > div > section > form > div > div:nth-child(4) > button:nth-child(2)"));
		return element;
	}
	
	public static WebElement cpassword(WebDriver wd) {
		element = wd.findElement(By.id("cpassword"));
		return element;
	}
	
	public static WebElement fname(WebDriver wd) {
		element = wd.findElement(By.id("fname"));
		return element;
	}
	
	public static WebElement lname(WebDriver wd) {
		element = wd.findElement(By.id("lname"));
		return element;
	}
	
	public static WebElement selectRole(WebDriver wd) {
		element = wd.findElement(By.cssSelector("<select _ngcontent-c3=\"\" class=\"form-control ng-trigger ng-trigger-enterAnimation ng-pristine ng-invalid ng-touched\" id=\"chosenRole\" name=\"chosenRole\" required=\"\" type=\"number\" ng-reflect-required=\"\" ng-reflect-name=\"chosenRole\"><option _ngcontent-c3=\"\" class=\"ng-tns-c3-0\" value=\"0: undefined\">Select Role...</option><option _ngcontent-c3=\"\" class=\"ng-tns-c3-0\" value=\"1: 5\" ng-reflect-ng-value=\"5\">Associate</option><option _ngcontent-c3=\"\" class=\"ng-tns-c3-0\" value=\"2: 2\" ng-reflect-ng-value=\"2\">Trainer</option></select>"));
		return element;
	}
	
	public static WebElement selectAssociateRole(WebDriver wd) {
		element = wd.findElement(By.cssSelector("#chosenRole > option:nth-child(1)"));
		return element;
	}
	
	public static WebElement selectTrainerRole(WebDriver wd) {
		element = wd.findElement(By.cssSelector("#chosenRole > option:nth-child(2)"));
		return element;
	}

	public static WebElement registerNewUser(WebDriver wd) {
		element = wd.findElement(By.cssSelector("#pwd-container > div > section > form > div > button:nth-child(4)"));
		return element;
	}
	
	public static WebElement cancel(WebDriver wd) {
		element = wd.findElement(By.cssSelector("#pwd-container > div > section > form > div > button:nth-child(5)"));
		return element;
	}

}
