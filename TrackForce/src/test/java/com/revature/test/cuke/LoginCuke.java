package com.revature.test.cuke;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.revature.test.pom.Login;
import com.revature.test.pom.NavBar;
import com.revature.test.utils.LoginUtil;
import com.revature.test.utils.ServiceHooks;
import com.revature.test.utils.TestConfig;
import com.revature.test.utils.WebDriverUtil;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;



public class LoginCuke {

	String baseUrl = System.getenv("url");

	@Given("^I connect to trackforce$")
	public void i_connect_to_trackforce() throws Throwable {
		ServiceHooks.driver = WebDriverUtil.getChromeDriver();
		ServiceHooks.driver.manage().window().maximize();
		ServiceHooks.driver.get(baseUrl);
		ServiceHooks.wait = new WebDriverWait(ServiceHooks.driver,4);
	}

	@Given("^I login as an Administrator$")
	public void i_login_as_an_administrator() throws Throwable {
		i_submit_the_correct_admin_login_information();
	}

	@Given("^I login as an Associate$")
	public void i_login_as_an_Associate() throws Throwable {
		i_submit_the_correct_associate_login_information();
	}

	//Use this once we make trainer feature
	@Given("^I login as a Trainer$")
	public void i_login_as_a_trainer() throws Throwable {
		i_submit_the_correct_trainer_login_information();
	}

	@Given("^the login page loads$")
	public void the_login_page_loads() throws Throwable {
		ServiceHooks.wait.until(ExpectedConditions.visibilityOf(Login.getUsername(ServiceHooks.driver)));
		ServiceHooks.wait.until(ExpectedConditions.visibilityOf(Login.getPassword(ServiceHooks.driver)));
		assertEquals(ServiceHooks.driver.getCurrentUrl(), baseUrl + "/login");
	}

	@When("^I submit the correct admin login information$")
	public void i_submit_the_correct_admin_login_information() throws Throwable {
		LoginUtil.loginAsAdmin(ServiceHooks.driver);
		I_click_Submit();
	}

	@When("^I submit the correct associate login information$")
	public void i_submit_the_correct_associate_login_information() throws Throwable {
		LoginUtil.loginAsAssociate(ServiceHooks.driver);
		I_click_Submit();
	}

	@When("^I submit the correct manager login information$")
	public void i_submit_the_correct_manager_login_information() throws Throwable {
		LoginUtil.loginAsStaging(ServiceHooks.driver);
		I_click_Submit();
	}

	@When("^I submit the correct trainer login information$")
	public void i_submit_the_correct_trainer_login_information() throws Throwable {
		LoginUtil.loginAsTrainer(ServiceHooks.driver);
		I_click_Submit();
	}

	@When("^I submit the correct delivery login information$")
	public void i_submit_the_correct_delivery_login_information() throws Throwable {
		LoginUtil.loginAsSalesDelivery(ServiceHooks.driver);
		I_click_Submit();
	}

	@When("^I submit a correct username without a password$")
	public void i_submit_a_correct_username_without_a_password() throws Throwable {
		LoginUtil.login(ServiceHooks.driver, LoginUtil.getPropertyValue("adminUN"), "");
		I_click_Submit();
	}

	@When("^I submit a correct password with an incorrect username$")
	public void i_submit_a_correct_password_with_an_incorrect_username() throws Throwable {
		LoginUtil.login(ServiceHooks.driver, LoginUtil.getPropertyValue("notAUsername"), LoginUtil.getPropertyValue("adminPW"));
		I_click_Submit();
	}

	@When("^I submit an incorrect password with an incorrect username$")
	public void i_submit_an_incorrect_password_with_an_incorrect_username() throws Throwable {
		LoginUtil.login(ServiceHooks.driver, LoginUtil.getPropertyValue("notAUsername"), LoginUtil.getPropertyValue("notAPassword"));
		I_click_Submit();
	}

	@When("^I submit a correct username with an incorrect password$")
	public void i_submit_a_correct_username_with_an_incorrect_password() throws Throwable {
		LoginUtil.login(ServiceHooks.driver, LoginUtil.getPropertyValue("adminUN"),LoginUtil.getPropertyValue("notAPassword"));
		I_click_Submit();
	}

	@When("^I submit a correct password without a username$")
	public void i_submit_a_correct_password_without_a_username() throws Throwable {
		LoginUtil.login(ServiceHooks.driver, "", LoginUtil.getPropertyValue("adminPW"));
		I_click_Submit();
	}

	//TODO: Find way to wait without having hardcoded ids.
	@When("^I click Log out$")
	public void i_click_Log_out() throws Throwable {
		ServiceHooks.wait.until(ExpectedConditions.presenceOfElementLocated(By.id("navbarDropdown")));
		NavBar.getWelcomeDropdown(ServiceHooks.driver).click();
		ServiceHooks.wait.until(ExpectedConditions.presenceOfElementLocated(By.id("logout")));
		NavBar.getLogout(ServiceHooks.driver).click();
	}

	//Took out '#' in each "baseUrl + "#/<param>"" below because url automatically omits '#" on loading - Josh, 1808
	@Then("^I should remain on the login page$")
	public void i_should_remain_on_the_login_page() throws Throwable {
		ServiceHooks.wait.until(ExpectedConditions.urlContains(baseUrl + "/login"));
		assertEquals(ServiceHooks.driver.getCurrentUrl(), baseUrl + "/login");
	}

	@Then("^I should be taken to the home page$")
	public void i_should_be_taken_to_the_home_page() throws Throwable {
		ServiceHooks.wait.until(ExpectedConditions.urlContains( baseUrl + "/app-home"));
		assertEquals(ServiceHooks.driver.getCurrentUrl(), baseUrl + "/app-home");
	}

	@Then("^I should be taken to the trainer home page$")
	public void i_should_be_taken_to_the_trainer_home_page() throws Throwable {
		ServiceHooks.wait.until(ExpectedConditions.urlContains(baseUrl +  "/trainer-view"));
		assertEquals(ServiceHooks.driver.getCurrentUrl(), baseUrl + "/trainer-view");
	}

	@Then("^I should be taken to the associate home page$")
	public void i_should_be_taken_to_the_associate_home_page() throws Throwable {
		ServiceHooks.wait.until(ExpectedConditions.urlContains( baseUrl + "/associate-view"));
		assertEquals(ServiceHooks.driver.getCurrentUrl(), baseUrl + "/associate-view");
	}

	@Then("^I should be on the login page$")
	public void i_should_be_on_the_login_page() throws Throwable {
		ServiceHooks.wait.until(ExpectedConditions.urlContains( baseUrl + "/login"));
		assertEquals(ServiceHooks.driver.getCurrentUrl(), baseUrl + "/login");
	}
	
	public void I_click_Submit() throws Throwable {
		Login.getSignInButton(ServiceHooks.driver).click();
	}

	@After
	public void close() {
		ServiceHooks.driver.quit();
	}


}
