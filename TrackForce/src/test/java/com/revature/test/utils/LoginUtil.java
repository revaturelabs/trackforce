package com.revature.test.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.revature.test.pom.Login;

public class LoginUtil {
	private static Properties prop = new Properties();
	private static WebDriverWait wait = new WebDriverWait(ServiceHooks.driver, 20);

	static {
		InputStream locProps = Login.class.getClassLoader()
				.getResourceAsStream("tests.properties");
		try {
			prop.load(locProps);
			
			locProps.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
	}
	/*
	 * NOTE: username and password are still in the properties file - still insecure but should
	 * be acceptable for testing purposes
	 */
	private static String adminUsername = prop.getProperty("adminUN");
	private static String adminPassword = prop.getProperty("adminPW");

	private static String trainerUsername = prop.getProperty("trainerUN");
	private static String trainerPassword = prop.getProperty("trainerPW");

	private static String salesUsername = prop.getProperty("salesDeliveryUN");
	private static String salesPassword = prop.getProperty("salesDeliveryPW");

	private static String stagingUsername = prop.getProperty("stagingManagerUN");
	private static String stagingPassword = prop.getProperty("stagingManagerPW");

	private static String associateUsername = prop.getProperty("associateUN");
	private static String associatePassword = prop.getProperty("associatePW");
	
	public static void login(WebDriver wd, String user, String pass) {
		try {
			wait.until(ExpectedConditions.visibilityOf(Login.getUsername(wd)));
			Login.getUsername(wd).sendKeys(user);
			wait.until(ExpectedConditions.visibilityOf(Login.getPassword(wd)));
			Login.getPassword(wd).sendKeys(pass);
			Login.getSignInButton(wd).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void loginAsAdmin(WebDriver wd) {
		login(wd, adminUsername, adminPassword);
	}

	public static void loginAsTrainer(WebDriver wd) {
		login(wd, trainerUsername, trainerPassword);
	}

	public static void loginAsSalesDelivery(WebDriver wd) {
		login(wd, salesUsername, salesPassword);
	}

	public static void loginAsStaging(WebDriver wd) {
		login(wd, stagingUsername, stagingPassword);
	}

	public static void loginAsAssociate(WebDriver wd) {
		login(wd, associateUsername, associatePassword);
	}
	
	
	public static String getPropertyValue(String key) {
		String value = prop.getProperty(key);
		return value;
	}
}
