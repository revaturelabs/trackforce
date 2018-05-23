package com.revature.pageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.revature.tester.MethodUtil;




public class CirriculaPage {

	public static WebElement e = null;
	
	public static MethodUtil util = new MethodUtil();
	
	public static final String salesforceURL = "https://dev.assignforce.revaturelabs.com";
	public static final String vpUsername = "test.vpoftech@revature.com.int1";
	public static final String vpPassword = "yuvi1712";
	public static final String trainerUsername = "test.trainer@revature.com.int1";
	public static final String trainerPassword = "trainer123";
	
	
	public static void openSalesforceChrome(WebDriver driver) {
		driver.get(salesforceURL);
	}
	
	public static void openSalesforceFirefox(WebDriver driver) {
		driver.get(salesforceURL);
	}
	
	
	public static void authenticate(WebDriver driver) {
		driver.findElement(By.id("emc")).sendKeys("91757");
		driver.findElement(By.id("save")).click();
	}
	
	
	
	public static void loginVPCredentials(WebDriver driver) {
		MethodUtil.waitForLoad(driver, "//*[@id=\"username\"]").sendKeys(vpUsername);
		MethodUtil.waitForLoad(driver, "//*[@id=\"password\"]").sendKeys(vpPassword);
		MethodUtil.waitForLoad(driver, "//*[@id=\"Login\"]").click();
		
	}
	
	public static void loginTrainerCredentials(WebDriver driver) {
		MethodUtil.waitForLoad(driver, "//*[@id=\"username\"]").sendKeys(trainerUsername);
		MethodUtil.waitForLoad(driver, "//*[@id=\"password\"]").sendKeys(trainerPassword);
		MethodUtil.waitForLoad(driver, "//*[@id=\"Login\"]").click();
	}
//	/html/body/div/div[1]/ng-include/div/md-content/md-nav-bar/div/nav/ul/li[1]
	public static WebElement overviewTab(WebDriver driver) {
		e = MethodUtil.waitForLoad(driver, "/html/body/div/div[1]/ng-include/div/md-content/md-nav-bar/div/nav/ul/li[1]/a", 45);
		return e;
	}
	
	public static WebElement batchesTab(WebDriver driver) {
		e = MethodUtil.waitForLoad(driver, "/html/body/div/div[1]/ng-include/div/md-content/md-nav-bar/div/nav/ul/li[2]/a", 45);
		return e;
	}

	public static WebElement locationsTab(WebDriver driver) {
		e = MethodUtil.waitForLoad(driver, "/html/body/div/div[1]/ng-include/div/md-content/md-nav-bar/div/nav/ul/li[3]/a", 45);
		return e;
	}
	
//	/html/body/div/div[1]/ng-include/div/md-content/md-nav-bar/div/nav/ul/li[4]/a
	public static WebElement curriculaTab(WebDriver driver) {
		e = MethodUtil.waitForLoad(driver, "/html/body/div/div[1]/ng-include/div/md-content/md-nav-bar/div/nav/ul/li[4]/a", 45);
		return e;
		
	}
	
	public static WebElement trainersTab(WebDriver driver) {
		e = MethodUtil.waitForLoad(driver, "/html/body/div/div[1]/ng-include/div/md-content/md-nav-bar/div/nav/ul/li[5]/a", 45);
		return e;
	}
	
	public static WebElement reportsTab(WebDriver driver) {
		e = MethodUtil.waitForLoad(driver, "/html/body/div/div[1]/ng-include/div/md-content/md-nav-bar/div/nav/ul/li[7]/a", 45);
		return e;
	}
	
//	/html/body/div/div[1]/ng-include/div/md-content/md-nav-bar/div/nav/ul/li[8]
	public static WebElement settingsTab(WebDriver driver) {
		e = MethodUtil.waitForLoad(driver, "/html/body/div/div[1]/ng-include/div/md-content/md-nav-bar/div/nav/ul/li[8]/a", 45);
		return e;
	}
	
	public static WebElement logoutTab(WebDriver driver) {
		e = MethodUtil.waitForLoad(driver, "(//button)[1]", 45);
		return e;
	}
	
	
	
	
	
	/* Curricula Page specific methods */
	
	public static WebElement toggleCoreCurriculaPanel(WebDriver driver) {
		e = MethodUtil.waitForLoad(driver, "/html/body/div/div[2]/div/md-card/md-content/md-card/md-toolbar/div/button[2]", 45);
		return e;
	}
	
	public static boolean isCoreCurriculaPanelOpen(WebDriver driver) {
		e = MethodUtil.waitForLoad(driver, "/html/body/div/div[2]/div/md-card/md-content/md-card/md-card-content", 45);
		return e.isDisplayed();
	}
	
	
	public static WebElement editNthCurriculaPanelButton(WebDriver driver, int listIndex) {
		String xpath= "/html/body/div/div[2]/div/md-card/md-content/md-card/md-card-content/md-list/md-list-item[1]/button[1]";
		e = MethodUtil.waitForLoad(driver, xpath, 45);
		return e;
	}
	
	public static WebElement editCurriculumNameInputField(WebDriver driver) {
		e = MethodUtil.waitForLoad(driver, "/html/body/div[3]/md-dialog/md-dialog-content/form/div[2]/md-input-container/input", 45);
		return e;
	}
	
	
	public static WebElement editSkillsDropdownforCurriculum(WebDriver driver) {
		e = MethodUtil.waitForLoad(driver, "/html/body/div[3]/md-dialog/md-dialog-content/form/div[2]/md-input-container/md-select", 45);
		return e;
	}
	
	
	public static WebElement cancelCurriculumUpdateChanges(WebDriver driver) {
		e = MethodUtil.waitForLoad(driver, "/html/body/div[3]/md-dialog/md-dialog-actions/button[1]", 45);
		return e;
	}
	
	
	public static WebElement confirmButtonEditCurriculumPopup(WebDriver driver) {
		e = MethodUtil.waitForLoad(driver, "/html/body/div[3]/md-dialog/md-dialog-actions/button[2]", 45);
		return e;
	}
	
	
	
	public static WebElement removeNthCurriculaButton(WebDriver driver, int listIndex) {
		String xpath="/html/body/div/div[2]/div/md-card/md-content/md-card/md-card-content/md-list/md-list-item[" + listIndex + "]/button[2]";
		e = MethodUtil.waitForLoad(driver, xpath, 45);
		return e;
	}
	
	public static WebElement cancelRemoveCurriculumButton(WebDriver driver) {
		e = MethodUtil.waitForLoad(driver, "/html/body/div[3]/md-dialog/md-dialog-actions/button[1]", 45);
		return e;
	}
	
	public static WebElement confirmRemoveCurriculumButton(WebDriver driver) {
		e = MethodUtil.waitForLoad(driver, "/html/body/div[3]/md-dialog/md-dialog-actions/button[2]", 45);
		return e;
	}
	
	
	public static WebElement addNewCurriculumButton(WebDriver driver) {
		e = MethodUtil.waitForLoad(driver, "/html/body/div/div[2]/div/md-card/md-content/md-card/md-toolbar/div/button[1]", 45);
		return e;
	}
	
	public static WebElement trainerToggleCurriculumPanel(WebDriver driver) {
		e = MethodUtil.waitForLoad(driver, "/html/body/div/div[2]/div/md-card/md-content/md-card[1]/md-toolbar/div/button");
		return e;
	}
	
	public static WebElement trainerToggleFocusPanel(WebDriver driver) {
		e = MethodUtil.waitForLoad(driver, "/html/body/div/div[2]/div/md-card/md-content/md-card[2]/md-toolbar/div/button");
		return e;
	}
	
	
	

	
	/* Focus functionality	*/
	
	
	
	public static WebElement toggleFocusPanel(WebDriver driver) {
		e = MethodUtil.waitForLoad(driver, "(//button)[5]", 45);
		return e;
	}
	
	public static boolean isFocusPanelOpen(WebDriver driver) {
		e = MethodUtil.waitForLoad(driver, "/html/body/div/div[2]/div/md-card/md-content/md-card[2]/md-card-content", 45);
		return e.isDisplayed();
	}
	
	public static WebElement editFirstFocusPanelButton(WebDriver driver) {
		String xpath = "/html/body/div/div[2]/div/md-card/md-content/md-card[2]/md-card-content/md-list/md-list-item[1]/button[1]";
		e = MethodUtil.waitForLoad(driver, xpath, 45);
		return e;
	}
	
	
	public static WebElement cancelButtonEditFocusPopup(WebDriver driver) {
		e = MethodUtil.waitForLoad(driver, "/html/body/div[3]/md-dialog/md-dialog-actions/button[1]" , 45);
		return e;
	}
	
	
	
	public static WebElement confirmButtonEditFocusPopup(WebDriver driver) {
		e = MethodUtil.waitForLoad(driver, "/html/body/div[3]/md-dialog/md-dialog-actions/button[2]", 45);
		return e;
	}
	
	
	public static WebElement textInputEditFocusPopup(WebDriver driver) {
		e = MethodUtil.waitForLoad(driver, "/html/body/div[3]/md-dialog/md-dialog-content/form/div/md-input-container/input", 45);
		return e;
	}
	
	
	public static WebElement removeNthButtonFocus(WebDriver driver, int listIndex) {
		e = MethodUtil.waitForLoad(driver, "/html/body/div/div[2]/div/md-card/md-content/md-card[2]/md-card-content/md-list/md-list-item["+ listIndex +"]/button[2]");
		return e;
	}
	
	public static WebElement cancelButtonRemoveFocus(WebDriver driver) {
		e = MethodUtil.waitForLoad(driver, "/html/body/div[3]/md-dialog/md-dialog-actions/button[1]");
		return e;
	}
	
	public static WebElement addNewFocusButton(WebDriver driver) {
		e = MethodUtil.waitForLoad(driver, "/html/body/div/div[2]/div/md-card/md-content/md-card[2]/md-toolbar/div/button[1]", 45);
		return e;
	}
	
	public static WebElement editSkillsDropdownforFocus(WebDriver driver) {
		e = MethodUtil.waitForLoad(driver, "/html/body/div[3]/md-dialog/md-dialog-content/form/div[2]/md-input-container[2]/md-select", 45);
		return e;
	}
	
	public static WebElement editFocusNameInputField(WebDriver driver) {
		e = MethodUtil.waitForLoad(driver, "/html/body/div[3]/md-dialog/md-dialog-content/form/div[2]/md-input-container[1]/input", 45);
		return e;
	}
	
	
	
	
	/* Skill related Functionality */
	
	
	public static WebElement toggleSkillPanel(WebDriver driver) {
		e = MethodUtil.waitForLoad(driver, "/html/body/div/div[2]/div/md-card/md-content/md-card[3]/md-toolbar/div/button");
		return e;
	}
	
	
	public static boolean isSkillPanelOpen(WebDriver driver) {
		return MethodUtil.waitForLoad(driver, "/html/body/div/div[2]/div/md-card/md-content/md-card[3]/md-card-content", 45).isDisplayed();
	}
	
	
	public static WebElement addSkillInputField(WebDriver driver) {
		e = MethodUtil.waitForLoad(driver, "/html/body/div/div[2]/div/md-card/md-content/md-card[3]/md-card-content/form/div/md-input-container/input", 45);
		return e;
	}
	

//	Add Skill Button xpath	/html/body/div/div[2]/div/md-card/md-content/md-card[3]/md-card-content/form/div/div/div
	
	public static WebElement createButtonSkill(WebDriver driver) {
		e = MethodUtil.waitForLoad(driver, "//form[@name=\"skillForm\"]/div/div/div", 45);
		return e;
	}
}
