package com.revature.pageObjectModel;

import static com.revature.tester.MethodUtil.waitForLoadByAnyType;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	
	public static void goToAssignForce(WebDriver wd) {
		wd.get("http://dev.assignforce.revaturelabs.com/");
	}
	
	public static WebElement getUsernameInput(WebDriver wd) {
		return wd.findElement(By.id("username"));
	}
	
	public static WebElement getPasswordInput(WebDriver wd) {
		return wd.findElement(By.id("password"));
	}
	
	public static WebElement getLoginBtn(WebDriver wd) {
		return wd.findElement(By.id("Login"));
	}
	
	public static void loginAs(WebDriver wd,String username,String password) {
		wd.manage().window().maximize();
		getUsernameInput(wd).sendKeys(username);
		getPasswordInput(wd).sendKeys(password);
		getLoginBtn(wd).click();
	}
	
	public static WebElement getLogout(WebDriver wd) {
		return waitForLoadByAnyType(wd, By.xpath("(//button)[1]"));
	}
	
	public static void logout(WebDriver wd) {
		wd.findElement(By.xpath("/html/body/div/div[1]/ng-include/div/md-content/md-nav-bar/div/nav/ul/li[9]/button")).click();
	}
}