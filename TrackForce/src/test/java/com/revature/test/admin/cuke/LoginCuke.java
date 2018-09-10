package com.revature.test.admin.cuke;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.revature.test.admin.pom.HomeTab;
import com.revature.test.admin.pom.Login;
import com.revature.test.utils.LoginUtil;
import com.revature.test.utils.ServiceHooks;
import com.revature.test.utils.TestConfig;
import com.revature.test.utils.WebDriverUtil;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;



public class LoginCuke {
	
	
	@Given("^I connect to caliber$")
	public void i_connect_to_caliber(){

		ServiceHooks.driver = WebDriverUtil.getChromeDriver();
		//ServiceHooks.driver.manage().window().maximize();
		ServiceHooks.driver.get(TestConfig.getBaseURL());
		ServiceHooks.wait = new WebDriverWait(ServiceHooks.driver,3);

	
	}
	
	@Given("^I login as an Administrator$")
	public void i_login_as_an_administrator() throws Throwable {
		System.out.println("Running administrator login meta");
		i_enter_the_correct_admin_login_information();
		i_click_Submit();
	}
	
	@Given("^I login as an Associate$")
	public void i_login_as_an_associate() throws Throwable {
		System.out.println("Running associate login meta");
		i_enter_the_correct_associate_login_information();
		i_click_Submit();

	}
	
	//Use this once we make trainer feature
	@Given("^I login as a Trainer$")
	public void i_login_as_a_trainer() throws Throwable {
		System.out.println("Running trainer login meta");
		i_enter_the_correct_trainer_login_information();
		i_click_Submit();
	}
	
	@Given("^the login page loads$")
	public void the_login_page_loads() throws Throwable {
		assertEquals(ServiceHooks.driver.getCurrentUrl(), LoginUtil.getPropertyValue("urlBeingUsed") + "#/login");
	}

	@When("^I enter the correct admin login information$")
	public void i_enter_the_correct_admin_login_information() throws Throwable {
		System.out.println("Entering correct admin login info");
		Login.login(LoginUtil.getPropertyValue("adminUN"),LoginUtil.getPropertyValue("adminPW"),ServiceHooks.driver);
	}
	@When("^I enter the correct associate login information$")
	public void i_enter_the_correct_associate_login_information() throws Throwable {
		System.out.println("Entering correct associate login info");
	   Login.login(LoginUtil.getPropertyValue("associateUN"),LoginUtil.getPropertyValue("associatePW"),ServiceHooks.driver);
	}
	@When("^I enter the correct manager login information$")
	public void i_enter_the_correct_manager_login_information() throws Throwable {
		System.out.println("Entering correct testmanager login info");
	   Login.login(LoginUtil.getPropertyValue("stagingManagerUN"),LoginUtil.getPropertyValue("stagingManagerPW"),ServiceHooks.driver);
	}
	@When("^I enter the correct trainer login information$")
	public void i_enter_the_correct_trainer_login_information() throws Throwable {
		System.out.println("Entering correct trainer login info");
	   Login.login(LoginUtil.getPropertyValue("trainerUN"),LoginUtil.getPropertyValue("trainerPW"),ServiceHooks.driver);
	}
	@When("^I enter the correct delivery login information$")
	public void i_enter_the_correct_sales_login_information() throws Throwable {
		System.out.println("Entering correct sales login info");
	   Login.login(LoginUtil.getPropertyValue("salesDeliveryUN"),LoginUtil.getPropertyValue("salesDeliveryPW"),ServiceHooks.driver);
	}

	@When("^I click Submit$")
	public void i_click_Submit() throws Throwable {
		System.out.println("Clicking submit (login)");
		Login.getSignin(ServiceHooks.driver).click();
	}
	
	@When("^I enter a correct username without a password$")
	public void i_enter_a_correct_username_without_a_password() throws Throwable {
		System.out.println("Entering correct username with no password");

		Login.login(LoginUtil.getPropertyValue("adminUN"), "", ServiceHooks.driver);
	}
	
	@When("^I enter a correct password with an incorrect username$")
	public void i_enter_a_correct_password_with_an_incorrect_username() throws Throwable {
		System.out.println("Entering incorrect username with correct password");

		Login.login(LoginUtil.getPropertyValue("notAUsername"), LoginUtil.getPropertyValue("adminPW"), ServiceHooks.driver);

	}

	@When("^I enter an incorrect password with an incorrect username$")
	public void i_enter_an_incorrect_password_with_an_incorrect_username() throws Throwable {

		Login.login(LoginUtil.getPropertyValue("notAUsername"), LoginUtil.getPropertyValue("notAPassword"), ServiceHooks.driver);

	}
	
	@When("^I enter a correct username with an incorrect password$")
	public void i_enter_a_correct_username_with_an_incorrect_password() throws Throwable {

		Login.login(LoginUtil.getPropertyValue("adminUN"),LoginUtil.getPropertyValue("notAPassword"), ServiceHooks.driver);
	}

	@When("^I enter a correct password without a username$")
	public void i_enter_a_correct_password_without_a_username() throws Throwable {

	    Login.login("", LoginUtil.getPropertyValue("adminPW"), ServiceHooks.driver);

	}
	
	@When("^if I click Log out$")
	public void if_I_click_Log_out() throws Throwable {
		System.out.println("clicking logout");
		ServiceHooks.driver.findElement(By.xpath("/html/body/app-component/app-navbar/nav/div/ul[2]/li[1]/a")).click();
	}

	@Then("^I should remain on the login page$")
	public void i_should_remain_on_the_login_page() throws Throwable {
		ServiceHooks.wait.until(ExpectedConditions.urlContains(LoginUtil.getPropertyValue("urlBeingUsed") + "#/login"));
		assertEquals(HomeTab.getCurrentURL(ServiceHooks.driver), LoginUtil.getPropertyValue("urlBeingUsed") + "#/login");
	}

	@Then("^I should be taken to the home page$")
	public void i_should_be_taken_to_the_home_page() throws Throwable {
		ServiceHooks.wait.until(ExpectedConditions.urlContains( LoginUtil.getPropertyValue("urlBeingUsed") + "#/app-home"));
		assertEquals(HomeTab.getCurrentURL(ServiceHooks.driver),LoginUtil.getPropertyValue("urlBeingUsed") + "#/app-home");
	}
	
	@Then("^I should be taken to the trainer home page$")
	public void i_should_be_taken_to_the_trainer_home_page() throws Throwable {
		ServiceHooks.wait.until(ExpectedConditions.urlContains(LoginUtil.getPropertyValue("urlBeingUsed") +  "#/trainer-view"));
		assertEquals(HomeTab.getCurrentURL(ServiceHooks.driver), LoginUtil.getPropertyValue("urlBeingUsed") + "#/trainer-view");
	}
	
	@Then("^I should be taken to the associate home page$")
	public void i_should_be_taken_to_the_associate_home_page() throws Throwable {
		ServiceHooks.wait.until(ExpectedConditions.urlContains( LoginUtil.getPropertyValue("urlBeingUsed") + "#/associate-view"));
		assertEquals(ServiceHooks.driver.getCurrentUrl(), LoginUtil.getPropertyValue("urlBeingUsed") + "#/associate-view");
	}

	@Then("^I should be on the login page$")
	public void i_should_be_on_the_login_page() throws Throwable {
		ServiceHooks.wait.until(ExpectedConditions.urlContains( LoginUtil.getPropertyValue("urlBeingUsed") + "#/login"));
		assertEquals(ServiceHooks.driver.getCurrentUrl(), LoginUtil.getPropertyValue("urlBeingUsed") + "#/login");
	}
	
	@After
	public void close() {
		ServiceHooks.driver.quit();
	}
	
	
}
