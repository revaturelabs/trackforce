package com.revature.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AssociateHome {
	private static WebElement element;
	
	public static WebElement logout(WebDriver wd) {
		element = wd.findElement(By.xpath("/html/body/app-component/app-navbar/nav/div/ul[1]/li[2]/button"));
		return element;
		
	}

}
