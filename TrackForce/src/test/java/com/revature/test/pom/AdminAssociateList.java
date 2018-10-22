package com.revature.test.pom;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdminAssociateList {
	
	public static List<WebElement> elementList = new ArrayList<WebElement>();
	public static WebElement element;
	
	public static WebElement associate1(WebDriver wd) {
		element = wd.findElement(By.xpath("//*[@id=\"1\"]"));
		return element;
	}
	
	public static WebElement verificationMenu(WebDriver wd) {
		element = wd.findElement(By.id("uVerify"));
		return element;
	}
	
	public static WebElement statusMenu(WebDriver wd) {
		element = wd.findElement(By.id("uStatus"));
		return element;
	}
	
	public static WebElement clientMenu(WebDriver wd) {
		element = wd.findElement(By.id("uclient"));
		return element;
	}
	
	public static WebElement updateAssociateBTN(WebDriver wd) {
		element = wd.findElement(By.id("submit"));
		return element;
	}
}
