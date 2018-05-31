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
		return WaitToLoad.findDynamicElement(d, By.cssSelector(prop.getProperty("predictionsTab")), 10);
		//e= WaitToLoad.findDynamicElement(d,By.xpath("/html/body/app/app-predictions/app-navbar/nav/div/ul[1]/li[6]"), 10);
		//return e;										
	}
	
	public static String getCurrentURL(WebDriver d) {
		return d.getCurrentUrl();
	}
	
	public static WebElement startDate(WebDriver d) {
		e= WaitToLoad.findDynamicElement(d,By.xpath(prop.getProperty("predictionsStartDate")), 10);
		return e;
	}
	
	public static WebElement endDate(WebDriver d) {
		e= WaitToLoad.findDynamicElement(d,By.xpath(prop.getProperty("predictionsEndDate")), 10);
		
		return e;
	}
	
	public static WebElement numberofAssociates(WebDriver d) {
		e= WaitToLoad.findDynamicElement(d,By.xpath(prop.getProperty("productionsNumAssociates")), 10);
		return e;
	}
	
	public static WebElement filterbyTechnologies(WebDriver d) {
		e= WaitToLoad.findDynamicElement(d,By.xpath(prop.getProperty("predictionsFilterTechnologies")), 10);
		return e;
	}
	
	public static WebElement technologyOption(WebDriver d) {
		e= WaitToLoad.findDynamicElement(d,By.xpath(prop.getProperty("predictionsTechOptions")), 10);
		return e;
	}
	
	public static WebElement buttonPrediction(WebDriver d) {
		e= WaitToLoad.findDynamicElement(d,By.xpath(prop.getProperty("predictionsButtonPredict")), 10);
		return e;
	}
	
	public static WebElement technology(WebDriver d) {
		e= WaitToLoad.findDynamicElement(d,By.xpath(prop.getProperty("predictionsTechnology")), 10);
		return e;
	}
	
	public static WebElement requestedAssociates(WebDriver d) {
		e= WaitToLoad.findDynamicElement(d,By.xpath(prop.getProperty("predictionsRequestedAssociates")), 10);
		return e;
	}
	
	public static WebElement availableAssociates(WebDriver d) {
		e= WaitToLoad.findDynamicElement(d,By.xpath(prop.getProperty("predictionsAvailableAssociates")), 10);
		return e;
	}
	
	public static WebElement difference(WebDriver d) {
		e= WaitToLoad.findDynamicElement(d,By.xpath(prop.getProperty("predictionsDifference")), 10);
		return e;
	}

}
