package com.revature.test.admin.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.revature.test.utils.WaitToLoad;
import com.revature.test.utils.WebDriverUtil;

public class CreateUserTab {
	//public static WebDriver wd = WebDriverUtil.getChromeDriver();
	static WebElement e = null;

	public static boolean clickCreateUserTab(WebDriver d) {
		try {
			//Thread.sleep(5000);
			e = WaitToLoad.findDynamicElement(d,
					By.xpath("/html/body/app/div/app-root/div/app-navbar/nav/div/ul[1]/li[5]"), 10);
			e.click();
			System.out.println("Found Create Tab element!");
			return true;
		} catch (Throwable e) {
			System.out.println("Failed to find/click Create User Tab");
			return false;
		}
	}

	public static boolean onCreateUserTab(WebDriver d) {
		try {
			//Thread.sleep(2000);
			e = WaitToLoad.findDynamicElement(d, By.xpath("/html/body/app/div/app-create-user/div/div[2]/h3"), 10);
			if (e.getText().contains("Create New User")) {
				return true;
			}
			return false;
		} catch (Throwable e) {
			System.out.println("Failed to find 'Create New User' element");
			return false;
		}
	}

}
