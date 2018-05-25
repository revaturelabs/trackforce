package com.revature.test.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MethodUtil {
	
	public static WebElement waitForLoad(WebDriver driver, String xpath, int nTimes200Mill) {
		int times = 0;
		WebElement element = null;
		while(element == null) {
			try {
				element = driver.findElement(By.xpath(xpath));
			} catch(NoSuchElementException e) {
				if(times < nTimes200Mill) {

					try {
						times++;
						Thread.sleep(200);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
				else {
					e.printStackTrace();
					throw new NoSuchElementException("Element did not load in time", e);
				}
			}
		}
		return element;
	}
	
	public static WebElement waitForLoad(WebDriver driver, String xpath) {
		return waitForLoad(driver, xpath, 20);
	}
	
	
	public static WebElement waitForLoadByAnyType(WebDriver driver, By byType, int nTimes200Mill) {
		int times = 0;
		WebElement element = null;
		while(element == null) {
			try {
				element = driver.findElement(byType);
			} catch(NoSuchElementException e) {
				if(times < nTimes200Mill) {

					try {
						times++;
						Thread.sleep(200);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
				else {
					e.printStackTrace();
					throw new NoSuchElementException("Element did not load in time", e);
				}
			}
		}
		return element;
	}

	public static WebElement waitForLoadByAnyType(WebDriver driver, By byType) {
		return waitForLoadByAnyType(driver, byType, 20);
	}
	
	public static void loadPropertiesFile(Properties props) {
		try {
			URL resource = MethodUtil.class.getClassLoader().getResource("locators.properties");
			InputStream is = resource.openStream();
			props.load(is);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void waitAndCloseDriver(WebDriver wd, long millisecondsToWait) {
		try {
			Thread.sleep(millisecondsToWait);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		finally {
			wd.close();
		}
	}
	
	public static void executeJSClick(WebDriver wd, WebElement el) {
		JavascriptExecutor executor = (JavascriptExecutor) wd;
		executor.executeScript("arguments[0].click();", el);
	}
}
