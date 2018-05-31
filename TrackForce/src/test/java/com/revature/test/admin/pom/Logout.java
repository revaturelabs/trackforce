package com.revature.test.admin.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.revature.test.utils.WaitToLoad;

public class Logout {
	static WebElement e = null;

	public static void logout(WebDriver d) {
		try {
			Thread.sleep(1000);
			WaitToLoad.findDynamicElement(d, By.cssSelector("[href='/login']"), 10);
			d.quit();
		} catch (Throwable e) {
			System.out.println("Failed to log out");
			e.printStackTrace();
		}
	}

}