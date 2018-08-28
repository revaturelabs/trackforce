package com.revature.test.admin.cukes;
import com.revature.test.admin.pom.Login;
import com.revature.test.utils.ServiceHooks;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.testng.Assert.fail;

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

	@When("^I hit next$")
	public void i_hit_next() throws Throwable {
	    Login.clickNextButton(ServiceHooks.driver);
	}

	@When("^I enter a vaild firstname$")
	public void i_enter_a_vaild_firstname() throws Throwable {
	   Login.inputRegisterFirstName(ServiceHooks.driver, "Tom");
	}

	@When("^I enter a vaild lastname$")
	public void i_enter_a_vaild_lastname() throws Throwable {
	    Login.inputRegisteLastName(ServiceHooks.driver, "Smith");
	}

	@When("^I click register user$")
	public void i_click_register_user() throws Throwable {
	   Login.clickRegisterNewButton(ServiceHooks.driver);
	}

	@Then("^a new user should be registered$")
	public void a_new_user_should_be_registered() throws Throwable {
	    Thread.sleep(10000);
		//new check
	}

	@When("^I enter a invalid username \"([^\"]*)\"$")
	public void i_enter_a_invalid_username(String username) throws Throwable {
		Login.inputRegisterUsername(ServiceHooks.driver, username);
	}

	@Then("^an error message should appear$")
	public void an_error_message_should_appear() throws Throwable {
		Thread.sleep(1500);
		try {
			Login.getPopup(ServiceHooks.driver);
		} catch (Throwable e) {
			fail("No Pop up created");
		}
	}

	@When("^I enter a invaild password\"([^\"]*)\"$")
	public void i_enter_a_invaild_password(String password) throws Throwable {
		Login.inputRegisterPassword(ServiceHooks.driver, password);
	}

	@When("^I confirm my invalid password\"([^\"]*)\"$")
	public void i_confirm_my_invalid_password(String password) throws Throwable {
		Login.confirmPassword(ServiceHooks.driver, password);
	}
}
