package com.revature.test.admin.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.revature.test.utils.WaitToLoad;
import com.revature.utils.DriverUtil;

public class CreateUserTab {
	public static WebDriver wd = DriverUtil.getChromeDriver();
	static WebElement e = null;
	
	public static boolean clickCreateUserTab(WebDriver d) {
		try {
			e = WaitToLoad.findDynamicElement(wd, By.xpath("/html/body/app/div/app-root/div/app-navbar/nav/div/ul[1]/li[5]/a"), 10);
			e.click();
			return true;
		}
		catch(Throwable e){
			return false;
		}
	}
}
