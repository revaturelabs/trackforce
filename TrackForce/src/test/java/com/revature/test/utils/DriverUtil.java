package com.revature.test.utils;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverUtil {

	private static WebDriver chromeDriver = null;
	private static WebDriver firefoxDriver = null;
	
	private static String chromeDriverURL = "C:\\selenium\\chromedriver.exe";
	private static String firefoxDriverURL = "C:\\selenium\\geckodriver.exe";
	
	
	private DriverUtil() {
		
	}
	
	public static WebDriver getChromeDriver() {
		if (chromeDriver == null) {
			File f1 = new File(chromeDriverURL);
			System.setProperty("webdriver.chrome.driver", f1.getAbsolutePath());
			return new ChromeDriver();
		}
		else 
			return chromeDriver;
	}
	
	public static WebDriver getFirefoxDriver() {
		if (firefoxDriver == null) {
			File f1 = new File(firefoxDriverURL);
			System.setProperty("webdriver.geckodriver.driver", f1.getAbsolutePath());
			return new FirefoxDriver();
		}
		else
			return firefoxDriver;
	}
	
}