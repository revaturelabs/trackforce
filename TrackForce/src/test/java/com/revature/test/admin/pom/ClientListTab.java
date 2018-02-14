package com.revature.test.admin.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.revature.test.utils.WaitToLoad;

public class ClientListTab {
	static WebElement e = null;

	public static WebElement getClientTab(WebDriver d) {
		try {
			Thread.sleep(5000);
			return WaitToLoad.findDynamicElement(d,By.xpath("/html/body/app/app-root/div/app-navbar/nav/div/ul[1]/li[3]/a"), 10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("Failed XPATH FOR CLIENT LIST TAB !!!!!!!!!!!!!!!!!!!!!!!!!");
			e.printStackTrace();
			return WaitToLoad.findDynamicElement(d, By.cssSelector("[href='/client-listing']"), 10);
			
		}
		//return WaitToLoad.findDynamicElement(d, By.cssSelector("[href='/client-listing']"), 10);
	}

	public static String getCurrentURL(WebDriver d) {
		return d.getCurrentUrl();
	}

	public static WebElement getClientTabHeader(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.xpath("/html/body/app/div/app-client-list/div/div/div[1]/h3"), 10);
	}

	public static WebElement getClientSearchBox(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.xpath("//*[@id=\"clientSearch\"]"), 10);
	}

	public static WebElement getViewDataForAllClientsButton(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.xpath("/html/body/app/div/app-client-list/div/div/div[1]/button"),
				10);
	}

	public static WebElement getClientNameFromList(WebDriver d, int index) {
		return WaitToLoad.findDynamicElement(d, By.xpath("//*[@id=\"clients-list\"]/li[" + index + "]/span"), 15);
	}

	public static WebElement getBarChartHeader(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.xpath("/html/body/app/div/app-client-list/div/div/div[2]/h1"), 10);
	}

	public static WebElement getClientSearchBar(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.id("clientSearch"), 10);
	}
}
