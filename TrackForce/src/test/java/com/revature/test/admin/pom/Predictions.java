package com.revature.test.admin.pom;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.revature.test.utils.WaitToLoad;

/**
 * All the locations of the elements are specified in the tests.properties file
 * in src/test/resources. Any additional elements should be specified in there and 
 * referenced with prop.getProperty("element")
 * @author Jesse (reviewer)
 * @since 6.18.06.07
 */
public class Predictions {
	
	static WebElement e = null;
	private static Properties prop = new Properties();
	public static Map<String,String> technologies;
	static {
		InputStream locProps = Login.class.getClassLoader()
				.getResourceAsStream("tests.properties");
		try {
			prop.load(locProps);
		} catch (IOException e) {
			e.printStackTrace();
		}
		technologies = new HashMap<String,String>();
		technologies.put("JTA", "tech1");
		technologies.put("Java", "tech2");
		technologies.put(".Net", "tech3");
		technologies.put("PEGA", "tech4");
		technologies.put("DynamicCRM", "tech5");
		technologies.put("Salesforce", "tech6");
		technologies.put("Microservices", "tech7");
		technologies.put("SEED", "tech8");
		technologies.put("Oracle fusion", "tech9");
		
		technologies = Collections.unmodifiableMap(technologies);
	}
	
	public static WebElement clickPredictionsTab(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.xpath(prop.getProperty("predictionsTab")), 2);
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
		return WaitToLoad.findDynamicElement(d,By.name(prop.getProperty("predictionsNumAssociates")), 2);
	}
	
	public static WebElement filterbyTechnologies(WebDriver d) {
		return WaitToLoad.findDynamicElement(d,By.xpath(prop.getProperty("predictionsFilterTechnologies")), 2);
	}
	
	public static WebElement technologyOption(WebDriver d) {
		return WaitToLoad.findDynamicElement(d,By.xpath(prop.getProperty("predictionsTechOptions")), 2);
	}
	
	public static WebElement buttonPrediction(WebDriver d) {
		return WaitToLoad.findDynamicElement(d,By.className(prop.getProperty("predictionsButtonPredict")), 2);
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

	public static void selectFilter(WebDriver d,String tech) {
		d.findElement(By.id(tech)).click();
	}
}
