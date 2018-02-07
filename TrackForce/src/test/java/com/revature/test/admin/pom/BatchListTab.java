package com.revature.test.admin.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.revature.test.utils.WaitToLoad;
import com.revature.test.utils.WebDriverUtil;

public class BatchListTab {
	//public static WebDriver wd = WebDriverUtil.getChromeDriver();
	static WebElement e = null;
	
	public static void clickBatchListTab(WebDriver wd) {
			WaitToLoad.findDynamicElement(wd,By.xpath("/html/body/app/div/app-root/div/app-navbar/nav/div/ul[1]/li[3]"), 10).click();
	}
	
	public static boolean findAllBatchesHeader(WebDriver wd) {
		try {
			WaitToLoad.findDynamicElement(wd,By.xpath("/html/body/app/div/app-batch-list/div/div[2]/div[1]/h3"), 10);
			return true;
		} catch (Throwable e) {
			System.out.println("Failed to find All Batches header");
			return false;
		}
	}
}
