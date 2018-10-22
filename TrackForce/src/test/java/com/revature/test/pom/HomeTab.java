package com.revature.test.pom;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
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
	
	public static String getCurrentURL(WebDriver d) {
		return d.getCurrentUrl();
	}
	
	public static WebElement phone(WebDriver d) {
		e = d.findElement(By.xpath("/html/body/app-component/app-footer/footer/div/div/div[2]/ul/li[2]/a"));
		//e= WaitToLoad.findDynamicElement(d,By.partialLinkText(prop.getProperty("homePhone")), 2);
		return e;
	}
	
	public static WebElement email(WebDriver d) {
		e = d.findElement(By.xpath("/html/body/app-component/app-footer/footer/div/div/div[3]/ul/li[1]/a"));
		//e= WaitToLoad.findDynamicElement(d,By.partialLinkText(prop.getProperty("homeEmail")), 2);
		return e;
	}
	
	public static WebElement website(WebDriver d) {
		e = d.findElement(By.xpath("/html/body/app-component/app-footer/footer/div/div/div[3]/ul/li[2]/a"));
		//e= WaitToLoad.findDynamicElement(d,By.xpath(prop.getProperty("homeWebsite")), 2);
		return e;
	}
	
	
}
