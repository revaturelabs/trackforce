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
<<<<<<< HEAD
		e=WaitToLoad.findDynamicElement(d, By.cssSelector("[href='/NGTrackForce/root']"),2);
=======
		e= WaitToLoad.findDynamicElement(d,By.cssSelector(prop.getProperty("homeTab")), 10);
>>>>>>> 7a0ff14ae4f77ffa07d3cce1e8151107b9ce164a
		return e;
	}
	
	public static String getCurrentURL(WebDriver d) {
		return d.getCurrentUrl();
	}
	
	public static WebElement phone(WebDriver d) {
		e= WaitToLoad.findDynamicElement(d,By.cssSelector(prop.getProperty("homePhone")), 10);
		return e;
	}
	
	public static WebElement email(WebDriver d) {
		e= WaitToLoad.findDynamicElement(d,By.cssSelector(prop.getProperty("homeEmail")), 10);
		
		return e;
	}
	
	public static WebElement website(WebDriver d) {
		e= WaitToLoad.findDynamicElement(d,By.cssSelector(prop.getProperty("homeWebsite")), 10);
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
		e = WaitToLoad.findDynamicElement(d,By.xpath(prop.getProperty("homePieChart")), 10);
		return e;
	}
	
	public static WebElement getChart(WebDriver d, String str) {
		List<WebElement> e = WaitToLoad.findDynamicElements(d, By.id(prop.getProperty("homePie")), 10);
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
		e= WaitToLoad.findDynamicElement(d,By.xpath("/html/body/app/div/app-client-mapped/div[1]/div/button[1]"), 10); 
		return e;
	}
		
	public static WebElement pieChart1(WebDriver d) {
		e= WaitToLoad.findDynamicElement(d,By.xpath("/html/body/app/div/app-client-mapped/div[1]/div/button[2]"), 10);  
		return e;
	}
	
	public static WebElement polarChart(WebDriver d) {
		e= WaitToLoad.findDynamicElement(d,By.xpath("/html/body/app/div/app-client-mapped/div[1]/div/button[3]"), 10);
		return e;
	}
	*/
	
}
