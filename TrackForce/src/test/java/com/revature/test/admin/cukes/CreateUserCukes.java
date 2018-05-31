package com.revature.test.admin.cukes;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import com.revature.test.admin.pom.CreateUserTab;
import com.revature.test.admin.pom.HomeTab;
import com.revature.test.admin.testclasses.AdminSuite;
import com.revature.test.utils.ServiceHooks;
import com.revature.test.utils.TestConfig;

public class CreateUserCukes extends AdminSuite {
	static WebElement e = null;
	static WebDriver d=ServiceHooks.driver;
	
	@Given("^I click on Create User Tab$")
	public static void clickCreateUserTab() {
		try {
			
			CreateUserTab.getCreateUserTab(d).click();
			
		} catch (Throwable e) {
			System.out.println("Failed to click Create User Tab");
			fail("Failed to click Create User Tab");
			
		}
	}

	@Given("^Create User Tab loads$")
	public static void loadedCreateUserTab() {
		try {
			Thread.sleep(500);
			assertEquals(CreateUserTab.getCurrentURL(d),TestConfig.getBaseURL()+"/create-user");
			
		} catch (Throwable e) {
			System.out.println("Failed to get current URL");
			fail("Failed to get current URL");
			
		}
	}

	@When("^I type in a username$")
	public static void inputUsername(WebDriver d, String username) {
		try {
			Thread.sleep(250);
			CreateUserTab.getUsername(d).sendKeys(username);
			
		} catch (Throwable e) {
			System.out.println("Failed to input username");
			fail("Failed to input username");
		}
	}

	@When("^I type in a password$")
	public static void inputPassword(WebDriver d, String password) {
		try {
			Thread.sleep(250);
			CreateUserTab.getPassword(d).sendKeys(password);
			
		} catch (Throwable e) {
			System.out.println("Failed to click Create User Tab");
			fail("Failed to click Create User Tab");
			
		}
	}

	@When("^I confirm the password$")
	public static void inputPasswordConfirm(WebDriver d, String password) {
		try {
			Thread.sleep(250);
			CreateUserTab.getPasswordConfirm(d).sendKeys(password);
			
		} catch (Throwable e) {
			System.out.println("Failed to input username");
			fail("Failed to input username");
			
		}
	}

	@When("^I check the Administrator role$")
	public static void clickAdminRadio(WebDriver d) {
		try {
			Thread.sleep(250);
			CreateUserTab.getAdminRadio(d).click();
			
		} catch (Throwable e) {
			System.out.println("Failed to click Admin radio button");
			fail("Failed to click Admin radio button");
			
		}
	}

	@When("^I check the Manager role$")
	public static void clickManagerRadio(WebDriver d) {
		try {
			Thread.sleep(250);
			CreateUserTab.getManagerRadio(d).click();
			
		} catch (Throwable e) {
			System.out.println("Failed to click Manager radio button");
			fail("Failed to click Manager radio button");
			
		}
	}

	@When("^I check the VP role$")
	public static void clickVPRadio(WebDriver d) {
		try {
			Thread.sleep(250);
			CreateUserTab.getVPRadio(d).click();
			
		} catch (Throwable e) {
			System.out.println("Failed to click VP radio button");
			fail("Failed to click VP radio button");
			
		}
	}

	@When("^I check the Associate role$")
	public static void clickAssociateRadio(WebDriver d) {
		try {
			Thread.sleep(250);
			CreateUserTab.getAssociateRadio(d).click();
			
		} catch (Throwable e) {
			System.out.println("Failed to click Associate radio button");
			fail("Failed to click Associate radio button");
			
		}
	}

	@Then("^I press submit$")
	public static void submitForm(WebDriver d) {
		try {
			Thread.sleep(250);
			CreateUserTab.getSubmit(d).click();
			
		} catch (Throwable e) {
			System.out.println("Failed to click submit");
			fail("Failed to click submit");
			
		}
	}

	@Then("^Cancel the Error pop up if creating the user failed$")
	public static void cancelAlert(WebDriver d) {
		try {
			Thread.sleep(500);
			HomeTab.phone(d); //attempt to find the phone link to see if submit was successful and we are on home tab
			System.out.println("No alert detected; new user creation was successful");
			 //element click successful; no alert popped up
		} catch (Throwable e) {
			System.out.println("Alert detected; new user creation failed");
			Alert alert = d.switchTo().alert();
			fail("Alert detected; new user creation failed");
			alert.dismiss();
			
			Actions action= new Actions(d);
			action.sendKeys(Keys.ESCAPE).perform();
			action.sendKeys(Keys.ESCAPE);
			 // alert popped up saying there was error with creating a new user
		}
	}
}
