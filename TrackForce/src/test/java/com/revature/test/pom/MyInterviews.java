package com.revature.test.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MyInterviews {
	//Get the element holding the client list
	public static WebElement getClientSelect(WebDriver driver) {
		// get the select elements where the name is newClient
		return driver.findElement(By.name("newClient"));
	}
	//Select from the Client List by either value or index 
	public static WebElement getClientSelectOptionsByValue(WebDriver driver, String value) {
		return getClientSelect(driver).findElement(By.cssSelector("option[value="+ value +"]"));
	}
	public static WebElement getClientSelectOptionsByIndex(WebDriver driver, int index) {
		return getClientSelect(driver).findElement(By.cssSelector("option:nth-child("+ index +")"));
	}
	//Get the element holding the Interview type dropdown
	public static WebElement getTypeSelect(WebDriver driver) {
		return driver.findElement(By.name("type"));
	}
	//Select Interview type either by value (i.e."Phone") or by list index
	public static WebElement getTypeSelectOptionsByValue(WebDriver driver, int value) {
		return getTypeSelect(driver).findElement(By.cssSelector("option:nth-child("+ value +")"));
		
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
	public static WebElement getInterviewDate(WebDriver driver) {
		return driver.findElement(By.name("startDate"));
	}
	public static WebElement getInterviewTime(WebDriver driver) {
		return driver.findElement(By.name("startTime"));
	}
	
	public static WebElement getUpdateDate(WebDriver driver) {
		return driver.findElement(By.id("updateDate-1"));
	}
	
	public static WebElement getUpdateTime(WebDriver driver) {
		return driver.findElement(By.id("updateTime-1"));
	}
	
	public static WebElement getUpdateButton(WebDriver driver) {
		return driver.findElement(By.id("updateButton-1"));
	}
	
	//Works for both the "Add Interview" test and "Update Interview" test, since the alert has a timeout
	public static WebElement getSuccessAlert(WebDriver driver) {
		return driver.findElement(By.className("alert-success"));
	}
	public static WebElement getFailureAlert(WebDriver driver) {
		return driver.findElement(By.id("failureAlert"));
	}
	
	public static WebElement getUpdateFailureAlert(WebDriver driver) {
		return driver.findElement(By.id("updateFailureAlert"));
	}
	
	public static WebElement getUpdateSuccessAlert(WebDriver driver) {
		return driver.findElement(By.id("updateSuccessAlert"));
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
	//fetch the number of rows currently in the My Interviews table
	public static Integer getNumberOfInterviews(WebDriver driver) {
		//xpath to the first data in each row of the Interviews table returns a list of those elements with size equal to # rows
		return driver.findElements(By.id("tableRows")).size();
	}
}
