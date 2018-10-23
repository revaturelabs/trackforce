package com.revature.test.pom.Admin;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdminCreateUser {

	public static List<WebElement> elementList = new ArrayList<WebElement>();
	public static WebElement element;

	public static WebElement getUsernameInput(WebDriver driver) {
		element = driver.findElement(By.id("username"));
		return element;
	}

	public static WebElement getPasswordInput(WebDriver driver) {
		element = driver.findElement(By.id("password"));
		return element;
	}

	public static WebElement getConfirmPasswordInput(WebDriver driver) {
		element = driver.findElement(By.id("password2"));
		return element;
	}

	public static WebElement getSelectAssociate(WebDriver driver) {
		element = driver.findElement(By.cssSelector("body > app-component > div > app-create-user > form > fieldset:nth-child(3) > div:nth-child(6) > input"));
		return element;
	}

	public static WebElement getSubmitAssociateBtn(WebDriver driver) {
		element = driver.findElement(By.cssSelector("button[class='btn btn-default']"));
		return element;
	}
	
}
