package com.revature.test.utils;

import java.util.List;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// Implicit wait util method
// Accepts webdriver as first param, the selector method, and the maximum time it will wait until it times out
public class WaitToLoad {
	
	public static WebElement findDynamicElement(WebDriver wd, By by, int timeOut) {
	    WebDriverWait wait = new WebDriverWait(wd, timeOut);
	    
	    // WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	    WebElement element = (WebElement) wait.until(new Function() {
	    	@Override
	    	public Object apply(Object t) {
	    		WebDriver driver = (WebDriver) t;
	    		ExpectedConditions.visibilityOfElementLocated(by);
	    		return driver.findElement(by);
	    	}
	    });
	    
	    return element;
	}
	
	public static List<WebElement> findDynamicElements(WebDriver wd, By by, int timeOut){
		WebDriverWait wait = new WebDriverWait(wd,timeOut);
		
		// wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by);
		
		List<WebElement> elements = (List<WebElement>) wait.until(new Function() {
			@Override
			public Object apply(Object t) {
				WebDriver driver = (WebDriver) t;
				ExpectedConditions.visibilityOfAllElementsLocatedBy(by);
				return driver.findElements(by);
			}
		});
		
		return elements;
	}
	
	public static void waitForVisible(WebDriver wd, WebElement el,int timeOut) {
		WebDriverWait wait = new WebDriverWait(wd,timeOut);
		
		// wait.until(ExpectedConditions.visibilityOf(el));
		
		WebElement element = (WebElement) wait.until(new Function() {
			@Override
			public Object apply(Object t) {
				WebDriver driver = (WebDriver) t;
				ExpectedConditions.visibilityOf(el);
				return el;
			}
		});
	}
	public static void waitForClickable(WebDriver wd,WebElement el,int timeOut) {
		WebDriverWait wait = new WebDriverWait(wd,timeOut);
		// wait.until(ExpectedConditions.elementToBeClickable(el));
		
		WebElement element = (WebElement) wait.until(new Function() {
			@Override
			public Object apply(Object t) {
				WebDriver driver = (WebDriver) t;
				ExpectedConditions.elementToBeClickable(el);
				return el;
			}
		});
	}

	public static void AlertToBePresent(WebDriver wd,int timeOut) {
		WebDriverWait wait = new WebDriverWait(wd,timeOut);
		// wait.until(ExpectedConditions.alertIsPresent());
		
		Boolean alertIsPresent = (Boolean) wait.until(new Function() {
			@Override
			public Object apply(Object t) {
				ExpectedConditions.alertIsPresent();
				return true;
			}
		});
	}
}
