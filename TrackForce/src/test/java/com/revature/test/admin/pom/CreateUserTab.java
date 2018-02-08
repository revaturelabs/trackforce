package com.revature.test.admin.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.revature.test.utils.WaitToLoad;

public class CreateUserTab {

	static WebElement e = null;

	// find CreateUser tab *from Home page*
	public static WebElement getCreateUserTab(WebDriver d) {
		return WaitToLoad.findDynamicElement(d,
				By.xpath("/html/body/app/div/app-root/div/app-navbar/nav/div/ul[1]/li[5]"), 10);
	}

	// find "Create New User" header
	public static WebElement getCreateNewUserHeader(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.xpath("/html/body/app/div/app-create-user/div/div[2]/h3"), 10);
	}
	
	

}