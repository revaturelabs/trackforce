package com.revature.test.admin.cukes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import com.revature.test.admin.pom.CreateUserTab;
import com.revature.test.admin.testclasses.AdminSuite;
import com.revature.test.utils.TestConfig;

public class CreateUserCukes extends AdminSuite {
	static WebElement e = null;
	
	private static String user = "username";
	private static String pass = "password";
	
	@Given("^We click on Create User Tab$")
	public static boolean clickCreateUserTab(WebDriver d) {
		try {
			//Thread.sleep(5000);
			CreateUserTab.getCreateUserTab(d).click();
			return true;
		} catch (Throwable e) {
			System.out.println("Failed to click Create User Tab");
			return false;
		}
	}

	@Given("^Create User Tab loads$")
	public static boolean onCreateUserTab(WebDriver d) {
		try {
			if (CreateUserTab.getCurrentURL(d).equals(TestConfig.getBaseURL() + "/create-user")){
				return true;
			}
			System.out.println("Current URL does not end with /create-user");
			return false;
		} catch (Throwable e) {
			System.out.println("Failed to get current URL");
			return false;
		}
	}
	
	@When("^I type in a username$")
	public static boolean inputUsername(WebDriver d) {
		try {
			CreateUserTab.getUsername(d).sendKeys(user);
			return true;
		} catch (Throwable e) {
			System.out.println("Failed to input username");
			return false;
		}
	}
	
	@When("^I type in a password$")
	public static boolean inputPassword(WebDriver d) {
		try {
			CreateUserTab.getPassword(d).sendKeys(pass);
			return true;
		} catch (Throwable e) {
			System.out.println("Failed to click Create User Tab");
			return false;
		}
	}

	@When("^I confirm the password$")
	public static boolean inputPasswordConfirm(WebDriver d) {
		try {
			CreateUserTab.getPasswordConfirm(d).sendKeys(user);
			return true;
		} catch (Throwable e) {
			System.out.println("Failed to input username");
			return false;
		}
	}
	
	@When("^I check the Administrator role$")
	public static boolean clickAdminRadio(WebDriver d) {
		try {
			CreateUserTab.getAdminRadio(d).click();
			return true;
		} catch (Throwable e) {
			System.out.println("Failed to click Admin radio button");
			return false;
		}
	}
	
	@When("^I check the Manager role$")
	public static boolean clickManagerRadio(WebDriver d) {
		try {
			CreateUserTab.getManagerRadio(d).click();
			return true;
		} catch (Throwable e) {
			System.out.println("Failed to click Manager radio button");
			return false;
		}
	}
	
	@When("^I check the VP role$")
	public static boolean clickVPRadio(WebDriver d) {
		try {
			CreateUserTab.getVPRadio(d).click();
			return true;
		} catch (Throwable e) {
			System.out.println("Failed to click VP radio button");
			return false;
		}
	}
	
	@Then("^I press submit$")
	public static boolean submitForm(WebDriver d) {
		try {
			CreateUserTab.getSubmit(d).click();
			return true;
		} catch (Throwable e) {
			System.out.println("Failed to click submit");
			return false;
		}
	}
}
