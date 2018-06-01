package com.revature.test.admin.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.revature.test.utils.WaitToLoad;

public class CreateUserTab {
	static WebElement e = null;

	public static WebElement getCreateUserTab(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.cssSelector("[href='/NGTrackForce/create-user']"), 1);
	}
	
	public static WebElement getCreateNewUserHeader(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.xpath("/html/body/app/div/app-create-user/div/div[2]/h3"), 1);
	}

	public static String getCurrentURL(WebDriver d) {
		return d.getCurrentUrl();
	}

	public static WebElement getTabHeader(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.xpath("/html/body/app/app-create-user/div/div[1]/h3"), 1);
	}
	
	public static WebElement getUsername(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.cssSelector("[name='username']"), 1);
	}
	
	public static WebElement getPassword(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.cssSelector("[name='password']"), 1);
	}

	public static WebElement getPasswordConfirm(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.cssSelector("[name='password2']"), 1);
	}

	public static WebElement getAdminRadio(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.cssSelector("[value='1']"), 1);
	}
	
	public static WebElement getTrainerRadio(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.cssSelector("[value='2']"), 1);
	}
	
	public static WebElement getDelivaryRadio(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.cssSelector("[value='3']"), 1);
	}

	public static WebElement getManagerRadio(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.cssSelector("[value='4']"), 1);
	}
	
	public static WebElement getAssociateRadio(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.cssSelector("[value='5']"), 1);
	}
	
	public static WebElement getSubmit(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.xpath("/html/body/app/app-create-user/div/div[2]/form/input[4]"), 1);
	}
	
	public static WebElement getPopup(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.className("alert"), 1);
	}

}