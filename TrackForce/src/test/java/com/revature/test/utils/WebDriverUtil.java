package com.revature.test.utils;

import java.io.File;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class WebDriverUtil {

	private static WebDriver chromeDriver = null;
	
	private WebDriverUtil() {
		
	}
	
//	public static WebDriver getChromeDriver() {
//		if (chromeDriver == null) {
//			File f1 = new File("src/main/resources/chromedriver.exe");
//			System.setProperty("webdriver.chrome.driver", f1.getAbsolutePath());
//			return new ChromeDriver();
//		}
//		else 
//			return chromeDriver;
//	}
	
	
	public static WebDriver getChromeDriver() {
		if (chromeDriver == null) {
			File f = null;
			if (System.getenv("PATH").contains("/home/ec2-user/")) {
				f = new File("/home/ec2-user/.jenkins/workspace/TrackForce Server/TrackForce/src/main/resources/chromedriver");
				System.out.println("Chrome driver path: " + f.getAbsolutePath());
				System.setProperty("webdriver.chrome.driver", f.getAbsolutePath());
				return new ChromeDriver();
			} else {
				f = new File("src/main/resources/chromedriver.exe");
				System.out.println("Chrome driver path:"+f.getAbsolutePath());
				System.setProperty("webdriver.chrome.driver", f.getAbsolutePath());
				return new ChromeDriver();
			}
		} else 
			return chromeDriver;
		}
	
}