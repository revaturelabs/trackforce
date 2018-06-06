package com.revature.test.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.revature.test.admin.pom.Login;

public class LoginUtil {
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
	/*
	 * NOTE: username and password are still in the properties file - still insecure but should
	 * be acceptable for testing purposes
	 */
	private static String adminUsername = prop.getProperty("adminUN");
	private static String adminPassword = prop.getProperty("adminPW");

	//TODO: Login Utils for different roles
	
	public static void loginAsAdmin(WebDriver wd) {
		try {
			Login.getUsername(wd).sendKeys(adminUsername);
			Thread.sleep(1000);
			Login.getPassword(wd).sendKeys(adminPassword);
			wd.findElement(By.tagName("button")).click();;
			//Login.getSignin(wd).click();	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
