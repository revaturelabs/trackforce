package com.revature.test.admin.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.revature.test.utils.WaitToLoad;

public class ClientListTab
{
	public WebDriver wd = null;
	static WebElement e = null;
	
	public static WebElement getClientSearchBox(WebDriver d)
	{
		e = WaitToLoad.findDynamicElement(d, By.xpath("//*[@id=\"clientSearch\"]"), 10);
		return e;
	}
	
	public static WebElement getViewDataForAllClientsButton(WebDriver d)
	{
		e = WaitToLoad.findDynamicElement(d, By.xpath("/html/body/app/div/app-client-list/div/div/div[1]/button"), 10);
		return e;
	}
	
	public static WebElement getClientName(WebDriver d, int index)
	{
		int i = index;
		e = WaitToLoad.findDynamicElement(d, By.xpath("//*[@id=\"clients-list\"]/li[" + i + "]/span"), 10);
		return e;
	}
	
	//doesnt work
	public static WebElement getClientBarChartMapped(WebDriver d, int index)
	{
		int i = index;
		e = WaitToLoad.findDynamicElement(d, By.xpath("//*[@id=\"clients-list\"]/li[" + i + "]/span"), 10);
		return e;
	}
	
}
