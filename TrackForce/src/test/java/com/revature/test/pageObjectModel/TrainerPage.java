package com.revature.pageObjectModel;

import static com.revature.tester.MethodUtil.waitForLoad;
import static com.revature.tester.MethodUtil.waitForLoadByAnyType;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TrainerPage {

	public static WebElement selectTrainersTab(WebDriver wd) {
		// return
		// wd.findElement(By.xpath("//*[@id=\"view\"]/md-card/md-toolbar[1]/div/button[1]"));
		return waitForLoad(wd, "/html/body/div/div[1]/ng-include/div/md-content/md-nav-bar/div/nav/ul/li[5]", 40);
	}

	public static WebElement selectAddTrainer(WebDriver wd) {
		// return
		// wd.findElement(By.xpath("//*[@id=\"view\"]/md-card/md-toolbar[1]/div/button[1]"));
		return waitForLoad(wd, "//*[@id=\"view\"]/md-card/md-toolbar[1]/div/button[1]");
	}

	public static WebElement selectViewPTOCalendar(WebDriver wd) {
		// return
		// wd.findElement(By.xpath("//*[@id=\"view\"]/md-card/md-toolbar[1]/div/button[2]"));
		return waitForLoadByAnyType(wd, By.cssSelector("[ng-click*='tCtrl.showCalendar()']"));

	}

	public static WebElement selectDownloadResume(WebDriver wd) {
		// return
		// wd.findElement(By.xpath("//*[@id=\"view\"]/md-card/md-content[1]/md-list/md-list-item[1]/div/div[1]/button[1]"));
		return waitForLoad(wd, "//*[@id=\"view\"]/md-card/md-content[1]/md-list/md-list-item[1]/div/div[1]/button[1]");

	}

	public static WebElement selectDeactivateTrainer(WebDriver wd) {
		// return
		// wd.findElement(By.xpath("//*[@id=\"view\"]/md-card/md-content[1]/md-list/md-list-item[1]/div/div[1]/button[2]"));
		return waitForLoad(wd, "//*[@id=\"view\"]/md-card/md-content[1]/md-list/md-list-item[1]/div/div[1]/button[2]");
	}

	public static WebElement selectReactivateTrainer(WebDriver wd) {
		// return
		// wd.findElement(By.xpath("//*[@id=\"view\"]/md-card/md-content[2]/md-list/md-list-item/button"));
		return waitForLoad(wd, "//*[@id=\"view\"]/md-card/md-content[2]/md-list/md-list-item/button");
	}

	public static List<WebElement> selectReactivateAllTrainers(WebDriver wd) {
		// List<WebElement> e =
		// wd.findElements(By.xpath("//*[@id=\"view\"]/md-card/md-content[2]"));
		return wd.findElements(By.xpath("//*[@id=\"view\"]/md-card/md-content[2]"));
		// Gets the parent tag for deactivated md-content list
	}

	public static WebElement selectSaveNewTrainer(WebDriver wd) {
		// return
		// wd.findElement(By.xpath("/html/body/div[3]/md-dialog/form/md-dialog-actions/button[1]"));
		return waitForLoad(wd, "/html/body/div[3]/md-dialog/form/md-dialog-actions/button[1]");

	}

	public static WebElement selectCancelAddTrainer(WebDriver wd) {
		// return
		// wd.findElement(By.xpath("/html/body/div[3]/md-dialog/form/md-dialog-actions/button[2]"));
		return waitForLoad(wd, "/html/body/div[3]/md-dialog/form/md-dialog-actions/button[2]");
	}

	public static WebElement insertTrainerFirstname(WebDriver wd) {
		// return wd.findElement(By.xpath("//*[@id=\"input_208\"]"));
		return waitForLoad(wd, "//*[@id=\"input_1\"]");
	}

	public static WebElement insertTrainerLastname(WebDriver wd) {
		// return wd.findElement(By.xpath("//*[@id=\"input_209\"]"));
		return waitForLoad(wd, "//*[@id=\"input_2\"]");
	}

	public static WebElement selectMoveBackInCalendar(WebDriver wd) {
		// return wd.findElement(By.xpath("//*[@id=\"navBack1\"]"));
		return waitForLoad(wd, "//*[@id=\"navBack1\"]", 20);
	}

	public static WebElement selectMoveForwardInCalendar(WebDriver wd) {
		// return wd.findElement(By.xpath("//*[@id=\"navForward1\"]"));
		return waitForLoad(wd, "//*[@id=\"navForward1\"]", 20);
	}

	public static WebElement selectCalendarWeekView(WebDriver wd) {
		// return
		// wd.findElement(By.xpath("//*[@id=\"calendarTabs1\"]/table/tbody/tr/td[1]/div[3]"));
		return waitForLoad(wd, "//*[@id=\"calendarTabs1\"]/table/tbody/tr/td[1]/div[3]");
	}

	public static WebElement selectCalendarMonthView(WebDriver wd) {
		// return
		// wd.findElement(By.xpath("//*[@id=\"calendarTabs1\"]/table/tbody/tr/td[2]/div[3]"));
		return waitForLoad(wd, "//*[@id=\"calendarTabs1\"]/table/tbody/tr/td[2]/div[3]");
	}

	public static WebElement selectCalendarAgendaView(WebDriver wd) {
		// return
		// wd.findElement(By.xpath("//*[@id=\"calendarTabs1\"]/table/tbody/tr/td[3]/div[3]"));
		return waitForLoad(wd, "//*[@id=\"calendarTabs1\"]/table/tbody/tr/td[3]/div[3]");
	}

	public static WebElement selectAddToCalandar(WebDriver wd) {
		// return wd.findElement(By.xpath("//*[@id=\"footer1\"]/tbody/tr/td[2]/div"));
		return waitForLoad(wd, "//*[@id=\"footer1\"]/tbody/tr/td[2]/div");
	}

	public static WebElement selectAddPTORequest(WebDriver wd) {
		// return
		// wd.findElement(By.xpath("//*[@id=\"dialogContent_241\"]/md-dialog-actions/button[1]"));
		// return waitForLoad(wd,
		// "//*[@id=\"dialogContent_241\"]/md-dialog-actions/button[1]");
		return waitForLoadByAnyType(wd, By.cssSelector("[ng-click*='tCtrl.showPTODialog()']"));
	}

	public static WebElement selectCancelCalendar(WebDriver wd) {
		// return
		// wd.findElement(By.xpath("//*[@id=\"dialogContent_241\"]/md-dialog-actions/button[2]"));
		// return waitForLoad(wd,
		// "//*[@id=\"dialogContent_241\"]/md-dialog-actions/button[2]");
		return waitForLoadByAnyType(wd, By.cssSelector("[ng-click*='tCtrl.hideCalendar()']"));
	}

	public static WebElement insertPTOStartDate(WebDriver wd) {
		// return wd.findElement(By.xpath("//*[@id=\"input_243\"]"));
		return waitForLoadByAnyType(wd, By.cssSelector("[ng-model*='ptoCtrl.startDate']"), 25).findElement(By.tagName("input"));
	}

	public static WebElement insertPTOEndDate(WebDriver wd) {
		// return wd.findElement(By.xpath("//*[@id=\"input_245\"]"));
		return waitForLoadByAnyType(wd, By.cssSelector("[ng-model*='ptoCtrl.endDate']"), 25).findElement(By.tagName("input"));
	}
	
	public static WebElement selectStartDateCalendarPopup(WebDriver wd) {
		insertPTOStartDate(wd);
		return wd.findElements(By.cssSelector("[ng-click*='ctrl.openCalendarPane($event)']")).get(0);
		//return waitForLoadByAnyType(wd, By.cssSelector("html/body/div[3]/md-dialog/form/div/md-input-container[1]/md-datepicker/div[1]/button"));
	}
	public static WebElement selectEndDateCalendarPopup(WebDriver wd) {
		insertPTOEndDate(wd);
		return wd.findElements(By.cssSelector("[ng-click*='ctrl.openCalendarPane($event)']")).get(1);
		//return waitForLoadByAnyType(wd, By.xpath("html/body/div[3]/md-dialog/form/div/md-input-container[2]/md-datepicker/div[1]/button"));
	}
	//*[@id="dialogContent_12"]/div/md-input-container[2]/md-datepicker/div[1]/button
	public static WebElement selectSendPTORequest(WebDriver wd) {
		// return
		// wd.findElement(By.xpath("/html/body/div[3]/md-dialog/form/md-dialog-actions/button[1]"));
		return waitForLoad(wd, "/html/body/div[3]/md-dialog/form/md-dialog-actions/button[1]");
	}

	public static WebElement selectCancelPTORequest(WebDriver wd) {
		// return
		// wd.findElement(By.xpath("/html/body/div[3]/md-dialog/form/md-dialog-actions/button[2]"));
		// return waitForLoad(wd,
		// "/html/body/div[3]/md-dialog/form/md-dialog-actions/button[2]");
		return waitForLoadByAnyType(wd, By.cssSelector("[ng-click*='ptoCtrl.cancel()']"));
	}

	public static WebElement selectStartDateDropdown(WebDriver wd) {
		// return
		// wd.findElement(By.xpath("//*[@id=\"dialogContent_246\"]/div/md-input-container[1]/md-datepicker/div/button"));
		return waitForLoad(wd, "//*[@id=\"dialogContent_246\"]/div/md-input-container[1]/md-datepicker/div/button");
	}

	public static WebElement selectEndDateDropdown(WebDriver wd) {
		// return
		// wd.findElement(By.xpath("//*[@id=\"dialogContent_246\"]/div/md-input-container[2]/md-datepicker/div/button"));
		return waitForLoad(wd, "//*[@id=\"dialogContent_246\"]/div/md-input-container[2]/md-datepicker/div/button");
	}

	public static WebElement selectTodayOnCalendar(WebDriver wd) {
		// return wd.findElement(By.xpath("//*[@id=\"todayButton1\"]"));
		return waitForLoad(wd, "//*[@id=\"todayButton1\"]");
	}

	public static WebElement selectCalanderList(WebDriver wd) {
		// return wd.findElement(By.xpath("//*[@id=\"calendarListButton1\"]"));
		return waitForLoad(wd, "//*[@id=\"calendarListButton1\"]");
	}

	public static WebElement selectCalanderMonthDropdown(WebDriver wd) {
		// return wd.findElement(By.xpath("//*[@id=\"dateEditableBox1\"]"));
		return waitForLoad(wd, "//*[@id=\"dateEditableBox1\"]");
	}

	public static WebElement selectProfile(WebDriver wd) {
		// return
		// wd.findElement(By.xpath("//*[@id=\"view\"]/md-card/md-content[1]/md-list/md-list-item[1]/div/button"));
		return waitForLoad(wd, "//*[@id=\"view\"]/md-card/md-content[1]/md-list/md-list-item[1]/div/button");
	}

	public static List<WebElement> selectDeactivatedTrainersList(WebDriver wd) {
		selectDeactivateTrainer(wd);
		return waitForLoad(wd, "//*[@id=\"view\"]/md-card/md-content[2]").findElements(By.tagName("md-list-item"));
	}

	public static List<WebElement> selectActivatedTrainersList(WebDriver wd) {
		selectReactivateTrainer(wd);
		return waitForLoad(wd, "//*[@id=\"view\"]/md-card/md-content[1]").findElements(By.tagName("md-list-item"));
	}

	public static WebElement selectTrainerByName(WebDriver wd, String firstname, String lastname) {
		List<WebElement> names = selectActivatedTrainersList(wd);
		WebElement name = null;
		for (WebElement we : names)
			if (we.getText().contains(firstname + " " + lastname))
				return name = we;
		names = selectDeactivatedTrainersList(wd);
		for (WebElement we : names)
			if (we.getText().contains(firstname + " " + lastname))
				return name = we;
		return name;
	}
	
	public static WebElement selectTrainerProfileButton(WebDriver wd, String firstname, String lastname) {
		WebElement name = null;
		List<WebElement> names = selectActivatedTrainersList(wd);
		for (WebElement we : names)
			if (we.getText().contains(firstname + " " + lastname))
				name = we;
		return name.findElements(By.tagName("button")).get(0);
	}
	
	public static WebElement selectTrainerDownloadResumeButton(WebDriver wd, String firstname, String lastname) {
		WebElement name = null;
		List<WebElement> names = selectActivatedTrainersList(wd);
		for (WebElement we : names)
			if (we.getText().contains(firstname + " " + lastname))
				name = we;
		System.out.println("reactivated: " + name.findElements(By.tagName("button")).get(0).getText());
		return name.findElements(By.tagName("button")).get(1);
		
	}

	public static WebElement selectTrainerDeactivateButton(WebDriver wd, String firstname, String lastname) {
		WebElement name = null;
		List<WebElement> names = selectActivatedTrainersList(wd);
		for (WebElement we : names)
			if (we.getText().contains(firstname + " " + lastname))
				name = we;
		return name.findElements(By.tagName("button")).get(2);
	}
	
	public static WebElement selectTrainerReactivateButton(WebDriver wd, String firstname, String lastname) {
		WebElement name = null;
		List<WebElement> names = selectDeactivatedTrainersList(wd);
		for (WebElement we : names)
			if (we.getText().contains(firstname + " " + lastname))
				name = we;
		return name.findElements(By.tagName("button")).get(0);
	}
}