package com.revature.test.utils;

import org.openqa.selenium.WebDriver;

import com.revature.test.admin.pom.Login;

public class LoginUtil {
	private static String adminUsername = "TestAdmin";
	private static String adminPassword = "TestAdmin";
	
	public static void loginAsAdmin(WebDriver wd) {
		try {
			Login.getUsername(wd).sendKeys(adminUsername);
			Thread.sleep(1000);
			Login.getPassword(wd).sendKeys(adminPassword);
			Thread.sleep(1000);
			Login.getSignin(wd).click();	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
