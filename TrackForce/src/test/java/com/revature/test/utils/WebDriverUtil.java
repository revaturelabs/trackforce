package com.revature.test.utils;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverUtil {

	private static WebDriver chromeDriver = null;
	
	private WebDriverUtil() {
		
	}
	
	public static WebDriver getChromeDriver() {
		if (chromeDriver == null) {
			File f1 = new File("src/main/resources/chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", f1.getAbsolutePath());
			return new ChromeDriver();
		}
		else 
			return chromeDriver;
	}
	
}