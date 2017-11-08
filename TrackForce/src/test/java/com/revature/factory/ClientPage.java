package com.revature.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ClientPage {
	
	public ClientPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "/html/body/div/div[1]/div[1]/div[2]/h1")
	public WebElement chartTitle; // /html/body/div/div[1]/div[1]/div[2]/h1
	
	@FindBy(id = "clientSearch")
	public WebElement searchBar;
	
	@FindBy(xpath = "/html/body/div/div[1]/div[1]/div[1]/div/button")
	public WebElement allClientsBtn;
	
	@FindBy(xpath = "/html/body/div/div[1]/div[1]/div[1]/div/ul/li/a")
	public WebElement clientListItem; // /html/body/div/div[1]/div[1]/div[1]/div/ul/li

}
