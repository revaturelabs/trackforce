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
<<<<<<< HEAD
		return WaitToLoad.findDynamicElement(d, By.cssSelector("[href='/NGTrackForce/create-user']"), 1);
	}
	
	public static WebElement getCreateNewUserHeader(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.xpath("/html/body/app/div/app-create-user/div/div[2]/h3"), 1);
=======
		return WaitToLoad.findDynamicElement(d, By.cssSelector(prop.getProperty("createUserTab")), 10);
	}
	
	public static WebElement getCreateNewUserHeader(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.xpath(prop.getProperty("createNewUserHeader")), 10);
>>>>>>> 7a0ff14ae4f77ffa07d3cce1e8151107b9ce164a
	}

	public static String getCurrentURL(WebDriver d) {
		return d.getCurrentUrl();
	}

	public static WebElement getTabHeader(WebDriver d) {
<<<<<<< HEAD
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
=======
		return WaitToLoad.findDynamicElement(d, By.xpath(prop.getProperty("createUserTabHeader")), 10);
	}
	
	public static WebElement getUsername(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.cssSelector(prop.getProperty("createUserUsername")), 10);
	}
	
	public static WebElement getPassword(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.cssSelector(prop.getProperty("createUserPassword")), 10);
	}

	public static WebElement getPasswordConfirm(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.cssSelector(prop.getProperty("createUserPasswordConfirm")), 10);
	}

	public static WebElement getAdminRadio(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.cssSelector(prop.getProperty("createUserAdminRadio")), 10);
	}
	
	public static WebElement getManagerRadio(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.cssSelector(prop.getProperty("createUserManagerRadio")), 10);
	}
	
	public static WebElement getVPRadio(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.cssSelector(prop.getProperty("createUserVPRadio")), 10);
>>>>>>> 7a0ff14ae4f77ffa07d3cce1e8151107b9ce164a
	}

	public static WebElement getManagerRadio(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.cssSelector("[value='4']"), 1);
	}
	
	public static WebElement getAssociateRadio(WebDriver d) {
<<<<<<< HEAD
		return WaitToLoad.findDynamicElement(d, By.cssSelector("[value='5']"), 1);
	}
	
	public static WebElement getSubmit(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.xpath("/html/body/app/app-create-user/div/div[2]/form/input[4]"), 1);
	}
	
	public static WebElement getPopup(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.className("alert"), 1);
=======
		return WaitToLoad.findDynamicElement(d, By.cssSelector(prop.getProperty("createUserAssociateRadio")), 10);
	}
	
	public static WebElement getSubmit(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.cssSelector(prop.getProperty("createUserSubmit")), 10);
>>>>>>> 7a0ff14ae4f77ffa07d3cce1e8151107b9ce164a
	}

}