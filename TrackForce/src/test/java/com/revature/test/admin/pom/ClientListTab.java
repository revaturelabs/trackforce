package com.revature.test.admin.pom;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.revature.test.utils.WaitToLoad;

public class ClientListTab {
	static WebElement e = null;
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

	public static WebElement getClientTab(WebDriver d) {

		e = WaitToLoad.findDynamicElement(d, By.cssSelector(prop.getProperty("clientListing")), 10);
		if (e == null) {
			e = WaitToLoad.findDynamicElement(d, By.cssSelector(prop.getProperty("clientList")), 10);
		}
		return e;
	}

	// returns the current url
	public static String getCurrentURL(WebDriver d) {
		return d.getCurrentUrl();
	}

	// finds the Client Tab header using the header tag
	public static WebElement getClientTabHeader(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.tagName(prop.getProperty("clientHeader")), 10);
	}

	// finds the client search box using the search box id
	public static WebElement getClientSearchBox(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.cssSelector(prop.getProperty("clientSearchBox")), 10);
	}

	// finds the view data for all clients button using the button class
	public static WebElement getViewDataForAllClientsButton(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.cssSelector(prop.getProperty("clientAllClients")),
				10);
	}

	//finds a specific client name when used
	public static WebElement getClientNameFromList(WebDriver d, int index) {
		return WaitToLoad.findDynamicElement(d, By.xpath(prop.getProperty("clientNameFromList")), 15);
	}

	// finds the bar chart header using the xpath
	public static WebElement getBarChartHeader(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.xpath(prop.getProperty("clientBarChartHeader")), 10);
	}
}
