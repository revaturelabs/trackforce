package com.revature.test.pom;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AssociateView {
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
	
	public static String getClientTestSubject() {
		return prop.getProperty("clientTestSubject");
	}
	
	public static String getClientInterviewDate() {
		return prop.getProperty("clientInterviewDate");
	}
	
	public static String getClientInterviewAssignedDate() {
		return prop.getProperty("clientInterviewAssignedDate");
	}
	
	public static String getClientInterviewType() {
		return prop.getProperty("clientInterviewType");
	}
	
	public static void clickUpdate(WebDriver d) {
		//d.findElement(By.xpath(prop.getProperty("associateViewUpdate"))).click();
		d.findElement(By.cssSelector(prop.getProperty("associateViewUpdate"))).click();
	}
	
	public static void clickSave(WebDriver d) {
		d.findElement(By.cssSelector(prop.getProperty("associateViewSave"))).click();
	}
	public static void clickReset(WebDriver d) {
		d.findElement(By.cssSelector(prop.getProperty("associateViewReset"))).click();
	}
	
	public static void clickSaveInterview(WebDriver d) {
		d.findElement(By.className(prop.getProperty("associateViewAdd"))).click();
	}
	
	public static void enterFirstName(WebDriver d) {
		d.findElement(By.cssSelector(prop.getProperty("associateViewFirstNameField"))).sendKeys(prop.getProperty("associateViewFirstNameValue"));
	}

	public static void enterLastName(WebDriver d) {
		d.findElement(By.cssSelector(prop.getProperty("associateViewLastNameField"))).sendKeys(prop.getProperty("associateViewLastNameValue"));
	}
	
	public static String getFirstNameValue(WebDriver d) {
		return prop.getProperty("associateViewFirstNameValue");
	}
	
	public static String getLastNameValue(WebDriver d) {
		return prop.getProperty("associateViewLastNameValue");
	}
	
	public static String getCurrentFirstName(WebDriver d) {
		return d.findElement(By.cssSelector(prop.getProperty("associateViewCurrentFirstName"))).getText();
	}
	
	public static String getCurrentLastName(WebDriver d) {
		return d.findElement(By.cssSelector(prop.getProperty("associateViewCurrentLastName"))).getText();
	}

	public static void enterAssignedDate(WebDriver d,String date) {
		d.findElement(By.cssSelector(prop.getProperty("associateViewAssignedDate"))).sendKeys(date);
	}

	public static void enterInterviewDate(WebDriver d,String date) {
		d.findElement(By.cssSelector(prop.getProperty("associateViewInterviewDate"))).sendKeys(date);
	}

	public static void clickInterviewTab(WebDriver d) {
		d.findElement(By.cssSelector(prop.getProperty("associateViewInterviewTab"))).click();	
	}
	
	public static void dropDown(WebDriver d, String option,String name) throws InterruptedException {
		Select dropDown = new Select(d.findElement(By.name(prop.getProperty("associateView"+name))));
		for(WebElement el : dropDown.getOptions()) {
			if(el.getText().equals(option))
				dropDown.selectByVisibleText(el.getText());
		}
	}
	
	public static void dropDownInterviewType(WebDriver d, String option) throws InterruptedException {
		Select dropDown = new Select(d.findElement(By.name("type")));
		for(WebElement el : dropDown.getOptions()) {
			if(el.getText().equals(option))
				dropDown.selectByVisibleText(el.getText());
		}
	}
	
	//Might need to refactor for cssSelector
	public static void toggleNotice(WebDriver d) {
		d.findElement(By.name(prop.getProperty("associateViewNotice"))).click();
	}
	
	public static boolean popUp(WebDriver d) {
		return d.findElement(By.cssSelector(prop.getProperty("associateViewPopup"))).isDisplayed();
	}
	
}