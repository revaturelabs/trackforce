package com.revature.test.admin.pom;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
		return d.findElement(By.cssSelector(prop.getProperty("loginSignin")));
	}
	
	public static String getTitle(WebDriver d) {
		return d.findElement(By.xpath(prop.getProperty("loginTitle"))).getText();
	}
	
	public static void login(String username, String password,WebDriver d) {
		getUsername(d).sendKeys(username);
		getPassword(d).sendKeys(password);
	}
}
