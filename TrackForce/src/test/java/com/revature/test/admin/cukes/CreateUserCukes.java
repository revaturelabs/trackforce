package com.revature.test.admin.cukes;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import com.revature.test.admin.pom.CreateUserTab;
import com.revature.test.admin.pom.HomeTab;
import com.revature.test.admin.pom.Login;
import com.revature.test.admin.testclasses.AdminSuite;
import com.revature.test.utils.ServiceHooks;
import com.revature.test.utils.TestConfig;
import com.revature.test.utils.WaitToLoad;

public class CreateUserCukes extends AdminSuite {
	static WebElement e = null;

		
	
	@Given("^I click on Create User Tab$")
	public static void clickCreateUserTab() {
		try {
			Thread.sleep(100);
			CreateUserTab.getCreateUserTab(ServiceHooks.driver).click();
			
		} catch (Throwable e) {
			fail("Failed to click Create User Tab");
			
		}
	}
	
	@Given("^I login$")
	public static void login() {
		Login.login("TestAdmin","TestAdmin",ServiceHooks.driver);
		Login.getSignin(ServiceHooks.driver).click();
	}

	@Given("^Create User Tab loads$")
	public static void loadedCreateUserTab() {
		try {
			Thread.sleep(500);
			assertEquals(CreateUserTab.getCurrentURL(ServiceHooks.driver),TestConfig.getBaseURL()+"create-user");
			
		} catch (Throwable e) {
			System.out.println("Failed to get current URL");
			fail("Failed to get current URL");
			
		}
	}
	
	@When("^I type in a valid username \"([^\"]*)\"$")
	public void i_type_in_a_valid_username(String username) {
		CreateUserTab.getUsername(ServiceHooks.driver).sendKeys(username);
	}

	@When("^I type in a valid password\"([^\"]*)\"$")
	public void i_type_in_a_valid_password(String password){
		CreateUserTab.getPassword(ServiceHooks.driver).sendKeys(password);
	}

	@When("^I confirm the password\"([^\"]*)\"$")
	public void i_confirm_the_password(String password) throws Throwable {
		CreateUserTab.getPasswordConfirm(ServiceHooks.driver).sendKeys(password);
	}
	@When("^I check the \"([^\"]*)\" role$")
	public void i_check_the_role(String role) throws Throwable {
	    CreateUserTab.selectRole(ServiceHooks.driver, role);
	}
	
	@When("^I press submit$")
	public static void submitForm() {
		try {
			Thread.sleep(250);
			CreateUserTab.getSubmit(ServiceHooks.driver).click();
			
		} catch (Throwable e) {
			System.out.println("Failed to click submit");
			fail("Failed to click submit");
			
		}
	}

	@Then("^A Pop Up Error should occur$")
	public static void cancelAlert() throws InterruptedException {
		Thread.sleep(1500);
		try {
			CreateUserTab.getPopup(ServiceHooks.driver);
		} catch (Throwable e) {
			fail("No Pop up created");
		}
	}
	
	@Then("^Pop Up Error should not occur$")
	public static void No_popup() {
		try {
			if(CreateUserTab.getPopup(ServiceHooks.driver).isDisplayed()) {
				fail("pop up was created");
			}
		} catch (Throwable e) {
			fail("pop up was created");
		}
	}
	
	@Then("^A new User should be created$")
	public void a_new_User_should_be_created() throws Throwable {
		Thread.sleep(1500);
		assertEquals(HomeTab.getCurrentURL(ServiceHooks.driver),"http://34.227.178.103:8090/NGTrackForce/app-home");
	}
}
