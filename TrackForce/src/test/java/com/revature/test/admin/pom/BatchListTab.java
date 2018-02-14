package com.revature.test.admin.pom;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.revature.test.utils.WaitToLoad;

import cucumber.api.java.en.Then;

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
	
	// Gets all rows under the first batch in Batch List Tab
	public static List<WebElement> getAssociatesInfo(WebDriver wd) {
		// Commented out lines are for  Local Host
		//WebElement table_element = WaitToLoad.findDynamicElement(wd, By.xpath("//table[@class='table table-striped table-hover table-bordered']"), 30);
		WebElement table_element = WaitToLoad.findDynamicElement(wd, By.xpath("/html/body/app/app-batch-details/div/div/div[2]/table"), 30);
		//List<WebElement> rows = table_element.findElements(By.xpath("//table[@class='table table-striped table-hover table-bordered']/tbody/tr[1]"));
		List<WebElement> rows = table_element.findElements(By.xpath("/html/body/app/app-batch-details/div/div/div[2]/table/tbody/tr"));
		return rows;
	}
	
	// Gets all Associate IDs under the first batch in Batch List Tab
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

		WebElement table_element = WaitToLoad.findDynamicElement(wd, By.xpath("/html/body/app/app-associate-list/div/div[2]/div/table"), 30);
		//WebElement table_element = WaitToLoad.findDynamicElement(wd, By.xpath("//table[@class='table table-striped table-hover table-bordered']"), 30);
		//List<WebElement> rows = table_element.findElements(By.xpath("//table[@class='table table-striped table-hover table-bordered']/tbody/tr"));
		List<WebElement> rows = table_element.findElements(By.xpath("/html/body/app/app-associate-list/div/div[2]/div/table/tbody/tr"));
		return rows;
	}

	public static WebElement clickFromDateArrow(WebDriver wd) {
		return WaitToLoad.findDynamicElement(wd, By.xpath("//*[@id=\"startDate\"]"), 10);
		
	}

	public static WebElement clickToDateArrow(WebDriver wd) {
		return WaitToLoad.findDynamicElement(wd, By.id("endDate"), 10);
	}
	
	public static WebElement clickSubmit(WebDriver wd) {
		return WaitToLoad.findDynamicElement(wd, By.xpath("/html/body/app/app-batch-list/div/div[2]/div[2]/form/div[3]/input[1]"), 10);
	}
	
	public static WebElement clickReset(WebDriver wd) {
		return WaitToLoad.findDynamicElement(wd, By.xpath("/html/body/app/app-batch-list/div/div[2]/div[2]/form/div[3]/input[2]"), 10);
	}
	
	public static List<WebElement> getStartDates(WebDriver wd) {
		WebElement table_element = WaitToLoad.findDynamicElement(wd, By.xpath("/html/body/app/app-batch-list/div/div[2]/div[1]/table"), 30);
        List<WebElement> rows=table_element.findElements(By.xpath("/html/body/app/app-batch-list/div/div[2]/div[1]/table/tbody/tr"));
        List<WebElement> columns = new ArrayList<WebElement>();
        
		for (WebElement e : rows) {
			columns.addAll(e.findElements(By.xpath("td[2]")));
		}
		return columns;
	}
	
	public static List<WebElement> getEndDates(WebDriver wd) {
		WebElement table_element = WaitToLoad.findDynamicElement(wd, By.xpath("/html/body/app/app-batch-list/div/div[2]/div[1]/table"), 30);
        List<WebElement> rows=table_element.findElements(By.xpath("/html/body/app/app-batch-list/div/div[2]/div[1]/table/tbody/tr"));
        List<WebElement> columns = new ArrayList<WebElement>();
        
		for (WebElement e : rows) {
			columns.addAll(e.findElements(By.xpath("td[3]")));
		}
		return columns;
	}

	public static boolean correctResults(List<WebElement> Nfrom, List<WebElement> Nto, WebDriver wd)	throws Throwable {
		boolean done = true;
		List<String> start = new ArrayList<String>();
		for (WebElement col : Nfrom) {
			start.add(col.getText());
		}
		start.removeAll(Arrays.asList("", null));
		List<String> end = new ArrayList<String>();
		for (WebElement col : Nto) {
			end.add(col.getText());
		}
		end.removeAll(Arrays.asList("", null));

		SimpleDateFormat format=new SimpleDateFormat("mm. dd, yyyy");  
		Date date = new Date();
		System.out.println(format.format(date));
		
		for (int i=0; i < start.size(); i++) {
			try {
				Date startDate=new SimpleDateFormat("mm. dd, yyyy").parse(start.get(i));
				Date endDate=new SimpleDateFormat("mm. dd, yyyy").parse(end.get(i));
				Date fromDate = new SimpleDateFormat("mm. dd, yyyy").parse("9/15/2017");
				Date toDate = new SimpleDateFormat("mm. dd, yyyy").parse("11/15/2017");

				if(startDate.after(toDate) || endDate.before(fromDate)) {
				    done = false;
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return done;
	}
}
