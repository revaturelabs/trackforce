package com.revature.test.admin.pom;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.revature.test.utils.WaitToLoad;

public class Predictions {
	
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
	
	public static WebElement clickPredictionsTab(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.cssSelector(prop.getProperty("predictionsTab")), 2);
	}
	
	public static String getCurrentURL(WebDriver d) {
		return d.getCurrentUrl();
	}
	
	public static WebElement startDate(WebDriver d) {
		return WaitToLoad.findDynamicElement(d,By.cssSelector(prop.getProperty("predictionsStartDate")), 2);
	}
	
	public static WebElement endDate(WebDriver d) {
		return WaitToLoad.findDynamicElement(d,By.cssSelector(prop.getProperty("predictionsEndDate")), 2);
	}
	
	public static WebElement numberofAssociates(WebDriver d) {
		return WaitToLoad.findDynamicElement(d,By.name(prop.getProperty("productionsNumAssociates")), 2);
	}
	
	public static WebElement filterbyTechnologies(WebDriver d) {
		return WaitToLoad.findDynamicElement(d,By.xpath(prop.getProperty("predictionsFilterTechnologies")), 2);
	}
	
	public static WebElement technologyOption(WebDriver d) {
		return WaitToLoad.findDynamicElement(d,By.xpath(prop.getProperty("predictionsTechOptions")), 2);
	}
	
	public static WebElement buttonPrediction(WebDriver d) {
		return WaitToLoad.findDynamicElement(d,By.xpath(prop.getProperty("predictionsButtonPredict")), 2);
	}
	
	public static WebElement technology(WebDriver d) {
		return WaitToLoad.findDynamicElement(d,By.xpath(prop.getProperty("predictionsTechnology")), 2);
	}
	
	public static WebElement requestedAssociates(WebDriver d) {
		return WaitToLoad.findDynamicElement(d,By.xpath(prop.getProperty("predictionsRequestedAssociates")), 2);
	}
	
	public static WebElement availableAssociates(WebDriver d) {
		return WaitToLoad.findDynamicElement(d,By.xpath(prop.getProperty("predictionsAvailableAssociates")), 2);
	}
	
	public static WebElement difference(WebDriver d) {
		return WaitToLoad.findDynamicElement(d,By.xpath(prop.getProperty("predictionsDifference")), 2);
	}

}
