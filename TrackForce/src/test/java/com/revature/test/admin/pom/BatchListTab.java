package com.revature.test.admin.pom;

import java.util.ArrayList;
import java.util.List;

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
	
	public static List<WebElement> getBatchNames(WebDriver wd) {
		WebElement table_element = WaitToLoad.findDynamicElement(wd, By.xpath("//table[@class='table table-striped table-hover table-bordered']"), 30);
		List<WebElement> rows = table_element.findElements(By.xpath("//table[@class='table table-striped table-hover table-bordered']/tbody/tr"));
		return rows;
	}
	public static List<WebElement> getFirstBatchName(WebDriver wd) {
//		WebElement table_element = WaitToLoad.findDynamicElement(wd, By.xpath("//table[@class='table table-striped table-hover table-bordered']"), 30);
//		List<WebElement> rows = table_element.findElements(By.xpath("//table[@class='table table-striped table-hover table-bordered']/tbody/tr"));
//		return rows;
	}
	
}
