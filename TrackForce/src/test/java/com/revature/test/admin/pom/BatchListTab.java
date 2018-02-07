package com.revature.test.admin.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.revature.test.utils.WaitToLoad;

public class BatchListTab {
	static WebElement e = null;
	
	public static WebElement clickBatchListTab(WebDriver wd) {
			return WaitToLoad.findDynamicElement(wd,By.xpath("/html/body/app/div/app-root/div/app-navbar/nav/div/ul[1]/li[3]"), 10);
	}
	
	public static WebElement findAllBatchesHeader(WebDriver wd) {
			return WaitToLoad.findDynamicElement(wd,By.xpath("/html/body/app/div/app-batch-list/div/div[2]/div[1]/h3"), 10);
	}
}
