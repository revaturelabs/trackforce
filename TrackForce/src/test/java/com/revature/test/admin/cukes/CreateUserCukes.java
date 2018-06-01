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
			Thread.sleep(100);
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
	
	@When("^I type in a \"([^\"]*)\" username$")
	public void i_type_in_a_username(String username) throws Throwable {
		try {
			Thread.sleep(250);
			CreateUserTab.getUsername(d).clear();
			CreateUserTab.getUsername(d).sendKeys(username);
			
		} catch (Throwable e) {
			System.out.println("Failed to input username");
			fail("Failed to input username");
		}
	}

	@When("^I type in a \"([^\"]*)\" password$")
	public void i_type_in_a_password(String password){
		try {
			Thread.sleep(250);
			CreateUserTab.getPassword(d).clear();
			CreateUserTab.getPassword(d).sendKeys(password);
			
		} catch (Throwable e) {
			System.out.println("Failed to click Create User Tab");
			fail("Failed to click Create User Tab");
			
		}
	}

	@When("^I confirm the \"([^\"]*)\"$")
	public void i_confirm_the(String password){
		try {
			Thread.sleep(250);
			CreateUserTab.getPasswordConfirm(d).clear();
			CreateUserTab.getPasswordConfirm(d).sendKeys(password);
			
		} catch (Throwable e) {
			System.out.println("Failed to input username");
			fail("Failed to input username");
			
		}
	}
	@When("^I check the Administrator role$")
	public static void clickAdminRadio() {
		try {
			Thread.sleep(250);
			CreateUserTab.getAdminRadio(d).click();
			
		} catch (Throwable e) {
			System.out.println("Failed to click Admin radio button");
			fail("Failed to click Admin radio button");
			
		}
	}

	@When("^I check the Manager role$")
	public static void clickManagerRadio() {
		try {
			Thread.sleep(250);
			CreateUserTab.getManagerRadio(d).click();
			
		} catch (Throwable e) {
			System.out.println("Failed to click Manager radio button");
			fail("Failed to click Manager radio button");
			
		}
	}

	@When("^I check the Trainer role$")
	public static void clickVPRadio() {
		try {
			Thread.sleep(250);
			CreateUserTab.getTrainerRadio(d).click();
			
		} catch (Throwable e) {
			System.out.println("Failed to click VP radio button");
			fail("Failed to click VP radio button");
			
		}
	}

	@When("^I check the Associate role$")
	public static void clickAssociateRadio() {
		try {
			Thread.sleep(250);
			CreateUserTab.getAssociateRadio(d).click();
			
		} catch (Throwable e) {
			System.out.println("Failed to click Associate radio button");
			fail("Failed to click Associate radio button");
			
		}
	}
	
	@When("^I check the Delivary role$")
	public static void clickDelivaryRadio() {
		try {
			Thread.sleep(250);
			CreateUserTab.getDelivaryRadio(d).click();
			
		} catch (Throwable e) {
			System.out.println("Failed to click Delivary radio button");
			fail("Failed to click Delivary radio button");
			
		}
	}
	
	@Then("^I press submit$")
	public static void submitForm() {
		try {
			Thread.sleep(250);
			CreateUserTab.getSubmit(d).click();
			
		} catch (Throwable e) {
			System.out.println("Failed to click submit");
			fail("Failed to click submit");
			
		}
	}

	@Then("^Pop Up Error should occur$")
	public static void cancelAlert() {
		try {
			CreateUserTab.getPopup(d);
		} catch (Throwable e) {
			fail("No Pop up created");
		}
	}
	
	@Then("^Pop Up Error should not occur$")
	public static void No_popup() {
		try {
			if(CreateUserTab.getPopup(d).isDisplayed()) {
				fail("pop up was created");
			}
		} catch (Throwable e) {
			fail("pop up was created");
		}
	}
}
