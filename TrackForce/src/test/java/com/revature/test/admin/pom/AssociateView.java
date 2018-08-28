package com.revature.test.admin.pom;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

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
	
	public static void clickUpdate(WebDriver d) {
		d.findElement(By.className(prop.getProperty("associateViewUpdate"))).click();
	}
	
	public static void clickSave(WebDriver d) {
		d.findElement(By.xpath(prop.getProperty("associateViewSave"))).click();
	}
	
	public static void clickCreateInterview(WebDriver d) {
		d.findElement(By.className(prop.getProperty("associateViewInterview"))).click();
	}
	
	public static void clickSaveInterview(WebDriver d) {
		d.findElement(By.className(prop.getProperty("associateViewAdd"))).click();
	}
	
	public static void enterFirstName(WebDriver d,String name) {
		d.findElement(By.xpath(prop.getProperty("associateViewFirstNameEdit"))).sendKeys(name);
	}

	public static void enterLastName(WebDriver d,String name) {
		d.findElement(By.xpath(prop.getProperty("associateViewLastNameEdit"))).sendKeys(name);
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
	
	public static void toggleNotice(WebDriver d) {
		d.findElement(By.name(prop.getProperty("associateViewNotice"))).click();
	}
	
	public static boolean popUp(WebDriver d) {
		return d.findElement(By.xpath(prop.getProperty("associateViewPopup"))).isDisplayed();
	}
	
}