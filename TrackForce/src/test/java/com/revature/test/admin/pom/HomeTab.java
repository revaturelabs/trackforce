package com.revature.test.admin.pom;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.revature.test.utils.WaitToLoad;

public class HomeTab {
	
	static WebElement e =null;
	private static Properties prop = new Properties();
	static {
		InputStream locProps = Login.class.getClassLoader()
				.getResourceAsStream("tests.properties");
		try {
			prop.load(locProps);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static WebElement clickHomeTab(WebDriver d) {
		e= WaitToLoad.findDynamicElement(d,By.cssSelector(prop.getProperty("homeTab")), 2);
		return e;
	}
	
	public static WebElement phone(WebDriver d) {
		e= WaitToLoad.findDynamicElement(d,By.xpath(prop.getProperty("homePhone")), 2);
		return e;
	}
	
	public static WebElement email(WebDriver d) {
		e= WaitToLoad.findDynamicElement(d,By.xpath(prop.getProperty("homeEmail")), 2);
		
		return e;
	}
	
	public static WebElement website(WebDriver d) {
		e= WaitToLoad.findDynamicElement(d,By.xpath(prop.getProperty("homeWebsite")), 2);
		return e;
	}
	
	
	public static WebElement populateDatabase(WebDriver d) {
		e = d.findElement(By.xpath(prop.getProperty("homePopDatabase")));
		return e;
	}
	
	
	public static WebElement populateStaticSalesforce(WebDriver d) {
		e = d.findElement(By.xpath(prop.getProperty("homePopStaticSalesforce")));
		return e;
	}
	
	public static WebElement emptyDatabase(WebDriver d) {
		e = d.findElement(By.xpath(prop.getProperty("homeEmptyDatabase")));
		return e;
	}
	
	public static WebElement pieChart(WebDriver d) {
		e = WaitToLoad.findDynamicElement(d,By.xpath(prop.getProperty("homePieChart")), 2);
		return e;
	}
	
	public static WebElement getChart(WebDriver d, String str) {
		List<WebElement> e = WaitToLoad.findDynamicElements(d, By.id(prop.getProperty("homePie")), 2);
		if(str.equals(prop.getProperty("homeMapvUnmapNoDep"))) {
			return e.get(0);
		}
		else if(str.equals(prop.getProperty("homeMapvUnmapDep"))) {
			return e.get(1);
		}
		else if(str.equals(prop.getProperty("homeMapped"))) {
			return e.get(2);
		}
		else if(str.equals(prop.getProperty("homeUnmapped"))) {
			return e.get(3);
		}
		return null;
	}
	/*
	public static WebElement barChart(WebDriver d) {
		e= WaitToLoad.findDynamicElement(d,By.xpath("/html/body/app/div/app-client-mapped/div[1]/div/button[1]"), 2); 
		return e;
	}
		
	public static WebElement pieChart1(WebDriver d) {
		e= WaitToLoad.findDynamicElement(d,By.xpath("/html/body/app/div/app-client-mapped/div[1]/div/button[2]"), 2);  
		return e;
	}
	
	public static WebElement polarChart(WebDriver d) {
		e= WaitToLoad.findDynamicElement(d,By.xpath("/html/body/app/div/app-client-mapped/div[1]/div/button[3]"), 2);
		return e;
	}
	*/
	
}
