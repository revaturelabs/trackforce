package com.revature.test.admin.pom;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


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
		return d.findElement(By.cssSelector(prop.getProperty("loginSignin")));
	}
	
	public static String getTitle(WebDriver d) {
		return d.findElement(By.xpath(prop.getProperty("loginTitle"))).getText();
	}
	
}
