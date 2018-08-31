package com.revature.test.admin.pom;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.revature.test.utils.WaitToLoad;

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
		d.findElement(By.xpath(prop.getProperty("associateViewUpdate"))).click();
	}
	
	public static void clickSave(WebDriver d) {
		d.findElement(By.xpath(prop.getProperty("associateViewSave"))).click();
	}
	
	
	public static void clickSaveInterview(WebDriver d) {
		d.findElement(By.className(prop.getProperty("associateViewAdd"))).click();
	}
	
	public static void enterFirstName(WebDriver d) {
		d.findElement(By.xpath(prop.getProperty("associateViewFirstNameEdit"))).sendKeys(prop.getProperty("associateViewEnterFirstName"));
	}

	public static void enterLastName(WebDriver d) {
		d.findElement(By.xpath(prop.getProperty("associateViewLastNameEdit"))).sendKeys(prop.getProperty("associateViewEnterLastName"));
	}
	
	public static String getEnterFirstName(WebDriver d) {
		return prop.getProperty("associateViewEnterFirstName");
	}
	
	public static String getEnterLastName(WebDriver d) {
		return prop.getProperty("associateViewEnterLastName");
	}
	
	public static String getFirstName(WebDriver d) {
		return d.findElement(By.xpath(prop.getProperty("associateViewFirstName"))).getText();
	}
	
	public static String getLastName(WebDriver d) {
		return d.findElement(By.xpath(prop.getProperty("associateViewLastName"))).getText();
	}
	
	public static void enterAssignedDate(WebDriver d,String date) {
		d.findElement(By.xpath(prop.getProperty("associateViewAssignedDate"))).sendKeys(date);
	}
	
	public static void enterInterviewDate(WebDriver d,String date) {
		d.findElement(By.xpath(prop.getProperty("associateViewInterviewDate"))).sendKeys(date);
	}

	public static void clickInterviewTab(WebDriver d) {
		d.findElement(By.xpath(prop.getProperty("associateViewInterviewTab"))).click();	
	}
	
	public static void dropDown(WebDriver d, String option,String name) throws InterruptedException {
		Select dropDown = new Select(d.findElement(By.name(prop.getProperty("associateView"+name))));
		for(WebElement el : dropDown.getOptions()) {
			if(el.getText().equals(option))
				dropDown.selectByVisibleText(el.getText());
		}
	}
	
	public static void dropDownInterviewType(WebDriver d, String option) throws InterruptedException {
		Select dropDown = new Select(d.findElement(By.xpath("/html/body/app-component/div/app-myinterview-view/div/div[1]/form/div[1]/div[2]/select")));
		for(WebElement el : dropDown.getOptions()) {
			if(el.getText().equals(option))
				dropDown.selectByVisibleText(el.getText());
		}
	}
	
	//Might need to refactor for xpath
	public static void toggleNotice(WebDriver d) {
		d.findElement(By.name(prop.getProperty("associateViewNotice"))).click();
	}
	
	public static boolean popUp(WebDriver d) {
		return d.findElement(By.xpath(prop.getProperty("associateViewPopup"))).isDisplayed();
	}
	
}