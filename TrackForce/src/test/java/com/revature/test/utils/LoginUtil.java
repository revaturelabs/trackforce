package com.revature.test.utils;

import org.openqa.selenium.WebDriver;

import com.revature.test.admin.pom.Login;

public class LoginUtil {
	private static String adminUsername = "TestAdmin";
	private static String adminPassword = "TestAdmin";
	
	public static void loginAsAdmin(WebDriver wd) {
		try {
			Login.user(wd).sendKeys(adminUsername);
			Thread.sleep(1000);
			Login.password(wd).sendKeys(adminPassword);
			Thread.sleep(1000);
			Login.signin(wd).click();	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
