package com.revature.test.admin.pom;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.revature.test.utils.WaitToLoad;

public class Logout {
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
	
	public static void logout(WebDriver d) {
		try {
			Thread.sleep(1000);
			WaitToLoad.findDynamicElement(d, By.cssSelector(prop.getProperty("logout")), 2);
			d.quit();
		} catch (Throwable e) {
			System.out.println("Failed to log out");
			e.printStackTrace();
		}
	}

}