package com.revature.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ClientPage {
	
	public ClientPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "")
	public WebElement headline;
	
	@FindBy(id = "clientSearch")
	public WebElement searchBar;
	
	@FindBy(id = "")
	public WebElement clientTab;
	
	@FindBy(id = "")
	public WebElement mainTab;

}
