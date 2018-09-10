package com.revature.pom;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdminBatchList {
	
	public static List<WebElement> elementList = new ArrayList<WebElement>();
	public static WebElement element;
	
	public static List<WebElement> batchesNames(WebDriver wd) {
		elementList = wd.findElements(By.xpath("//td[@class='name-column']"));
		return elementList;
	}
	
	public static List<WebElement> batchesDates(WebDriver wd) {
		elementList = wd.findElements(By.xpath("//td[@class='date-column']"));
		return elementList;
	}

	public static WebElement startDateInput(WebDriver wd) {
		element = wd.findElement(By.xpath("//*[@id=\"startDate\"]/div/div/input"));
		return element;
	}

	public static WebElement endDateInput(WebDriver wd) {
		element = wd.findElement(By.xpath("//*[@id=\"endDate\"]/div/div/input"));
		return element;
	}	

	public static WebElement submitFilter(WebDriver wd) {
		element = wd.findElement(By.xpath("/html/body/app-component/div/app-batch-list/div/div[2]/div[2]/form/div[3]/input[1]"));
		return element;
	}	
	
}
