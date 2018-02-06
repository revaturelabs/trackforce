package com.revature.test.admin.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AssociateListTab {
	
	static WebElement element = null;
	
	//Associate List tab on navbar
	public static WebElement tab(WebDriver d) {
		element = d.findElement(By.xpath("/html/body/app/div/app-associate-list/app-navbar/nav/div/ul[1]/li[4]/a "));
		return element;
	}
	
	
	//********************TEXT FIELDS ************************
	
	//Search By Text input field
	public static WebElement searchByTextInputField(WebDriver d) {
		element = d.findElement(By.xpath("//*[@id=\"name\"]"));
		return element;
	}
	
	
	//*************DROP DOWNS ****************************
	
	//Marketing Status drop down
	public static WebElement marketingStatusDropDown(WebDriver d) {
		element = d.findElement(By.xpath("//*[@id=\"mStatus\"]"));
		return element;
	}
	
	//Curriculum drop down
	public static WebElement curriculumDropDown(WebDriver d) {
		element = d.findElement(By.xpath("//*[@id=\"curriculum\"]"));
		return element;
	}
	
	//Client drop down
	public static WebElement clientDropDown(WebDriver d) {
		element = d.findElement(By.xpath("//*[@id=\"client\"]"));
		return element;
	}
	
	//Update by Marketing Status drop down
	public static WebElement updateByMarketingStatusDropDown(WebDriver d) {
		element = d.findElement(By.xpath("//*[@id=\"uStatus\"]"));
		return element;
	}
	
	//Client drop down
	public static WebElement clientDropDrown(WebDriver d) {
		element = d.findElement(By.xpath("//*[@id=\"uclient\"]"));
		return element;
	}
	
	//******************** BUTTONS **************************
	
	//Update button
	public static WebElement updateButton(WebDriver d) {
		element = d.findElement(By.xpath("//*[@id=\"submit\"] "));
		return element;
	}
	
	//****************** CHECKBOXES **************************
	
	//Edit check box for first row in the field
	public static WebElement editCheckBox(WebDriver d) {
		element = d.findElement(By.xpath("//*[@id=\"70\"]"));
		return element;
	}
	

}
