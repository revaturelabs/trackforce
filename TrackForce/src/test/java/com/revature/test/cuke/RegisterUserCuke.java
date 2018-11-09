package com.revature.test.cuke;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.revature.test.pom.Login;
import com.revature.test.utils.ServiceHooks;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class RegisterUserCuke {

	@Given("^I click on the register button$")
	public void i_click_on_the_register_button() throws Exception {
		ServiceHooks.wait.until(ExpectedConditions.elementToBeClickable(Login.getRegisterButton(ServiceHooks.driver)));
		Login.getRegisterButton(ServiceHooks.driver).click();
	}

	@When("^I enter a valid username$")
	public void i_enter_a_valid_username() throws Exception {
		ServiceHooks.wait.until(ExpectedConditions.elementToBeClickable(Login.getUsername(ServiceHooks.driver)));
		Login.getUsername(ServiceHooks.driver).sendKeys("TestUsername");
	}

	@When("^I enter a valid password$")
	public void i_enter_a_vaild_password() throws Exception {
		ServiceHooks.wait.until(ExpectedConditions.elementToBeClickable(Login.getPassword(ServiceHooks.driver)));
		Login.getPassword(ServiceHooks.driver).sendKeys("Password");
	}

	@When("^I confirm my password$")
	public void i_confirm_my_password() throws Exception {
		ServiceHooks.wait.until(ExpectedConditions.elementToBeClickable(Login.getConfirmPassword(ServiceHooks.driver)));
		Login.getConfirmPassword(ServiceHooks.driver).sendKeys("Password");
	}

	@When("^I enter a invalid firstname$")
	public void i_enter_a_vaild_firstname() throws Exception {
		ServiceHooks.wait.until(ExpectedConditions.elementToBeClickable(Login.getFirstName(ServiceHooks.driver)));
		Login.getFirstName(ServiceHooks.driver).sendKeys("Bob");
	}

	@When("^I enter a invalid lastname$")
	public void i_enter_a_vaild_lastname() throws Exception {
		ServiceHooks.wait.until(ExpectedConditions.elementToBeClickable(Login.getLastName(ServiceHooks.driver)));
		Login.getLastName(ServiceHooks.driver).sendKeys("Builder");
	}

	@When("^I click register user$")
	public void i_click_register_user() throws Exception {
		ServiceHooks.wait.until(ExpectedConditions.elementToBeClickable(Login.getRegisterButton(ServiceHooks.driver)));
		Login.getRegisterButton(ServiceHooks.driver).click();
	}

	@Then("^a new user should be registered$")
	public void a_new_user_should_be_registered() throws Exception {
		//check that user is registered
		//Needs to use a verify method to check if the user has been successfully created.


	}

	@When("^I enter a invalid username \"([^\"]*)\"$")
	public void i_enter_a_invalid_username(String username) throws Exception {
		ServiceHooks.wait.until(ExpectedConditions.elementToBeClickable(Login.getUsername(ServiceHooks.driver)));
		Login.getUsername(ServiceHooks.driver).sendKeys(username);
	}

	@Then("^an error message should appear$")
	public void an_error_message_should_appear() throws Exception {
		ServiceHooks.wait.until(ExpectedConditions.elementToBeClickable(Login.getErrorPopup(ServiceHooks.driver)));
		Assert.assertTrue(Login.getErrorPopup(ServiceHooks.driver).isDisplayed());
		
	}

	@When("^I enter a invalid password\"([^\"]*)\"$")
	public void i_enter_a_invalid_password(String password) throws Exception {
		ServiceHooks.wait.until(ExpectedConditions.elementToBeClickable(Login.getPassword(ServiceHooks.driver)));
		Login.getPassword(ServiceHooks.driver).sendKeys("password");
	}

	@When("^I confirm my invalid password\"([^\"]*)\"$")
	public void i_confirm_my_invalid_password(String password) throws Exception {
		ServiceHooks.wait.until(ExpectedConditions.elementToBeClickable(Login.getConfirmPassword(ServiceHooks.driver)));
		Login.getConfirmPassword(ServiceHooks.driver).sendKeys("password");
	}

	@And("^I select an associate role$")
	public void i_select_an_associate_role() throws Exception {
		ServiceHooks.wait.until(ExpectedConditions.elementToBeClickable(Login.getSelectRole(ServiceHooks.driver)));
		Login.getSelectRole(ServiceHooks.driver).click();
		ServiceHooks.wait.until(ExpectedConditions.elementToBeClickable(Login.getAssociateRole(ServiceHooks.driver)));
		Login.getAssociateRole(ServiceHooks.driver).click();
	}

	@And("^I select a trainer role$")
	public void i_select_a_trainer_role() throws Exception {
		ServiceHooks.wait.until(ExpectedConditions.elementToBeClickable(Login.getSelectRole(ServiceHooks.driver)));
		Login.getSelectRole(ServiceHooks.driver).click();
		ServiceHooks.wait.until(ExpectedConditions.elementToBeClickable(Login.getTrainerRole(ServiceHooks.driver)));
		Login.getTrainerRole(ServiceHooks.driver).click();
	}


}

