package com.revature.pom;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdminCreateUser {
	
	public static List<WebElement> elementList = new ArrayList<WebElement>();
	public static WebElement element;
	
	public static WebElement usernameInput(WebDriver wd) {
		element = wd.findElement(By.id("username"));
		return element;
	}
	
	public static WebElement passwordInput(WebDriver wd) {
		element = wd.findElement(By.id("password"));
		return element;
	}
	
	public static WebElement confirmPasswordInput(WebDriver wd) {
		element = wd.findElement(By.id("password2"));
		return element;
	}
	
	public static WebElement selectAssociate(WebDriver wd) {
		element = wd.findElement(By.xpath("//*[@id=\"NewUserForm\"]/div/input[4]"));
		return element;
	}
	public static WebElement submitAssociateBTN(WebDriver wd) {
		element = wd.findElement(By.xpath("//*[@id=\"NewUserForm\"]/input[4]"));
		return element;
	}

	
}
