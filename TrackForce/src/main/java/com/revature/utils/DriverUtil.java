package com.revature.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverUtil {

	private static Properties properties = initProperties();

	/**
	 * Hide default no-args constructor
	 */
	private DriverUtil() {
	}

	private static Properties initProperties() {
		properties = new Properties();
		File configFile = new File("src/main/resources/driver.properties");
		try (FileInputStream fileInput = new FileInputStream(configFile)) {
			properties.load(fileInput);
		} catch (IOException ioe) {
			LogUtil.logger.error(ioe);
		}
		return properties;
	}

	public static WebDriver getDriver(String browser) {
		WebDriver driver;
		switch (browser) {
		case "chrome":
			driver = getChromeDriver();
			return driver;
		case "firefox":
			driver = getFirefoxDriver();
			return driver;
		default:
			return new ChromeDriver();
		}
	}

	public static WebDriver getChromeDriver() {
		File chromeExe = new File(properties.getProperty("chrome_driver"));
		System.setProperty("webdriver.chrome.driver", chromeExe.getAbsolutePath());
		return new ChromeDriver();
	}

	public static WebDriver getFirefoxDriver() {
		File firefoxExe = new File(properties.getProperty("firefox_driver"));
		System.setProperty("webdriver.gecko.driver", firefoxExe.getAbsolutePath());
		return new FirefoxDriver();
	}
}
