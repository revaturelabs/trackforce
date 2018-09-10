package com.revature.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdminNavbarElements {
	
	private static WebElement element;
	
	public static WebElement home(WebDriver wd) {
		element = wd.findElement(By.xpath("/html/body/app-component/app-navbar/nav/div/ul[2]/li[1]/a"));
		return element;
	}
	
	public static WebElement clientList(WebDriver wd) {
		element = wd.findElement(By.xpath("/html/body/app-component/app-navbar/nav/div/ul[2]/li[2]/a"));
		return element;
	}
	
	public static WebElement associateList(WebDriver wd) {
		element = wd.findElement(By.xpath("/html/body/app-component/app-navbar/nav/div/ul[2]/li[4]/a"));
		return element;
	}
	
	public static WebElement batchList(WebDriver wd) {
		element = wd.findElement(By.xpath("/html/body/app-component/app-navbar/nav/div/ul[2]/li[3]/a"));
		return element;
	}
	
	public static WebElement predictions(WebDriver wd) {
		element = wd.findElement(By.xpath("/html/body/app-component/app-navbar/nav/div/ul[2]/li[5]/a"));
		return element;
	}
	
	public static WebElement createUser(WebDriver wd) {
		element = wd.findElement(By.xpath("/html/body/app-component/app-navbar/nav/div/ul[2]/li[6]/a"));
		return element;
	}
	
	public static WebElement salesForce(WebDriver wd) {
		element = wd.findElement(By.xpath("/html/body/app-component/app-navbar/nav/div/ul[2]/li[7]/a"));
		return element;
	}
	
	public static WebElement logout(WebDriver wd) {
		element = wd.findElement(By.xpath("/html/body/app-component/app-navbar/nav/div/ul[1]/li[2]/button"));
		return element;
	}
	
	public static WebElement welcomeText(WebDriver wd) {
		element = wd.findElement(By.xpath("/html/body/app-component/app-navbar/nav/div/ul[1]/li[1]/span"));
		return element;
	}
	
	
}
