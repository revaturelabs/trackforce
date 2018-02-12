package com.revature.test.admin.pom;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.revature.test.utils.WaitToLoad;

public class BatchListTab {
	static WebElement e = null;

	public static WebElement clickBatchListTab(WebDriver wd) {
		// return
		// WaitToLoad.findDynamicElement(wd,By.xpath("/html/body/app/div/app-root/div/app-navbar/nav/div/ul[1]/li[3]"),
		// 10);
		return WaitToLoad.findDynamicElement(wd, By.cssSelector("[href='/batch-listing']"), 10);
	}

	public static WebElement clickAssociateListTab(WebDriver wd) {
		// return
		// WaitToLoad.findDynamicElement(wd,By.xpath("/html/body/app/div/app-root/div/app-navbar/nav/div/ul[1]/li[3]"),
		// 10);
		return WaitToLoad.findDynamicElement(wd, By.cssSelector("[href='/associate-listing']"), 10);
	}

	public static String getCurrentURL(WebDriver d) {

		return d.getCurrentUrl();

	}

	public static WebElement findAllBatchesHeader(WebDriver wd) {
		// Local Host XPath
		// return
		// WaitToLoad.findDynamicElement(wd,By.xpath("/html/body/app/div/app-batch-list/div/div[2]/div[1]/h3"),
		// 10);
		// Static Website XPath
		return WaitToLoad.findDynamicElement(wd, By.xpath("/html/body/app/app-batch-list/div/div[2]/div[1]/h3"), 10);

	}

	public static List<WebElement> getBatchNames(WebDriver wd) {
		WebElement table_element = WaitToLoad.findDynamicElement(wd,
				By.xpath("//table[@class='table table-striped table-hover table-bordered']"), 30);
		List<WebElement> rows = table_element
				.findElements(By.xpath("//table[@class='table table-striped table-hover table-bordered']/tbody/tr"));
		return rows;
	}

	// Gets first Batch Link in Batch List Tab
	public static WebElement getFirstBatchName(WebDriver wd) {
		// Local Host
		// WebElement table_element = WaitToLoad.findDynamicElement(wd,
		// By.xpath("//table[@class='table table-striped table-hover table-bordered']"),
		// 30);
		// WebElement row =
		// table_element.findElement(By.xpath("/html/body/app/div/app-batch-list/div/div[2]/div[1]/table/tbody/tr[1]/td[1]/a"));

		// Static Website
		WebElement table_element = WaitToLoad.findDynamicElement(wd,
				By.xpath("//table[@class='table table-striped table-hover table-bordered']"), 30);
		WebElement row = table_element
				.findElement(By.xpath("/html/body/app/app-batch-list/div/div[2]/div[1]/table/tbody/tr[1]/td[1]/a"));
		return row;
	}

	// Gets all Associate IDs under the first Batch List Tab
	public static List<WebElement> getAssociatesIDs(WebDriver wd) {
		WebElement table_element = WaitToLoad.findDynamicElement(wd,
				By.xpath("//table[@class='table table-striped table-hover table-bordered']"), 30);
		List<WebElement> rows = table_element
				.findElements(By.xpath("//table[@class='table table-striped table-hover table-bordered']/tbody/tr"));
		List<WebElement> ID = new ArrayList<WebElement>();
		for (WebElement e : rows) {
			ID.addAll(e.findElements(By.xpath("td[1]")));
		}
		return ID;
	}

	// Gets every Associate ID from ASSOCIATE LIST TAB
	public static List<WebElement> grabAssociatesIDs(WebDriver wd) {
		WebElement table_element = WaitToLoad.findDynamicElement(wd,
				By.xpath("//table[@class='table table-striped table-hover table-bordered']"), 30);
		List<WebElement> rows = table_element
				.findElements(By.xpath("//table[@class='table table-striped table-hover table-bordered']/tbody/tr"));
		List<WebElement> id = new ArrayList<WebElement>();
		for (WebElement e : rows) {
			id.addAll(e.findElements(By.xpath("td[2]")));
		}
		return id;
	}

	// Gets every Batch Name from ASSOCIATE LIST TAB
	public static List<WebElement> grabBatchNames(WebDriver wd) {
		WebElement table_element = WaitToLoad.findDynamicElement(wd,
				By.xpath("//table[@class='table table-striped table-hover table-bordered']"), 30);
		List<WebElement> rows = table_element
				.findElements(By.xpath("//table[@class='table table-striped table-hover table-bordered']/tbody/tr"));
		List<WebElement> batchNames = new ArrayList<WebElement>();
		for (WebElement e : rows) {
			batchNames.addAll(e.findElements(By.xpath("td[7]")));
		}
		return batchNames;
	}

	// Gets EVERY ROW from ASSOCIATE LIST TAB
	public static List<WebElement> grabAssociatesBatchInfo(WebDriver wd) {
		WebElement table_element = WaitToLoad.findDynamicElement(wd,
				By.xpath("//table[@class='table table-striped table-hover table-bordered']"), 30);
		List<WebElement> rows = table_element
				.findElements(By.xpath("//table[@class='table table-striped table-hover table-bordered']/tbody/tr"));
		return rows;
	}

	public static WebElement clickFromDateArrow(WebDriver wd) {
		return WaitToLoad.findDynamicElement(wd, By.id("startDate"), 10);
	}

	public static WebElement clickToDateArrow(WebDriver wd) {
		return WaitToLoad.findDynamicElement(wd, By.id("endDate"), 10);
	}
	
	public static WebElement clickSubmit(WebDriver wd) {
		return WaitToLoad.findDynamicElement(wd, By.xpath("/html/body/app/app-batch-list/div/div[2]/div[2]/form/div[3]/input[1]"), 10);
	}
}
