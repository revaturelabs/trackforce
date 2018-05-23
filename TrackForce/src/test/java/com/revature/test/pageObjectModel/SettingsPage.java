package com.revature.pageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SettingsPage {
	
	public static WebElement settingsTab(WebDriver wd) {
		try { Thread.sleep(5000); } catch (InterruptedException e) { e.printStackTrace(); }
		return wd.findElement(By.xpath("/html/body/div[1]/div[1]/ng-include/div/md-content/md-nav-bar/div/nav/ul/li[8]/a"));
	}
	
	public static WebElement timelineTrainersPerPage(WebDriver wd) {
		return wd.findElement(By.cssSelector("[ng-model*='sCtrl.settings.trainersPerPage']"));
	}
	
	public static WebElement reportsOutgoingGrads(WebDriver wd) {
		return wd.findElement(By.cssSelector("[ng-model*='sCtrl.settings.reportGrads']"));
	}
	
	public static WebElement reportsIncomingCandidates(WebDriver wd) {
		return wd.findElement(By.cssSelector("[ng-model*='sCtrl.settings.reportIncomingGrads']"));
	}
	
	// Drop Down Menu
	public static WebElement defaultLocation(WebDriver wd) {
		return wd.findElement(By.cssSelector("[ng-model*='sCtrl.settings.defaultLocation']"));
//		return wd.findElement(By.xpath("//*[@id=\"view\"]/md-card/md-content/md-list/md-list-item[4]/md-input-container"));
	}

	// Drop Down Menu - only updates when the page is updated
	public static WebElement defaultBuilding(WebDriver wd) {
		return wd.findElement(By.cssSelector("[ng-model*='sCtrl.settings.defaultBuilding']"));
	}
	
	public static WebElement minBatchSize(WebDriver wd) {
		return wd.findElement(By.cssSelector("[ng-model*='sCtrl.settings.minBatchSize']"));
	}
	
	public static WebElement maxBatchSize(WebDriver wd) {
		return wd.findElement(By.cssSelector("[ng-model*='sCtrl.settings.maxBatchSize']"));
	}
	
	public static WebElement defaultBatchLength(WebDriver wd) {
		return wd.findElement(By.cssSelector("[ng-model*='sCtrl.settings.batchLength']"));
	}
	
	public static WebElement minBetweenTrainerBatch(WebDriver wd) {
		return wd.findElement(By.cssSelector("[ng-model*='sCtrl.settings.trainerBreakDays']"));
	}
	
	// -------- only used when logged-in as VP --------
	public static WebElement findSaveButton(WebDriver wd) {
		return wd.findElement(By.cssSelector("[ng-click='sCtrl.updateSettings()']"));
//		return wd.findElement(By.xpath("//*[@id=\"view\"]/md-card/md-content/md-list/section/button[1]"));
	}

	// -------- only used when logged-in as VP --------
	public static WebElement findResetButton(WebDriver wd) {
		return wd.findElement(By.xpath("//*[@id=\"view\"]/md-card/md-content/md-list/section/button[2]"));
	}
}
