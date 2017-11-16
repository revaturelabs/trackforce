package com.revature.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BatchPage {

    public BatchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "/html/body/nav/div/ul[1]/li[2]/a")
    public WebElement batchPageTab;

    @FindBy(xpath = "/html/body/div/div/div[2]/div[1]/div/div/div/table/tbody/tr[1]/td[1]/a")
    public WebElement firstBatchListing;

    @FindBy(xpath = "/html/body/div/div/div/div[2]/table/tbody/tr[1]/td[1]/a")
    public WebElement firstAssociateListing;
}
