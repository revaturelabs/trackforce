package com.revature.test.pom.Staging;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StagingAssociateDetails {
	
	private static WebElement getAssociateInfo(WebDriver driver) {
		return driver.findElement(By.cssSelector("div[class='col-md-4']"));
	}

	public static WebElement getApproveButton(WebDriver driver) {
		return driver.findElement(By.cssSelector("button[class='btn btin-primary'][innerText='Approve']"));
	}
	
	public static WebElement getAssociateID(WebDriver driver) {
		return getAssociateInfo(driver).findElements(By.tagName("tr")).get(1).findElements(By.tagName("td")).get(1);
	}
	
	public static WebElement getFirstName(WebDriver driver) {
		return getAssociateInfo(driver).findElements(By.tagName("tr")).get(2).findElements(By.tagName("td")).get(1);
	}
	
	public static WebElement getLastName(WebDriver driver) {
		return getAssociateInfo(driver).findElements(By.tagName("tr")).get(3).findElements(By.tagName("td")).get(1);
	}
	
	public static WebElement getMarketingStatus(WebDriver driver) {
		return getAssociateInfo(driver).findElements(By.tagName("tr")).get(4).findElements(By.tagName("td")).get(1);
	}
	
	public static WebElement getBatchName(WebDriver driver) {
		return getAssociateInfo(driver).findElements(By.tagName("tr")).get(5).findElements(By.tagName("td")).get(1);
	}
	
	public static WebElement getClientName(WebDriver driver) {
		return getAssociateInfo(driver).findElements(By.tagName("tr")).get(6).findElements(By.tagName("td")).get(1);
	}
	
	public static WebElement getStartDate(WebDriver driver) {
		return getAssociateInfo(driver).findElements(By.tagName("tr")).get(7).findElements(By.tagName("td")).get(1);
	}
	
	public static WebElement getChooseStatusSelect(WebDriver driver) {
		return driver.findElement(By.cssSelector("select[name='marketingStatus']"));
	}
	
	public static WebElement getChooseStatusOptionByName(WebDriver driver, String statusName) {
		return getChooseStatusSelect(driver).findElement(By.cssSelector("option[innerText='"+statusName+"']"));
	}
	
	public static WebElement getChooseStatusOptionByValue(WebDriver driver, int statusValue) {
		return getChooseStatusSelect(driver).findElement(By.cssSelector("option[value='"+statusValue+"']"));
	}
	
	public static WebElement getAssignClientSelect(WebDriver driver) {
		return driver.findElement(By.cssSelector("select[name='selectedClient']"));
	}
	
	public static WebElement getAssignClientOptionByName(WebDriver driver, String clientName) {
		return getChooseStatusSelect(driver).findElement(By.cssSelector("option[innerText='"+clientName+"']"));
	}
	
	public static WebElement getCAssignClientOptionByValue(WebDriver driver, int clientValue) {
		return getChooseStatusSelect(driver).findElement(By.cssSelector("option[value='"+clientValue+"']"));
	}
	
	public static WebElement getUpdateStartDateField(WebDriver driver) {
		return driver.findElement(By.cssSelector("input[type='date']"));
	}
	
	public static WebElement getAssociateMappedCheckbox(WebDriver driver) {
		WebElement div =  driver.findElement(By.cssSelector("div[class='checkbox'][innerText=' Is the associate mapped? ']"));
		return div.findElement(By.tagName("input"));
	}
	
	public static WebElement getEligibleForInterviewCheckbox(WebDriver driver) {
		WebElement div =  driver.findElement(By.cssSelector("div[class='checkbox'][innerText=' Is the associate eligible for an interview? ']"));
		return div.findElement(By.tagName("input"));
	}
	
	public static WebElement getUpdateAssociateButton(WebDriver driver) {
		return driver.findElement(By.cssSelector("button[type='button'][ Update Associate ]"));
	}
	
	public static WebElement getInterviewRow(WebDriver driver, int id) {
		return null;
	}
	
	public static WebElement getInterviewId(WebDriver driver, WebElement row) {
		return null;
	}
	
	public static WebElement getInterviewDate(WebDriver driver, WebElement row) {
		return null;
	}
	
	public static WebElement getInterviewType(WebDriver driver, WebElement row) {
		return null;
	}
	
	public static WebElement getInterviewFeedback(WebDriver driver, WebElement row) {
		return null;
	}
	
}
