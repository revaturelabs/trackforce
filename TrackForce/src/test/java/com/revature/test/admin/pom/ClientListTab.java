package com.revature.test.admin.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.revature.test.utils.WaitToLoad;

public class ClientListTab {
	static WebElement e = null;

	public static WebElement getClientTab(WebDriver d) {
		e = WaitToLoad.findDynamicElement(d, By.cssSelector("[href='/client-listing']"), 10);
		if (e == null) {
			e = WaitToLoad.findDynamicElement(d, By.cssSelector("[href='/client-list']"), 10);
		}
		return e;
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
		return WaitToLoad.findDynamicElement(d, By.xpath("/html/body/app/app-client-list/div/div/div[1]/button"),
				10);
	}

	public static WebElement getClientNameFromList(WebDriver d, int index) {
		return WaitToLoad.findDynamicElement(d, By.xpath("//*[@id=\"clients-list\"]/li[" + index + "]/span"), 15);
	}

	public static WebElement getBarChartHeader(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.xpath("/html/body/app/app-client-list/div/div/div[2]/h1"), 10);
	}

	public static WebElement getClientSearchBar(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.id("clientSearch"), 10);
	}
}
