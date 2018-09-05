package com.revature.test.admin.pom;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * All the locations of the elements are specified in the tests.properties file
 * in src/test/resources. Any additional elements should be specified in there and 
 * referenced with prop.getProperty("element")
 * @author Jesse (reviewer)
 * @since 6.18.06.07
 */
public class Login {
	public static WebDriver wd = null;
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

	public static WebElement getUsername(WebDriver d) {
		return d.findElement(By.id(prop.getProperty("loginUsername")));
	}

	public static WebElement getPassword(WebDriver d) {
		return d.findElement(By.id(prop.getProperty("loginPassword")));
	}	

	public static WebElement getSignin(WebDriver d) {
		return d.findElement(By.className(prop.getProperty("loginSignin")));
	}
	
	public static String getTitle(WebDriver d) {
		return d.findElement(By.xpath(prop.getProperty("loginTitle"))).getText();
	}
	
	public static void login(String username, String password,WebDriver d) {
		getUsername(d).sendKeys(username);
		getPassword(d).sendKeys(password);
	}
	
	public static void clickRegisterButton(WebDriver d) {
		d.findElement(By.xpath(prop.getProperty("loginRegister"))).click();
	}
	
	public static void clickNextButton(WebDriver d) {
		d.findElement(By.xpath(prop.getProperty("registerNext"))).click();
	}
	
	public static void clickRegisterNewButton(WebDriver d) {
		d.findElement(By.xpath(prop.getProperty("registerNew"))).click();
	}
	
	public static void inputRegisterUsername(WebDriver d,String username) {
		d.findElement(By.id(prop.getProperty("registrationUsername"))).sendKeys(username);
	}
	
	public static void inputRegisterPassword(WebDriver d,String password) {
		d.findElement(By.id(prop.getProperty("registrationPassword"))).sendKeys(password);
	}
	
	public static void confirmPassword(WebDriver d,String password) {
		d.findElement(By.id(prop.getProperty("registrationPasswordConfirm"))).sendKeys(password);
	}
	
	public static void inputRegisterFirstName(WebDriver d,String name) {
		d.findElement(By.xpath(prop.getProperty("registrationFName"))).sendKeys(name);
	}
	
	public static void inputRegisteLastName(WebDriver d,String name) {
		d.findElement(By.xpath(prop.getProperty("registrationLName"))).sendKeys(name);
	}
	
	public static void getPopup(WebDriver d) {
		d.findElement(By.xpath(prop.getProperty("registerPopup")));
	}
	
	public static void selectAssociateRole(WebDriver d) {
		Select dropDown = new Select(d.findElement(By.xpath(prop.getProperty("registrationRole"))));
		for(WebElement el : dropDown.getOptions()) {
			if(el.getText().equals("Associate"))
				dropDown.selectByVisibleText(el.getText());
		}
		
	}

	public static void selectTrainerRole(WebDriver d) {
		Select dropDown = new Select(d.findElement(By.xpath(prop.getProperty("registrationRole"))));
		for(WebElement el : dropDown.getOptions()) {
			if(el.getText().equals("Trainer"))
				dropDown.selectByVisibleText(el.getText());
		}
		
}
}
