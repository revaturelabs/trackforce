package com.revature.utils;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverUtil {

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
        File chromeExe = new File("C:\\Automation\\chromedriver_win32\\chromedriver.exe");
        System.out.println(chromeExe.getPath());
        System.setProperty("webdriver.chrome.driver", chromeExe.getAbsolutePath());
        return new ChromeDriver();
    }

    public static WebDriver getFirefoxDriver() {
        File firefoxExe = new File("C:\\Automation\\geckodriver-v0.19.0-win64\\geckodriver.exe");
        System.setProperty("webdriver.gecko.driver", firefoxExe.getAbsolutePath());
        return new FirefoxDriver();
    }
}
