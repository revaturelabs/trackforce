package com.revature.pageObjectModel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.revature.tester.MethodUtil;



public class ProfilePage {
	
	public static WebElement selectProfileTab(WebDriver wd) {
		return MethodUtil.waitForLoadByAnyType(wd, By.xpath("/html/body/div/div[1]/ng-include/div/md-content/md-nav-bar/div/nav/ul/li[6]/a"), 100);
	}
	
	public static WebElement insertFirstname(WebDriver wd) {
		
		//return wd.findElements(By.tagName("input")).get(0);
		return MethodUtil.waitForLoadByAnyType(wd, By.cssSelector("[ng-model*='pCtrl.trainer.firstName']"));
	}

	public static WebElement insertLastname(WebDriver wd) {
		
		//return wd.findElements(By.tagName("input")).get(1);
		return MethodUtil.waitForLoadByAnyType(wd, By.cssSelector("[ng-model*='pCtrl.trainer.lastName']"));
	}
	
	public static WebElement selectAddResume(WebDriver wd) {
		return MethodUtil.waitForLoadByAnyType(wd, By.cssSelector("[ng-show*='lockProfile']"));
	}
	
	public static WebElement selectSaveSkill(WebDriver wd) {
		return MethodUtil.waitForLoadByAnyType(wd, By.xpath("//*[@id=\"view\"]/md-card[1]/md-content[2]/md-toolbar/div/button"));
	}
	
	public static WebElement selectChooseSkill(WebDriver wd) {
		return MethodUtil.waitForLoadByAnyType(wd, By.xpath("//*[@id=\"view\"]/md-card[1]/md-content[2]/div/md-chips/md-chips-wrap/md-chip[1]"), 20);
	}
	
	public static List<WebElement> getChooseSkillList(WebDriver wd){
		selectChooseSkill(wd);
		return wd.findElements(By.tagName("md-chip"));
	}
	
	public static WebElement selectCurrentSkill(WebDriver wd) {
		return MethodUtil.waitForLoadByAnyType(wd, By.xpath("//*[@id=\"view\"]/md-card[1]/md-content[2]/div/md-list/button[1]"), 20);
	}
	
	public static List<WebElement> getCurrentSkillList(WebDriver wd){
		selectCurrentSkill(wd);
		return wd.findElement(By.tagName("md-list")).findElements(By.tagName("button"));
	}
	
	public static WebElement selectAddCertification(WebDriver wd) {
		return MethodUtil.waitForLoadByAnyType(wd, By.cssSelector("[for*='input-file-cert']"));
	}
}
