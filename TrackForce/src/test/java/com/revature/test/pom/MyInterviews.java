package com.revature.test.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MyInterviews {
	public static WebElement getClientSelect(WebDriver driver) {
		return driver.findElement(By.name("newClient"));
	}
	//Warning this method might return A LOT of options (could be deleted).  If you are worried about creating 1000+ WebElements try the next one
//	public static List<WebElement> getAllClientSelectOptions(WebDriver driver) {
//		return getClientSelect(driver).findElements(By.cssSelector("*"));
//	}
	public static WebElement getClientSelectOptionsByValue(WebDriver driver, String value) {
		return getClientSelect(driver).findElement(By.cssSelector("option[value="+ value +"]"));
	}
	public static WebElement getClientSelectOptionsByIndex(WebDriver driver, int index) {
		return getClientSelect(driver).findElement(By.cssSelector("option:nth-child("+ index +")"));
	}
	public static WebElement getTypeSelect(WebDriver driver) {
		return driver.findElement(By.name("type"));
	}
	//might be obsolete but returns not too much WebElements
//	public static List<WebElement> getTypeSelectOptions(WebDriver driver) {
//		return getTypeSelect(driver).findElements(By.cssSelector("*"));
//	}
	public static WebElement getTypeSelectOptionsByValue(WebDriver driver, String value) {
		return getTypeSelect(driver).findElement(By.cssSelector("option[value="+ value +"]"));
	}
	public static WebElement getTypeSelectOptionsByIndex(WebDriver driver, int index) {
		return getTypeSelect(driver).findElement(By.cssSelector("option:nth-child("+ index +")"));
	}
	public static WebElement get24HrNoticeCheckbox(WebDriver driver) {
		return driver.findElement(By.name("24hournotice"));
	}
	public static WebElement getAddInterviewButton(WebDriver driver) {
		return driver.findElement(By.id("add-interview"));
	}
	public static WebElement getTable(WebDriver driver) {
		return driver.findElement(By.className("table"));
	}
	/*
	 * returns the row index which contains specified client name, return -1 if unsuccessful  
	 */
	public static int getIndexOfTableByClientName(WebDriver driver, String clientName) {
		List<WebElement> rows = driver.findElements(By.cssSelector(".table > tbody > *"));
		for(int i = 0; i < rows.size(); i++) {
			String client = rows.get(i).findElement(By.cssSelector("td:nth-child(2)")).getText(); //get the client name from second column
			if(client.contains(clientName))
				return i;
		}
		return -1;
	}
	/*
	 * returns a <td> element for Client name in the nth row
	 */
	public static WebElement getInterviewClientByRowIndex(WebDriver driver, int index) {
		return driver.findElement(By.cssSelector(".table > tbody > tr:nth-child("+index+") > td:nth-child(2)"));
	}
	public static WebElement getInterviewDateNotifiedByRowIndex(WebDriver driver, int index) {
		return driver.findElement(By.cssSelector(".table > tbody > tr:nth-child("+index+") > td:nth-child(3)"));
	}
	public static WebElement getInterviewDateByRowIndex(WebDriver driver, int index) {
		return driver.findElement(By.cssSelector(".table > tbody > tr:nth-child("+index+") > td:nth-child(4)"));
	}
}
