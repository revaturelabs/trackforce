package com.revature.test.admin.cuke;


import com.revature.test.admin.pom.Login;
import com.revature.test.utils.ServiceHooks;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class RegisterUserCuke {
	
	@Given("^I click on the register button$")
	public void i_click_on_the_register_button() throws Throwable {
	    Login.clickRegisterButton(ServiceHooks.driver);
	}

	@When("^I enter a valid username$")
	public void i_enter_a_valid_username() throws Throwable {
	    Login.inputRegisterUsername(ServiceHooks.driver, "NewUserTom");
	}

	@When("^I enter a vaild password$")
	public void i_enter_a_vaild_password() throws Throwable {
		Login.inputRegisterPassword(ServiceHooks.driver, "Tom'spass!");
	}

	@When("^I confirm my password$")
	public void i_confirm_my_password() throws Throwable {
		Login.confirmPassword(ServiceHooks.driver, "Tom'spass!");
	}

	@When("^I enter a invalid firstname$")
	public void i_enter_a_vaild_firstname() throws Throwable {
	   Login.inputRegisterFirstName(ServiceHooks.driver, "$$$$");
	}

	@When("^I enter a invalid lastname$")
	public void i_enter_a_vaild_lastname() throws Throwable {
	    Login.inputRegisteLastName(ServiceHooks.driver, "@@@@34");
	}

	@When("^I click register user$")
	public void i_click_register_user() throws Throwable {
	   Login.clickRegisterNewButton(ServiceHooks.driver);
	}

	@Then("^a new user should be registered$")
	public void a_new_user_should_be_registered() throws Throwable {
		//check that user is registered
	}

	@When("^I enter a invalid username \"([^\"]*)\"$")
	public void i_enter_a_invalid_username(String username) throws Throwable {
		Login.inputRegisterUsername(ServiceHooks.driver, username);
	}

	@Then("^an error message should appear$")
	public void an_error_message_should_appear() throws Throwable {
		
		Login.getPopup(ServiceHooks.driver);

	}

	@When("^I enter a invalid password\"([^\"]*)\"$")
	public void i_enter_a_invalid_password(String password) throws Throwable {
		Login.inputRegisterPassword(ServiceHooks.driver, password);
	}

	@When("^I confirm my invalid password\"([^\"]*)\"$")
	public void i_confirm_my_invalid_password(String password) throws Throwable {
		Login.confirmPassword(ServiceHooks.driver, password);
	}
	
	@And("^I select an associate role$")
	public void i_select_an_associate_role() throws Throwable {
		Login.selectAssociateRole(ServiceHooks.driver);
	}
	
	@And("^I select a trainer role$")
	public void i_select_a_trainer_role() throws Throwable {
		Login.selectTrainerRole(ServiceHooks.driver);
	}
	
}