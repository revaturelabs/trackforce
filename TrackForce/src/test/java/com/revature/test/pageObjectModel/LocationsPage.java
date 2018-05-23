package com.revature.pageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LocationsPage {
	
	public static WebElement navigateToLocationsPage(WebDriver wd) {
		try { Thread.sleep(3000); } catch (InterruptedException e) { e.printStackTrace(); }
		return wd.findElement(By.xpath("/html/body/div/div[1]/ng-include/div/md-content/md-nav-bar/div/nav/ul/li[3]/a"));
	}
	
	public static WebElement newLocationButton(WebDriver wd) {
		return wd.findElement(By.cssSelector("[ng-click*='lCtrl.addLocation()']"));
	}
	
	public static WebElement newLocationName(WebDriver wd) {
		return wd.findElement(By.cssSelector("[ng-model='ldCtrl.location.name']"));
	}
	
	public static WebElement newLocationCity(WebDriver wd) {
		return wd.findElement(By.cssSelector("[ng-model='ldCtrl.location.city']"));
	}
	
	public static WebElement newLocationState(WebDriver wd) {
		return wd.findElement(By.cssSelector("[ng-model='ldCtrl.location.state']"));
	}
	
	public static WebElement saveButton(WebDriver wd) {
		return wd.findElement(By.cssSelector("[class*='md-primary md-button md-ink-ripple'][type*='submit']"));
	}
	
	// Not Sure We Need This
	public static WebElement newLocationCancel(WebDriver wd) {
		return wd.findElement(By.cssSelector("[ng-click='ldCtrl.cancel()']"));
	}
	
	// Building
	public static WebElement locationCheckBox(WebDriver wd, String city, String state) {
		return wd.findElement(By.cssSelector("[aria-label*='Toggle  "+ city + ", " + state +"']"));
	}
	
	public static WebElement newBuildingButton(WebDriver wd) {
		return wd.findElement(By.id("bldgAdd"));
	}
	
	public static WebElement newBuildingName(WebDriver wd) {
		return wd.findElement(By.cssSelector("[ng-model='bldgCtrl.building.name']"));
	}
	
	// Not Sure We Need This
	public static WebElement newBuildingCancel(WebDriver wd) {
		return wd.findElement(By.cssSelector("[ng-click*='bldgCtrl.cancel()']"));
	}
	
	// Room
	public static WebElement buildingCheckBox(WebDriver wd) {
		return wd.findElement(By.xpath("//*[@id=\"loc1\"]/md-list-item[2]/div/div[1]/md-checkbox"));
	}
	
	public static WebElement newRoomButton(WebDriver wd) {
		return wd.findElement(By.id("roomAdd"));
	}
	
	public static WebElement newRoomName(WebDriver wd) {
		return wd.findElement(By.cssSelector("[ng-model*='rdCtrl.room.roomName']"));
	}

	public static WebElement newRoomCancel(WebDriver wd) {
		return wd.findElement(By.cssSelector("[ng-click*='rdCtrl.cancel()']"));
	}
	
	// Edit
	public static WebElement editButton(WebDriver wd) {
		return wd.findElement(By.id("locEdit"));
	}
	
	// Deactivate
	public static WebElement deactivateButton(WebDriver wd) {
		return wd.findElement(By.cssSelector("[ng-click='lCtrl.deleteSelected()']"));
	}
	
	public static WebElement confirmDeactivate(WebDriver wd) {
		return wd.findElement(By.cssSelector("[ng-click='dCtrl.delete()']"));
	}
}
