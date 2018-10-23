package com.revature.test.pom.Admin;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.revature.test.pom.Login;

//IMPORTANT: A lot of code for this POM already exists in the general POM Package. (Predictions.java)
public class AdminPredictionList {
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
	
	public static WebElement getStartDateFeild(WebDriver driver){
		return driver.findElement(By.cssSelector("#startDate"));
	}
	
	public static WebElement getEndDateFeild(WebDriver driver){
		return driver.findElement(By.cssSelector("#endDate"));
	}
}
