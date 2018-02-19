package com.revature.test.admin.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.revature.test.utils.WaitToLoad;

public class HomeTab {
	
static WebElement e =null;
	
	public static WebElement clickHomeTab(WebDriver d) {
		e= WaitToLoad.findDynamicElement(d,By.xpath("/html/body/app/app-root/div/app-navbar/nav/div/ul[1]/li[2]/a"), 10);
		return e;
	}
	
	public static WebElement phone(WebDriver d) {
		e= WaitToLoad.findDynamicElement(d,By.xpath("/html/body/app/app-root/div/app-footer/footer/div/div/div[2]/ul/li[2]/a"), 10);
		return e;
	}
	
	public static WebElement email(WebDriver d) {
		e= WaitToLoad.findDynamicElement(d,By.xpath("/html/body/app/app-root/div/app-footer/footer/div/div/div[3]/ul/li[1]/a"), 10);
		
		return e;
	}
	
	public static WebElement website(WebDriver d) {
		e= WaitToLoad.findDynamicElement(d,By.xpath("/html/body/app/app-root/div/app-footer/footer/div/div/div[3]/ul/li[2]/a"), 10);
		return e;
	}
	
	
	public static WebElement populateDatabase(WebDriver d) {
		e = d.findElement(By.xpath("/html/body/app/div/app-root/div/app-home/div/div[1]/div[4]/button[1]"));
		return e;
	}
	
	
	public static WebElement populateStaticSalesforce(WebDriver d) {
		e = d.findElement(By.xpath("/html/body/app/div/app-root/div/app-home/div/div[1]/div[4]/button[2]"));
		return e;
	}
	
	public static WebElement emptyDatabase(WebDriver d) {
		e = d.findElement(By.xpath("/html/body/app/div/app-root/div/app-home/div/div[1]/div[4]/button[3]"));
		return e;
	}
	
	
	public static WebElement pieChart(WebDriver d) {
		e = WaitToLoad.findDynamicElement(d,By.xpath("/html/body/app/app-root/div/app-home/div/div[2]/div[2]"), 10);
		return e;
		
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
