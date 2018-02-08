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
	
	public static WebElement getFirstBatchName(WebDriver wd) {
		WebElement table_element = WaitToLoad.findDynamicElement(wd, By.xpath("//table[@class='table table-striped table-hover table-bordered']"), 30);
		WebElement row = table_element.findElement(By.xpath("//table[@class='table table-striped table-hover table-bordered']/tbody/tr[1]/td[1]"));
		return row;
	}
	
	public static List<WebElement> getAssociatesIDs(WebDriver wd) {
		WebElement table_element = WaitToLoad.findDynamicElement(wd, By.xpath("//table[@class='table table-striped table-hover table-bordered']"), 30);
		List<WebElement> rows = table_element.findElements(By.xpath("//table[@class='table table-striped table-hover table-bordered']/tbody/tr"));
		List<WebElement> ID = new ArrayList<WebElement>();
		for (WebElement e : rows) {
			ID.addAll(e.findElements(By.xpath("td[1]")));
		}
		return ID;
	}
	
//	public static List<WebElement> getAssociateLastNames(WebDriver wd) {
//		WebElement table_element = WaitToLoad.findDynamicElement(wd, By.xpath("//table[@class='table table-striped table-hover table-bordered']"), 30);
//		List<WebElement> rows = table_element.findElements(By.xpath("//table[@class='table table-striped table-hover table-bordered']/tbody/tr"));
//		List<WebElement> lastNames = new ArrayList<WebElement>();
//		for (WebElement e : rows) {
//			lastNames.addAll(e.findElements(By.xpath("td[3]")));
//		}
//		return lastNames;
//	}
//	
//	public static List<WebElement> matchFirstNames(WebDriver wd) {
//		WebElement table_element = WaitToLoad.findDynamicElement(wd, By.xpath("//table[@class='table table-bordered']"), 30);
//		List<WebElement> rows = table_element.findElements(By.xpath("//table[@class='table table-bordered']/tbody/tr"));
//		List<WebElement> fNames = new ArrayList<WebElement>();
//		for (WebElement e : rows) {
//			fNames.addAll(e.findElements(By.xpath("td[3]")));
//		}
//		return fNames;
//	}
	
	public static List<WebElement> grabAssociates(WebDriver wd) {
//		WebElement table_element = WaitToLoad.findDynamicElement(wd, By.xpath("//table[@class='table table-bordered']"), 30);
//		List<WebElement> rows = table_element.findElements(By.xpath("//table[@class='table table-bordered']/tbody/tr"));
		WebElement table_element = WaitToLoad.findDynamicElement(wd, By.xpath("//table[@class='table table-striped table-hover table-bordered']"), 30);
		List<WebElement> rows = table_element.findElements(By.xpath("//table[@class='table table-striped table-hover table-bordered']/tbody/tr"));
//		List<WebElement> id = new ArrayList<WebElement>();
//		for (WebElement e : rows) {
//			id.addAll(e.findElements(By.xpath("td[2]")));
//		}
		return rows;
	}
	
	
}
