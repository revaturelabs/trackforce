package com.revature.test.utils;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverUtil {

	private static WebDriver chromeDriver = null;
	
	public WebDriverUtil() {
		
	}
	
	public static WebDriver getChromeDriver() {
			//doesnt work, for some reason i have to put driver in \sts-3.9.1-RELEASE\src\main\resources
			//some path like that
			//like the actual installation of sts, if you get a testNG error, check the stack trace
			// for the directory this code is looking into
			//if you want to see what happening on the ec2-54-210-36-233.compute-1 instance
			//you can check the logs folder in the apache installation
			// currently there is a chromedriver in the bin/src/main/resources of the Apache folder
			//
			File f1 = new File("src/main/resources/chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", f1.getAbsolutePath());
			chromeDriver=new ChromeDriver();
			return chromeDriver;
	}
	
}