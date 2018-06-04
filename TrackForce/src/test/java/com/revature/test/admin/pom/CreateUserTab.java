package com.revature.test.admin.pom;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.revature.test.utils.WaitToLoad;

public class CreateUserTab {
	static WebElement e = null;
	private static Properties prop = new Properties();
	static {
		InputStream locProps = Login.class.getClassLoader()
				.getResourceAsStream("tests.properties");
		try {
			prop.load(locProps);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static WebElement getCreateUserTab(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.cssSelector(prop.getProperty("createUserTab")), 2);
	}
	
	public static WebElement getCreateNewUserHeader(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.xpath(prop.getProperty("createNewUserHeader")), 2);
	}

	public static String getCurrentURL(WebDriver d) {
		return d.getCurrentUrl();
	}

	public static WebElement getTabHeader(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.xpath(prop.getProperty("createUserTabHeader")), 2);
	}
	
	public static WebElement getUsername(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.cssSelector(prop.getProperty("createUserUsername")), 2);
	}
	
	public static WebElement getPassword(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.cssSelector(prop.getProperty("createUserPassword")), 2);
	}

	public static WebElement getPasswordConfirm(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.cssSelector(prop.getProperty("createUserPasswordConfirm")), 2);
	}

	public static WebElement getAdminRadio(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.cssSelector(prop.getProperty("createUserAdminRadio")), 2);
	}
	
	public static WebElement getManagerRadio(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.cssSelector(prop.getProperty("createUserManagerRadio")), 2);
	}
	
<<<<<<< HEAD
	public static WebElement getDelivaryRadio(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.cssSelector(prop.getProperty("createUserVPRadio")), 10);
=======
	public static WebElement getVPRadio(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.cssSelector(prop.getProperty("createUserVPRadio")), 2);
>>>>>>> cb49ca481ec07ab41c005ee90ea88914e2a64696
	}
	
	public static WebElement getTrainerRadio(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.cssSelector(prop.getProperty("createUserTrainerRadio")), 10);
	}
	
	public static WebElement getAssociateRadio(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.cssSelector(prop.getProperty("createUserAssociateRadio")), 2);
	}
	
	public static WebElement getSubmit(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.cssSelector(prop.getProperty("createUserSubmit")), 2);
	}
	
	public static WebElement getPopup(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.cssSelector(prop.getProperty("createUserPopup")), 10);
	}

}