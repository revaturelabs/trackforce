package com.revature.test.pom;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.revature.test.utils.WaitToLoad;

/**
 * All the locations of the elements are specified in the tests.properties file
 * in src/test/resources. Any additional elements should be specified in there and 
 * referenced with prop.getProperty("element")
 * @author Jesse (reviewer)
 * @since 6.18.06.07
 */
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
		return WaitToLoad.findDynamicElement(d, By.cssSelector(prop.getProperty("createUserStagingRadio")), 2);
	}
	
	public static WebElement getDelivaryRadio(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.cssSelector(prop.getProperty("createUserDeliveryRadio")), 10);
	}
	
	public static WebElement getTrainerRadio(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.cssSelector(prop.getProperty("createUserTrainerRadio")), 10);
	}
	
	public static WebElement getAssociateRadio(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.cssSelector(prop.getProperty("createUserAssociateRadio")), 2);
	}
	
	public static WebElement getSubmit(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.xpath(prop.getProperty("createUserSubmit")), 2);
	}
	
	public static WebElement getPopup(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.xpath(prop.getProperty("createUserPopup")), 10);
	}

	/**
	 * selects the a given role checkbox during the creation of a user
	 * 
	 * @param d the driver currently being used
	 * @param role the role to be selected
	 * 
	 * @author Daniel Lani
	 * 
	 * @since 6.06.15.18
	 */
	public static void selectRole(WebDriver d, String role) {
		WebElement el = null;
	    switch(role){
	    case "delivery": el = CreateUserTab.getDelivaryRadio(d);
	    case "trainer": el = CreateUserTab.getDelivaryRadio(d);
	    case "manager": el = CreateUserTab.getDelivaryRadio(d);
	    case "associate": el = CreateUserTab.getDelivaryRadio(d);
	    case "admin": el = CreateUserTab.getDelivaryRadio(d);
	    }
	    WaitToLoad.waitForClickable(d, el, 5);
	    el.click();
	}
}